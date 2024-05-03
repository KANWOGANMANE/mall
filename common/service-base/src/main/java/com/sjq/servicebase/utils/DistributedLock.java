package com.sjq.servicebase.utils;

import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author Kemp
 * @create 2024/3/16 11:47
 */
@Slf4j
public class DistributedLock implements Lock {
    private static final ScheduledThreadPoolExecutor EXECUTOR;

    static {
        EXECUTOR = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder()
                        .setDaemon(true)
                        .setNameFormat("distributed-lock-watchdog-%d")
                        .build());
    }

    private static final Set<DistributedLock> ALL_LOCKS = Sets.newConcurrentHashSet();

    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get',KEYS[1])==ARGV[1] then "
            + "return redis.call('del',KEYS[1]) else return 0 end";

    private static final long TIMEOUT_LIMIT = 60L;

    private static final long TIME_RETRY = 100L;

    private static final long LIFE_SAVE_PERIOD = TIMEOUT_LIMIT / 6;

    private final String lockKey;

    private final String lockValue;

    private final RedisTemplate redisTemplate;

    public DistributedLock(String lockKey, String lockValue, RedisTemplate redisTemplate) {
        this.lockKey = lockKey;
        this.lockValue = lockValue;
        this.redisTemplate = redisTemplate;
    }

    public DistributedLock(String lockKey, RedisTemplate redisTemplate) {
        this.lockKey = lockKey;
        this.redisTemplate = redisTemplate;
        this.lockValue = UUID.randomUUID().toString();
    }


    static {
        // 关闭应用及时释放锁
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(5L);
            } catch (InterruptedException e) {
                // ignore
            }
            if (!ALL_LOCKS.isEmpty()) {
                log.error("application haven bean waiting for 5 ms, force to unlock distribute lock.");
                ALL_LOCKS.forEach(lock -> {
                    log.error("force to unlock distributed lock with key " + lock.lockKey);
                    lock.unlock();
                });
            }
        }));
    }

    // 对锁进行时间续订的任务
    private ScheduledFuture saverTask;

    @Override
    public void lock() {
        while (!tryLock()) {
            try {
                TimeUnit.MILLISECONDS.sleep(TIME_RETRY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        while (!tryLock()) {
            TimeUnit.MILLISECONDS.sleep(TIME_RETRY);
        }
    }

    @Override
    public boolean tryLock() {
        Boolean isLock = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, TIMEOUT_LIMIT, TimeUnit.SECONDS);
        if (isLock != null && isLock) {
            ALL_LOCKS.add(this);
            saverTask = EXECUTOR.scheduleWithFixedDelay(() -> {
                try {
                    redisTemplate.expire(lockKey, TIMEOUT_LIMIT, TimeUnit.SECONDS);
                } catch (Exception ex) {
                    // ignore
                }
            }, LIFE_SAVE_PERIOD, LIFE_SAVE_PERIOD, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        long end = System.currentTimeMillis() + unit.toMillis(time);
        while (!tryLock()) {
            if (System.currentTimeMillis() + TIME_RETRY > end) {
                return false;
            }
            TimeUnit.MILLISECONDS.sleep(TIME_RETRY);
        }
        return true;
    }

    @Override
    public void unlock() {
        if (saverTask != null) {
            ALL_LOCKS.remove(this);
            saverTask.cancel(false);
            saverTask = null;
            RedisScript<Boolean> script = new DefaultRedisScript<>(RELEASE_LOCK_SCRIPT);
            redisTemplate.execute(script, Arrays.asList(lockKey), lockValue);
        }
    }

    @Override
    public Condition newCondition() {
        throw new RuntimeException("not support Condition!");
    }
}
