package com.zzz.fun.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
public class SseController {

    @RequestMapping(value = "/push", produces = "text/event-stream;charset=UTF-8")
    public @ResponseBody
    String push() throws InterruptedException {
        Random r = new Random();
        TimeUnit.SECONDS.sleep(5L);
        return "url:" + r.nextInt() + "\n\n";
    }

}

