package com.boy.springbootallpersistencelayer;

import com.boy.springbootallpersistencelayer.entity.User;
import com.boy.springbootallpersistencelayer.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAllPersistenceLayerApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void test() {
        User user = new User();
        user.setName("Brad Pitt");
        user.setAddress("US");
        int add = userService.addMultiDatasource(user);
        System.out.println("add = " + add);
    }


    /*
    @Test
    void test() {
        User user = new User();
        user.setName("刘德华");
        user.setAddress("HongKong");
        int add = userService.add(user);
        System.out.println("add = " + add);
    }

    @Test
    void test2() {
        int updated = userService.updateById("AndyLau", 1);
        System.out.println("updated = " + updated);
    }

    @Test
    void test3() {
        int deleted = userService.deleteById("2");
        System.out.println("deleted = " + deleted);
    }

    @Test
    void test4() {
        System.out.println("userService.getAll() = " + userService.getAll());
        System.out.println("userService.getAll2() = " + userService.getAll2());
    }

     */

}
