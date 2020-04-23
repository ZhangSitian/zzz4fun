package com.zzz.learn.queue;

public class MyTask implements Runnable {

    private String name;

    public MyTask(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start process this task: "+this.name);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end process this task: "+this.name);
    }


    public String getName() {
        return name;
    }
}
