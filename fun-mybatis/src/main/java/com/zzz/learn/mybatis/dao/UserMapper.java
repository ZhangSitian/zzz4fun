package com.zzz.learn.mybatis.dao;


import com.zzz.learn.mybatis.dao.domain.User;

public interface UserMapper {

    User findUserById(int id);

    void updateUserById(User user);

}
