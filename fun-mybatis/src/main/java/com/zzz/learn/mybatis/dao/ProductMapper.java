package com.zzz.learn.mybatis.dao;


import com.zzz.learn.mybatis.dao.domain.Product;

import java.util.List;

public interface ProductMapper extends ProductDao {

    //根据用户名列查询用户列表
    List<Product> findProductByIds(List<Long> ids);

}
