package com.boy.springbootalldynamicdatasource.datasource;

import com.boy.springbootalldynamicdatasource.util.DataSourceUtil;
import com.boy.springbootalldynamicdatasource.util.SpringContextHolder;
import com.google.common.collect.Maps;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.Set;

public class JdbcTemplateManager {
    private static final Map<String, JdbcTemplate> JDBC_TEMPLATE_MAP = Maps.newConcurrentMap();
    private static final Map<String, String> BEAN_NAMES = Maps.newConcurrentMap();

    public static JdbcTemplate getJdbcTemplate(String id) {
        if(id == null){
            return null;
        }
        return JDBC_TEMPLATE_MAP.get(id);
    }


    public static void put(String id, String beanName) {
        JdbcTemplate jdbcTemplate = SpringContextHolder.getBean(DataSourceUtil.generateJdbcTemplateBeanName(beanName));
        if (jdbcTemplate != null) {
            BEAN_NAMES.put(id, beanName);
            JDBC_TEMPLATE_MAP.put(id, jdbcTemplate);
        }
    }

    public static void remove(String id) {
        if (JDBC_TEMPLATE_MAP.containsKey(id)) {
            JDBC_TEMPLATE_MAP.remove(id);
            BEAN_NAMES.remove(id);
        }
    }

    public static Set<Map.Entry<String, String>> getAllBean() {
        return BEAN_NAMES.entrySet();
    }
}
