package com.boy.springbootallsecurity.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class HelloService {


    @PreAuthorize("hasRole('admin')")  //只有具有该角色的用户才能调用该方法
    public String hi1(){
        return "admin only";
    }

    //注意, 该注解的值必须有ROLE_前缀
    @Secured("ROLE_user,ROE_admin")  // 有admin或者user角色权限的用户都可以调用, 但是该注解不能满足 "同时具备这两种权限才能访问的" 业务需求
    public String hi2(){
        return "role_user/role_admin";
    }


    @PreAuthorize("hasAnyRole('user','admin')")  //只有具有该角色的用户才能调用该方法
    public String hi3(){
        return "any role";
    }



}
