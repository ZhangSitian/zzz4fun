package com.zzz.learn.mybatis.dao.annotation;

import com.zzz.learn.mybatis.dao.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ProductMapper  {

    @Select("SELECT * FROM product")
    List<Product> selectAll();

    @Insert("INSERT INTO `product` (`product_name`, `product_type`, `left_num`) VALUES (#{name}, '1', #{desc})")
    int insert(@Param("name") String name, @Param("desc") String desc);

    @Update("UPDATE `product` SET left_num =  #{desc} WHERE id = #{id}")
    int updateDescById(@Param("id") long id, @Param("desc") String desc);

}
