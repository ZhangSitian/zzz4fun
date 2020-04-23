package com.zzz.learn.controller;

import com.zzz.learn.common.ResponseData;
import com.zzz.learn.common.exception.GlobalException;
import com.zzz.learn.common.exception.ParamException;
import com.zzz.learn.common.exception.ServerException;
import com.zzz.learn.dao.StudentDao;
import com.zzz.learn.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController("students")
@EnableAutoConfiguration
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    StudentDao studentDao;


    @RequestMapping("/")
    Object queryStudents() throws GlobalException {
        return ResponseData.ok(studentService.queryStudents());
    }

    @RequestMapping("/{name}")
    Object queryStudentByName(@PathVariable String name) throws Exception {
        if(name.equals("1")){
            throw new ParamException("参数错误");
        }
        if(name.equals("2")){
            throw new ServerException("内部错误");
        }
        System.out.println("queryStudentByName");
        return ResponseData.ok(studentDao.findByName(name));
    }

    @RequestMapping( path= "/post" ,method = RequestMethod.POST)
    Object welcome(Date time) {
        System.out.println(time);
        return time;
    }

}
