package com.zzz.learn.proxy;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class CglibProxy implements InvocationHandler {

    private Subject subject;

    public CglibProxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------begin-------------");
        Object invoke = method.invoke(subject, args);
        System.out.println("--------------end-------------");
        return invoke;
    }

}
