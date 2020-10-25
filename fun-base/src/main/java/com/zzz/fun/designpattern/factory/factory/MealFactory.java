package com.zzz.fun.designpattern.factory.factory;

import com.zzz.fun.designpattern.factory.abstractfactory.Dishable;
import com.zzz.fun.designpattern.factory.abstractfactory.Prepareable;
import com.zzz.fun.designpattern.factory.meal.Meal;

public interface MealFactory extends Prepareable, Dishable {

    Meal cookMeal();

}
