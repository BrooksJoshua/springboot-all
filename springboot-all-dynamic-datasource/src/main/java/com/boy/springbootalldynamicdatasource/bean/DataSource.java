package com.boy.springbootalldynamicdatasource.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-01 16:37
 */

@ApiModel(value = "数据源配置信息")
public class DataSource extends BaseBean<DataSource> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "系统Code", example = "localhost_test", required = true)
    private String dsrcCode;// 系统Code

    @ApiModelProperty(value = "数据库名称", example = "本地测试", required = true)
    private String dsrcName; //数据库名称

    @ApiModelProperty(hidden = true)
    private String beanName;// 数据库系统标识

    @ApiModelProperty(value = "数据库类型", example = "MYSQL", required = true)
    private DBType dbType;// 数据库类型

    private String dbTypeStr;// 数据库类型

    public String getDbTypeStr() {
        return dbTypeStr;
    }

    public void setDbTypeStr(String dbTypeStr) {
        this.dbTypeStr = dbTypeStr;
    }

    @ApiModelProperty(value = "数据库IP", example = "", required = true)
    private String ip;

    @ApiModelProperty(value = "端口", example = "3306", required = true)
    private String port;

    @ApiModelProperty(value = "数据库连接URL", example = "jdbc:mysql://localhost:3306/dc?useUnicode=true&characterEncoding=UTF-8", required = true)
    private String url;// 数据库连接URL

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ApiModelProperty(value = "库名称（数据库实例）", example = "dc", required = true)

    private String serviceName; // 库名称（数据库实例）

    @ApiModelProperty(value = "数据库连接用户名", example = "root", required = true)
    private String username;// 数据库连接用户名

    @ApiModelProperty(value = "用户信息密码", example = "123456", required = true)
    private String password;// 用户信息密码

    @ApiModelProperty(value = "初始化连接池大小", example = "0")
    private Integer initSize;// 初始化连接池大小

    @ApiModelProperty(value = "最小空闲连接数", example = "0")
    private Integer minIdle;// 最小空闲连接数

    @ApiModelProperty(value = "最大连接会话数目", example = "10")
    private Integer maxActive;// 最大连接会话数目

    @ApiModelProperty(value = "连接等待超时的时间", example = "120000")
    private Integer maxWait;// 连接等待超时的时间

    @ApiModelProperty(value = " 检测间隔", example = "120000")
    private Integer timeBetweenEvictionRunMillis;// 检测间隔

    @ApiModelProperty(value = "连接在池中最小生存的时间", example = "1800000")
    private Integer minEvictableIdleTimeMillis;// 连接在池中最小生存的时间

    @ApiModelProperty(value = "验证查询SQL", example = "select * from sys_user")
    private String validationQuery;// 验证查询SQL

    @ApiModelProperty(value = "用户信息密码", example = "120000")
    private Integer queryTimeout;// SQL查询超时时间


    @ApiModelProperty(hidden = true)
    private String userId;

    public String getDsrcCode() {
        return dsrcCode;
    }

    public void setDsrcCode(String dsrcCode) {
        this.dsrcCode = dsrcCode;
    }

    public String getDsrcName() {
        return dsrcName;
    }

    public void setDsrcName(String dsrcName) {
        this.dsrcName = dsrcName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public DBType getDbType() {
        return dbType;
    }

    public void setDbType(DBType dbType) {
        this.dbType = dbType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getInitSize() {
        return initSize;
    }

    public void setInitSize(Integer initSize) {
        this.initSize = initSize;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getTimeBetweenEvictionRunMillis() {
        return timeBetweenEvictionRunMillis;
    }

    public void setTimeBetweenEvictionRunMillis(Integer timeBetweenEvictionRunMillis) {
        this.timeBetweenEvictionRunMillis = timeBetweenEvictionRunMillis;
    }

    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public Integer getQueryTimeout() {
        return queryTimeout;
    }

    public void setQueryTimeout(Integer queryTimeout) {
        this.queryTimeout = queryTimeout;
    }

}
