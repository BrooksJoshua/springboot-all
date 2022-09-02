package com.boy.springbootalldruid.service;

import com.boy.springbootalldruid.dao.UserDao1;
import com.boy.springbootalldruid.dao.UserDao2;
import com.boy.springbootalldruid.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by Joshua.H.Brooks on 2020.6月.13.17.49
 */
@Service
public class UserServiceImpl {
    @Autowired
    UserDao1 userDao1;
    @Autowired
    UserDao2 userDao2;

    List<User> getAllUser1(){
       return userDao1.getAllUser();
    }

    List<User> getAllUser2(){
        return userDao2.getAllUser();
    }





}
