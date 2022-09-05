package com.boy.springbootallmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.boy.springbootallmybatis.mapper"})
public class SpringbootAllMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAllMybatisApplication.class, args);
    }
}
