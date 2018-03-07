package com.dyq.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello,spring boot!";
    }


    @GetMapping("/")
    @ResponseBody
    public String root(){
        return "hello,root,spring boot!";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
