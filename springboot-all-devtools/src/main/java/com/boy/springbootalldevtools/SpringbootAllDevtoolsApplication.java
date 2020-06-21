package com.boy.springbootalldevtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootAllDevtoolsApplication {

    public static void main(String[] args) {


        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(SpringbootAllDevtoolsApplication.class, args);
    }

}
