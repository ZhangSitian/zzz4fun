package com.zzz.fun.designpattern.singleton;

import org.junit.Test;

public class LazySingletonTest {
    @Test
    public void test(){
        LazySingleton.getInnerInstance();
        LazySingleton.getInstance();
        LazySingleton.getLockInstance();
        LazySingleton.getSyncInstance();
    }
}