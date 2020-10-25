package com.zzz.fun.designpattern.observer.other;


import org.junit.Test;

public class WeatherConditionTest {

    @Test
    public void test(){
        // 创建
        WeatherCondition weatherCondition = new WeatherCondition();
        Observer observer1 = new Observer1(weatherCondition);
        Observer observer2 = new Observer2(weatherCondition);
        weatherCondition.setMsg("阴天" );
        weatherCondition.setMsg("暴雨" );

    }

}