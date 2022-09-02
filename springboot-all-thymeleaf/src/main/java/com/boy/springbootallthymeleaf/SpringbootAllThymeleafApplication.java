package com.boy.springbootallthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringbootAllThymeleafApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAllThymeleafApplication.class, args);
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*
         *  addResourceHandler: 图片访问路径
         *  addResourceLocations: 本地路径
         */
        registry.addResourceHandler("/res/**").addResourceLocations("file:E:/res/");
    }
}
