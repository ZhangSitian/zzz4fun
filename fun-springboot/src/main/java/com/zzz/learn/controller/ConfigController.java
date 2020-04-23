package com.zzz.learn.controller;

import com.zzz.learn.common.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigController {
    @Autowired
    private Environment env;
    /**
     * 通过配置的key获取value<br>
     * {key:.+}是为了解决通过url参数访问的时候小数点丢失的问题
     * @param key
     * @return
     */
    @RequestMapping("/config/{key:.+}")
    Object getConfig(@PathVariable String key) {
        return env.getProperty(key);
    }

    /**
     * 读取application.properties中的配置值
     */
    @Value("${server.context-path}")
    private String contextPath;
    @RequestMapping("/config/contextpath")
    Object getConfigContextPath() {
        return contextPath;
    }

    @Autowired
    private Config config;
    @RequestMapping("/config")
    Object queryConfig() {
        return config;
    }

    @RequestMapping("/config/classpath")
    Object getClasspath() {
        return System.getProperty("java.class.path");
    }

}
