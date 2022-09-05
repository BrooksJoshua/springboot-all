package com.boy.springbootallredis.config;

import com.boy.springbootallredis.interceptor.MyIdempotenceInteceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-05 16:02
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    MyIdempotenceInteceptor myIdempotenceInteceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myIdempotenceInteceptor).addPathPatterns("/**").excludePathPatterns("/login");
    }
}
