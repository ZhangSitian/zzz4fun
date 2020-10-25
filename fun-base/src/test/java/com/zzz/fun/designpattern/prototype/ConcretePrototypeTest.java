package com.zzz.fun.designpattern.prototype;

import org.junit.Test;

public class ConcretePrototypeTest {

    @Test
    public void test() {
        ConcretePrototype cp = new ConcretePrototype();
        for (int i = 0; i < 10; i++) {
            ConcretePrototype clonecp = (ConcretePrototype) cp.clone();
            clonecp.show();
        }
    }

}