create database db_02;
use db_01;

create table DataSource(
    -- 以下是BaseBean中的
    id              varchar(255)  not null comment '主键(自动生成)',
    remarks         varchar(255)  not null comment '备注',
    create_by        varchar(255)  not null comment '创建者',
    create_date      timestamp     not null DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    update_by        varchar(255)  not null comment '修改者',
    update_date      datetime      not null DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '修改时间',
    del_flag         TINYINT       not null comment '删除标记(0：正常；1：删除；2：审核)',
    -- 以下为DataSourceBean
    db_type          varchar(255)  not null comment '数据库类型',
    dsrc_code         varchar(255)  not null comment '系统Code',
    dsrc_came         varchar(255)  not null comment '数据库名称',
    bean_name         varchar(255)  not null comment '数据库系统标识',
    ip               varchar(255)  not null comment 'ip',
    port             varchar(255)  not null comment '端口',
    url              varchar(255)  not null comment 'jdbc-url',
    username         varchar(255)  not null comment '数据库连接用户名',
    password         varchar(255)  not null comment '用户信息密码',
    init_size         int(10)       not null default 5 comment '初始化连接池大小',
    min_idle          int(10)       not null default 5 comment '最小空闲连接数',
    max_active        int(10)       not null default 15 comment '最大连接会话数目',
    max_wait          int(10)       not null default 120000 comment '连接等待超时的时间',
    validation_query  varchar(255)  not null comment '验证查询SQL',
    query_timeout     varchar(255)  not null comment 'SQL查询超时时间',
    time_between_eviction_run_millis   int(10)       not null default 120000 comment '检测间隔',
    min_evictable_idle_time_millis     int(10)       not null default 1800000 comment '连接在池中最小生存的时间'
) comment '数据源配置信息';