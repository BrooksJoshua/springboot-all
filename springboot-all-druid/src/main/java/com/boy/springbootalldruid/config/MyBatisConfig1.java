package com.boy.springbootalldruid.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.boy.springbootalldruid.mapper1",
        sqlSessionFactoryRef = "sqlSessionFactory1",
        sqlSessionTemplateRef = "sqlSessionTemplate1")
public class MyBatisConfig1 {

    @Resource(name = "dsOne")
    DataSource dsOne;

    @Bean
    SqlSessionFactory sqlSessionFactory1(){

        SqlSessionFactory sqlSessionFactory=null;

        try{
            SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
            bean.setDataSource(dsOne);
            sqlSessionFactory = bean.getObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return sqlSessionFactory;


    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate1(){

        return new SqlSessionTemplate(sqlSessionFactory1());
    }






}
