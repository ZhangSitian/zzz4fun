package com.zzz.fun;

import java.util.*;

public class ZSetTest {

    public static void main(String[] args) {
        //创建集合
        SortedSet ss = new TreeSet();
        //添加元素
        ss.add("12345678");
        ss.add(99);
        ss.add(434);
        ss.add(12);
        ss.add(78);
        ss.add(36);
        showAll(ss);

        /*结果为：
          12
          36
          78
          99
          434
          说明Integer类型是以从小到大的顺序排序的
        */
        SortedSet sss = new TreeSet();
        sss.add("baidu");
        sss.add("ali");
        sss.add("tencent");
        showAll(sss);
    }

    private static void showAll(SortedSet ss){
        Iterator it = ss.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }


}
