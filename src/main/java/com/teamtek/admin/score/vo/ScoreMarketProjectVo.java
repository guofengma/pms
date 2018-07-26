package com.teamtek.admin.score.vo;

import java.io.Serializable;
import java.util.Date;

import com.sargeraswang.util.ExcelUtil.ExcelCell;

public class ScoreMarketProjectVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelCell(index = 0)
	private String projectName;
	/**
	 * 产品项目
	 */
	@ExcelCell(index = 1)
	private Date loanDate;
	/**
	 * 落地部门
	 */
	@ExcelCell(index = 2)
	private String fallDept;

	@ExcelCell(index = 3)
	private String customerName;
	/**
	 * 业务识别码（客户身份证号码/法人营业执照号码
	 */
	@ExcelCell(index =4)
	private String idCard;

	/**
	 * 营销人员工号
	 */
	@ExcelCell(index = 6)
	private String marketManNo;

	/**
	 * 借据号（属于联动填写,非联动项目不填）
	 */
	@ExcelCell(index = 7)
	private String loanNo;
	/**
	 * 积分量
	 */
	@ExcelCell(index = 8)
	private String scoreAmount;
	/**
	 * 单位(个等)
	 */
	@ExcelCell(index = 9)
	private String unit;

	/**
	 * 业务数量
	 */
	@ExcelCell(index = 10)
	private String bizAmount;

	/**
	 * 业务量单位 @see com.teamtek.admin.comm.util.ExcelConst.BizUnit
	 */
	@ExcelCell(index = 11)
	private String bizUnit;

	@ExcelCell(index = 12)
	private String remark;

	public String getBizUnit() {
		return bizUnit;
	}

	public void setBizUnit(String bizUnit) {
		this.bizUnit = bizUnit;
	}

	public String getScoreAmount() {
		return scoreAmount == null ? null : scoreAmount.trim();
	}

	public void setScoreAmount(String scoreAmount) {
		this.scoreAmount = scoreAmount;
	}

	public String getProjectName() {
		return projectName == null ? null : projectName.trim();
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public String getFallDept() {
		return fallDept == null ? null : fallDept.trim();
	}

	public void setFallDept(String fallDept) {
		this.fallDept = fallDept;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMarketManNo() {
		return marketManNo == null ? null : marketManNo.trim();
	}

	public void setMarketManNo(String marketManNo) {
		this.marketManNo = marketManNo;
	}

	public String getLoanNo() {
		return loanNo == null ? null : loanNo.trim();
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBizAmount() {
		return bizAmount == null ? null : bizAmount.trim();
	}

	public void setBizAmount(String bizAmount) {
		this.bizAmount = bizAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
