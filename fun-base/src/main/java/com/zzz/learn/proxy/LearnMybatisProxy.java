package com.zzz.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LearnMybatisProxy implements InvocationHandler {

    /**
     * 获取被代理接口实例对象
     *
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> proxyInterface) {
        return (T) Proxy.newProxyInstance(proxyInterface.getClassLoader(), new Class[]{proxyInterface}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (Object.class.equals(method.getDeclaringClass())) {
            // 有实际的代理类
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
            }
        }
        System.out.println("com.zzz.learn.proxy.LearnMybatisProxy");
        // 对于返回为void的方法来说，返回啥都无所谓，是否看返回值根据method来的返回值类型
        return "2345678";
    }
}
