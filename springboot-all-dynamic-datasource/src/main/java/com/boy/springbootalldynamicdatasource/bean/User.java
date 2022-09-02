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

	@ApiModelProperty(value = "登录账号", example = "lijl")
	private String userCode;
	@ApiModelProperty(value = "用户名称", example = "李嘉陵")
	private String userName;
	@ApiModelProperty(value = "用户密码", example = "123456")
	private String passWord;
	@ApiModelProperty(value = "用户邮箱", example = "lijl@126.com")
	private String userEmail;
	@ApiModelProperty(value = "有效开始时间", example = "2017-09-07")
	private String effStartDate;
	@ApiModelProperty(value = "有效结束时间", example = "9999-12-12")
	private String effEndDate;
	@ApiModelProperty(value = "电话号码", example = "18520836944")
	private String userMobileNo;
	@ApiModelProperty(value = "岗位", example = "项目经理")
	private String positionName;
	@ApiModelProperty(value = "钉钉登录账号", example = "lijl")
	private String dingTalkUserCode;
	@ApiModelProperty(value = "微信登录账号", example = "lijl")
	private String weChatUserCode;
	@ApiModelProperty(value = "登录账号", example = "lijl",hidden = true)
	private String userAccount;
	@ApiModelProperty(value = "加密权限", example = "false")
	private Boolean encryption;
	@ApiModelProperty(value = "解密权限", example = "false")
	private Boolean decryption;

//	private TokenMes tokenMes;

//	private List<Role> roles;

	private Boolean pushErrorFlag;

	private String departmentname;

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public Boolean getPushErrorFlag() {
		return pushErrorFlag;
	}

	public void setPushErrorFlag(Boolean pushErrorFlag) {
		this.pushErrorFlag = pushErrorFlag;
	}

//	public List<Role> getRoles() {
//		return roles;
//	}

//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}

//	public TokenMes getTokenMes() {
//		return tokenMes;
//	}
//
//	public void setTokenMes(TokenMes tokenMes) {
//		this.tokenMes = tokenMes;
//	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getDingTalkUserCode() {
		return dingTalkUserCode;
	}

	public void setDingTalkUserCode(String dingTalkUserCode) {
		this.dingTalkUserCode = dingTalkUserCode;
	}

	public String getWeChatUserCode() {
		return weChatUserCode;
	}

	public void setWeChatUserCode(String weChatUserCode) {
		this.weChatUserCode = weChatUserCode;
	}

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

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getEffStartDate() {
		return effStartDate;
	}

	public void setEffStartDate(String effStartDate) {
		this.effStartDate = effStartDate;
	}

	public String getEffEndDate() {
		return effEndDate;
	}

	public void setEffEndDate(String effEndDate) {
		this.effEndDate = effEndDate;
	}

	public String getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Boolean getEncryption() {
		return encryption;
	}

	public void setEncryption(Boolean encryption) {
		this.encryption = encryption;
	}

	public Boolean getDecryption() {
		return decryption;
	}

	public void setDecryption(Boolean decryption) {
		this.decryption = decryption;
	}
}
