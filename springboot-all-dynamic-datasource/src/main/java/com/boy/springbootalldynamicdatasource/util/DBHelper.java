package com.boy.springbootalldynamicdatasource.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.fastjson.JSONObject;
import com.boy.springbootalldynamicdatasource.bean.DBType;
import com.boy.springbootalldynamicdatasource.bean.DataSource;
import com.boy.springbootalldynamicdatasource.datasource.JdbcTemplateManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.*;


public class DBHelper {

    private static Logger LOG = LoggerFactory.getLogger(DBHelper.class);

    // 定义原生JDBC变量
    private static Connection conn = null;
    private static PreparedStatement ps = null; //大多数情况使用这个
    private static Statement statement;
    private static ResultSet rs = null;

    // 连接数据库的参数
    private static String code = "";
    private static String url = "";
    private static String username = "";
    private static String driver = "";
    private static String passwd = "";
    private static Integer initSize = 1;// 初始化连接池大小
    private static Integer minIdle = 0;// 最小空闲连接数
    private static Integer maxActive = 50;// 最大连接会话数目
    private static Integer maxWait = 120000;// 连接等待超时的时间
    private static Integer timeBetweenEvictionRunMillis = 120000;// 检测间隔
    private static Integer minEvictableIdleTimeMillis = 1800000;// 连接在池中最小生存的时间
    private static Integer queryTimeout = 120000;// SQL查询超时时间

    private static DataSource db;

