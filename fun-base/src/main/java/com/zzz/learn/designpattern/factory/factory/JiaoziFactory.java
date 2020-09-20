package com.zzz.learn.designpattern.factory.factory;

import com.zzz.learn.designpattern.factory.abstractfactory.Dishable;
import com.zzz.learn.designpattern.factory.abstractfactory.Prepareable;
import com.zzz.learn.designpattern.factory.meal.Jiaozi;
import com.zzz.learn.designpattern.factory.meal.Meal;

public class JiaoziFactory implements MealFactory , Prepareable, Dishable {
    @Override
    public Meal cookMeal() {
        return new Jiaozi();
    }


    @Override
    public void dish() {
        System.out.println("装盘饺子");
    }

    @Override
    public void prepare() {
        System.out.println("准备饺子");
    }

}
