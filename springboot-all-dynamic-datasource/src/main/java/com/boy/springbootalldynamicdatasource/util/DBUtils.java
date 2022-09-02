package com.boy.springbootalldynamicdatasource.util;

import com.boy.springbootalldynamicdatasource.bean.DBType;
import com.boy.springbootalldynamicdatasource.bean.DataSource;
import com.boy.springbootalldynamicdatasource.datasource.JdbcTemplateManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import static com.boy.springbootalldynamicdatasource.bean.DBType.IMPALA;

/**
 * @Author: ZHILI LIU
 * @Date: 2019-4-12 8:21
 * @Description:
 **/
public class DBUtils {
    private static Logger LOG = LoggerFactory.getLogger(DBUtils.class);

    // 定义变量
    private Connection conn = null;
    // 大多数情况下用preparedstatement替代statement
    private PreparedStatement ps = null;
    private Statement statement;
    private ResultSet rs = null;

    // 连接数据库的参数
    private String code = "";
    private String url = "";
    private String username = "";
    private String driver = "";
    private String passwd = "";
    private Integer initSize = 1;// 初始化连接池大小
    private Integer minIdle = 0;// 最小空闲连接数
    private Integer maxActive = 50;// 最大连接会话数目
    private Integer maxWait = 120000;// 连接等待超时的时间
    private Integer timeBetweenEvictionRunMillis = 120000;// 检测间隔
    private Integer minEvictableIdleTimeMillis = 1800000;// 连接在池中最小生存的时间
    private Integer queryTimeout = 120000;// SQL查询超时时间

    private static DataSource db;


    public DBUtils(DataSource database) throws Exception {
        init(database);
    }

    public DBUtils() throws Exception {

    }

    public void init(DataSource database) throws Exception {
        db = database;
        try {
            driver = database.getDbType().getDriverClass();
            url = database.getUrl();
//            if(DBType.MYSQL.getCode().equals(database.getDbType().getCode()) && !url.contains("serverTimezone")){
//                url+="&serverTimezone=Asia/Shanghai";
//            }
            username = database.getUsername();
            passwd = database.getPassword(); //解密
            initSize = database.getInitSize();
            maxActive = database.getMaxActive();
            minIdle = database.getMinIdle();
            timeBetweenEvictionRunMillis = database.getTimeBetweenEvictionRunMillis();
            minEvictableIdleTimeMillis = database.getMinEvictableIdleTimeMillis();
            queryTimeout = database.getQueryTimeout();
            maxWait = database.getMaxWait();
            code = database.getDsrcCode();
            Class.forName(driver);// 指定连接类型
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
    }


    public Connection getConnection() throws Exception {
        try {
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (Exception e) {
            try {
                conn = DriverManager.getConnection(url, username, passwd);
            } catch (Exception e1) {
                //e1.printStackTrace();
                LOG.error(e1.getMessage());
                throw e1;
            }
        }
        return conn;
    }

    public Connection getImpalaConnection(Integer maxWait,Integer queryTimeout) throws Exception {
        try {
            DriverManager.setLoginTimeout(maxWait/1000);
            Integer socketTimeout=queryTimeout+5;
            if (!StringUtils.endsWith(url,";")) {
                url+= ";socketTimeout="+socketTimeout;
            }else {
                url+= "socketTimeout="+socketTimeout;
            }
            conn = DriverManager.getConnection(url, username, passwd);
        } catch (Exception e) {
            //e1.printStackTrace();
            LOG.error(e.getMessage());
            throw e;
        }
        return conn;
    }


    /**
     * 获取某信息的集合
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public ResultSet getResultSetInfo(String sql) throws Exception {
        ResultSet rs = null;
        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            //e.printStackTrace();
            LOG.error(e.getMessage());
            throw e;
        } finally {
            //conn.close();
        }
    }


    public void colseAll() {
        colseResource(conn, statement, rs);
    }


    /**
     * 释放资源
     *
     * @param conn
     * @param statement
     * @param rs
     */
    public void colseResource(Connection conn, Statement statement, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }

        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }

        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }

        }
    }


    public ResultSet getResultSetInfoTest(String id, String sql, DBType dbType, Integer queryTimeout, Integer maxWait) throws Exception {
        ResultSet rs = null;
        try {
            JdbcTemplate template = JdbcTemplateManager.getJdbcTemplate(id);
            if (null == template){
                //JDBC链接未获取,抛出异常
                throw new Exception("JDBC链接未获取");
            }

            if (IMPALA.equals(dbType)){
                conn = getImpalaConnection(maxWait,queryTimeout);
                statement = conn.createStatement();
                statement.setQueryTimeout(queryTimeout);
            }else {
                conn = template.getDataSource().getConnection();
                statement = conn.createStatement();
            }
            rs = statement.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            //e.printStackTrace();
            LOG.error(e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {}
}
