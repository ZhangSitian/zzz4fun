package com.zzz.learn.common.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerEnvironmentPrepared implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent arg0) {
        System.err.println("spring boot 对应Enviroment已经准备完毕。 进来了："+getClass().getSimpleName());
    }
}
