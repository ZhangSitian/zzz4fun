package com.zzz.learn.queue;

import java.util.concurrent.BlockingQueue;

/**
 * 生产者线程
 *
 * @author jackyuj
 */
public class Producer {

    public static void process(String s, BlockingQueue<String> queue){
        System.out.println(Thread.currentThread().getName()+" Producer try add " );
        if(queue.add(s)){
            System.out.println(Thread.currentThread().getName()+" Producer add success" );
        }else{
            System.out.println(Thread.currentThread().getName()+" Producer add fail" );
        }
    }
}
