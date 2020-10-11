package com.zzz.fun.mybatis;

import com.zzz.fun.mybatis.dao.OrderMapper;
import com.zzz.fun.mybatis.dao.domain.Orders;
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
 * 懒加载
 */
public class LazyLoadingTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyLoadingTest.class);

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

    @Test
    public void testLazyLoading() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<Orders> ordersList = orderMapper.findOrdersUserLazyLoading();
        ordersList.forEach(order -> LOGGER.info(order.getUser().getUser_name()));
        sqlSession.close();
    }

}
