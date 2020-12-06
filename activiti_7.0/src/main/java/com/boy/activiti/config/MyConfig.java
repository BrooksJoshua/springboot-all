package com.boy.activiti.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * created by Joshua.H.Brooks on 2020.12月.05.20.22
 */
@Configuration
public class MyConfig {
    private static final Logger logger = LoggerFactory.getLogger(MyConfig.class);

    @Bean
    public ProcessEngineConfiguration getProcessEngineConfiguration(){
        StandaloneProcessEngineConfiguration standaloneProcessEngineConfiguration = new StandaloneProcessEngineConfiguration();
        standaloneProcessEngineConfiguration.setDataSource(getDataSource());
        //standaloneProcessEngineConfiguration.setDatabaseSchemaUpdate("true");
        return standaloneProcessEngineConfiguration;
    }


    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource getDataSource(){
        DruidDataSource dataSource = (DruidDataSource) DataSourceBuilder.create().type(DruidDataSource.class).build();
        return dataSource;
    }


    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource authDataSource) throws IOException {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(authDataSource);
        //sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mappers/**/*.xml"));
        try {
            return  sessionFactory.getObject();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(),e);
        }
        return null;
    }
    
}
