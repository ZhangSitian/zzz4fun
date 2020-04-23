package com.zzz.learn.mybatis.dao;


import com.zzz.learn.mybatis.dao.domain.Orders;
import com.zzz.learn.mybatis.dao.domain.OrdersCustomVO;
import com.zzz.learn.mybatis.dao.domain.User;

import java.util.List;

public interface OrderMapper {
    //查询订单关联查询用户，用户信息是延迟加载
    List<Orders> findOrdersUserLazyLoading();

    void updateOrderById(Orders orders);

    Orders findOrderById(int id);

}
