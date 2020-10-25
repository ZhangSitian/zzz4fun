package com.zzz.fun.designpattern.factory.factory;

import com.zzz.fun.designpattern.factory.abstractfactory.Dishable;
import com.zzz.fun.designpattern.factory.abstractfactory.Prepareable;
import com.zzz.fun.designpattern.factory.meal.Jiaozi;
import com.zzz.fun.designpattern.factory.meal.Meal;

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
