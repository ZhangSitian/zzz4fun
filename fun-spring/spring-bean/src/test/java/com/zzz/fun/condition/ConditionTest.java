package com.zzz.fun.condition;

import com.zzz.fun.domain.Domain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConditionConfig.class)
public class ConditionTest {


    @Autowired
    private Domain domain;

    @Test
    public void test(){
        System.out.println(domain.getDesc());
    }

}