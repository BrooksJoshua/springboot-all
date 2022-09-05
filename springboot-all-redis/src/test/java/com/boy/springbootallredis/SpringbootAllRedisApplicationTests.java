package com.boy.springbootallredis;

import com.boy.springbootallredis.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class SpringbootAllRedisApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void test() {
        User user = new User();
        user.setName("Josh");
        user.setAddress("China");
        ValueOperations vo = redisTemplate.opsForValue();
        vo.set("u1",user);
        System.out.println("vo.get(\"u1\") = " + vo.get("u1"));
    }


    @Test
    void test2() {
        User user = new User();
        user.setName("Josh");
        user.setAddress("China");
        ValueOperations vo = stringRedisTemplate.opsForValue();
        vo.set("u2",user.toString());
        System.out.println("vo.get(\"u2\") = " + vo.get("u2"));
    }




}
