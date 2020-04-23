package com.zzz.learn;


import com.zzz.learn.interfaces.Performer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotationAutowiringTest {

    private ApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("spring-annotation-autowiring.xml");
        System.out.println("----------------Construction complete----------------");
    }

    @After
    public void after(){
        System.out.println("----------------Construction destroy----------------");
    }

    /**
     * 构造方法自动注入
     */
    @Test
    public void autowiringOnConstructor(){
        Performer performer = (Performer) context.getBean("duke");
        performer.perform();
    }

}
