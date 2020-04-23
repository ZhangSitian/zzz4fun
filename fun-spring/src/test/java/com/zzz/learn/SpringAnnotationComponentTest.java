package com.zzz.learn;


import com.zzz.learn.interfaces.Performer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotationComponentTest {

    private ApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("spring-annotation-component.xml");
        System.out.println("----------------Construction complete----------------");
    }

    @After
    public void after(){
        System.out.println("----------------Construction destroy----------------");
    }

    /**
     * bean在AppConfig中定义了duke bean
     * 使用@Configuration可以达到schema-based和java-config-based混合使用
     */
    @Test
    public void ComponentScan(){
        Performer performer = (Performer) context.getBean("duke");
        performer.perform();
    }

}
