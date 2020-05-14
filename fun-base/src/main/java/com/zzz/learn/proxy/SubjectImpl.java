package com.zzz.learn.proxy;

public class SubjectImpl implements Subject {
    @Override
    public void hello(String param) {
        System.out.println("hello  " + param);
    }

    @Override
    public String hello1(String param) {
        return "hello1";
    }


}