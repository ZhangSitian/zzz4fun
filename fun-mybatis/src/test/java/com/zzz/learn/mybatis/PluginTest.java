package com.zzz.learn.mybatis;

import com.alibaba.fastjson.JSON;
import com.zzz.learn.mybatis.generate2.dao.ProductDao;
import com.zzz.learn.mybatis.generate2.po.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config-mybatis-page-plugin.xml"})
public class PluginTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void insert(){
        for (int i = 0; i < 100; i++) {
            Product product = new Product();
            product.setProductName("name"+i);
            product.setProductType("food");
            product.setLeftNum(i+"");
            productDao.insert(product);
        }
    }

    @Test
    public void testPage(){
        Product product = new Product();
        product.setProductType("food");
        product.setOrderByDesc("id");
        List<Product> products = productDao.selectListByRecord(product, 1,2);
        System.out.println(JSON.toJSONString(products));
    }


}
