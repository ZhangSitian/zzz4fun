package com.zzz.fun.hutool;

import cn.hutool.core.date.*;
import cn.hutool.core.lang.Console;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class HutoolDateTest {

    @Test
    public void testDate() {
        //当前时间
        Date date = DateUtil.date();
        //当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        //当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        System.out.println(now);
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();
        System.out.println(today);
    }

    @Test
    public void testDateParse() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        Date date1 = DateUtil.parse(dateStr, "yyyy-MM-dd");
    }

    @Test
    public void testDateFormat() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        //结果 2017/03/01
        String format = DateUtil.format(date, "yyyy/MM/dd");
        //常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);
        //结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);
        //结果：00:00:00
        String formatTime = DateUtil.formatTime(date);
    }

    @Test
    public void testDatePart() {
        Date date = DateUtil.date();
        //获得年的部分
        DateUtil.year(date);
        //获得月份，从0开始计数
        DateUtil.month(date);
        //获得月份枚举
        DateUtil.monthEnum(date);
    }

    @Test
    public void testDateStartEnd() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);
        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);
        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
    }


    @Test
    public void testDateBetween() {
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
        //相差31天
        Assert.assertEquals(31L, betweenDay);
        long betweenMS = DateUtil.between(date1, date2, DateUnit.MS);
        //Level.MINUTE表示精确到分
        String formatBetween = DateUtil.formatBetween(betweenMS, BetweenFormater.Level.MINUTE);
        //输出：31天1小时
        Console.log(formatBetween);
    }

    @Test
    public void testDateOffSet() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);
        //结果：2017-03-03 22:33:23
        Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);
        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);
        //昨天
        DateUtil.yesterday();
        //明天
        DateUtil.tomorrow();
        //上周
        DateUtil.lastWeek();
        //下周
        DateUtil.nextWeek();
        //上个月
        DateUtil.lastMonth();
        //下个月
        DateUtil.nextMonth();
    }


    @Test
    public void testDateTimeInterval() {
        TimeInterval timer = DateUtil.timer();

//---------------------------------
//-------这是执行过程
//---------------------------------
        //花费毫秒数
        timer.interval();
        //返回花费时间，并重置开始时间
        timer.intervalRestart();
        //花费分钟数
        timer.intervalMinute();
    }

    @Test
    public void testDateOther() {
        //年龄
        DateUtil.ageOfNow("1990-01-30");
        //是否闰年
        DateUtil.isLeapYear(2017);
    }

    @Test
    public void testDateTime() {
        DateTime dateTime = new DateTime("2017-01-05 12:34:23", DatePattern.NORM_DATETIME_FORMAT);

//年，结果：2017
        int year = dateTime.year();

//月份，结果：Month.JANUARY
        Month month = dateTime.monthEnum();

//日，结果：5
        int day = dateTime.dayOfMonth();

        //设置为不可变对象后变动将返回新对象，此时offset != dateTime
        dateTime.setMutable(false);

//结果：2017-01-05 12:34:23
        String dateStr = dateTime.toString();
    }

    @Test
    public void testChineseDate() {
        ChineseDate date = new ChineseDate(DateUtil.parseDate("2020-01-25"));
// 一月
        date.getChineseMonth();
// 正月
        date.getChineseMonthName();
// 初一
        date.getChineseDay();
// 庚子
        date.getCyclical();
// 鼠
        date.getChineseZodiac();
// 春节
        date.getFestivals();
// 庚子鼠年 正月初一
        Console.log(date.toString());
    }


}
