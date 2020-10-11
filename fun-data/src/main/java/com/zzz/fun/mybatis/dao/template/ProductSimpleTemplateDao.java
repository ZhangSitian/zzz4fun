package com.zzz.fun.mybatis.dao.template;

import com.zzz.fun.mybatis.dao.ProductDao;
import com.zzz.fun.autowiring.domain.Product;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * SimpleJdbcTemplate 是用实例
 */
public class ProductSimpleTemplateDao extends BaseDao implements ProductDao {
    /**
     * 插入语句
     *
     * @param product 语句
     * @return 影响行数
     */
    public int insert(Product product) {
        final String insertSql = "INSERT INTO product (product_name, product_type, left_num) VALUES (?,?,?) ";
        return getSimpleJdbcTemplate().update(insertSql, product.getProductName(), product.getProductType(), product.getLeftNum());
    }

    /**
     * columnIndex是从1开始的。。
     *
     * @param id 记录id
     * @return 记录
     */
    public Product selectById(long id) {
        final String querySql = "SELECT id, product_name, product_type, left_num, gmt_created, gmt_modified from product where id=? ";
        return getSimpleJdbcTemplate().queryForObject(querySql, (rs, rowNum) -> {
            Object[] args = new Object[6];
            for (int i = 0; i < 6; i++) {
                args[i] = rs.getObject(i + 1);
            }
            Product product = new Product();
            product.setId((Long)args[0]);
            product.setProductName((String) args[1]);
            product.setProductType((String)args[2]);
            product.setLeftNum(Integer.parseInt((String)args[3]));
            product.setGmtCreated((Date) args[4]);
            product.setGmtModified((Date)args[5]);
            return product;
        }, id);
    }

    /**
     * 参数绑定
     * 曾今的NamedParameterJdbcTemplate的功能
     *
     * @param product 入参
     * @return 影响行数
     */
    public int add(Product product) {
        final String addSql = "INSERT INTO product (product_name, product_type, left_num) VALUES (:productName,:productType,:leftNum) ";
        Map<String, Object> params = new HashMap<>();
        params.put("productName", product.getProductName());
        params.put("productType", product.getProductType());
        params.put("leftNum", product.getLeftNum());
        return getSimpleJdbcTemplate().update(addSql, params);
    }
}
