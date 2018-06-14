package com.yipzale.springclouddemo.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class IndexController {
    @Value("${name:unknow}")
    private String name;

    @RequestMapping("/hello")
    public String hello() {

        return "Hello " + this.name + "!";
    }
}
