package com.boy.springbootalldynamicdatasource.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataSourceUtil {
    private static Logger logger = LoggerFactory.getLogger(DataSourceUtil.class);

    private static DefaultListableBeanFactory beanFactory;

    public static String generateDataSourceBeanName(String id){
        return "dataSource_" + id;
    }

    public static String generateJdbcTemplateBeanName(String id){
        return "jdbcTemplate_" + id;
    }

    public static void addDataSource(String id, Map<String, Object> parameterMap, Integer queryTimeout) {
        if (parameterMap == null || parameterMap.isEmpty() || StringUtils.isBlank(id)) {
            throw new IllegalArgumentException();
        }
        String dataSourceBeanName = generateDataSourceBeanName(id);
        String jdbcTemplateBeanName = generateJdbcTemplateBeanName(id);

        addBean(DruidDataSource.class, dataSourceBeanName, parameterMap.entrySet(), "init", "close");

        DataSource dataSource;
        try {
            dataSource = SpringContextHolder.getBean(dataSourceBeanName);
        } catch (Exception e) {
            logger.warn("connection database failed, remove dataSource bean!");
            removeBean(dataSourceBeanName);
            throw e;
        }

        Map<String, Object> properties = new HashMap<String, Object>(1);
        properties.put("dataSource", dataSource);
        if(queryTimeout != null && queryTimeout > 0){
            properties.put("queryTimeout", queryTimeout);
        }
        addBean(JdbcTemplate.class, jdbcTemplateBeanName, properties.entrySet(), null, null);
    }

    private static void addBean(Class<?> type, String beanName, Set<Map.Entry<String, Object>> properties, String initMethodName, String destroyMethodName) {
        DefaultListableBeanFactory beanFactory = getBeanFactory();
        if(beanFactory.containsBean(beanName)){
            removeBean(beanName);
        }

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(type);
        if(StringUtils.isNotBlank(initMethodName)){
            logger.debug("{} setInitMethodName({})", beanName, initMethodName);
            beanDefinitionBuilder.setInitMethodName(initMethodName);
        }
        if(StringUtils.isNotBlank(destroyMethodName)){
            logger.debug("{} setDestroyMethodName({})", beanName, destroyMethodName);
            beanDefinitionBuilder.setDestroyMethodName(destroyMethodName);
        }
        if(!CollectionUtils.isEmpty(properties)){
            logger.debug("{} add properties, properties={}", beanName, properties);
            for (Map.Entry<String, Object> e : properties) {
                if(e.getValue() != null){
                    beanDefinitionBuilder.addPropertyValue(e.getKey(), e.getValue());
                }
            }
        }
        logger.info("Add {} to bean container.", beanName);
        beanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }


    private static DefaultListableBeanFactory getBeanFactory() {
        if (beanFactory != null) {
            return beanFactory;
        }
        ConfigurableApplicationContext applicationContext = (ConfigurableApplicationContext) SpringContextHolder.getApplicationContext();
        beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        return beanFactory;
    }

    /**jdbcTemplate_bean_of_datasource
     * 销毁dataSource
     *
     * @param beanName
     */
    private static void removeBean(String beanName) {
        DefaultListableBeanFactory beanFactory = getBeanFactory();
        if (beanFactory.containsBean(beanName)) {
            beanFactory.destroySingleton(beanName);
            beanFactory.destroyBean(beanName);
            beanFactory.removeBeanDefinition(beanName);
            logger.info("destroy bean " + beanName);
        } else {
            logger.info("No {} defined in bean container.", beanName);
        }
    }

    /**
     * 删除 dataSource
     * @param id
     */
    public static void removeDataSource(String id) {
        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException();
        }
        String dataSourceBeanName = generateDataSourceBeanName(id);
        String jdbcTemplateBeanName = generateJdbcTemplateBeanName(id);
        removeBean(dataSourceBeanName);
        removeBean(jdbcTemplateBeanName);
    }
}
