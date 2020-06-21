package com.boy.springbootallsecurity.controller;

import com.boy.springbootallsecurity.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MethodSecurityController {

    @Autowired
    HelloService helloService;

    @ResponseBody
    @GetMapping("/hi4")
    public String hi4(){
        return helloService.hi1();
    }


    @ResponseBody
    @GetMapping("/hi2")
    public String hi2(){
        return helloService.hi2();
    }

    @ResponseBody
    @GetMapping("/hi3")
    public String hi3(){
        return helloService.hi3();
    }





}
