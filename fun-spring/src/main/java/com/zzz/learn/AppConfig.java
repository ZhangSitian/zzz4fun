package com.zzz.learn;

import com.zzz.learn.domain.performer.Juggler;
import com.zzz.learn.domain.poem.Sonnet29;
import com.zzz.learn.interfaces.Performer;
import com.zzz.learn.interfaces.Poem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.zzz.learn")
public class AppConfig {

    @Bean
    public Performer duke(){
        return new Juggler();
    }

    @Bean
    public Performer duke1(){
        return new Juggler();
    }

    @Bean
    public Poem sonnet29(){
        return new Sonnet29();
    }

}
