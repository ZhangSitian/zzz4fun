package com.zzz.fun.mybatis.annotation;

import com.alibaba.fastjson.JSONObject;
import com.zzz.fun.mybatis.dao.annotation.ProductDao;
import com.zzz.fun.mybatis.dao.annotation.ProductMapper;
import com.zzz.fun.mybatis.dao.domain.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis一对多、多对多使用
 */
public class AnnotationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnnotationTest.class);
    
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        // mybatis配置文件
        String resource = "config/mybatis-annotation.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
    }

    /**
     * 直接查出来并且组装好
     * 一对多
     * mybatis使用resultMap的collection对关联查询的多条记录映射到一个list集合属性中。
     */
    @Test
    public void selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        ProductMapper productMapper = sqlSession
                .getMapper(ProductMapper.class);
        List<Product> list = productMapper.selectAll();
        System.out.println(JSONObject.toJSONString(list));
        sqlSession.close();
    }

    @Test
    public void insert() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        ProductMapper productMapper = sqlSession
                .getMapper(ProductMapper.class);
        System.out.println(productMapper.insert("zhangsan","\uD83D\uDE2F\uD83D\uDC02"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void update() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        System.out.println(productMapper.updateDescById(1,"QWERTY"));
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectDao() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        ProductDao productDao = sqlSession.getMapper(ProductDao.class);
        Product condition = new Product();
        condition.setLeft_num("QWERTY");
        System.out.println(productDao.selectListByRecord(condition));
        sqlSession.commit();
        sqlSession.close();
    }


}
