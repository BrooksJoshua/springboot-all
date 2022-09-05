package com.boy.springbootallmybatis;

import com.boy.springbootallmybatis.mapper.UserMapper;
import com.boy.springbootallmybatis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAllMybatisApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Test
    void test() {
        User user = userMapper.getUserById(1);
        System.out.println("user = " + user);
    }
    @Test
    void test2() {
        int rowAffected = userMapper.updateUserById(1,"布拉德皮特");
        System.out.println("rowAffected = " + rowAffected); 
    }
    @Test
    void test3() {
        int rowAffected = userMapper.deleteUserById(1);
        System.out.println("rowAffected = " + rowAffected);
    }
    @Test
    void test4() {
        User user = new User();
        user.setName("Angelina Julia");
        user.setAddress("Brooklyn");
        int  rowAffected= userMapper.insert(user);
        System.out.println("user = " + user);
    }

    @Test
    void test5() {
        User user = userMapper.getUserByIdWithXML(4);
        System.out.println("user = " + user);
    }
}
