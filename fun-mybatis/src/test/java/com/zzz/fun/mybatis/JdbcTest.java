package com.zzz.fun.mybatis;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 原生jdbc
 */
public class JdbcTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTest.class);
    
    @Before
    public void before() {
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final String CONNECT = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&useSSL=true";
    private static final String USER = "root";
    private static final String PW = "";

    @Test
    public void testJDBC() {
        //定义sql语句 ?表示占位符
        String sql = "select * from product where product_type = ?";
        LOGGER.info(select(sql, "food").toString());
    }

    private List<JSONObject> select(String sql, String... params) {
        List<JSONObject> result = new ArrayList<>();
        try (//通过驱动管理类获取数据库链接
             Connection connection = DriverManager.getConnection(CONNECT, USER, PW);
             //获取预编译的Statement，使用预编译的Statement提高数据库性能
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            try ( //向数据库发出sql执行查询，查询出结果集
                  ResultSet resultSet = preparedStatement.executeQuery()) {
                //遍历查询结果集
                while (resultSet.next()) {
                    JSONObject obj = new JSONObject();
                    obj.put("id", resultSet.getString("id"));
                    obj.put("product_name", resultSet.getString("product_name"));
                    obj.put("product_type", resultSet.getString("product_type"));
                    obj.put("left_num", resultSet.getString("left_num"));
                    result.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}