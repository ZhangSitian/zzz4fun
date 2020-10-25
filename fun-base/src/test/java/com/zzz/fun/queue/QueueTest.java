package com.zzz.fun.queue;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QueueTest {

    @Test
    public void test() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                3, 1L, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), (runnable, executor) -> {
            try {
                System.out.println(Thread.currentThread().getName() + " stop to put task " + ((MyTask) runnable).getName());
                executor.getQueue().put(runnable);
                System.out.println(Thread.currentThread().getName() + " put task success ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new MyTask("task" + i));
        }
    }

    @Test
    public void tryActionTest() {
        AsyncHandler.tryAction(AsyncHandler::process);
    }

}
