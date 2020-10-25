package com.zzz.fun.designpattern.decorator;


import org.junit.Test;

public class DecoratorTest {

    @Test
    public void test(){
        Human person = new Person();
        Decorator decorator = new DecoratorTwo(new DecoratorFirst(
                new DecoratorZero(person)));
        decorator.wearClothes();
        decorator.walkToWhere();
    }

}