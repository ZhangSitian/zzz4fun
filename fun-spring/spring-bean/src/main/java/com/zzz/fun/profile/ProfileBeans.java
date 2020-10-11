package com.zzz.fun.profile;

import com.zzz.fun.domain.Domain;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.zzz.fun.profile")
public class ProfileBeans {
    @Bean
    @Profile("dev")
    public Domain devDomain(){
        return new Domain(" dev Domain");
    }

    @Bean
    @Profile("pro")
    public Domain prodDomain(){
        return new Domain(" pro Domain");
    }

}
