package com.zzz.fun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Test;

public class ParseJsonTest {


    @Test
    public void parseJSONTest() {
        VO vo = new VO();
        vo.setDateTime("3456789");
        vo.setErrorCode("34567890");
        vo.setErrorMsg("4567890");
        System.out.println(JSON.toJSONString(vo));
    }


    @Test
    public void parseObjTest() {
        JSONObject json = getJSON();
        VO vo = JSONObject.parseObject(json.toJSONString(),VO.class);
        System.out.println(JSONObject.toJSONString(vo));
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("date_time", "234567890");
        json.put("error_code", "234567890");
        json.put("error_msg", "234567890");
        return json;
    }


    static class VO {

        @JSONField(name = "date_time",label="224324324")
        private String dateTime;
        @JSONField(name = "error_code")
        private String errorCode;
        @JSONField(name = "error_msg")
        private String errorMsg;

        public String getDateTime() {
            return dateTime;
        }

        public void setDateTime(String dateTime) {
            this.dateTime = dateTime;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

    }

}