    /**
     * 有参构造， 在其中调用init(database);进行数据源初始化。
     * 即 用入参database的druid连接池参数为上述静态成员变量赋值。
     * @param database
     * @throws Exception
     */
    public DBHelper(DataSource database) throws Exception {
        init(database);
    }
    public DBHelper() {
    }
    public static void init(DataSource database) throws Exception {
        db = database;
        try {
            driver = database.getDbType().getDriverClass();
            url = database.getUrl();
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

    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    private static Connection getConnection() throws Exception {
        try {
            conn = DriverManager.getConnection(url, username, passwd);

        } catch (Exception e) {
            //e.printStackTrace();
            LOG.error(e.getMessage());
            throw e;
        }
        return conn;
    }

    /**
     * 获取数据库连接, 指定获取连接的最大等待时间maxWait
     * 从连接池中获取连接的最大等待时间，单位ms，默认-1，即会一直等待下去
     * @param maxWait
     * @return
     * @throws Exception
     */
    private static Connection getConnection(Integer maxWait) throws Exception{
        try {
            DriverManager.setLoginTimeout(maxWait/1000);
            conn = DriverManager.getConnection(url,username,passwd);
        }catch (Exception e){
            LOG.error(e.getMessage());
            throw e;
        }
        return conn;
    }
    /**
     * 获取Impala类型的数据库连接， 指定获取连接的最大等待时间maxWait和查询超时时间queryTimeout。
     * @param maxWait
     * @param queryTimeout
     * @return
     * @throws Exception
     */
    private Connection getImpalaConnection(Integer maxWait,Integer queryTimeout) throws Exception {
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
            LOG.error(e.getMessage());
            throw e;
        }
        return conn;
    }
    /**
     * 获取某信息的集合
     *
     * @param sql
     * @param sql
     * @return
     * @throws Exception
     */
    public ResultSet getResultSetInfo(String sql) throws Exception {
        //ResultSet rs = null;
        try {
            //synchronized (this){
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            //}
            return rs;
        } catch (Exception e) {
            //e.printStackTrace();
            LOG.error(e.getMessage());
            throw e;
        } finally {
            //conn.close();
        }
    }

    /**
     * 执行sql语句， 适用于MySQL和Oracle类型的连接
     * @param sql
     * @param commitFlag 自动提交标签， 如果为true， 则执行完便提交
     * @throws Exception
     * @returnexecuteQuery
     */
    public static boolean executeSql(String sql, Boolean commitFlag) throws Exception {
        boolean b = false;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.execute(sql);
            if (commitFlag) {
                conn.commit();
            }
            b = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    /**
     * 执行Impala类型数据连接的sql语句
     * @param sql
     * @param commitFlag 自动提交标签， 如果为true， 则执行完便提交
     * @return
     * @throws Exception
     */
    public static boolean executeImpalaSql(String sql, Boolean commitFlag) throws Exception {
        boolean b = false;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.execute();
            if (commitFlag) {
                conn.commit();
            }
            b = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    /**
     * 执行Hive类型数据连接的sql语句
     * @param sql
     * @param commitFlag 自动提交标签， 如果为true， 则执行完便提交
     * @return
     * @throws Exception
     */
    public static boolean executeHiveSql(String sql, Boolean commitFlag) throws Exception {
        boolean b = false;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            statement.execute(sql);
            if (commitFlag) {
                conn.commit();
            }
            b = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    /**
     * @param dbType
     * @param sql
     * @autor:JiaLing Li 公共执行jdbc类
     */
    public static boolean getCommonExcute(DBType dbType, String sql) throws Exception {
        boolean flag = true;
        try {
            switch (dbType) {
                case MYSQL:
                    flag = executeSql(sql, false);
                    break;
                case ORACLE:
                    flag = executeSql(sql, false);
                    break;
                case HIVE:
                    flag = executeHiveSql(sql, false);
                    break;
                case IMPALA:
                    flag = executeImpalaSql(sql, false);
                    break;
                case SQLSERVER:
                    flag = executeHiveSql(sql, false);
                    break;
                default:
                    flag = executeSql(sql, false);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
        return flag;
    }
    /**
     * 公共执行jdbc类(commit)
     * 根据传入的数据库类型dbType和sql字符串定位到对应的最终执行逻辑executeSql
     * @param dbType
     * @param sql
     * @return
     * @throws Exception
     */
    public boolean getExcuterResult(DBType dbType, String sql) throws Exception {
        boolean flag = true;
        try {
            switch (dbType) {
                case MYSQL:
                    flag = executeSql(sql, true);
                    break;
                case ORACLE:
                    flag = executeSql(sql, true);
                    break;
                case HIVE:
                    flag = executeHiveSql(sql, true);
                    break;
                case SQLSERVER:
                    flag = executeHiveSql(sql, true);
                    break;
                default:
                    flag = executeSql(sql, true);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
        return flag;

    }
    /**
     * 获取数据源URL
     *
     * @return
     * @autor jialing Li
     */
    public static String getJDBCUrl(DBType dbType, String ip, String port, String serviceName) {
        String jdbcUrl = "";
        switch (dbType) {
            case MYSQL:
//                jdbcUrl = "jdbc:mysql://" + ip + ":" + port + "/" + serviceName
//                        + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
                jdbcUrl = "jdbc:mysql://" + ip + ":" + port + "/" + serviceName
                        + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
                break;
            case ORACLE:
                jdbcUrl = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + serviceName;
                break;
            case HIVE:
                jdbcUrl = "jdbc:hive2://" + ip + ":" + port + "/" + serviceName;
                break;
            case HANA:
                jdbcUrl = "jdbc:sap://" + ip + ":" + port + "?reconnect=true";
                break;
            case SQLITE:
                jdbcUrl = "jdbc:sqlite:" + serviceName;
                break;
            case DB2:
                jdbcUrl = "jdbc:db2://" + ip + ":" + port + "/" + serviceName;
                break;
            case SQLSERVER:
                jdbcUrl = "jdbc:sqlserver://" + ip + ":" + port + ";databaseName=" + serviceName + ";";
                break;
            case POSTGRESQL:
                jdbcUrl = "jdbc:postgresql://" + ip + ":" + port + "/" + serviceName;
                break;
            case IMPALA:
                jdbcUrl = "jdbc:impala://" + ip + ":" + port + "/" + serviceName;
                break;
            default:
                jdbcUrl = "jdbc:mysql://" + ip + ":" + port + "/" + serviceName;
                break;
        }
        return jdbcUrl;
    }
    /**
     * 检查能否连接数据库， 新增数据源和请求查询数据接口关联数据源时 会用。
     * 根据JDBC四要素： Driver，Url，Username password, 使用原生JDBC的方式获取DB连接，进行连通性测试.
     * 原生JDBC六个步骤:
     * 1. 获取驱动
     * 2. 建立连接
     * 3. statement
     * 4. 执行SQL
     * 5. 处理结果
     * 6. 关闭资源
     */
    public static boolean checkConnectionDB(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            LOG.info("not found driver class:" + driver, e);
            return false;
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            return true;
        } catch (SQLException e) {
            LOG.info("connection database failed, url=" + url, e);
            return false;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    public static void close() {
        // 关闭资源(先开后关)

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
            rs = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
            ps = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
            statement = null;
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
            conn = null;
        }
    }
    private boolean executeImpalaSqlByCheck(String id, String sql,Integer queryTimeout,Integer maxWait)throws Exception {
        boolean b = false;
        try {
            JdbcTemplate template = JdbcTemplateManager.getJdbcTemplate(id);
            if (null == template){
                //JDBC链接未获取,抛出异常
                throw new Exception("JDBC链接未获取");
            }
            // template.getDataSource().getConnection();
            //conn = DataSourceUtils.getConnection(template.getDataSource());
            conn = getImpalaConnection(maxWait,queryTimeout);
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            statement.setQueryTimeout(queryTimeout);
            statement.execute(sql);
            b = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    private boolean executeHiveSqlByCheck(String id, String sql)throws Exception {
        boolean b = false;
        try {
            JdbcTemplate template = JdbcTemplateManager.getJdbcTemplate(id);
            if (null == template){
                //JDBC链接未获取,抛出异常
                throw new Exception("JDBC链接未获取");
            }
            //conn = getConnection();
            conn = template.getDataSource().getConnection();
            conn.setAutoCommit(false);
            statement = conn.createStatement();
            statement.execute(sql);
            b = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    private boolean executeSqlByCheck(String id, String sql)throws Exception {
        boolean b = false;
        try {
            JdbcTemplate template = JdbcTemplateManager.getJdbcTemplate(id);
            if (null == template){
                //JDBC链接未获取,抛出异常
                throw new Exception("JDBC链接未获取");
            }
            //conn = DataSourceUtils.getConnection(template.getDataSource());
            conn = template.getDataSource().getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.execute(sql);
            b = true;
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }

    // ################### 从此行 往下都是一些实现了某个功能， 但暂时还没使用到具体场景的方法  ###################

    /**
     * 执行数据库查询操作
     *
     * @param sql
     * @param parameters
     * @return
     * @throws Exception
     */
    public static ResultSet executeQuery(String sql, Object[] parameters) throws Exception {
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if (parameters != null && parameters.length > 0) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setObject(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
        return rs;
    }
    /**
     * 执行hive查询
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public static ResultSet executeHiveQuery(String sql) throws Exception {
        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
        return rs;
    }
    /**
     * 根据表名获取数据库表信息
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public static ResultSet queryTableInfo(String tableName) throws Exception {
        try {
            conn = getConnection();
            DatabaseMetaData md = conn.getMetaData();
            rs = md.getTables(null, null, tableName, null);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
        return rs;
    }
    /**
     * 根据表名、字段名获取数据库表字段信息
     *
     * @param tableName
     * @param columnName
     * @return
     * @throws Exception
     */
    public static ResultSet queryTableColumnInfo(String tableName, String columnName) throws Exception {
        try {
            conn = getConnection();
            DatabaseMetaData md = conn.getMetaData();
            rs = md.getColumns(null, null, tableName, columnName);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
        return rs;
    }
    /**
     * 判断Mysql表是否存在
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public static boolean tableExist(String tableName) throws Exception {
        boolean b = false;
        try {
            conn = getConnection();
            DatabaseMetaData md = conn.getMetaData();
            rs = md.getTables(db.getServiceName(), null, tableName, new String[]{"TABLE"});
            b = rs.next();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    /**
     * 判断oracle表是否存在
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public static boolean oracleTableExist(String tableName) throws Exception {
        boolean b = false;
        try {
            conn = getConnection();
            DatabaseMetaData md = conn.getMetaData();
            rs = md.getTables(null, db.getUsername().toUpperCase(), tableName.toUpperCase(), new String[]{"TABLE"});
            b = rs.next();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    /**
     * 获取表信息
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> getTableInfo(String sql) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("tableName", rs.getObject(1));
                map.put("tableComment", rs.getString(2));
                list.add(map);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return list;
    }
    /**
     * 获取某信息的集合
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getCommonInfo(String sql) throws Exception {
        Map<String, Object> map = new HashMap<>();
        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                map.put(rs.getObject(1).toString(), rs.getObject(1));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return map;
    }
    /**
     * 获取表字段信息
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> getColumnInfo(String sql) throws Exception {
        List<Map<String, Object>> mapList = new ArrayList<>();
        try {
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("colName", rs.getObject(1));
                map.put("formName", rs.getObject(2));
                map.put("colType", rs.getObject(3));
                map.put("colSize", rs.getObject(4));
                map.put("primaryKey", rs.getObject(5));
                map.put("colPointSize", rs.getObject(6));
                map.put("colEmpty", rs.getObject(7));
                mapList.add(map);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return mapList;
    }
    /**
     * 判断hive数据库某种表是否存在
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public static boolean hiveTableExist(String tableName) throws Exception {
        boolean b = false;
        try {
            String sql = "show tables '" + tableName + "'";
            conn = getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(sql);
            b = rs.next();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    /**
     * 根据表名、字段名判断列名是否存在
     *
     * @param tableName
     * @param columnName
     * @return
     * @throws Exception
     */
    public static boolean columnExist(String tableName, String columnName) throws Exception {
        boolean b = false;
        try {
            conn = getConnection();
            DatabaseMetaData md = conn.getMetaData();
            rs = md.getColumns(db.getServiceName(), null, tableName, columnName);
            b = rs.next();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        } finally {
            close();
        }
        return b;
    }
    /**
     * 将数据集转换成List
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        if (rs == null)
            return null;
        ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> rowData = new HashMap<String, Object>();
        while (rs.next()) {
            rowData = new HashMap<String, Object>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(rowData);
        }
        return list;
    }
    /**
     * 根据结果集获取列名
     *
     * @param rs
     * @return
     * @throws Exception
     */
    public static List<String> getSqlColumns(ResultSet rs) throws Exception {
        List<String> sqlColumns = new ArrayList<String>();
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                String columnName = rsmd.getColumnName(i + 1);
                sqlColumns.add(columnName);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
        return sqlColumns;
    }
    // 将字Clob转成String类型
    public static String clobToString(Clob c) throws SQLException, IOException {
        String reString = "";
        Reader is = c.getCharacterStream();// 得到流
        BufferedReader br = new BufferedReader(is);
        String s = br.readLine();
        StringBuffer sb = new StringBuffer();
        while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            s = br.readLine();
        }
        reString = sb.toString();
        return reString;
    }
    /**
     * list集合转换成json
     *
     * @param list
     * @return json字符串
     */
    public static String listTojson(List<Map<String, Object>> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                json.append(new JSONObject(map));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }
    public synchronized void closeAll() {
        // 关闭资源(先开后关)

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
            rs = null;
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
            ps = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
            statement = null;
        }
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage());
            }
            conn = null;
        }
    }
    public boolean initDataSource(DataSource dSource) {
        // TODO Auto-generated method stub
        boolean b = checkConnectionDB(dSource.getDbType().getDriverClass(), dSource.getUrl(), dSource.getUsername(),
                dSource.getPassword()); //解密
        try {
            if (b) {
                init(dSource);
                getConnection();
                getCommonExcute(dSource.getDbType(), "SELECT 1");
                close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            b = false;
            LOG.info(e.getMessage());
        }
        return b;

    }
    public boolean getCommonExcuteTest(String id, String sql,DBType dbType,Integer queryTimeout,Integer maxWait) throws Exception {
        boolean flag = true;
        try {
            switch (dbType) {
                case MYSQL:
                    flag = executeSqlByCheck(id,sql);
                    break;
                case ORACLE:
                    flag = executeSqlByCheck(id,sql);
                    break;
                case HIVE:
                    flag = executeHiveSqlByCheck(id,sql);
                    break;
                case IMPALA:
                    flag = executeImpalaSqlByCheck(id,sql,queryTimeout,maxWait);
                    break;
                case SQLSERVER:
                    flag = executeHiveSqlByCheck(id,sql);
                    break;
                default:
                    flag = executeSqlByCheck(id,sql);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
        return flag;
    }


    public static void main(String[] args) {
        //本类中定义的一些工具方法可在此处进行测试
        //test_1();
        //test_2();


    }
    private static void test_2(){
        try {
            String url = "jdbc:impala://10.18.25.72:21051/smart_ods;AuthMech=3";
            Class.forName("com.cloudera.impala.jdbc41.Driver");
            Connection connection = DriverManager.getConnection(url, "iot_share_bind", "4Fy6Uh6Nq5");
            String sql=
                    "select ORDER_DATE, BD_CODE, BD_NAME, BD_SHORT_NAME, APPTYPE from dw_iot.dws_intel_qty_dtl_m limit 5";
            PreparedStatement ps=connection.prepareStatement(sql);
            ResultSetMetaData re=ps.getMetaData();
            int count=re.getColumnCount();
            for(int i=1;i<=count;i++){
                System.out.println(re.getColumnLabel(i));
                System.out.println(re.getTableName(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void test_1() {

        Map<String, Object> dsMap = new HashMap<>();
        dsMap.put("driverClassName", "com.cloudera.hive.jdbc41.HS2Driver");
        dsMap.put("url", "jdbc:hive2://10.18.25.72:10001/default;AuthMech=3");
        dsMap.put("username", "lms_bind");
        dsMap.put("password", "ylTnrqxISj8");
        javax.sql.DataSource ds = null;
        try {
            ds = DruidDataSourceFactory.createDataSource(dsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 从连接池中取出连接
        try {
            Connection conn = ((javax.sql.DataSource) ds).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
