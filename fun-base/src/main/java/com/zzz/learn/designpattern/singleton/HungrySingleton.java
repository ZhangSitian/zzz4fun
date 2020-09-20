package com.zzz.learn.designpattern.singleton;

/**
 * @author zhangsitian
 * @description 单例懒汉
 * @date 2020/9/7 17:33
 */
public class HungrySingleton {
    private HungrySingleton() {
    }

    private static final HungrySingleton singleton = new HungrySingleton();

    //静态工厂方法
    public static HungrySingleton getInstance() {
        return singleton;
    }

}
