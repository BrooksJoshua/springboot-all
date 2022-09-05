package com.boy.springbootallglobalexception.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-03 17:29
 */
@Controller
@RequestMapping("greeting")
public class HiController {
    @RequestMapping(value = "hi",method = RequestMethod.GET)
    public String hi(){
        int i = 1/0;
        return "hi";
    }
}
