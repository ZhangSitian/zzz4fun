package com.zzz.fun.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

    public void executeTask(int i) {
        System.out.println("sync:" + i);
    }


    @Async
    public void executeAsyncTask(int i) {
        System.out.println("async:" + i);
    }

    @Async
    public void executeAsyncTaskPlus(int i) {
        System.out.println("async+1:" + (i + 1));
    }

}
