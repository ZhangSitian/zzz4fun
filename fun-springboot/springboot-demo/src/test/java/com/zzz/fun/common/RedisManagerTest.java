package com.zzz.fun.common;

import com.zzz.fun.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisManagerTest {

    @Autowired
    private RedisManager<String> redisManager;

    @Test
    public void test() {
        redisManager.set(RedisEnum.MINUTES_1, "111","222");
        System.out.println(redisManager.get(RedisEnum.MINUTES_1, "111"));
    }
}