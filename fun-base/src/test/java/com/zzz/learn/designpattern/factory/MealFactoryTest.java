package com.zzz.learn.designpattern.factory;


import com.zzz.learn.designpattern.factory.factory.JiaoziFactory;
import com.zzz.learn.designpattern.factory.factory.MalatangFactory;
import com.zzz.learn.designpattern.factory.factory.MealFactory;
import com.zzz.learn.designpattern.factory.factory.ShuizhuyuFactory;
import com.zzz.learn.designpattern.factory.simplefactory.SimpleMealFactory;
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