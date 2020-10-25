package com.zzz.fun;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan
public class AppConfig {



    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setTimeout(3000);
        jedisConnectionFactory.setUsePool(true);
//        jedisConnectionFactory.setPassword();
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        return jedisConnectionFactory;
    }


    @Bean
    @Qualifier("redisTemplate")
    public RedisTemplate jedisTemplate(JedisConnectionFactory jedisConnectionFactory,
                                       StringRedisSerializer stringRedisSerializer,
                                       JdkSerializationRedisSerializer jdkSerializationRedisSerializer){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    @Bean
    @Qualifier("simpleRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        return stringRedisTemplate;
    }


    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        return new StringRedisSerializer();
    }

    @Bean
    public JdkSerializationRedisSerializer jdkSerializationRedisSerializer(){
        return new JdkSerializationRedisSerializer();
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 控制一个pool可分配多少个jedis实例
        jedisPoolConfig.setMaxTotal(32);
        // 控制一个pool最多有多少个状态为idle(空闲)的jedis实例
        jedisPoolConfig.setMaxIdle(8);
        // 控制一个pool最多有多少个状态为idle(空闲)的jedis实例
        jedisPoolConfig.setMinIdle(2);
        // 表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException
        jedisPoolConfig.setMaxWaitMillis(1000);
        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(true);
        return jedisPoolConfig;
    }


    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig){
        return new JedisPool(jedisPoolConfig, "127.0.0.1",6379,3000);
    }

}
