package com.zzz.learn;

import com.zzz.learn.common.listener.ApplicationListenerEnvironmentPrepared;
import com.zzz.learn.common.listener.ApplicationListenerFailed;
import com.zzz.learn.common.listener.ApplicationListenerPrepared;
import com.zzz.learn.common.listener.ApplicationListenerStarted;
import org.springframework.boot.SpringApplication;


public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(AppConfig.class);
        application.addListeners(new ApplicationListenerEnvironmentPrepared());
        application.addListeners(new ApplicationListenerFailed());
        application.addListeners(new ApplicationListenerPrepared());
        application.addListeners(new ApplicationListenerStarted());
        application.run(args);
    }
}
