package com.zzz.fun.controllers;

import cn.hutool.json.JSONUtil;
import com.zzz.fun.common.MyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {

    @Autowired
    private MyValues myValues;

    @RequestMapping("/")
    public String index() {
        return "hello";
    }

    @RequestMapping("/info")
    public Object info(HttpServletRequest request) {
        System.out.println(request);
        return request;
    }

}
