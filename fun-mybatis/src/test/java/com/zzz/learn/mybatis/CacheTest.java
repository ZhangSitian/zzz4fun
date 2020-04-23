package com.zzz.learn.mybatis;

import com.alibaba.fastjson.JSON;
import com.zzz.learn.mybatis.dao.OrderMapper;
import com.zzz.learn.mybatis.dao.UserMapper;
import com.zzz.learn.mybatis.dao.domain.Orders;
import com.zzz.learn.mybatis.dao.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * mybatis缓存测试
 */
public class CacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheTest.class);

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        // mybatis配置文件
        String resource = "config/mybatis-order-custom.xml";
        // 得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 创建会话工厂，传入mybatis的配置文件信息
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
    }

    /**
     * 一级缓存
     */
    @Test
    public void testCache1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 下边查询使用一个SqlSession
        Orders order1 = orderMapper.findOrderById(3);
        Assert.assertNotNull(order1);
        Orders order2 = orderMapper.findOrderById(3);
        Assert.assertSame(order1, order2);
        // commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。
        Orders orderCondition = new Orders();
        orderCondition.setId(3);
        orderCondition.setNumber("22");
        orderMapper.updateOrderById(orderCondition);
        //执行commit操作去清空缓存
        sqlSession.commit();
        // 第二次发起请求，查询id为1的用户
        Orders order3 = orderMapper.findOrderById(3);
        Assert.assertNotSame(order3, order1);
        sqlSession.close();
    }

    /**
     * 二级缓存
     */
    @Test
    public void testCache2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 第一次发起请求，查询id为1的用户
        User user = userMapper.findUserById(1);
        LOGGER.info(JSON.toJSONString(user));
        //这里执行关闭操作，将sqlSession中的数据写到二级缓存区域
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        /// 第二次发起请求，查询id为1的用户
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.findUserById(1);
        // 二级缓存不一定在内存中，所以不是同一对象
        LOGGER.info(JSON.toJSONString(user2));
        User userCondition = new User();
        userCondition.setId(1);
        userCondition.setUser_name("张明明");
        userMapper2.updateUserById(userCondition);
        //执行提交，清空UserMapper下边的二级缓存
        sqlSession2.commit();
        sqlSession2.close();

        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        // 更新完发起请求，查询id为1的用户
        User user3 = userMapper3.findUserById(1);
        LOGGER.info(JSON.toJSONString(user3));
        sqlSession3.close();
    }

}
