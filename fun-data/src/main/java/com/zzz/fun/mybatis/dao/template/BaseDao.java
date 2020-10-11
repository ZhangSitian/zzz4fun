package com.zzz.fun.mybatis.dao.template;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * 这个类只省去了java类中jdbcTemplate的getter、setter。。。
 */
public class BaseDao {

    private SimpleJdbcTemplate simpleJdbcTemplate;

    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return simpleJdbcTemplate;
    }

    public void setSimpleJdbcTemplate(SimpleJdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }

}
