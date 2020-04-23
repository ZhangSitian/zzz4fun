package com.zzz.learn.common.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationListenerFailed implements ApplicationListener<ApplicationFailedEvent> {
    @Override
    public void onApplicationEvent(ApplicationFailedEvent arg0) {
        System.err.println("  spring boot启动异常时执行事件 进来了："+getClass().getSimpleName());
    }
}
