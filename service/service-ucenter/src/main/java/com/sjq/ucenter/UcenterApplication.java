package com.sjq.ucenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Kemp
 * @create 2022/4/13 10:24
 * 用户服务
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@ComponentScan(value = {"com.sjq.servicebase.config","com.sjq.ucenter"})
@MapperScan("com.sjq.ucenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
