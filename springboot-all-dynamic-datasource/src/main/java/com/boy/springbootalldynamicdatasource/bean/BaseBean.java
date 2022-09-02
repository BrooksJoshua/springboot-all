package com.boy.springbootalldynamicdatasource.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-09-01 16:38
 */
public abstract class BaseBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键（自动生成）", example = "1")
    private String id;

    @ApiModelProperty(value = "备注", hidden = true)
    protected String remarks; // 备注

    @ApiModelProperty(value = "创建者", hidden = true)
    protected String createBy; // 创建者

    @ApiModelProperty(value = "创建日期", hidden = true)
    protected Date createDate = new Date(); // 创建日期

    @ApiModelProperty(value = "更新者", hidden = true)
    protected String updateBy; // 更新者

    @ApiModelProperty(value = "更新日期", hidden = true)
    protected Date updateDate = new Date(); // 更新日期

    @ApiModelProperty(value = "删除标记（0：正常；1：删除；）", hidden = true)
    protected String delFlag = "0"; // 删除标记（0：正常；1：删除；2：审核）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
