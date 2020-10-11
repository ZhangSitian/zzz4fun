package com.zzz.fun.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.zzz.fun.mybatis.dao.ProductMapper;
import com.zzz.fun.mybatis.dao.domain.Product;
import com.zzz.fun.mybatis.dao.domain.ProductQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//MyBatis 将按照下面的顺序(优先级)来加载属性：
//
//        在properties元素体内定义的属性首先被读取。
//        然后会读取properties元素中resource或url加载的属性，它会覆盖已读取的同名属性。
//        最后读取parameterType传递的属性，它会覆盖已读取的同名属性。

public class ConfigTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigTest.class);
    
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        // mybatis配置文件
        String resource = "config/mybatis-config-test.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
    }

    @Test
    public void configTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取ProductMapper的对象
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        // 调用ProductMapper的方法
        Product product = productMapper.findProductById(1L);
        sqlSession.close();
       LOGGER.info(JSONObject.toJSONString(product));
    }

    @Test
    public void configVOTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取ProductMapper的对象
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        // 调用ProductMapper的方法
        ProductQueryVo productQueryVo = new  ProductQueryVo();
        Product productCondition = new Product();
        productCondition.setId(1L);
        productQueryVo.setProduct(productCondition);
        List<Product> products = productMapper.findProductByVO(productQueryVo);
       LOGGER.info(JSONObject.toJSONString(products));

        // 不用关心where ，即使扔进去的是个空的
        productQueryVo.setProduct(new Product());
        List<Product> products1 = productMapper.findProductByVO(productQueryVo);
       LOGGER.info(JSONObject.toJSONString(products1));

        // VO是空的，则所有参数为空
        List<Product> products2 = productMapper.findProductByVO(null);
        sqlSession.close();
       LOGGER.info(JSONObject.toJSONString(products2));
    }

    /**
     * ResultType
     */
    @Test
    public void configVOResultTypeIntTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取ProductMapper的对象
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        // 调用ProductMapper的方法
        ProductQueryVo productQueryVo = new  ProductQueryVo();
        Product productCondition = new Product();
        productCondition.setLeft_num("100");
        productQueryVo.setProduct(productCondition);
        int count = productMapper.countProduct(productQueryVo);
        sqlSession.close();
       LOGGER.info(Integer.toString(count));
    }

    /**
     * ResultMap
     */
    @Test
    public void configResultMapTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取ProductMapper的对象
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        // 调用ProductMapper的方法
        List<Product> products = productMapper.findProductByName("日历");
        sqlSession.close();
       LOGGER.info(JSONObject.toJSONString(products));
    }


    /**
     * Foreach
     */
    @Test
    public void configForeachTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取ProductMapper的对象
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(4L);
        // 调用ProductMapper的方法
        List<Product> products = productMapper.findProductByIds(list);
        sqlSession.close();
       LOGGER.info(JSONObject.toJSONString(products));
    }

}
