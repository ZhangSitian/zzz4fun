package com.zzz.fun.designpattern.factory;


import com.zzz.fun.designpattern.factory.simplefactory.SimpleMealFactory;
import org.junit.Test;

public class SimpleMealFactoryTest {

    @Test
    public void test(){
        SimpleMealFactory.cookMeal(SimpleMealFactory.MealEnum.JIAOZI);
        SimpleMealFactory.cookMeal(SimpleMealFactory.MealEnum.MALATANG);
        SimpleMealFactory.cookMeal(SimpleMealFactory.MealEnum.SHUIZHUYU);
    }

}