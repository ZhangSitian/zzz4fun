package com.zzz.learn.importanno;

import org.springframework.context.annotation.Bean;

public class ImportConfig {

    @Bean
    public DemoService getDemoService(){
        return new DemoService();
    }

}
