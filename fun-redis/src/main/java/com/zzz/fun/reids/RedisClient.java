package com.zzz.fun.reids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisClient {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    public void set(String key, String value, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key,value,timeout, unit);
    }

    public String  get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
