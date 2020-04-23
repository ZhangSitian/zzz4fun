package com.zzz.learn.mybatis.dao;

import com.zzz.learn.mybatis.dao.domain.Product;
import com.zzz.learn.mybatis.dao.domain.ProductQueryVo;

import java.util.List;

/**
 * 从这里的返回类型判断是调用selectOne还是selectList
 */
public interface ProductDao {
    //根据id查询用户信息
    Product findProductById(long id);

    //根据用户名列查询用户列表
    List<Product> findProductByName(String name);

    //添加用户信息
    void insertProduct(Product user);

    //删除用户信息
    void deleteProduct(long id);

    List<Product> findProductByVO(ProductQueryVo productQueryVo);

    int countProduct(ProductQueryVo productQueryVo);

}
