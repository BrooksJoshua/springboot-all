package com.boy.springbootallcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootAllCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAllCacheApplication.class, args);
    }

}
