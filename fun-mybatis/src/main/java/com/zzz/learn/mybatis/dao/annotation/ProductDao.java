/*
 * 文件名：ProductDao.java 修改人：zhangsitian 修改时间：2019年2月11日
 */

package com.zzz.learn.mybatis.dao.annotation;


import java.util.List;

import com.zzz.learn.mybatis.dao.domain.Product;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;



public interface ProductDao extends BaseDao<Product> {

    @SelectProvider(type = ProductProvider.class, method = "selectByRecord")
    @ResultType(Product.class)
    Product selectByRecord(@Param("condition") Product condition);

    @SelectProvider(type = ProductProvider.class, method = "selectListByRecord")
    @ResultType(Product.class)
    List<Product> selectListByRecord(@Param("condition") Product condition);

    @SelectProvider(type = ProductProvider.class, method = "selectById")
    @ResultType(Product.class)
    Product selectById(@Param("id") Long id);

    @SelectProvider(type = ProductProvider.class, method = "selectByIds")
    @ResultType(Product.class)
    List<Product> selectByIds(Long... ids);

    @UpdateProvider(type = ProductProvider.class, method = "updateById")
    int updateById(@Param("record") Product record);

    @UpdateProvider(type = ProductProvider.class, method = "updateByRecord")
    int updateByRecord(@Param("condition") Product condition, @Param("record") Product record);

    @InsertProvider(type = ProductProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(@Param("record") Product record);

}
