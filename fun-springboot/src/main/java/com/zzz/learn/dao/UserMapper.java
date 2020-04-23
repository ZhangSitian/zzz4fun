package com.zzz.learn.dao;

import com.zzz.learn.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Insert("insert into t_user (user_id,name,age) values (#{userId},#{name},#{age}) ")
    Integer insert(User u);

    @Select("select * from t_user ")
    List<User> findAll();

    @Select("select * from t_user where user_id in (#{userIds}) ")
    List<User> findByUserIds(List<Integer> userIds);


}
