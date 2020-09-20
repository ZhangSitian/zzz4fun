package com.zzz.learn.designpattern.builder;


import com.zzz.learn.designpattern.builder.domain.Person;
import org.junit.Test;

public class PersonBuilderTest {

    @Test
    public void test(){
        PersonBuilder manBuilder = new ManBuilder();
        Person manPerson = manBuilder.buildHead().buildBody().buildFoot().buildPerson();
        PersonBuilder womanBuilder = new WomanBuilder();
        Person womanPerson = womanBuilder.buildHead().buildBody().buildFoot().buildPerson();
    }

}