package com.zzz.learn.service;

import com.zzz.learn.po.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    public List<Items> queryAll(){
        //调用service查找数据库，查询商品列表，这里使用静态数据模拟
        List<Items> itemsList = new ArrayList<>();

        //向list中填充静态数据
        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);
        return itemsList;
    }
}
