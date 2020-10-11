package com.zzz.fun.controllers;

import com.alibaba.fastjson.JSON;
import com.zzz.fun.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class DemoController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String success() {
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        System.out.println(new String(file.getBytes(), StandardCharsets.UTF_8));
        return "ok";
    }

    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public String index(HttpServletRequest request) {
        return "url:" + request.getRequestURI() + " can access";
    }

    @RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String demoPathVar(@PathVariable String str, HttpServletRequest request) {
        return "url:" + request.getRequestURI() + " can access,str:" + str;
    }

    @ResponseBody
    @RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
    public String passRequestParam(HttpServletRequest request) {
        return "url:" + request.getRequestURI() + " can access";
    }

    @ResponseBody
    @RequestMapping(value = "/obj", produces = "application/json;charset=UTF-8")
    public String passObj(DemoObj obj, HttpServletRequest request) {
        return "url:" + request.getRequestURI() + " can access," + JSON.toJSONString(obj);
    }


    @RequestMapping(value = {"/name", "/name1"}, produces = "text/plain;charset=UTF-8")
    public @ResponseBody
    String remove(HttpServletRequest request) {
        return "url:" + request.getRequestURI() + " can access";
    }

    @RequestMapping(value = "exception")
    @ResponseBody
    public String exception(@ModelAttribute("msg") String msg, HttpServletRequest request) {
        throw new IllegalArgumentException("参数有误：" + msg);
    }


    @RequestMapping(value = "/converter", produces = "application/x-zzz;charset=UTF-8")
    @ResponseBody
    public DemoObj converter(@RequestBody DemoObj demoObj) {
        System.out.println(JSON.toJSONString(demoObj));
        return demoObj;
    }

}

