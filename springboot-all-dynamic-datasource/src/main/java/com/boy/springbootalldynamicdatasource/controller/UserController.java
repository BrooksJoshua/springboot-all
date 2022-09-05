package com.boy.springbootalldynamicdatasource.controller;

import com.boy.springbootalldynamicdatasource.bean.DBType;
import com.boy.springbootalldynamicdatasource.bean.DataSource;
import com.boy.springbootalldynamicdatasource.bean.User;
import com.boy.springbootalldynamicdatasource.bean.UserInfo;
import com.boy.springbootalldynamicdatasource.datasource.DynamicDataSourceContextHolder;
import com.boy.springbootalldynamicdatasource.service.DataSourceService;
import com.boy.springbootalldynamicdatasource.service.UserService;
import com.boy.springbootalldynamicdatasource.util.DBHelper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-02 22:45
 */
@Api(tags = "用户User接口测试")
@Controller
@RequestMapping(value = "user")
public class UserController extends BasicController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    UserService userService;

    @Autowired
    DataSourceService dataSourceService;

    @ApiOperation(value = "用户列表", notes = "用来列出所有后台的用户信息以json格式返回")
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes="text/plain", produces="application/json")
    public @ResponseBody
    Object list(HttpServletRequest request) throws Exception {
        DynamicDataSourceContextHolder.setDataSourceType("default");
        User user = new User();
        List<User> list = userService.findList(user);
        return list;
    }

}
