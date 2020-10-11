package com.zzz.fun.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EventConfig.class)
public class DemoListenerTest {

    @Autowired
    private DemoPublisher demoPublisher;

    @Test
    public void test() {
        demoPublisher.publish("hello");
    }


}