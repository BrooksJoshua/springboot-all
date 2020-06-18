package com.boy.springbootalldruid.config;

import com.boy.springbootalldruid.DSRepo1;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * created by Joshua.H.Brooks on 2020.6月.13.17.04
 */

@MapperScan(basePackages = "com.boy.springbootalldruid.dao",
        sqlSessionFactoryRef = "sqlSessionFactory1",
        sqlSessionTemplateRef = "sqlSessionTemplate1",
        annotationClass = DSRepo1.class)
@Configuration
public class MyBatisConfig {

    @Resource(name="getDSone")
    DataSource dsOne;

    @Bean("getSqlSessionFactory1")
    SqlSessionFactory sqlSessionFactory1(){
        SqlSessionFactory ssf1=null;
        try{
            SqlSessionFactoryBean ssfb1=new SqlSessionFactoryBean();
            ssfb1.setDataSource(dsOne);
            ssf1 = (SqlSessionFactory) ssfb1.getObject();
        }catch (Exception e){
            e.printStackTrace();
        }
            return ssf1;
    }

    @Bean(name="sqlSessionTemplate1")
    SqlSessionTemplate sqlSessionTemplate1(){
        return new SqlSessionTemplate(sqlSessionFactory1());
    }



}
