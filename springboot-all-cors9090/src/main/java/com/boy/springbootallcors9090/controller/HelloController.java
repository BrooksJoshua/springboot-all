package com.boy.springbootallcors9090.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by Joshua.H.Brooks on 2020.6月.15.18.08
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    //@CrossOrigin( value = "http://localhost:9091")
    public String hello() {
        return "hello";
    }
    @PostMapping("/hello")
    public String hello2() {
        return "post hello";
    }
}