package com.zzz.fun.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String saySomething(String msg){
        System.out.println(msg);
        return msg;
    }

}
