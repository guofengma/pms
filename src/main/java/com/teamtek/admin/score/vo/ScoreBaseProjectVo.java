package com.teamtek.admin.score.vo;

import java.util.Date;

import com.sargeraswang.util.ExcelUtil.ExcelCell;

/**
 * 用于 excel 导入
 * 
 * @author syj
 * @createTime：2018年3月14日 上午11:44:01
 */
public class ScoreBaseProjectVo {
	@ExcelCell(index = 0)
	private String loanNo;
	@ExcelCell(index = 1)
	private Date loanDate;
	@ExcelCell(index = 2)
	private String projectName;
	@ExcelCell(index = 3)
	private String unit;
	@ExcelCell(index = 4)
	private Double bizAmount;

	// 存量 、增量
	@ExcelCell(index = 5)
	private String saveOrAdd;

	@ExcelCell(index = 6)
	private String standardRate;
	@ExcelCell(index = 7)
	private String loanRate;
	// 利率得分
	@ExcelCell(index = 8)
	private Double ratescore;

	@ExcelCell(index = 9)
	private String rateSharemanNo;
	@ExcelCell(index = 10)
	private String customer;
	// 客户身份证号码/客户编号（如果是追溯的项目要填写贷种+客户编号）小企业和零售确定模板后返回
	@ExcelCell(index = 11)
	private String id_card;
	@ExcelCell(index = 15)
	private String marketManNo;
	@ExcelCell(index = 16)
	private String teamEmpNames;
	/**
	 * 团队分配名单工号(以“+”分割)
	 */
	@ExcelCell(index = 17)
	private String teamEmpNos;

	/**
	 * 团队分配比例(以“+”分割)
	 */
	@ExcelCell(index = 18)
	private String teamEmpRates;
	// 信贷主调工号
	@ExcelCell(index = 22)
	private String masterNo;
	// 信贷主调工号
	@ExcelCell(index = 24)
	private String assistantNo;
	// 是否追溯联动
	@ExcelCell(index = 25)
	private String isRetrospectLink;
	// 联动贷款客户编号（属于需追溯联动填写）
	@ExcelCell(index = 26)
	private String retrospectLinkNo;;

	public ScoreBaseProjectVo() {
		super();
	}

	public ScoreBaseProjectVo(String loanNo, Date loanDate, String projectName, String unit, Double bizAmount,
			String saveOrAdd, String standardRate, String loanRate, Double ratescore, String rateSharemanNo,
			String customer, String id_card, String marketManNo, String teamEmpNos, String teamEmpRates,
			String masterNo, String assistantNo, String isRetrospectLink, String retrospectLinkNo) {
		super();
		this.loanNo = loanNo;
		this.loanDate = loanDate;
		this.projectName = projectName;
		this.unit = unit;
		this.bizAmount = bizAmount;
		this.saveOrAdd = saveOrAdd;
		this.standardRate = standardRate;
		this.loanRate = loanRate;
		this.ratescore = ratescore;
		this.rateSharemanNo = rateSharemanNo;
		this.customer = customer;
		this.id_card = id_card;
		this.marketManNo = marketManNo;
		this.teamEmpNos = teamEmpNos;
		this.teamEmpRates = teamEmpRates;
		this.masterNo = masterNo;
		this.assistantNo = assistantNo;
		this.isRetrospectLink = isRetrospectLink;
		this.retrospectLinkNo = retrospectLinkNo;
	}

	public String getLoanNo() {
		return loanNo == null ? null : loanNo.trim();
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getProjectName() {
		return projectName == null ? null : projectName.trim();
	}

	public String getTeamEmpNames() {
		return teamEmpNames;
	}

	public void setTeamEmpNames(String teamEmpNames) {
		this.teamEmpNames = teamEmpNames;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getStandardRate() {
		return standardRate;
	}

	public void setStandardRate(String standardRate) {
		this.standardRate = standardRate;
	}

	public String getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(String loanRate) {
		this.loanRate = loanRate;
	}

	public String getRateSharemanNo() {
		return rateSharemanNo == null ? null : rateSharemanNo.trim();
	}

	public void setRateSharemanNo(String rateSharemanNo) {
		this.rateSharemanNo = rateSharemanNo;
	}

	public String getMarketManNo() {
		return marketManNo == null ? null : marketManNo.trim();
	}

	public void setMarketManNo(String marketManNo) {
		this.marketManNo = marketManNo;
	}

	public String getMasterNo() {
		return masterNo == null ? null : masterNo.trim();
	}

	public void setMasterNo(String masterNo) {
		this.masterNo = masterNo;
	}

	public String getAssistantNo() {
		return assistantNo == null ? null : assistantNo.trim();
	}

	public void setAssistantNo(String assistantNo) {
		this.assistantNo = assistantNo;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Double getBizAmount() {
		return bizAmount;
	}

	public void setBizAmount(Double bizAmount) {
		this.bizAmount = bizAmount;
	}

	public String getSaveOrAdd() {
		return saveOrAdd;
	}

	public void setSaveOrAdd(String saveOrAdd) {
		this.saveOrAdd = saveOrAdd;
	}

	public Double getRatescore() {
		return ratescore;
	}

	public void setRatescore(Double ratescore) {
		this.ratescore = ratescore;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getTeamEmpNos() {
		return teamEmpNos;
	}

	public void setTeamEmpNos(String teamEmpNos) {
		this.teamEmpNos = teamEmpNos;
	}

	public String getTeamEmpRates() {
		return teamEmpRates;
	}

	public void setTeamEmpRates(String teamEmpRates) {
		this.teamEmpRates = teamEmpRates;
	}

	public String getIsRetrospectLink() {
		return isRetrospectLink;
	}

	public void setIsRetrospectLink(String isRetrospectLink) {
		this.isRetrospectLink = isRetrospectLink;
	}

	public String getRetrospectLinkNo() {
		return retrospectLinkNo;
	}

	public void setRetrospectLinkNo(String retrospectLinkNo) {
		this.retrospectLinkNo = retrospectLinkNo;
	}

}
