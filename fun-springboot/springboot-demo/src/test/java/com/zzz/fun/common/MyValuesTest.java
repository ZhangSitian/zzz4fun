package com.zzz.fun.common;

import cn.hutool.json.JSONUtil;
import com.zzz.fun.Application;
import com.zzz.fun.service.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyValuesTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyValuesTest.class);


    @Autowired
    private MyValues myValues;

    @Autowired
    private Book book;


    @Test
    public void value(){
//        LOGGER.info(JSONUtil.toJsonStr(myValues));
        LOGGER.info(JSONUtil.toJsonStr(book));
    }

    @Autowired
    private MyService myService;

    @Test
    public void getBean(){
        LOGGER.info(myService.hi("111"));
    }



}