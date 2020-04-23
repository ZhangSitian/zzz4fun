package com.zzz.learn.dao;

import com.zzz.learn.domain.Blog;
import org.apache.ibatis.annotations.Insert;

public interface BlogDao {

    @Insert("insert into blog (user_id,name,address) values(#{userId},#{name},#{address})")
    int insert(Blog blog);

}
