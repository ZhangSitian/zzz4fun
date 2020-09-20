package com.zzz.learn.designpattern.factory.simplefactory;

import com.zzz.learn.designpattern.factory.meal.Jiaozi;
import com.zzz.learn.designpattern.factory.meal.Malatang;
import com.zzz.learn.designpattern.factory.meal.Meal;
import com.zzz.learn.designpattern.factory.meal.Shuizhuyu;

/**
 * @author zhangsitian
 * @description 客户不需要知道对象是怎么创建的，只需要选择
 * @date 2020/9/7 14:51
 */
public class SimpleMealFactory {

    public static Meal cookMeal(MealEnum mealEnum) {
        switch (mealEnum) {
            case JIAOZI:
                return new Jiaozi();
            case MALATANG:
                return new Malatang();
            case SHUIZHUYU:
                return new Shuizhuyu();
            default:
                return null;
        }
    }


    public enum MealEnum {
        JIAOZI,
        MALATANG,
        SHUIZHUYU
    }

}
