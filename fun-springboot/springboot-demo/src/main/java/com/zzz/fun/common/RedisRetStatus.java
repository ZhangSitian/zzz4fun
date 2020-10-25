package com.zzz.fun.common;

/**
 * @author zhangsitian
 */
public enum RedisRetStatus {

    REDIS_ERROR("00100", "redis异常"),
    REDIS_CACHE_ERROR("00101", "redis cache 刷新冲突"),
    FLOW_ERROR("00200", "服务器流量超限"),
    VISIT_INVALID("00201", "别点太快了，我反应不过来"),
    TOKEN_INVALID("00300", "登录态过期，请重新登录"),
    ;

    private String retCode;
    private String retMsg;

    RedisRetStatus(String retCode, String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }
}
