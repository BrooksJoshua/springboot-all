package com.boy.springbootallhttps.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 23:01
 */
@RestController
public class HiController {
    @GetMapping("/hi")
    public String hi(){
        return "Hi";
    }
}
