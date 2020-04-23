package com.zzz.learn.common.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerPrepared implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent arg0) {
        System.err.println("spring boot上下文context创建完成 进来了："+getClass().getSimpleName());
    }
}
