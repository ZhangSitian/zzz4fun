package com.zzz.learn.designpattern.observer.other;

import java.util.ArrayList;
import java.util.List;

public class WeatherCondition implements Subject {

    private List<Observer> observers = new ArrayList<>();
    /**
     * 天气预报
     */
    private String msg;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    /**
     * 主题更新消息
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        notifyObservers();
    }

}

