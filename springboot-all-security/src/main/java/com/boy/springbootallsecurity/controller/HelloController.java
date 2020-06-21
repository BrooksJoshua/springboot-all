package com.boy.springbootallsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hi")
    @ResponseBody
    public String hello(){ //
        return "hello";
    }


    @GetMapping("admin/hi")
    @ResponseBody
    public String helloAdmin(){ //
        return "hi, Admin";
    }



    @GetMapping("user/hi")
    @ResponseBody
    public String helloUser(){ //
        return "hi, User";
    }


    @GetMapping("login")
    @ResponseBody
    public String login(){ //
        return "please login first !";
    }




}
