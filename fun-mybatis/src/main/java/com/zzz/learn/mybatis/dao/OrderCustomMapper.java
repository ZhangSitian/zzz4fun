package com.zzz.learn.mybatis.dao;


import com.zzz.learn.mybatis.dao.domain.Orders;
import com.zzz.learn.mybatis.dao.domain.OrdersCustomVO;
import com.zzz.learn.mybatis.dao.domain.User;

import java.util.List;

public interface OrderCustomMapper {
    //根据用户名列查询用户列表
    List<OrdersCustomVO> findOrdersUser();

    List<Orders> findOrdersUserResultMap();

    // 一对多
    List<Orders>  findOrdersAndOrderDetailResultMap();

    List<User>  findUserAndItemsResultMap();
}
