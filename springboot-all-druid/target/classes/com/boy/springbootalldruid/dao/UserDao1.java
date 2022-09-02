package com.boy.springbootalldruid.dao;

import com.boy.springbootalldruid.DSRepo1;
import com.boy.springbootalldruid.entity.User;

import java.util.List;

/**
 * created by Joshua.H.Brooks on 2020.6月.13.17.05
 */
@DSRepo1
public interface UserDao1 {
    List<User> getAllUser();
}
