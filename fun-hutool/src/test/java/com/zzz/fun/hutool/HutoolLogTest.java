package com.zzz.fun.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.dialect.commons.ApacheCommonsLogFactory;
import cn.hutool.log.dialect.console.ConsoleLogFactory;
import cn.hutool.log.dialect.jdk.JdkLogFactory;
import cn.hutool.log.level.Level;
import org.junit.Test;

public class HutoolLogTest {

    @Test
    public void logUtil1() {
        Log log = LogFactory.get();

        log.debug("This is {} log", Level.DEBUG);
        log.info("This is {} log", Level.INFO);
        log.warn("This is {} log", Level.WARN);

        Exception e = new Exception("test Exception");
        log.error(e, "This is {} log", Level.ERROR);
    }

    @Test
    public void logUtil() {
        // 自动选择日志实现
        Log log = LogFactory.get();
        log.debug("This is {} log", "default");
        Console.log("----------------------------------------------------------------------");

//自定义日志实现为Apache Commons Logging
        LogFactory.setCurrentLogFactory(new ApacheCommonsLogFactory());
        log.debug("This is {} log", "custom apache commons logging");
        Console.log("----------------------------------------------------------------------");

//自定义日志实现为JDK Logging
        LogFactory.setCurrentLogFactory(new JdkLogFactory());
        log.info("This is {} log", "custom jdk logging");
        Console.log("----------------------------------------------------------------------");

//自定义日志实现为Console Logging
        LogFactory.setCurrentLogFactory(new ConsoleLogFactory());
        log.info("This is {} log", "custom Console");
        Console.log("----------------------------------------------------------------------");
    }



}
