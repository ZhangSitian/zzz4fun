package com.zzz.fun.proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class CglibProxyTest {

    @Test
    public void testJDK(){
        Subject subject = new SubjectImpl();
        InvocationHandler subjectProxy = new CglibProxy(subject);
        Subject proxyInstance = (Subject) Proxy.newProxyInstance(subjectProxy.getClass().getClassLoader(), subject.getClass().getInterfaces(), subjectProxy);
        proxyInstance.hello("world");
    }

    @Test
    public void testEnhancer(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SubjectImpl.class);
        enhancer.setUseCache(false);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("before "+method);
                //此处不可以使用invoke方法需使用invokeSuper否则会进入递归循环
                Object result = methodProxy.invokeSuper(o,objects);
                System.out.println("after "+method);
                return result;
            }
        });
        Subject enhancerObj = (Subject) enhancer.create();
        enhancerObj.hello("world");
    }
}
