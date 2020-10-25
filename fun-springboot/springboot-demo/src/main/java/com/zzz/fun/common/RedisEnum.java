package com.zzz.fun.common;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangsitian
 */
public enum RedisEnum {
    // 关键业务缓存时间列举
    NONCE_CHECK(30L, TimeUnit.SECONDS, "防重放标志"),
    FLOW_CHECK(1L, TimeUnit.SECONDS, "流量控制"),
    VISIT_FREQUENCY(400L, TimeUnit.MILLISECONDS, "访问频率限制"),
    TOKEN_CHECK(1L, TimeUnit.DAYS, "token保留时间"),
    AUTH_CODE(5L, TimeUnit.MINUTES, "验证码保留时间"),
    IMAGE_CODE(5L, TimeUnit.MINUTES, "图形验证码保留时间"),
    DISTRIBUTED_LOCK(1L, TimeUnit.HOURS, "分布式锁保留时间"),
    LARGE_CACHE_VERSION(6L, TimeUnit.HOURS, "大对象的版本信息保留时间"),
    LARGE_CACHE_VALUE(6L, TimeUnit.HOURS, "大对象的值信息保留时间"),
    MATCH_CACHE_IDS(6L, TimeUnit.HOURS, "匹配到的Id列表信息保留时间"),
    HYPERLOGLOG(-1L, TimeUnit.SECONDS, "redis保留时间"),
    // 通用缓存时间
    SECONDS_1(1L, TimeUnit.SECONDS, "1秒"),
    SECONDS_2(2L, TimeUnit.SECONDS, "2秒"),
    SECONDS_5(5L, TimeUnit.SECONDS, "5秒"),
    SECONDS_15(15L, TimeUnit.SECONDS, "15秒"),
    SECONDS_30(30L, TimeUnit.SECONDS, "30秒"),
    MINUTES_1(1L, TimeUnit.MINUTES, "1分钟"),
    MINUTES_2(2L, TimeUnit.MINUTES, "2分钟"),
    MINUTES_5(5L, TimeUnit.MINUTES, "5分钟"),
    MINUTES_15(15L, TimeUnit.MINUTES, "15分钟"),
    MINUTES_30(30L, TimeUnit.MINUTES, "30分钟"),
    HOURS_1(1L, TimeUnit.HOURS, "1小时"),
    HOURS_2(2L, TimeUnit.HOURS, "2小时"),
    HOURS_5(5L, TimeUnit.HOURS, "5小时"),
    HOURS_12(12L, TimeUnit.HOURS, "12小时"),
    DAYS_1(1L, TimeUnit.DAYS, "1天"),
    DAYS_2(2L, TimeUnit.DAYS, "2天"),
    DAYS_5(5L, TimeUnit.DAYS, "5天"),
    WEEKS_1(7L, TimeUnit.DAYS, "1周"),
    MONTHS_1(30L, TimeUnit.DAYS, "1个月"),
    ;

    /**
     * 过期时间
     */
    private Long time;
    /**
     * 时间单位
     */
    private TimeUnit timeUnit;

    /**
     * 描述
     */
    private String desc;

    RedisEnum(Long time, TimeUnit timeUnit, String desc) {
        this.time = time;
        this.timeUnit = timeUnit;
        this.desc = desc;
    }

    public Long getTime() {
        return time;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public String getDesc() {
        return desc;
    }
}
