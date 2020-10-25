package com.zzz.fun.designpattern.observer.jdk;

import com.zzz.fun.queue.AsyncHandler;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class MyObserver implements Observer {

    private String name;

    public MyObserver(String name){
        this.name = "Task"+name;
    }

    @Override
    public void update(Observable o, Object arg) {
        AsyncHandler.tryAction(()->{
            System.out.println(Thread.currentThread().getName()+" "+this.name+" 任务开始");
            try {
                TimeUnit.SECONDS.sleep(6L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" "+this.name+" 任务结束");
        });
    }
}
