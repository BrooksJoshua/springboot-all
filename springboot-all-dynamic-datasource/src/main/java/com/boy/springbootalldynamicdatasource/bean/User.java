package com.boy.springbootalldynamicdatasource.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @description:用户实体类
 * @autor:Jialing Li
 * @date:2017-09-15
 * @version: 1.1
 *
 */
@ApiModel(value = "用户信息")
public class User extends BaseBean<User> {

	@ApiModelProperty(value = "用户id", example = "aaa")
	private String id;
	@ApiModelProperty(value = "用户名", example = "张三")
	private String username;
	@ApiModelProperty(value = "用户密码", example = "123456")
	private String passWord;
	@ApiModelProperty(value = "真实姓名", example = "张三丰")
	private String name;

	public User() {
	}

	public User(String id, String username, String passWord, String name) {
		this.id = id;
		this.username = username;
		this.passWord = passWord;
		this.name = name;
	}

	@Override
	public String toString() {
		return "User{" +
				"remarks='" + remarks + '\'' +
				", createBy='" + createBy + '\'' +
				", createDate=" + createDate +
				", updateBy='" + updateBy + '\'' +
				", updateDate=" + updateDate +
				", delFlag='" + delFlag + '\'' +
				", id='" + id + '\'' +
				", username='" + username + '\'' +
				", passWord='" + passWord + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
