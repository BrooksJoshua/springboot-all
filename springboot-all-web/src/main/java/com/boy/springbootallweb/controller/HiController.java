package com.boy.springbootallweb.controller;

import com.boy.springbootallweb.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 20:21
 */
@RestController
public class HiController {

    @GetMapping("/hi")
    public String hi(){
        return "Hi";
    }

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < 10_000; i++) {
            users.add(new User("Elo"+i));
        }
        return users;
    }
}
