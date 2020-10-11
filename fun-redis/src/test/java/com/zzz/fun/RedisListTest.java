package com.zzz.fun;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisListTest {

    private StringRedisTemplate redisTemplate;

    private ListOperations<String, String> option;

    @Before
    public void before(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        redisTemplate = context.getBean("stringRedisTemplate", StringRedisTemplate.class);
        option = redisTemplate.opsForList();
    }

    @Test
    public void getBit() {
        System.out.println( option.range("213", 0,option.size("213")));
        System.out.println(option.rightPop("213", 10, TimeUnit.SECONDS));
        System.out.println( option.range("213", 0,option.size("213")));
    }

    @Test
    public void getwwwBit() {
        System.out.println( option.range("213", 0,option.size("213")));
        System.out.println(option.rightPop("213", 10, TimeUnit.SECONDS));
        System.out.println( option.range("213", 0,option.size("213")));
        redisTemplate.boundListOps("213");
    }

    @Test
    public void has() {
        System.out.println( option.range("213", 0,option.size("213")));
        System.out.println(option.rightPop("213", 10, TimeUnit.SECONDS));
        System.out.println( option.range("213", 0,option.size("213")));
        redisTemplate.boundListOps("213");
    }

}
