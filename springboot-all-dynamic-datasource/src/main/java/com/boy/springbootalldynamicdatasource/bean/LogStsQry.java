package com.boy.springbootalldynamicdatasource.bean;

public class LogStsQry {
	private String dimCode;// 统计维度

	private String dimName;// 统计维度名称

	private String sumNum;// 统计总数

	public String getDimCode() {
		return dimCode;
	}

	public void setDimCode(String dimCode) {
		this.dimCode = dimCode;
	}

	public String getDimName() {
		return dimName;
	}

	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	public String getSumNum() {
		return sumNum;
	}

	public void setSumNum(String sumNum) {
		this.sumNum = sumNum;
	}
}
