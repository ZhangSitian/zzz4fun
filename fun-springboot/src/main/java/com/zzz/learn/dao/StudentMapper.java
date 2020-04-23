package com.zzz.learn.dao;


import com.zzz.learn.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {

    @Insert("insert into t_student (student_id,name,age) values (#{studentId},#{name},#{age})")
    Integer insert(Student s);

    @Select("select * from t_student")
    List<Student> findAll();

    @Select("select * from t_student where id in (#{studentIds})")
    List<Student> findByStudentIds(List<Integer> studentIds);

}
