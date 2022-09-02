package com.boy.springbootallannotationdemos.config;

import com.boy.springbootallannotationdemos.entity.Author;
import com.boy.springbootallannotationdemos.entity.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-19 10:09
 */
@Configuration // 被@Configuration注解修饰的类会被增强：
//@Component
public class MyConfig {
    @Bean
    Author author(){
        return new Author();
    }

    @Bean
    Book book(){
        return new Book(author());
    }
}
