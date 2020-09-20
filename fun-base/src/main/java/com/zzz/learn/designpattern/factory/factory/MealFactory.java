package com.zzz.learn.designpattern.factory.factory;

import com.zzz.learn.designpattern.factory.abstractfactory.Dishable;
import com.zzz.learn.designpattern.factory.abstractfactory.Prepareable;
import com.zzz.learn.designpattern.factory.meal.Meal;

public interface MealFactory extends Prepareable, Dishable {

    Meal cookMeal();

}
