package com.zzz.learn.designpattern.factory;


import com.zzz.learn.designpattern.factory.factory.JiaoziFactory;
import com.zzz.learn.designpattern.factory.factory.MalatangFactory;
import com.zzz.learn.designpattern.factory.factory.MealFactory;
import com.zzz.learn.designpattern.factory.factory.ShuizhuyuFactory;
import org.junit.Test;

public class AbstractMealFactoryTest {

    @Test
    public void test(){
        MealFactory mealFactory = new ShuizhuyuFactory();
        mealFactory.prepare();
        mealFactory.dish();
        mealFactory = new JiaoziFactory();
        mealFactory.prepare();
        mealFactory.dish();
        mealFactory = new MalatangFactory();
        mealFactory.prepare();
        mealFactory.dish();
    }

}