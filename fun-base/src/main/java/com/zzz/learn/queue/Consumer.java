package com.zzz.learn.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者线程
 *
 * @author jackyuj
 */
public class Consumer implements Runnable {

    private BlockingQueue<String> queue;

    //构造函数
    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName()+ " Consumer try take ");
        String s = null;
        try {
            s = queue.take();
            System.out.println(Thread.currentThread().getName()+ " Consumer process ");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+ " Consumer process over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
