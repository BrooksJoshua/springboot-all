package com.boy.springbootalldynamicdatasource.service.impl;

import com.boy.springbootalldynamicdatasource.bean.DataSource;
import com.boy.springbootalldynamicdatasource.bean.User;
import com.boy.springbootalldynamicdatasource.mapper.DataSourceMapper;
import com.boy.springbootalldynamicdatasource.mapper.UserMapper;
import com.boy.springbootalldynamicdatasource.service.DataSourceService;
import com.boy.springbootalldynamicdatasource.service.UserService;
import com.boy.springbootalldynamicdatasource.util.DBHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-02 22:39
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    DataSourceService dataSourceService;

    @Override
    public List<User> findList(User entity) {
        DataSource dataSource = dataSourceService.get(String.valueOf(2));  //这里id决定到底使用哪个
        DBHelper dbHelper = null;
        try {
            dbHelper = new DBHelper(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            boolean rs_flag = dbHelper.getExcuterResult(dataSource.getDbType(), "select *  from t_user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = dbHelper.getResultSetInfo("select *  from t_user");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 声明两个变量分别表示id和年龄
        String id, remarks, username, password, createBy, updateBy, name, delFlag;
        Date createDate, updateDate;

        ArrayList<User> userList = new ArrayList<>();
        // 遍历结果集
        try {
            while (rs.next()) {
                // 获得id值
                id = rs.getString("id");
                remarks = rs.getString("remarks");
                // 获得用户名
                username = rs.getString("username");
                // 获得密码
                password = rs.getString("password");
                // 获得性别
                createBy = rs.getString("create_by");
                // 获得年龄
                updateBy = rs.getString("update_by");
                name = rs.getString("name");
                createDate = rs.getDate("create_date");
                updateDate = rs.getDate("update_date");
                delFlag = rs.getString("del_flag");
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassWord(password);
                user.setName(name);
                user.setDelFlag(delFlag);
                user.setCreateBy(createBy);
                user.setCreateDate(createDate);
                user.setUpdateBy(updateBy);
                user.setUpdateDate(updateDate);
                user.setRemarks(remarks);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userList;
    }
}
