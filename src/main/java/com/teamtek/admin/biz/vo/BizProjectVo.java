package com.teamtek.admin.biz.vo;

import java.util.Date;

import com.sargeraswang.util.ExcelUtil.ExcelCell;

public class BizProjectVo {

	/**
	 * 项目名称
	 */
	@ExcelCell(index = 0)
	private String projectName;
	/**
	 * 项目编号
	 */
	@ExcelCell(index = 1)
	private String projectNum;
	/**
	 * 项目类型
	 */
	@ExcelCell(index = 2)
	private String projectTypeId;
	/**
	 * 状态
	 */
	@ExcelCell(index = 3)
	private String status;
	/**
	 * 创建时间
	 */
	@ExcelCell(index = 4)
	private Date createTime;
	/**
	 */
	@ExcelCell(index = 5)
	private Double upperLimit;

	/**
	 * 单位
	 */
	@ExcelCell(index = 6)
	private String unit;

	/**
	 * 项目描述
	 */
	@ExcelCell(index = 7)
	private String projectRemark;

	private String other;

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getProjectTypeId() {
		return projectTypeId == null ? null : projectTypeId.trim();
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getProjectName() {
		return projectName == null ? null : projectName.trim();
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectRemark() {
		return projectRemark;
	}

	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
