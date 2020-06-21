package com.boy.springbootalldruid;

import com.boy.springbootalldruid.entity.User;
import com.boy.springbootalldruid.mapper1.UserMapper1;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public  class SpringbootAllDruidApplicationTests {

  @Autowired
  UserMapper1 userMapper1;

  //@Autowired
  //UserMapper2 userMapper2;


  @Test
  public void contextLoads() {


    List<User> users1=userMapper1.findAllUsers();
    System.out.println(users1);
    //List<User> users2=userMapper2.findAllUsers();
    //System.out.println(users2);






  }
}
