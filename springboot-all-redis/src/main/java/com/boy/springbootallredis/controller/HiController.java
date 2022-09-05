package com.boy.springbootallredis.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-05 14:02
 */
@RestController
public class HiController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("get")
    String get(HttpSession session){
        String name = (String) session.getAttribute("name");
        return name+port;
    }

    @GetMapping("set")
    String set(HttpSession session){
        session.setAttribute("name", "Joshua.H.Brooks");
        return String.valueOf(port);
    }
}
