package com.zzz.learn.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.zzz.learn.mybatis.dao.domain.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 单独使用mybatis
 */
public class MybatisFirstTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisFirstTest.class);

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before()  throws IOException {
            // mybatis配置文件
            String resource = "config/mybatis-hello.xml";
            // 得到配置文件流
            InputStream inputStream =  Resources.getResourceAsStream(resource);
            //创建会话工厂，传入mybatis配置文件的信息
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findProductByIdTest() {
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过SqlSession操作数据库
        // 第一个参数：映射文件中statement的id，等于=namespace+"."+statement的id
        // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        // sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
        // selectOne查询出一条记录
        Product product = sqlSession.selectOne("test.findProductById", 1L);
        LOGGER.info(JSONObject.toJSONString(product));
        // 释放资源
        sqlSession.close();

    }

    // 根据用户名称模糊查询用户列表
    @Test
    public void findProductByNameTest() {
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // list中的user和映射文件中resultType所指定的类型一致
        List<Product> list = sqlSession.selectList("test.findProductByName", "burger");
        sqlSession.close();
        LOGGER.info(JSONObject.toJSONString(list));

    }

    // 添加用户信息
    @Test
    public void insertProductTest()  {
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 插入用户对象
        Product product = new Product();
        product.setProduct_name("日历3");
        product.setProduct_type("日用品");
        product.setLeft_num("100");
        sqlSession.insert("test.insertProduct", product);
        // 提交事务
        sqlSession.commit();
        // 关闭会话
        sqlSession.close();
        // 获取用户信息主键
        LOGGER.info(Long.toString(product.getId()));
    }

    // 根据id删除 用户信息
    @Test
    public void deleteProductTest() {
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 传入id删除 用户
        sqlSession.delete("test.deleteProduct", 3);
        // 提交事务
        sqlSession.commit();
        // 关闭会话
        sqlSession.close();
    }

    // 更新用户信息
    @Test
    public void updateProductTest() {
        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 更新用户信息
        Product product = new Product();
        product.setId(1L);
        product.setProduct_name("日历");
        product.setProduct_type("日用品");
        product.setLeft_num("200");
        sqlSession.update("test.updateProductById", product);
        // 提交事务
        sqlSession.commit();
        // 关闭会话
        sqlSession.close();
    }

}
