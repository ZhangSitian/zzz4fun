package com.zzz.fun.controllers;

import com.zzz.fun.domain.DemoObj;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class DemoRestController {

    @ResponseBody
    @RequestMapping(value = "/getJson",produces = MediaType.APPLICATION_JSON_VALUE )
    public DemoObj passObj(DemoObj obj) {
        return obj;
    }

    @RequestMapping(value = "/getXml",produces = MediaType.APPLICATION_XML_VALUE)
    public DemoObj passXml(DemoObj obj) {
        return obj;
    }


}

