package com.zzz.fun.mybatis;

import com.alibaba.fastjson.JSONObject;
import com.zzz.fun.mybatis.dao.OrderCustomMapper;
import com.zzz.fun.mybatis.dao.domain.Orders;
import com.zzz.fun.mybatis.dao.domain.OrdersCustomVO;
import com.zzz.fun.mybatis.dao.domain.User;
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
public class UserOrderDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserOrderDaoTest.class);
    
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
    public void selectOrderCustomTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrderCustomMapper orderCustomMapper = sqlSession.getMapper(OrderCustomMapper.class);
        List<OrdersCustomVO> ordersCustoms = orderCustomMapper.findOrdersUser();
        sqlSession.close();
        LOGGER.info(JSONObject.toJSONString(ordersCustoms));
    }

    /**
     * 直接查出来并且组装好
     * 一对一
     * resultMap可以实现延迟加载，resultType无法实现延迟加载。
     * mybatis使用resultMap的association对关联查询的多条记录映射到一个POJO属性中。
     */
    @Test
    public void testFindOrdersUserResultMapTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        OrderCustomMapper ordersMapperCustom = sqlSession
                .getMapper(OrderCustomMapper.class);
        List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();
        LOGGER.info(JSONObject.toJSONString(list));
        sqlSession.close();
    }

    /**
     * 直接查出来并且组装好
     * 一对多
     * mybatis使用resultMap的collection对关联查询的多条记录映射到一个list集合属性中。
     */
    @Test
    public void testFindOrdersAndDetailResultMapTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        OrderCustomMapper ordersMapperCustom = sqlSession
                .getMapper(OrderCustomMapper.class);
        List<Orders> list = ordersMapperCustom.findOrdersAndOrderDetailResultMap();
        LOGGER.info(JSONObject.toJSONString(list));
        sqlSession.close();
    }

    /**
     * 直接查出来并且组装好
     * 一对多
     * mybatis使用resultMap的collection对关联查询的多条记录映射到一个list集合属性中。
     */
    @Test
    public void testUserAndItemsResultMapTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        OrderCustomMapper ordersMapperCustom = sqlSession
                .getMapper(OrderCustomMapper.class);
        List<User> list = ordersMapperCustom.findUserAndItemsResultMap();
        LOGGER.info(JSONObject.toJSONString(list));
        sqlSession.close();
    }

}
