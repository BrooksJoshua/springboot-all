package com.boy.springbootalljson.controller;

import com.boy.springbootalljson.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-24 11:38
 */
@RestController
public class UserController {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public Object hi(){
        User user = new User();
        user.setId("1");
        user.setAge("18");
        user.setName("Joshua");
        user.setSlogan("Nothing is impossible~");
        user.setGrade("9");
        user.setAddress("广州");
        user.setBirthday(new Date());
        return user;
    }
}
