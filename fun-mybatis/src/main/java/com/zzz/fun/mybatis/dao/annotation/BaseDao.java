/*
 * 文件名：BaseDao.java
 * 修改人：zhangsitian
 * 修改时间：2019年2月13日
 */
 
package com.zzz.fun.mybatis.dao.annotation;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T> {

    int insert(T record);

    T selectById(Long id);

    List<T> selectByIds(Long... ids);

    T selectByRecord(@Param("condition") T condition);

    List<T> selectListByRecord(@Param("condition") T condition);

    int updateById(@Param("record") T record);

    int updateByRecord(@Param("condition") T condition, @Param("record") T record);
}
