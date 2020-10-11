package com.zzz.fun.controllers;

import com.zzz.fun.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NormalController {


    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/normal")
    public String testPage(Model model) {
        model.addAttribute("msg",demoService.saySomething("hello"));
        return "page";
    }

}

