package com.zzz.fun.controllers;

import com.zzz.fun.po.Items;
import com.zzz.fun.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DemoController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String success() {
        return "index";
    }

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    public ModelAndView getItem() {
        //调用service查找数据库，查询商品列表，这里使用静态数据模拟
        List<Items> itemsList = itemService.queryAll();
        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //相当于request的setAttribute方法,在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        //指定视图
        modelAndView.setViewName("itemsList");

        return modelAndView;
    }

}

