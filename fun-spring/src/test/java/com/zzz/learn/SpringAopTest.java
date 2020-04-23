package com.zzz.learn;


import com.zzz.learn.interfaces.Contestant;
import com.zzz.learn.interfaces.MindReader;
import com.zzz.learn.interfaces.Performer;
import com.zzz.learn.interfaces.Thinker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAopTest {

    private ApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("spring-aop.xml");
        System.out.println("----------------Construction complete----------------");
    }

    @After
    public void after(){
        System.out.println("----------------Construction destroy----------------");
    }

    /**
     * AOP
     * Audience before
     * around start
     * Juggler 15 beanBags
     * around end coast :0
     * Audience afterReturn
     * Audience after
     */
    @Test
    public void aop(){
        Performer performer = (Performer) context.getBean("duke");
        performer.perform();
        performer = (Performer) context.getBean("dukeConstructor");
        performer.perform();
    }

    /**
     * 将参数传递进切面
     */
    @Test
    public void aopArgs(){
        Thinker thinker = (Thinker) context.getBean("volunteer");
        thinker.thinkOfSomething("thinker");
        MindReader mindReader = (MindReader) context.getBean("magician");
        System.out.println(mindReader.getThoughts());
    }

    /**
     * 为实现了Performer的duke对象添加实现Contestant 的方法
     */
    @Test
    public void declareParents(){
        Contestant contestant = (Contestant) context.getBean("duke");
        contestant.receiveAward();
    }

}
