package com.zzz.learn.mybatis.dao;

import com.zzz.learn.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.util.Map;

/**
 * 接口
 */
public interface ProductDao {

    /**
     * 插入语句
     * @param product  对象
     * @return 影响行数
     */
    int insert(Product product);

    /**
     * columnIndex是从1开始的。。
     * @param id 记录id
     * @return 记录
     */
    Product selectById(long id);



    /**
     * 参数绑定
     * 曾今的NamedParameterJdbcTemplate的功能
     * @param product 入参
     * @return 影响行数
     */
    int add(Product product);
}
