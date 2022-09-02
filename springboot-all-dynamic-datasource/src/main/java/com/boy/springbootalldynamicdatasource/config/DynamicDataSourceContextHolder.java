package com.boy.springbootalldynamicdatasource.config;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-02 00:09
 */
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }

}