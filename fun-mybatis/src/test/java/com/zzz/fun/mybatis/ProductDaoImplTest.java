package com.zzz.fun.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.zzz.fun.mybatis.dao.ProductDao;
import com.zzz.fun.mybatis.dao.domain.Product;
import com.zzz.fun.mybatis.dao.impl.ProductDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * 自己实现dao
 */
public class ProductDaoImplTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisFirstTest.class);

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        // mybatis配置文件
        String resource = "config/mybatis-hello.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
    }

    /**
     * dao 形式由自己创建实现类ProductDaoImpl
     */
    @Test
    public void testDaoImplByMe() {
        // 创建ProductDao的对象
        ProductDao productDao = new ProductDaoImpl(sqlSessionFactory);
        // 调用ProductDao的方法
        Product product = productDao.findProductById(1L);
        LOGGER.info(JSONObject.toJSONString(product));
    }

    /**
     * Mapper 形式由代理自动创建实现类ProductDaoImpl
     */
    @Test
    public void testDaoImplByProxy() {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        //创建Mapper代理对象
        ProductDao productDao=sqlSession.getMapper(ProductDao.class);
        //调用Mapper的方法
        Product product=productDao.findProductById(1);
        sqlSession.close();
        LOGGER.info(product.getProduct_name());
    }

}
