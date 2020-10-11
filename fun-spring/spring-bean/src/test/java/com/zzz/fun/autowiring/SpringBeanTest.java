package com.zzz.fun.autowiring;


import com.zzz.fun.domain.Auditorium;
import com.zzz.fun.domain.Values;
import com.zzz.fun.interfaces.Performer;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanTest {

    private ApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("spring-bean.xml");
        System.out.println("----------------Construction complete----------------");
    }

    @After
    public void after(){
        System.out.println("----------------Construction destroy----------------");
    }

    /**
     * 简单实例
     * 原文：实际上Spring是使用反射来创建bean的
     */
    @Test
    public void simpleBean(){
        Performer performer = (Performer) context.getBean("duke");
        performer.perform();
    }

    /**
     * 由含参构造器构造的实例
     */
    @Test
    public void constructorBean(){
        Performer performer = (Performer) context.getBean("dukeConstructor");
        performer.perform();
    }

    /**
     * 由含参构造器传入对象的引用构造的实例
     */
    @Test
    public void constructorRefBean(){
        Performer performer = (Performer) context.getBean("poeticDuke");
        performer.perform();
    }

    /**
     * 通过静态方法构造bean
     */
    @Test
    public void constructorFactoryMethodBean(){
        Stage stage = (Stage) context.getBean("theStage");
        System.out.println(stage.hashCode());
    }

    /**
     * 构造原型bean和单例bean
     * 单例bean：scope="singleton"
     * 原型bean：scope="prototype"
     *
     */
    @Test
    public void scopeBean(){
        // duke和duke1不是同一个实例
        Performer performer = (Performer) context.getBean("duke1");
        System.out.println(performer.hashCode());
        System.out.println("singleton bean:");
        for (int i = 0; i <5 ; i++) {
            performer = (Performer) context.getBean("duke");
            System.out.println(performer.hashCode());
        }

        System.out.println("prototype bean:");
        for (int i = 0; i <5 ; i++) {
            performer = (Performer) context.getBean("dukePrototype");
            System.out.println(performer.hashCode());
        }
    }

    /**
     * 构造后调用初始化方法，销毁前调用销毁方法
     */
    @Test
    public void initMethodBean(){
        Auditorium auditorium = (Auditorium) context.getBean("auditorium");
        System.out.println(auditorium.hashCode());
    }

    /**
     * 配置中修改bean的属性，要求一定要有setter方法
     */
    @Test
    public void initBeanSetter(){
        Performer performer = (Performer) context.getBean("kenny");
        performer.perform();
    }

    /**
     * 配置中修改bean的属性，创建内部bean
     */
    @Test
    public void initBeanInnerSetter(){
        Performer performer = (Performer) context.getBean("kenny1");
        performer.perform();
    }

    /**
     * 配置中修改bean的属性，创建内部list属性
     */
    @Test
    public void initBeanCollection(){
        Performer performer = (Performer) context.getBean("hank");
        performer.perform();
    }

    /**
     * 配置中修改bean的属性，创建内部set和map
     */
    @Test
    public void initBeanCollection1(){
        Performer performer = (Performer) context.getBean("hank1");
        performer.perform();
    }

    /**
     * 创建value bean
     */
    @Test
    public void initValueBean(){
        Values values = (Values) context.getBean("values");
        System.out.println(values.getFloatValue());
        System.out.println(values.getStringValue());
        System.out.println(values.getIntValue());
        System.out.println(values.getMessage());
        System.out.println(values.isBooleanValue());
        System.out.println(values.getRandom());
        System.out.println(values.getSomeString());
        values.getInstrument().play();
        values.getOtherInstrument().play();
        values.getSomeInstrument().play();
    }



}
