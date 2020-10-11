package com.zzz.fun.aspect;


import com.zzz.fun.interfaces.Contestant;
import com.zzz.fun.interfaces.MindReader;
import com.zzz.fun.interfaces.Performer;
import com.zzz.fun.interfaces.Thinker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-aop-annotation.xml"})
public class SpringAopAnnotationTest {


    private ApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("spring-aop-annotation.xml");
        System.out.println("----------------Construction complete----------------");
    }

    @After
    public void after() {
        System.out.println("----------------Construction destroy----------------");
    }

    /**
     * 使用注解完成所有切面功能
     */
    @Test
    public void aop() {
        Performer performer = (Performer) context.getBean("duke");
        performer.perform();
        performer = (Performer) context.getBean("dukeConstructor");
        performer.perform();
    }

    /**
     * 将参数传递进切面
     */
    @Test
    public void aopArgs() {
        Thinker thinker = (Thinker) context.getBean("volunteer");
        thinker.thinkOfSomething("thinker");
        MindReader mindReader = (MindReader) context.getBean("magician");
        System.out.println(mindReader.getThoughts());
    }

    /**
     * 为实现了Performer的duke对象添加实现Contestant 的方法
     */
    @Test
    public void declareParents() {
        Contestant contestant = (Contestant) context.getBean("duke");
        contestant.receiveAward();
    }

}
