package com.zzz.learn.designpattern.observer.jdk;

import com.zzz.learn.queue.AsyncHandler;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class ObserverJDKTest {

    @Test
    public void test() {
        System.out.println("START ALL");
        Movie4JDK movie = new Movie4JDK();
        for (int i = 0; i < 10; i++) {
            movie.addObserver(new MyObserver(i+""));
        }
        movie.action();
        for (int i = 0; ; i++) {
            AsyncHandler.showSize();
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----------------------"+i+"--------------------------------");
            if(AsyncHandler.completed()){
                System.out.println(" all completed ");
                break;
            }
        }
    }

}
