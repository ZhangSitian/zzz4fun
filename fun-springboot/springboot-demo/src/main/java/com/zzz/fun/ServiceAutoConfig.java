package com.zzz.fun;

import com.zzz.fun.common.Book;
import com.zzz.fun.service.MyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(Book.class)
@ConditionalOnClass(MyService.class)
@ConditionalOnProperty(prefix = "book",name="service", havingValue = "true")
public class ServiceAutoConfig {

    @Bean
    @ConditionalOnMissingBean(MyService.class)
    public MyService myService() {
        return new MyService();
    }

}
