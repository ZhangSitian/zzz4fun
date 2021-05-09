package com.zzz.fun.init;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.annotation.PostConstruct;


public class InitBean implements InitializingBean, BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InitBean========postProcessBeforeInitialization "+beanName);
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("InitBean========postProcessAfterInitialization "+beanName);
        return null;
    }

    public InitBean() {
        System.out.println("InitBean========InitBean");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("InitBean========postConstruct");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitBean========afterPropertiesSet");
    }
}
