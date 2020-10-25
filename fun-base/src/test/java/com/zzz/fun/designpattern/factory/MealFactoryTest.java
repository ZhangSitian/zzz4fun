package com.zzz.fun.designpattern.factory;


import com.zzz.fun.designpattern.factory.factory.JiaoziFactory;
import com.zzz.fun.designpattern.factory.factory.MalatangFactory;
import com.zzz.fun.designpattern.factory.factory.MealFactory;
import com.zzz.fun.designpattern.factory.factory.ShuizhuyuFactory;
import org.junit.Test;

public class MealFactoryTest {

    @Test
    public void test(){
        MealFactory mealFactory = new ShuizhuyuFactory();
        mealFactory.cookMeal();
        mealFactory = new JiaoziFactory();
        mealFactory.cookMeal();
        mealFactory = new MalatangFactory();
        mealFactory.cookMeal();
    }

}