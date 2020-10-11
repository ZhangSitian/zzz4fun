package com.zzz.fun.condition;

import com.zzz.fun.domain.Domain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public Domain devDomain(){
        return new Domain(" windows Domain");
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public Domain prodDomain(){
        return new Domain(" linux Domain");
    }

}
