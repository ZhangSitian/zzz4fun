package com.zzz.fun.mybatis.dao;


import com.zzz.fun.mybatis.dao.domain.User;

public interface UserMapper {

    User findUserById(int id);

    void updateUserById(User user);

}
