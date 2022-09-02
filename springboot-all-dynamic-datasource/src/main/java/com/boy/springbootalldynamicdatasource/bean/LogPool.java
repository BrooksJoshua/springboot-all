package com.boy.springbootalldynamicdatasource.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 日志统计分析信息
 * 
 * @author LEICHAO
 * @date 2018年6月11日下午4:41:12
 */
public class LogPool {
	@ApiModelProperty(value = "访问的url", example = "/MSS/user/list")
	private String requestUrl;

	@ApiModelProperty(value = "创建人", example = "admin")
	private String createBy;

	@ApiModelProperty(value = "访问方式", example = "post")
	private String method;

	private String sumNum;// 统计总数

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSumNum() {
		return sumNum;
	}

	public void setSumNum(String sumNum) {
		this.sumNum = sumNum;
	}
}
