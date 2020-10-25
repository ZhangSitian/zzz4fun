package com.zzz.fun.designpattern.factory;


import com.zzz.fun.designpattern.factory.factory.JiaoziFactory;
import com.zzz.fun.designpattern.factory.factory.MalatangFactory;
import com.zzz.fun.designpattern.factory.factory.MealFactory;
import com.zzz.fun.designpattern.factory.factory.ShuizhuyuFactory;
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