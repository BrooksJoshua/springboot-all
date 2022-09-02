package com.boy.springbootalldynamicdatasource.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "角色用户")
public class RoleUser {

	/**
	 * 角色
	 */
	@ApiModelProperty(value = "角色id", example = "1" ,required = true)
	private String roleId;
	/**
	 * 权限组Id
	 */
	@ApiModelProperty(value = "用户id", example = "1" ,required = true)
	private String userId;

	@ApiModelProperty(hidden = true)
	private String userName;

	@ApiModelProperty(hidden = true)
	private String userCode;

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
