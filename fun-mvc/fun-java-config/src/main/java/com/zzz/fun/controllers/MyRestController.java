package com.zzz.fun.controllers;

import com.zzz.fun.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {


    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String testRest(Model model) {
        return demoService.saySomething("hello");
    }

}

