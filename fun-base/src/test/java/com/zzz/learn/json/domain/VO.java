package com.zzz.learn.json.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class VO implements Serializable {
    private String errorCode;
    private String errorMsg;
    @JSONField(name = "describe")
    private String errorDesc;

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

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @Override
    public String toString() {
        return "VO{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", errorDesc='" + errorDesc + '\'' +
                '}';
    }
}
