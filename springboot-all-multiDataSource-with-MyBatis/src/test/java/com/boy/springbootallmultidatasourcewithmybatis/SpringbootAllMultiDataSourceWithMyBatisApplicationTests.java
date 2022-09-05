package com.boy.springbootallmultidatasourcewithmybatis;

import com.boy.springbootallmultidatasourcewithmybatis.entity.User;
import com.boy.springbootallmultidatasourcewithmybatis.mapper1.User1Mapper;
import com.boy.springbootallmultidatasourcewithmybatis.mapper2.User2Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAllMultiDataSourceWithMyBatisApplicationTests {

    @Autowired
    User1Mapper user1Mapper;
    @Autowired
    User2Mapper user2Mapper;

    @Test
    void test() {
        //User u1 = user1Mapper.getById(4);
        User u2 = user2Mapper.getById(1);
        //System.out.println("u1 = " + u1);
        System.out.println("u2 = " + u2);
    }

}
