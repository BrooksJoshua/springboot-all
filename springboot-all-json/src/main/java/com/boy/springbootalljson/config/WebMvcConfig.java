package com.boy.springbootalljson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-08-24 11:39
 */
@Configuration
public class WebMvcConfig {
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy年MM月dd日 HH时:mm分:ss秒"));
        return om;
    }
}
