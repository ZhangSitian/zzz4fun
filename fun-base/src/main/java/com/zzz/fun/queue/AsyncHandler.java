package com.zzz.fun.queue;

import java.util.concurrent.*;

public class AsyncHandler {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
            3, 1L, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),new MyRejectedExecutionHandler());

    public static void tryAction(Runnable runnable) {
        try {
            threadPoolExecutor.execute(runnable);
        } catch (Throwable t) {
            // ignore
            System.out.println(t);
        }
    }

    public static void showSize(){
        System.out.println("ActiveCount:"+threadPoolExecutor.getActiveCount());
        System.out.println("CorePoolSize:"+threadPoolExecutor.getCorePoolSize());
        System.out.println("PoolSize:"+threadPoolExecutor.getPoolSize());
        System.out.println("LargestPoolSize:"+threadPoolExecutor.getLargestPoolSize());
        System.out.println("MaximumPoolSize:"+threadPoolExecutor.getMaximumPoolSize());
        System.out.println("TaskCount:"+threadPoolExecutor.getTaskCount());
        System.out.println("CompletedTaskCount:"+threadPoolExecutor.getCompletedTaskCount());
    }

    public static boolean completed(){
        return 0 == threadPoolExecutor.getActiveCount();
    }

    public static void process(){
        try {
            Thread.sleep(6000);
            System.out.println("over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

  static class MyRejectedExecutionHandler implements RejectedExecutionHandler{

        MyRejectedExecutionHandler(){
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
