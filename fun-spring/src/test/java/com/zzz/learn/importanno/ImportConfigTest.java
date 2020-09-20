package com.zzz.learn.importanno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImportConfigTest.class)
@Configuration
@Import(value = {ImportConfig.class})
public class ImportConfigTest {


    @Autowired
    private DemoService demoService;

    @Test
    public void test() {
        demoService.say("hello");
    }

}