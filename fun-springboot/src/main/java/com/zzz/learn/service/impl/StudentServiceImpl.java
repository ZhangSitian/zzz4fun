package com.zzz.learn.service.impl;

import com.zzz.learn.dao.StudentMapper;
import com.zzz.learn.domain.Student;
import com.zzz.learn.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> queryStudents() {
        return new ArrayList<Student>();
    }

    @Resource
    public StudentMapper studentMapper;

    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0 ? true : false;
    }


}
