package com.zzz.fun.controllers;


import com.zzz.fun.po.Items;
import com.zzz.fun.service.ItemService;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by brian on 2016/2/19.
 */
public class ItemsController2 implements HttpRequestHandler {

    private ItemService itemService = new ItemService();

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        //调用service查找数据库，查询商品列表，这里使用静态数据模拟
        List<Items> itemsList = itemService.queryAll();

        //设置模型数据
        httpServletRequest.setAttribute("itemsList", itemsList);

        //设置转发的视图
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(httpServletRequest, httpServletResponse);

    }

}
