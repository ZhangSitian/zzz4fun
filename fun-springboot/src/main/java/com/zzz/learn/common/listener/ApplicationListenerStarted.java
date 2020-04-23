package com.zzz.learn.common.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerStarted implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent arg0) {
        System.err.println("spring boot启动开始时执行的事件。  进来了："+getClass().getSimpleName());
    }
}
