package com.boy.springbootalldynamicdatasource.bean;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-01 16:37
 */
public enum DBType {

    MYSQL("MYSQL", "com.mysql.cj.jdbc.Driver"),
    ORACLE("ORACLE", "oracle.jdbc.driver.OracleDriver"),
    HIVE("HIVE", "com.cloudera.hive.jdbc41.HS2Driver"),
    HANA("HANA", "com.sap.db.jdbc.Driver"),
    SQLITE("SQLITE", "org.sqlite.JDBC"),
    DB2("DB2", "com.ibm.db2.jcc.DB2Driver"),
    SQLSERVER("SQLSERVER", "com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    POSTGRESQL("POSTGRESQL", "org.postgresql.Driver"),
    IMPALA("IMPALA", "com.cloudera.impala.jdbc41.Driver");

    /**
     * 枚举码
     */
    private String code;

    /**
     * 枚举描述
     */
    private String driverClass;

    private DBType(final String code, final String driverClass) {
        this.code = code;
        this.driverClass = driverClass;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getCode() {
        return code;
    }

    /**
     * 根据枚举码获取枚举对象
     *
     * @param code 枚举码
     * @return 枚举对象
     */
    public static DBType getByCode(String code) {
        if (code == null) {
            return null;
        }
        DBType[] enumValues = values();
        for (DBType enumValue : enumValues) {
            if (enumValue.getCode().equals(code)) {
                return enumValue;
            }
        }
        return null;
    }
}
