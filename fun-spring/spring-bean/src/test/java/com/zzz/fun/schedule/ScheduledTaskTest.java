package com.zzz.fun.schedule;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ScheduledTaskTest.class)
@Configuration
@ComponentScan("com.zzz.fun.schedule")
@EnableScheduling
public class ScheduledTaskTest {



    @Test
    public void test() throws InterruptedException {
        System.out.println("ScheduledTaskTest start"+ DateUtil.now());
        TimeUnit.MINUTES.sleep(2);
        System.out.println("ScheduledTaskTest end");
    }


}