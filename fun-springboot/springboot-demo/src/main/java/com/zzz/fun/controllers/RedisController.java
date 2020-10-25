package com.zzz.fun.controllers;

import com.zzz.fun.common.RedisEnum;
import com.zzz.fun.common.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisManager<String> redisManager;

    @RequestMapping("/get")
    public Object redisGet(@RequestParam("key") String key) {
        return redisManager.get(RedisEnum.MINUTES_1, key);
    }

    @RequestMapping("/set")
    public Object redisSet(String key, String value) {
        redisManager.set(RedisEnum.MINUTES_1, key,value);
        return 1;
    }

}
