package com.zzz.learn.mybatis.dao.template;

import com.zzz.learn.mybatis.dao.ProductDao;
import com.zzz.learn.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * SimpleJdbcTemplate 是用实例
 */
public class ProductTemplateDao implements ProductDao {

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 插入语句
     * @return 影响行数
     */
    public int insert(Product product) {
        final String insertSql = "INSERT INTO product (product_name, product_type, left_num) VALUES (?,?,?) ";
        return jdbcTemplate.update(insertSql, product.getProductName(), product.getProductType(), product.getLeftNum());
    }

    /**
     * columnIndex是从1开始的。。
     * @param id 记录id
     * @return 记录
     */
    public Product selectById(long id) {
        final String querySql = "SELECT id, product_name, product_type, left_num, gmt_created, gmt_modified from product where id=? ";
        return getJdbcTemplate().queryForObject(querySql, (rs, rowNum) -> {
            Object[] args = new Object[6];
            for (int i = 0; i <6 ; i++) {
                args[i] = rs.getObject(i+1);
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
     * @param product 入参
     * @return 影响行数
     */
    public int add(Product product){
        final String addSql = "INSERT INTO product (product_name, product_type, left_num) VALUES  (?,?,?) ";
        return jdbcTemplate.update(addSql, product.getProductName(),product.getProductType(),product.getLeftNum());
    }
}
