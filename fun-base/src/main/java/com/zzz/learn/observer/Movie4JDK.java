package com.zzz.learn.observer;

import java.util.Observable;

public class Movie4JDK extends Observable {

    public void action(){
        Long actionTime = System.currentTimeMillis();
        setChanged();
        notifyObservers(actionTime);
    }

}
