package com.zzz.fun.json;

import com.alibaba.fastjson.JSONObject;
import com.zzz.fun.json.domain.VO;
import org.junit.Assert;
import org.junit.Test;

public class FastJSONParseTest {

    /**
     * 默认就有下划线和驼峰规则之间的转换
     */
    @Test
    public void fastJSONParseTest(){
        JSONObject jsonObject = new JSONObject();
        // 正常对应的字段
        jsonObject.put("errorCode","1000");
        // error_msg 会自动转为 errorMsg
        jsonObject.put("error_msg","成功");
        // 使用@JSONField 注释转换命名
        jsonObject.put("describe","哈哈哈");
        VO vo = JSONObject.parseObject(jsonObject.toJSONString(),VO.class);
        Assert.assertEquals("{\"describe\":\"哈哈哈\",\"errorCode\":\"1000\",\"errorMsg\":\"成功\"}",JSONObject.toJSONString(vo));
    }

}