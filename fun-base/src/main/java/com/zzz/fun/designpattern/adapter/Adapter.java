package com.zzz.fun.designpattern.adapter;

public class Adapter extends Adaptee implements Target{
    public void request() {
        super.specificRequest();
    }
}
