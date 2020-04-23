package com.zzz.learn.domain;

/**
 * 单例
 */
public class Stage {

    private Stage() {
    }

    /**
     * "initialization on demand holder."
     * 懒加载
     */
    private static class StageSingletonHolder {
        static Stage instance = new Stage();
    }

    public static Stage getInstance() {
        return StageSingletonHolder.instance;
    }

}
