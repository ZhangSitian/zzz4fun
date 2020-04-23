package com.zzz.learn.controller;

import com.zzz.learn.po.Items;
import com.zzz.learn.service.ItemService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by brian on 2016/2/19.
 */
public class ItemsController implements Controller {

    private ItemService itemService = new ItemService();

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //调用service查找数据库，查询商品列表，这里使用静态数据模拟
        List<Items> itemsList = itemService.queryAll();
        //返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        //相当于request的setAttribute方法,在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        //指定视图
        modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");

        return modelAndView;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    public ItemService getItemService() {
        return itemService;
    }
}
