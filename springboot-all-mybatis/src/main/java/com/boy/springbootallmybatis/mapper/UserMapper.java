package com.boy.springbootallmybatis.mapper;


import com.boy.springbootallmybatis.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-04 16:52
 */
public interface UserMapper {

    User getUserByIdWithXML(Integer id);

    @Select("select * from JDBCTemplate_user where id=#{id}")
    User getUserById(Integer id);

    @Update("update JDBCTemplate_user set `name` = #{name} where id=#{id}")
    int updateUserById(@Param("id") Integer id,@Param("name") String name);

    @Delete("delete from JDBCTemplate_user where id=#{id}")
    int deleteUserById(@Param("id") Integer id);

    @Insert("insert into JDBCTemplate_user (`name`,`address`) values (#{name}, #{address}) ")
    @SelectKey(statement = "select last_insert_id()",keyProperty = "id", before = false, resultType = Integer.class)
    int insert(User user);
}
