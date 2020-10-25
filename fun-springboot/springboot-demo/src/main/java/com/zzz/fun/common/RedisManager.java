package com.zzz.fun.common;


import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redisTemplate封装
 *
 * @author zzz
 */
@Component
public class RedisManager<T> {

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    public boolean hasKey(RedisEnum redisEnum, String key) {
        Boolean hasKey = redisTemplate.hasKey(redisEnum.name() + key);
        return Objects.nonNull(hasKey) && hasKey;
    }

    public T get(RedisEnum redisEnum, String key) {
        return redisTemplate.opsForValue().get(redisEnum.name() + key);
    }

    public T hGet(RedisEnum redisEnum, String key, String item) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(redisEnum.name() + key, item);
    }

    public Set<String> hmGet(RedisEnum redisEnum, String key) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.keys(redisEnum.name() + key);
    }

    public void set(RedisEnum redisEnum, String key, T value) {
        if (redisEnum.getTime() > 0 && Objects.nonNull(value)) {
            redisTemplate.opsForValue().set(redisEnum.name() + key, value, redisEnum.getTime(), redisEnum.getTimeUnit());
        } else {
            throw new IllegalArgumentException( "禁止设置无限期");
        }
    }

    public boolean setIfAbsent(RedisEnum redisEnum, String key, T value) {
        Boolean success = null;
        if (redisEnum.getTime() > 0) {
            success = redisTemplate.opsForValue().setIfAbsent(key, value, redisEnum.getTime(), redisEnum.getTimeUnit());
        } else {
            throw new IllegalArgumentException( "禁止设置无限期");
        }
        return Objects.nonNull(success) && success;
    }

    public void hSet(RedisEnum redisEnum, String key, String item, T value) {
        boolean firstAdd = !hasKey(redisEnum, key);
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        opsForHash.put(redisEnum.name() + key, item, value);
        if (firstAdd) {
            expire(redisEnum, key);
        }
    }

    public boolean hmset(RedisEnum redisEnum, String key, Map<String, T> map) {
        boolean firstAdd = !hasKey(redisEnum, key);
        redisTemplate.opsForHash().putAll(redisEnum.name() + key, map);
        if (firstAdd) {
            expire(redisEnum, key);
        }
        return true;
    }

    public boolean expire(RedisEnum redisEnum, String key) {
        Boolean success = redisTemplate.expire(redisEnum.name() + key, redisEnum.getTime(), redisEnum.getTimeUnit());
        return Objects.nonNull(success) && success;
    }

    public void hDel(RedisEnum redisEnum, String key, T... items) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        opsForHash.delete(redisEnum.name() + key, items);
    }

    public boolean hHasKey(RedisEnum redisEnum, String key, String item) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.hasKey(redisEnum.name() + key, item);
    }

    public double hIncr(RedisEnum redisEnum, String key, String item, double by) {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.increment(redisEnum.name() + key, item, by);
    }

    public double hDecr(RedisEnum redisEnum, String key, String item, double by) {
        return hIncr(redisEnum, key, item, -by);
    }

    public long getExpireSeconds(RedisEnum redisEnum, String key) {
        Long expire = redisTemplate.getExpire(redisEnum.name() + key, TimeUnit.SECONDS);
        return Objects.nonNull(expire) ? expire : 0L;
    }

    public long increment(RedisEnum redisEnum, String key) {
        return increment(redisEnum, key, 1L);
    }

    public long increment(RedisEnum redisEnum, String key, long delta) {
        String realKey = redisEnum.name() + key;
        boolean firstAdd = !hasKey(redisEnum, key);
        Long value = redisTemplate.opsForValue().increment(realKey, delta);
        if (firstAdd) {
            expire(redisEnum, key);
        }
        return Objects.nonNull(value) ? value : 0L;
    }


    public boolean delete(RedisEnum redisEnum, String key) {
        Boolean deleted = null;
        if (hasKey(redisEnum, key)) {
            deleted = redisTemplate.delete(redisEnum.name() + key);
        }
        return Objects.nonNull(deleted) && deleted;
    }

    public boolean execute(RedisScript redisScript, Object expectValue, RedisEnum redisEnum, String key, String value) {
        Long result = (Long) redisTemplate.execute(redisScript, Collections.singletonList(redisEnum.name() + key), value);
        return Objects.equals(result, expectValue);
    }

}