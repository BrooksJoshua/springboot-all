package com.boy.springbootallsecuritydb.mapper;

import com.boy.springbootallsecuritydb.entity.Role;
import com.boy.springbootallsecuritydb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper
public interface UserMapper {

    User getUserByUsername(String username);
    List<Role> getUserRolesByUid(int uid);


}
