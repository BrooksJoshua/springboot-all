package com.boy.springbootallproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@SpringBootApplication
public class SpringbootAllPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAllPropertiesApplication.class, args);
    }

//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
//        return new PropertySourcesPlaceholderConfigurer();
//    }

}
