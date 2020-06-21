package com.boy.springbootalldruid.controller;

import com.boy.springbootalldruid.entity.User;
import com.boy.springbootalldruid.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * created by Joshua.H.Brooks on 2020.6月.13.17.54
 */
@Controller
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/user1")
    @ResponseBody
    List<User> findUsers(){


        return null;

    }




}
