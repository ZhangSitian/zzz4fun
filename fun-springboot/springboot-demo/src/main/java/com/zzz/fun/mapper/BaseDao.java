/*
 * 文件名：BaseDao.java
 * 修改人：zhangsitian
 * 修改时间：2019年2月13日
 */
 
package com.zzz.fun.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDao<T> {

    int insert(T record);

    T selectById(Long id);

    List<T> selectByIds(Long... ids);

    T selectByRecord(@Param("condition") T condition);

    List<T> selectListByRecord(@Param("condition") T condition);

    // 只要加了pageNum和pageSize参数，就是分页查询
    List<T> selectListByRecord(@Param("condition") T condition, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    int countByRecord(@Param("condition") T condition);

    int updateById(@Param("record") T record);

    int updateByRecord(@Param("condition") T condition, @Param("record") T record);
}
