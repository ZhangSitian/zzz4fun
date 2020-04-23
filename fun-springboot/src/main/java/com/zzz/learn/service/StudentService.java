package com.zzz.learn.service;

import com.zzz.learn.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> queryStudents();

    boolean insert(Student student);
}
