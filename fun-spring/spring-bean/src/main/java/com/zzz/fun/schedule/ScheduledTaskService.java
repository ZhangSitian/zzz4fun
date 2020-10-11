package com.zzz.fun.schedule;

import cn.hutool.core.date.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ScheduledTaskService {


    @Scheduled(initialDelay = 3000, fixedDelay = 5000)
    public void delayTime() throws InterruptedException {
        System.out.println("执行完后，隔了5秒执行:"+DateUtil.now());
        TimeUnit.SECONDS.sleep(1L);
    }

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws InterruptedException {
        System.out.println("每隔五秒执行一次:"+DateUtil.now());
        TimeUnit.SECONDS.sleep(1L);
    }


    @Scheduled(cron = "0,5,10 * * * * ?")
    public void fixTimeExecute(){
        System.out.println("每个分钟的时间:"+DateUtil.now());
    }

}
