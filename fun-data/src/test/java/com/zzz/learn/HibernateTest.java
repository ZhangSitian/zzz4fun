package com.zzz.learn;

import com.alibaba.fastjson.JSONObject;
import com.zzz.learn.mybatis.dao.ProductDao;
import com.zzz.learn.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateTest {

    private ApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("spring-hibernate.xml");
        System.out.println("----------------Construction before----------------");
    }

    @After
    public void after(){
        System.out.println("----------------Construction destroy----------------");
    }

    @Test
    public void insertSimpleTemplateTest(){
        ProductDao hibernateProductDao = (ProductDao) context.getBean("hibernateProductDao");
        Product product = getBurger();
        int row = hibernateProductDao.insert(product);
        System.out.println("effect lines "+row);
    }

    @Test
    public void selectSimpleTemplateTest(){
        ProductDao hibernateProductDao = (ProductDao) context.getBean("hibernateProductDao");
        Product product = hibernateProductDao.selectById(22L);
        System.out.println("result:"+ JSONObject.toJSONString(product));
    }

    private Product getBurger(){
        Product product = new Product();
        product.setProductName("burger");
        product.setProductType("food");
        product.setLeftNum(1290);
        return product;
    }

}
