package com.boy.springbootallpersistencelayer.service;

import com.boy.springbootallpersistencelayer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-04 14:55
 */
@Service
public class UserService {
//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplateOne;
    @Autowired
    JdbcTemplate jdbcTemplateTwo;

    public int addMultiDatasource(User user) {
        //增删改都是用jdbcTemplate.update方法
        int rowsInfectedOne = jdbcTemplateOne.update("insert into JDBCTemplate_user (`name`,`address`) values (?,?)", user.getName(), user.getAddress());
        int rowsInfectedTwo = jdbcTemplateTwo.update("insert into JDBCTemplate_user_2 (`name`,`address`) values (?,?)", user.getName(), user.getAddress());
        System.out.println("rowsInfectedOne = " + rowsInfectedOne);
        System.out.println("rowsInfectedTwo = " + rowsInfectedTwo);
        return rowsInfectedOne + rowsInfectedTwo;
    }
    /*
    public int add(User user) {
        //增删改都是用jdbcTemplate.update方法
        int rowsInfected = jdbcTemplate.update("insert into JDBCTemplate_user (`name`,`address`) values (?,?)", user.getName(), user.getAddress());
        System.out.println("rowsInfected = " + rowsInfected);
        return rowsInfected;
    }

    public int addWithPreparedStatement(User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        //增删改都是用jdbcTemplate.update方法
        int rowsInfected = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into JDBCTemplate_user (`name`,`address`) values (?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getAddress());
                return ps;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue()); //主键回填
        System.out.println("addWithPreparedStatement rowsInfected = " + rowsInfected);
        return rowsInfected;
    }

    public int updateById(String name,Integer id) {
        int rowsAffected = jdbcTemplate.update("update JDBCTemplate_user set `name` = ? where `id` = ?", name, id);
        return  rowsAffected;
    }

    public int deleteById(String id) {
        int rowsAffected = jdbcTemplate.update("delete from JDBCTemplate_user where `id` = ?",  id);
        return  rowsAffected;
    }


    public List<User> getAll() {
        //第二个参数是RowMapper匿名方法的lambda表达式
        List<User> list = jdbcTemplate.query("select * from JDBCTemplate_user ", (resultSet, i) -> new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("address")));
        return  list;
    }

    public List<User> getAll2() {
        //new BeanPropertyRowMapper(class) 这种方式必须保证返回对象属性和表字段一一匹配
        List<User> list = jdbcTemplate.query("select * from JDBCTemplate_user ", new BeanPropertyRowMapper<>(User.class));
        return  list;
    }

    */

}
