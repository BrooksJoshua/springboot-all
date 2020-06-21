package com.boy.springbootalldruid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * created by Joshua.H.Brooks on 2020.6月.13.16.46
 */
@Configuration
public class DataSourceConcig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource getDSone(){
        DruidDataSource dsOne = DruidDataSourceBuilder.create().build();
        return  dsOne;

    }
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.two")
    public DataSource getDStwo(){
        DruidDataSource dsTwo = DruidDataSourceBuilder.create().build();
        return  dsTwo;

    }




}
