package com.zzz.learn;

import com.zzz.learn.mybatis.dao.template.ProductSimpleTemplateDao;
import com.zzz.learn.domain.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;



public class TransactionTest {

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

    @Test(expected = RuntimeException.class)
    public void insertTransactionTest(){
        insertTransaction();
    }

    @Transactional
    public void insertTransaction(){
        ProductSimpleTemplateDao templateDao = (ProductSimpleTemplateDao) context.getBean("simpleTemplateDao");
        Product product = getBurger();
        product.setProductName("burger is awful");
        int row = templateDao.insert(product);
        System.out.println("effect lines "+row);
        // 抛出后之前的插入语句会被回滚
        throw new RuntimeException();
    }

    // JDBCTemplate没有参数绑定:productName,:productType,:leftNum

    private Product getBurger(){
        Product product = new Product();
        product.setProductName("burger");
        product.setProductType("food");
        product.setLeftNum(1000);
        return product;
    }

}
