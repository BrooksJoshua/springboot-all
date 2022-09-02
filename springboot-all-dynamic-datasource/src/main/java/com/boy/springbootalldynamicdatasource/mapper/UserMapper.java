package com.boy.springbootalldynamicdatasource.mapper;


import com.boy.springbootalldynamicdatasource.bean.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

	/**
	 * 根据用户编码查询用户
	 * @param userCode
	 * @return
	 */
	public User getByUserCode(String userCode);

	public User checkUnion(User user);

	public User getByUserCodeWithOutDelFlag(String userCode);


	/**
	 * 根据组织ID或者用户名获取用户列表
	 * @param officeId
	 * @param userName
	 * @return 用户列表
	 */
	List<User> findUserList(@Param("officeId")String officeId, @Param("userName")String userName);

	// 找出用户账号与uuid的映射关系
	@MapKey("map_key")
	Map<String,Map<String,String>> findAccountAndUuidMap();
}
