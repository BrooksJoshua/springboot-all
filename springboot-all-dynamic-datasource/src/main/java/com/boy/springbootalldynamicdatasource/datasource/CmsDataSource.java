package com.boy.springbootalldynamicdatasource.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


public class CmsDataSource extends DynamicDataSource {


    private static CmsDataSource cmsDataSource;

    private static Map<Object, Object> dataSources = new HashMap<Object, Object>();

    public CmsDataSource(Environment env) throws Exception {
        cmsDataSource = this;
        CmsDataSource.initCustomDataSources(env);

    }

    public static DataSource initDataSource(Map<String, Object> dsMap) throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName((String)dsMap.get("driver-class-name"));
        dataSource.setUsername((String)dsMap.get("username"));
        dataSource.setPassword((String)dsMap.get("password"));
        dataSource.setUrl((String)dsMap.get("url"));
        dataSource.setInitialSize(dsMap.get("initialSize")!=null?Integer.parseInt(dsMap.get("initialSize").toString()):5);
        dataSource.setMinIdle(dsMap.get("minIdle")!=null?Integer.parseInt(dsMap.get("minIdle").toString()):1);
        dataSource.setMaxActive(dsMap.get("maxActive")!=null?Integer.parseInt(dsMap.get("maxActive").toString()):100);
        dataSource.setDefaultAutoCommit(false);
        if(dsMap.get("dsname")!=null && !dsMap.get("dsname").equals("")){
            dataSources.put(dsMap.get("dsname"),dataSource);
        }
        // 启用监控统计功能
        dataSource.setFilters("stat");
        dataSource.addFilters("wall");
        // for mysql  dataSource.setPoolPreparedStatements(false);
        return dataSource;
    }


    /**
     * 初始化更多数据源
     *
     */
    private static void initCustomDataSources(Environment env) throws Exception {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源


        // RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
        //String dsPrefixs = propertyResolver.getProperty("names");
        String dsPrefixs = env.getProperty("custom.datasource.names");
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
            //Map<String, Object> dsMap = propertyResolver(dsPrefix + ".");
            Map<String, Object> dsMap =
                    Binder.get(env).bind("custom.datasource."+dsPrefix,Map.class).get();
            DataSource ds = initDataSource(dsMap);
            cmsDataSource.getDataSources().put(dsPrefix, ds);
        }
        cmsDataSource.setTargetDataSources(cmsDataSource.getDataSources(), (DataSource)cmsDataSource.getDataSources().get("default"));
        cmsDataSource.init();
    }




    @Override
    public void afterPropertiesSet() {

    }

    public void init() {
        super.afterPropertiesSet();
    }

    public void put(Object name, Object dataSource) {
        dataSources.put(name, dataSource);
    }

    public void remove(Object name){
        dataSources.remove(name);
    }

    public static Map<Object, Object> getDataSources() {
        return dataSources;
    }
    public static DruidDataSource  getDataSources(String dsname) {
        //需要先判断数据源列表中是否存在已经注册的数据源
        for (Map.Entry entry : dataSources.entrySet()) {
            DruidDataSource ds=(DruidDataSource)entry.getValue();
            if(entry.getKey().equals(dsname)){
                return ds;
            }
        }
        return null;
    }

    public static void reflash(){
        cmsDataSource.init();
    }
}
