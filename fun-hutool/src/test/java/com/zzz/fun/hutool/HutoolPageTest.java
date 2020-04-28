package com.zzz.fun.hutool;

import cn.hutool.core.util.PageUtil;
import org.junit.Test;

public class HutoolPageTest {

    @Test
    public void testRead() {
        int[] startEnd1 = PageUtil.transToStartEnd(0, 10);//[0, 10]
        int[] startEnd2 = PageUtil.transToStartEnd(1, 10);//[10, 20]
        int totalPage = PageUtil.totalPage(20, 3);//7
        //参数意义分别为：当前页、总页数、每屏展示的页数
        int[] rainbow = PageUtil.rainbow(5, 20, 6);
        //结果：[3, 4, 5, 6, 7, 8]
    }



}
