package com.zzz.fun.controllers;

import com.zzz.fun.mapper.dao.MrTblPhoneLocationDao;
import com.zzz.fun.mapper.po.MrTblPhoneLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/sql")
public class SqlController {

    @Autowired
    private MrTblPhoneLocationDao mrTblPhoneLocationDao;

    @RequestMapping("/get")
    public Object sqlGet(MrTblPhoneLocation condition) {
        if(Objects.nonNull(condition)){
            return mrTblPhoneLocationDao.selectByRecord(condition);
        }
        return null;
    }

    @RequestMapping("/set")
    public Object sqlSet(MrTblPhoneLocation record) {
        if(Objects.nonNull(record)){
            return mrTblPhoneLocationDao.insert(record);
        }
        return null;
    }

}
