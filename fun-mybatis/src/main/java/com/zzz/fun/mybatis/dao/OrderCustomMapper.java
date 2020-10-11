package com.zzz.fun.mybatis.dao;


import com.zzz.fun.mybatis.dao.domain.Orders;
import com.zzz.fun.mybatis.dao.domain.OrdersCustomVO;
import com.zzz.fun.mybatis.dao.domain.User;

import java.util.List;

public interface OrderCustomMapper {
    //根据用户名列查询用户列表
    List<OrdersCustomVO> findOrdersUser();

    List<Orders> findOrdersUserResultMap();

    // 一对多
    List<Orders>  findOrdersAndOrderDetailResultMap();

    List<User>  findUserAndItemsResultMap();
}
