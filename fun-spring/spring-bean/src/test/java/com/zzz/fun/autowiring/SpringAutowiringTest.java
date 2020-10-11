package com.zzz.fun.autowiring;


import com.zzz.fun.interfaces.Performer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAutowiringTest {

    private ApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("spring-autowiring.xml");
        System.out.println("----------------Construction complete----------------");
    }

    @After
    public void after(){
        System.out.println("----------------Construction destroy----------------");
    }

    /**
     * xml形式不够灵活，多个bean只要命名相同就只能注入相同的bean
     */
    @Test
    public void autowiringByName(){
        Performer performer = (Performer) context.getBean("kenny");
        performer.perform();
    }

    /**
     * 要求Instrument类型的bean只有一个
     */
    @Test
    public void autowiringByType(){
        Performer performer = (Performer) context.getBean("kenny1");
        performer.perform();
    }

    /**
     * 类似byType
     * 要求Instrument类型的bean只有一个
     */
    @Test
    public void autowiringByConstructor(){
        Performer performer = (Performer) context.getBean("duke");
        performer.perform();
    }

}
