package com.boy.springbootallcors9091.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Joshua.H.Brooks on 2020.6月.15.18.13
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}