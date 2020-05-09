package com.zzz.learn.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxyTest {

    @Test
    public void testJDK(){
        Subject subject = new SubjectImpl();
        InvocationHandler subjectProxy = new JDKProxy(subject);
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(subjectProxy.getClass().getClassLoader(), subject.getClass().getInterfaces(), subjectProxy);
        proxyInstance.hello("world");
    }

    @Test
    public void testJDKDynamicProxy(){
        // 保存生成的代理类的字节码文件，这句在test方法里不生效
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // jdk动态代理测试
        Subject subject = new JDKDynamicProxy(new SubjectImpl()).getProxy();
        subject.hello("world");
    }

    public static void main(String[] args) {
        new JDKProxyTest().testJDKDynamicProxy();
    }



}
