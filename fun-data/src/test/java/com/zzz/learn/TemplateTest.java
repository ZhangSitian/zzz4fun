package com.zzz.learn;

import com.zzz.learn.mybatis.dao.template.ProductSimpleTemplateDao;
import com.zzz.learn.mybatis.dao.template.ProductTemplateDao;
import com.zzz.learn.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TemplateTest {

    private ApplicationContext context;

    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("spring-simple-template.xml");
        System.out.println("----------------Construction before----------------");
    }

    @After
    public void after(){
        System.out.println("----------------Construction destroy----------------");
    }

    @Test
    public void insertSimpleTemplateTest(){
        ProductSimpleTemplateDao templateDao = (ProductSimpleTemplateDao) context.getBean("simpleTemplateDao");
       Product product = getBurger();
        int row = templateDao.insert(product);
        System.out.println("effect lines "+row);
    }

    @Test
    public void selectByIdSimpleTemplateTest(){
        ProductSimpleTemplateDao templateDao = (ProductSimpleTemplateDao) context.getBean("simpleTemplateDao");

         Product product = templateDao.selectById(1L);
        System.out.println("result: "+product);
    }

    @Test
    public void addSimpleTemplateTest(){
        ProductSimpleTemplateDao templateDao = (ProductSimpleTemplateDao) context.getBean("simpleTemplateDao");
        int row = templateDao.add(getBeer());
        System.out.println("effect lines "+row);
    }

    @Test
    public void insertTemplateTest(){
        ProductTemplateDao templateDao = (ProductTemplateDao) context.getBean("templateDao");
        Product product = getBurger();
        int row = templateDao.insert(product);
        System.out.println("effect lines "+row);
    }

    @Test
    public void selectByIdTemplateTest(){
        ProductTemplateDao templateDao = (ProductTemplateDao) context.getBean("templateDao");
        Product product = templateDao.selectById(1L);
        System.out.println("result: "+product);
    }

    // JDBCTemplate没有参数绑定:productName,:productType,:leftNum

    private Product getBurger(){
        Product product = new Product();
        product.setProductName("burger");
        product.setProductType("food");
        product.setLeftNum(1000);
        return product;
    }

    private Product getBeer(){
        Product product = new Product();
        product.setProductName("beer");
        product.setProductType("drink");
        product.setLeftNum(2000);
        return product;
    }

}
