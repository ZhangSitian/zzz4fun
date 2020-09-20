package com.zzz.learn.designpattern.singleton;

/**
 * @author zhangsitian
 * @description 单例懒汉
 * @date 2020/9/7 17:33
 */
public class LazySingleton {
    private LazySingleton() {
    }

    private static LazySingleton singleton = null;

    //静态工厂方法
    public static LazySingleton getInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

    // 同步
    public static synchronized LazySingleton getSyncInstance() {
        if (singleton == null) {
            singleton = new LazySingleton();
        }
        return singleton;
    }

    // 双重检查锁定
    public static LazySingleton getLockInstance() {
        if (singleton == null) {
            synchronized (LazySingleton.class) {
                if (singleton == null) {
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }


    /**
     * @description 按需加载和线程安全
     * @author zhangsitian
     * @date 2020/9/7 17:49
     */
    private static class LazyHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    public static LazySingleton getInnerInstance() {
        return LazyHolder.INSTANCE;
    }


}
