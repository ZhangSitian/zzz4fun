package com.zzz.fun;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RedisTest {

    private StringRedisTemplate redisTemplate;

    @Before
    public void before(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        redisTemplate = context.getBean("stringRedisTemplate", StringRedisTemplate.class);
    }

    @Test
    public void setGet()
    {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        option.set("cccc","100");
        System.out.println(option.get("aaa"));
        System.out.println(option.get("sssss"));
    }


    @Test
    public void setGetTimeout() throws InterruptedException {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        option.set("cccc","bbb",5, TimeUnit.SECONDS);
        for (int i = 0; i <10 ; i++) {
            System.out.println(option.get("cccc"));
            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Test
    public void setIfAbsent() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
       String key = System.currentTimeMillis()+"";
        System.out.println(option.setIfAbsent(key,"123123123"));
        System.out.println(option.get(key));
        System.out.println(option.setIfAbsent(key,"1"));
        System.out.println(option.get(key));
    }

    @Test
    public void multiSetGet() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        Map<String, String> map = new HashMap<>();
        map.put("1","1q");
        map.put("2","2q");
        map.put("3","3q");
        map.put("4","4q");
        option.multiSet(map);
        List<String> keys = new ArrayList<>();
        keys.add("1");
        keys.add("2");
        keys.add("3");
        keys.add("1");
        System.out.println(option.multiGet(keys));
    }

    @Test
    public void getAndSet() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        System.out.println(option.getAndSet("4","100"));
        System.out.println(option.get("4"));
    }

    @Test
    public void multiSetIfAbsent() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        Map<String, String> map = new HashMap<>();
        map.put("111","1q");
        map.put("211","2q");
        map.put("311","3q");
        map.put("41","4q");
        System.out.println(option.multiSetIfAbsent(map));
        List<String> keys = new ArrayList<>();
        keys.add("11");
        keys.add("21");
        keys.add("31");
        keys.add("41");
        System.out.println(option.multiGet(keys));
    }


    @Test
    public void incrementL() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        redisTemplate.delete("cccc");
        System.out.println(option.get("cccc"));
        System.out.println(option.increment("cccc",1L));
        for (int i = 0; i <10 ; i++) {
            System.out.println(option.increment("cccc",1L));
        }
    }

    @Test
    public void incrementD() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        System.out.println(option.get("cccsc"));
        System.out.println(option.increment("cccsc",0.01));
        for (int i = 0; i <10 ; i++) {
            System.out.println(option.increment("cccsc",0.01));
        }
    }

    @Test
    public void append() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        redisTemplate.delete("213123123123");
        System.out.println(option.get("213123123123"));
        option.set("213123123123","bbb");
        System.out.println(option.get("213123123123"));
        System.out.println(option.append("213123123123","1111"));
        System.out.println(option.get("213123123123"));
    }

    @Test
    public void setOffset() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        option.set("213123123123","bbb213wqeqwe131rqwr");
        System.out.println(option.get("213123123123",3L,40L));
//        option.set("213123123123","bbb213wqeqwe131rqwr", 30L);
//        System.out.println(option.get("213123123123"));
    }

    @Test
    public void size() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        System.out.println(option.size("213123123123"));
//        option.set("213123123123","bbb213wqeqwe131rqwr", 30L);
//        System.out.println(option.get("213123123123"));
    }


    @Test
    public void getBit() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        option.setBit("sssaaassaa",1L,true);
        System.out.println(option.getBit("sssaaassaa",8L));
//        option.set("213123123123","bbb213wqeqwe131rqwr", 30L);
//        System.out.println(option.get("213123123123"));
    }

    @Test
    public void has() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        option.set("1234567890","111111");
        System.out.println(option.get("1234567890"));
        System.out.println(redisTemplate.hasKey("1234567890"));
        System.out.println(redisTemplate.getExpire("1234567890"));
        redisTemplate.delete("1234567890");
        System.out.println(redisTemplate.hasKey("1234567890"));
    }

    @Test
    public void all() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
        System.out.println(redisTemplate.keys("*"));
        System.out.println(redisTemplate.randomKey());
        System.out.println(redisTemplate.renameIfAbsent("1","5"));
    }

    @Test
    public void RedisOperations() {
        ValueOperations<String, String> option = redisTemplate.opsForValue();
    }

}
