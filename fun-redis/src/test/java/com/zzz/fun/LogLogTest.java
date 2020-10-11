package com.zzz.fun;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

public class LogLogTest {

    private StringRedisTemplate redisTemplate;

    private HyperLogLogOperations<String, String> option;

    @Before
    public void before(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        redisTemplate = context.getBean("stringRedisTemplate", StringRedisTemplate.class);
        option = redisTemplate.opsForHyperLogLog();
    }

    @Test
    public void test() {
        String key = "123456998765432";
        redisTemplate.delete(key);
        System.out.println(option.size(key));
        long start  = System.currentTimeMillis();
        System.out.println(start);
        for (int i = 0; i < 10000 ; i++) {
            option.add(key,"111"+i);
        }
        System.out.println("10000 cost:"+(start-System.currentTimeMillis()));
        System.out.println(option.size(key));
        for (int i = 0; i < 100 ; i++) {
            System.out.println(option.add(key,"111"+i));
        }
        System.out.println("10100 cost:"+(start-System.currentTimeMillis()));
    }

    @Test
    public void test1() {
        String key = "123456998765432";
        redisTemplate.delete(key);
        System.out.println(option.size(key));
        long start  = System.currentTimeMillis();
        System.out.println(start);
        for (int i = 0; i < 100 ; i++) {
            option.add(key,"111"+i);
        }
        System.out.println("100 cost:"+(start-System.currentTimeMillis()));
        start  = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            redisTemplate.opsForValue().get("1");
        }
        System.out.println("100 cost:"+(start-System.currentTimeMillis()));
    }

}
