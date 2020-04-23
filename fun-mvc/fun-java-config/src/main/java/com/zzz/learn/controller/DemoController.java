package com.zzz.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String success() {
        return "index";
    }

}

