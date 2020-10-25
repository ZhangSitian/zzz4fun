package com.zzz.fun.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {

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
