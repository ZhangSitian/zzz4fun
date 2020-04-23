package com.zzz.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 自定义错误页面覆盖spring boot中的错误页面
 * @author yinjihuan
 *
 */
@Controller
public class ErrorController {
    @GetMapping("/400")
    public String badRequest() {
        return "error/400";
    }
    @GetMapping("/404")
    public String notFound() {
        return "error/404";
    }
    @GetMapping("/500")
    public String serverError() {
        return "error/500";
    }
}
