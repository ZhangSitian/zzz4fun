package com.zzz.learn.designpattern.factory.factory;

import com.zzz.learn.designpattern.factory.meal.Meal;
import com.zzz.learn.designpattern.factory.meal.Shuizhuyu;

public class ShuizhuyuFactory implements MealFactory {
    @Override
    public Meal cookMeal() {
        return new Shuizhuyu();
    }


    @Override
    public void dish() {
        System.out.println("装盘水煮鱼");
    }

    @Override
    public void prepare() {
        System.out.println("准备水煮鱼");
    }
}
