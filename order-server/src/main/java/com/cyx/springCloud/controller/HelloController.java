package com.cyx.springCloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HelloController {

    @Value("${hello}")
    private String hello;

    @RequestMapping("/hello")
    public String hello() {
        return "order-server:" + hello;
    }

}
