package com.zzz.fun.mapper.dao;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.zzz.fun.Application;
import com.zzz.fun.mapper.po.MrTblPhoneLocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocationDaoTest {

    @Autowired
    private MrTblPhoneLocationDao mrTblPhoneLocationDao;

    @Test
    public void test(){
        MrTblPhoneLocation condition = new MrTblPhoneLocation();
        condition.setMobilePrefix("1300000");
        MrTblPhoneLocation result = mrTblPhoneLocationDao.selectByRecord(condition);
        System.out.println(JSONUtil.toJsonStr(result));
    }

}
