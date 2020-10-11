package com.zzz.fun.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class AwareBean implements BeanNameAware, BeanFactoryAware {
    private String beanName;

    private BeanFactory factory;

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.factory = beanFactory;
    }

    public BeanFactory getFactory() {
        return factory;
    }

    @Override
    public String toString() {
        return "----------------------->";
    }
}