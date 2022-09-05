create database persistent_layer;

use persistent_layer;

create table JDBCTemplate_user(
      `id`      int(32)  not null AUTO_INCREMENT comment '主键'
    , `name`    varchar(32)  not null comment '名称'
    , `address` varchar(32)  not null comment '地址'
    , primary key(id)
) comment '用户信息';