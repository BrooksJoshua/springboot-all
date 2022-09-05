package com.boy.springbootalldynamicdatasource.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-03 13:17
 */

@Configuration
@EnableSwagger2
@EnableKnife4j
public class Knife4JConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.boy.springbootalldynamicdatasource.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("Knife4J在线API接口文档")
                .contact(new Contact("钟", "https://52zhongneng.cn/", "xxx@qq.com"))
                .version("v3.0.3")
                .title("Knife4J在线API接口文档")
                .build();
    }
}
