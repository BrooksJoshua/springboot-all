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
@MapperScan(basePackages = "com.boy.springbootalldruid.mapper2",
        sqlSessionFactoryRef = "sqlSessionFactory2",
        sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MyBatisConfig2 {

    @Resource(name = "dsTwo")
    DataSource dsTwo;

    @Bean
    SqlSessionFactory sqlSessionFactory2(){

        SqlSessionFactory sqlSessionFactory=null;

        try{
            SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
            bean.setDataSource(dsTwo);
            sqlSessionFactory = bean.getObject();
        }catch(Exception e){
            e.printStackTrace();
        }
        return sqlSessionFactory;


    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate2(){

        return new SqlSessionTemplate(sqlSessionFactory2());
    }






}
