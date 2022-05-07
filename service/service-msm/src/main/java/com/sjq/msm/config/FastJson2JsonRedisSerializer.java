package com.sjq.msm.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

public class FastJson2JsonRedisSerializer<T> implements org.springframework.data.redis.serializer.RedisSerializer<T> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public FastJson2JsonRedisSerializer(Class<T> clazz) {
        super(); this.clazz = clazz;
    }

    private Class<T> clazz;
    //redis在写入对象时,自动进行json转换,同时在json转换的同步,加入该对象的全限名,同时json的字符格式为utf-8
    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }
    //开启白名单操作
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        //添加白名单
        ParserConfig.getGlobalInstance().addAccept("com.springboot_redis.demo.entitys");
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSON.parseObject(str, clazz);
    }

}
