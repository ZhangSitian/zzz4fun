package com.zzz.fun.aware;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AwareBeanTest.class)
@Configuration
@ComponentScan("com.zzz.fun.aware")
public class AwareBeanTest {

    @Autowired
    private AwareBean awareBean;

    @Test
    public void test() {
        System.out.println(awareBean.getBeanName());
    }
}