package com.zzz.fun.mybatis;

import com.alibaba.fastjson.JSON;
import com.zzz.fun.mybatis.dao.ProductMapper;
import com.zzz.fun.mybatis.dao.domain.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * mybatis和spring整合
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mybatis-spring.xml"})
public class MybatisSpringTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisSpringTest.class);

    @Autowired
    private ProductMapper productMapper;


    @Test
    public void test() {
        Product product = productMapper.findProductById(1L);
        Assert.assertNotNull(product);
        LOGGER.info(JSON.toJSONString(product));
    }

}