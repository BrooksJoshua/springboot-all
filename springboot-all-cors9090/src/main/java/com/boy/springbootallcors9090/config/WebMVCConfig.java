package com.boy.springbootallcors9090.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * created by Joshua.H.Brooks on 2020.6月.15.18.49
 */
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:9091")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
