package com.zzz.fun.run;


import com.zzz.fun.aware.AwareBean;
import com.zzz.fun.init.InitBean;
import com.zzz.fun.interfaces.Performer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRunTest {

    private ApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("spring-run.xml");
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

    @Test
    public void test() {
        AwareBean awareBean = (AwareBean) context.getBean("awareBean");
        System.out.println(awareBean.getBeanName());
    }

    @Test
    public void test1() {
        InitBean awareBean = (InitBean) context.getBean("initBean");
    }

}
