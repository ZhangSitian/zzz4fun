package com.zzz.fun.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.IntStream;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TaskExecutorConfig.class)
public class AsyncTaskTest {


    @Autowired
    private AsyncTaskService asyncTaskService;

    @Test
    public void test() {
        for (int i : IntStream.range(10, 100).toArray()){
            asyncTaskService.executeTask(i);
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
    }

}