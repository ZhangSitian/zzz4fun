package com.zzz.fun.designpattern.proxy;

import org.junit.Test;

public class ProxyTest {

    @Test
    public void test() {

        People people1 =new People();
        people1.setCash(60000);
        people1.setUsername("jeck");


        People people2 =new People();
        people2.setCash(40000);
        people2.setUsername("rose");

        People people3 =new People();

        people3.setCash(0);
        people3.setUsername("tom");
        people3.setVip("vip");

        ProxyPerson proxyBuy = new ProxyPerson();
        proxyBuy.setPeople(people1);
        proxyBuy.buyCar();

        proxyBuy.setPeople(people2);
        proxyBuy.buyCar();

        proxyBuy.setPeople(people3);
        proxyBuy.buyCar();

    }
}