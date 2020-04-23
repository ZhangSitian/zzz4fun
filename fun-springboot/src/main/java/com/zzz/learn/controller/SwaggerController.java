package com.zzz.learn.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restapi")
public class SwaggerController {
    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户接口", notes = "根据用户名获取用户信息")
    Object getUserByName(@ApiParam(required = true, name = "name", value = "姓名") @PathVariable String name) {
        return name;
    }
//    @ApiIgnore // 使用该注解忽略这个API
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有用户接口", notes = "获取所有用户信息")
    Object getUsers() {
        return "success";
    }
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有订单接口", notes = "获取所有订单信息")
    Object getOrders() {
        return "success";
    }
}
