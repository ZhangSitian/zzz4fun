package com.zzz.fun.designpattern.factory.factory;

import com.zzz.fun.designpattern.factory.meal.Malatang;
import com.zzz.fun.designpattern.factory.meal.Meal;

public class MalatangFactory implements MealFactory {
    @Override
    public Meal cookMeal() {
        return new Malatang();
    }

    @Override
    public void dish() {
        System.out.println("装盘麻辣烫");
    }

    @Override
    public void prepare() {
        System.out.println("准备麻辣烫");
    }
}
