package com.zzz.fun.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class FastJSONTest {

    private static final String dataStr = "{\"data\":{\"id\":\"001\",\"name\":\"test\",\"value\":\"你好 白菜\"}}";

    private static final String dataStrWrong = "{\"data\":{\"id\":\"001\",\"name\":\"test\",\"value\":\"你好 白菜\"}";

    private static final String dataArrayStr = "[{\"id\":\"001\",\"name\":\"test\",\"value\":\"你好 白菜\"}," +
            "{\"id\":\"002\",\"name\":\"test2\",\"value\":\"你好 酸菜\"}]";

    private static final String dataArrayStrWrong = "[{\"id\":\"001\",\"name\":\"test\",\"value\":\"你好 白菜\"}," +
            "{\"id\":\"002\",\"name\":\"test2\",\"value\":\"你好 酸菜\"]";

    @Test
    public void executeValidTest() {
        Assert.assertTrue(JSONObject.isValid(dataStr));
        Assert.assertFalse(JSONObject.isValid(dataStrWrong));

        Assert.assertTrue(JSONObject.isValid(dataArrayStr));
        Assert.assertFalse(JSONObject.isValid(dataArrayStrWrong));
    }

    @Test
    public void executeValidObjectTest() {
        Assert.assertTrue(JSONObject.isValidObject(dataStr));
        Assert.assertFalse(JSONObject.isValidObject(dataStrWrong));
    }

    @Test
    public void executeValidArrayTest() {
        Assert.assertTrue(JSONObject.isValidArray(dataArrayStr));
        Assert.assertFalse(JSONObject.isValidArray(dataArrayStrWrong));
    }

    @Test
    public void executeForEachTest() {
        JSONObject dataObj = JSONObject.parseObject(dataStr);
        JSONObject data = dataObj.getJSONObject("data");
        data.forEach((key, value) -> {
            System.out.println(key+":"+value);
        });
    }
}



