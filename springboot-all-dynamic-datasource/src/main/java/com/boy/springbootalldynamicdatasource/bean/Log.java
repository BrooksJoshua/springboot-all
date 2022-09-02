package com.boy.springbootalldynamicdatasource.bean;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 日志信息
 * 
 * @author Joshua
 * @date
 */
public class Log {
	@ApiModelProperty(value = "主键id", example = "4243243243242365465")
	private String id;

	@ApiModelProperty(value = "访问的url", example = "/MSS/user/list")
	private String requestUrl;

	@ApiModelProperty(value = "请求的参数", example = "userId=test")
	private String params;

	@ApiModelProperty(value = "创建人", example = "admin")
	private String createBy;

	@ApiModelProperty(value = "创建日期", example = "2018-06-11")
	private String createDate;

	@ApiModelProperty(value = "访问的ip地址", example = "")
	private String remoteAddr;

	@ApiModelProperty(value = "日志类型", example = "0")
	private int type;// 0:用户访问日志，1：异常日志

	@ApiModelProperty(value = "用户浏览器类型", example = "Mozilla/5.0")
	private String userAgent;

	@ApiModelProperty(value = "访问方式", example = "post")
	private String method;

	@ApiModelProperty(value = "异常信息", example = "java.null.exception")
	private String exception;

	@ApiModelProperty(value = "创建时间", example = "")
	private Date createTime;

	@ApiModelProperty(value = "模块", example = "")
	private String module;

	private String operate;

	private String moduleName;

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}
