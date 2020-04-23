package com.zzz.learn.dao;

import com.zzz.learn.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate primaryJdbcTemplate;
    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate secondaryJdbcTemplate;

    public void savePrimary() {
        primaryJdbcTemplate.execute("insert into blog(phone) values('182182121')");
    }

    public void saveSecond() {
        secondaryJdbcTemplate.execute("insert into blog(phone) values('182182121')");
    }
    public Long queryCountPrimary() {
        return primaryJdbcTemplate.queryForObject("select count(*) from blog", Long.class);
    }

    public Long queryCountSecond() {
        return secondaryJdbcTemplate.queryForObject("select count(*) from blog", Long.class);
    }

    public void addStudent(Student student){
    }

    public Student findByName(String name){
        return new Student();
    }


}
