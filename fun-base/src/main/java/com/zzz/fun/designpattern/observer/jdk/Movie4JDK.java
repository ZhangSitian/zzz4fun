package com.zzz.fun.designpattern.observer.jdk;

import java.util.Observable;

public class Movie4JDK extends Observable {

    public void action(){
        Long actionTime = System.currentTimeMillis();
        setChanged();
        notifyObservers(actionTime);
    }

}
