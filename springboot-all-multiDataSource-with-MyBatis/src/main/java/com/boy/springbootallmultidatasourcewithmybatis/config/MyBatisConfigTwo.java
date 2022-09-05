package com.boy.springbootallmultidatasourcewithmybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-04 23:55
 */
@Configuration
@MapperScan(basePackages = {"com.boy.springbootallmultidatasourcewithmybatis.mapper2"}, sqlSessionFactoryRef = "ssfTwo", sqlSessionTemplateRef = "sstTwo")
public class MyBatisConfigTwo {
    @Autowired
    @Qualifier("dsTwo")
    DataSource ds;

    @Bean
    SqlSessionFactory ssfTwo(){
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(ds);
        SqlSessionFactory ssf  = null;
        try {
            ssf = ssfb.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssf;
    }


    @Bean
    SqlSessionTemplate sstTwo(){
        return new SqlSessionTemplate(ssfTwo());
    }
}
