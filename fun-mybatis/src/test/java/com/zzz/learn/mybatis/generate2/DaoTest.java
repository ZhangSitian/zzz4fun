package com.zzz.learn.mybatis.generate2;

import com.alibaba.fastjson.JSON;
import com.zzz.learn.mybatis.generate2.dao.ProductDao;
import com.zzz.learn.mybatis.generate2.po.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config-generate2.xml"})
public class DaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void test() {
        Product product = productDao.selectById(1L);
        System.out.println(JSON.toJSONString(product));
    }

    @Test
    public void testCount() {
        Product product = new Product();
        product.setId(1L);
        int row = productDao.countByRecord(product);
        System.out.println(row);
    }


}
