package com.boy.springbootallcache;


import com.boy.springbootallcache.entity.User;
import com.boy.springbootallcache.service.UserService;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.ArrayList;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringbootAllCacheApplicationTests {

    @Autowired
   UserService userService;

    @Test
    public void contextLoads() {


        User user = userService.getUserById(1);
        User user2 = userService.getUserById(1);
        User user3 = userService.getUserById(2);

        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);

        int i = userService.updateUserById(1);
        User userById = userService.getUserById(1);
        System.out.println("updated? s"+userById);


    }


}
