package com.zzz.fun.hutool;

import cn.hutool.core.util.PageUtil;
import cn.hutool.core.util.ReUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HutoolRegexTest {

    @Test
    public void extractMulti() {
        //抽取多个分组然后把它们拼接起来
        String content = "ZZZaaabbbccc中文1234";
        String resultExtractMulti = ReUtil.extractMulti("(\\w)aa(\\w)", content, "$1-$2");
        Assert.assertEquals("Z-a", resultExtractMulti);
    }

    @Test
    public void delFirst() {
        //删除第一个匹配到的内容
        String content = "ZZZaaabbbccc中文1234";
        String resultDelFirst = ReUtil.delFirst("(\\w)aa(\\w)", content);
        Assert.assertEquals("ZZbbbccc中文1234", resultDelFirst);
    }

    @Test
    public void findAll() {
        //查找所有匹配文本
        String content = "ZZZaaabbbccc中文1234";
        List<String> resultFindAll = ReUtil.findAll("\\w{2}", content, 0, new ArrayList<String>());
// 结果：["ZZ", "Za", "aa", "bb", "bc", "cc", "12", "34"]
    }

    @Test
    public void isMatch() {
        //给定字符串是否匹配给定正则
        String content = "ZZZaaabbbccc中文1234";
        boolean isMatch = ReUtil.isMatch("\\w+[\u4E00-\u9FFF]+\\d+", content);
        Assert.assertTrue(isMatch);
    }


    @Test
    public void replaceAll() {
        //通过正则查找到字符串，然后把匹配到的字符串加入到replacementTemplate中，$1表示分组1的字符串
        String content = "ZZZaaabbbccc中文1234";
//此处把1234替换为 ->1234<-
        String replaceAll = ReUtil.replaceAll(content, "(\\d+)", "->$1<-");
        Assert.assertEquals("ZZZaaabbbccc中文->1234<-", replaceAll);
    }

    @Test
    public void getFirstNumber() {
        //找到匹配的第一个数字
        String content = "ZZZaaabbbccc中文1234";
        Integer resultGetFirstNumber = ReUtil.getFirstNumber(content);
// 结果：1234
    }

    @Test
    public void escape() {
        //转义给定字符串，为正则相关的特殊符号转义
        String escape = ReUtil.escape("我有个$符号{}");
// 结果：我有个\\$符号\\{\\}
    }


}
