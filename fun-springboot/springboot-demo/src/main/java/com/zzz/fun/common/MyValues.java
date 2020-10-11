package com.zzz.fun.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyValues {

    @Value("${aaa.bbb}")
    private String bbb;
    @Value("${aaa.ccc}")
    private String ccc;

    public String getBbb() {
        return bbb;
    }

    public String getCcc() {
        return ccc;
    }
}
