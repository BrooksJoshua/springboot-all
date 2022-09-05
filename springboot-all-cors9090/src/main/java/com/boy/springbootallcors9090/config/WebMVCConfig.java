package com.boy.springbootallcors9090.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

/**
 * created by Joshua.H.Brooks on 2020.6月.15.18.49
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    /**
     * 方式一
     *
     * @Override public void addCorsMappings(CorsRegistry registry) {
     * registry.addMapping("/**")
     * .allowedOrigins("http://localhost:9091")
     * .allowedMethods("*")
     * .allowedHeaders("*")
     * .maxAge(1800).allowCredentials(Boolean.TRUE);
     * }
     */
    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.addAllowedOrigin("http://localhost:9091");
        cfg.addAllowedHeader("*");
        cfg.addAllowedMethod("*");
        //cfg.addAllowedMethod(HttpMethod.GET);
        //cfg.addAllowedMethod(HttpMethod.PUT);
        cfg.setAllowCredentials(Boolean.TRUE);
        cfg.setMaxAge(Duration.ofSeconds(1800));
        source.registerCorsConfiguration("/**", cfg);//对那些接口使用哪个cors配置
        return new CorsFilter(source);
    }
}
