package com.teamtek.admin.score.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 积分 数据导入 （基础项目）
 * </p>
 *
 * @author syj
 * @since 2018-03-27 18:01:01
 */
@TableName("score_base_project")
public class ScoreBaseProject implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 业务数据id
	 */
	@TableField(value="score_data_id")
	private Long scoreDataId;

	/**
	 * 借据号
	 */
	@TableField(value="loan_no")
	private String loanNo;

	/**
	 * 放款时间
	 */
	@TableField(value="loan_date")
	private Date loanDate;

	/**
	 * 项目
	 */
	@TableField(value="project_id")
	private Long projectId;

	/**
	 * 单位(元、万元等)
	 */
	private String unit;

	/**
	 * 业务数量
	 */
	@TableField(value="biz_amount")
	private Double bizAmount;

	/**
	 * 存量、增量
	 */
	@TableField(value="save_or_add")
	private String saveOrAdd;

	/**
	 * 对标利率
	 */
	@TableField(value="standard_rate")
	private String standardRate;

	/**
	 * 放款利率
	 */
	@TableField(value="loan_rate")
	private String loanRate;

	/**
	 * 利率得分
	 */
	@TableField(value="rate_score")
	private Double rateScore;

	/**
	 * 利率得分人工号
	 */
	@TableField(value="rate_shareman_no")
	private String rateSharemanNo;

	/**
	 * 客户名称
	 */
	private String customer;

	/**
	 * 客户身份证/营业执照号码
	 */
	@TableField(value="id_card")
	private String idCard;

	/**
	 * 落地部门
	 */
	@TableField(value="fall_dept")
	private Long fallDept;

	/**
	 * 营销人员工号
	 */
	@TableField(value="market_man_no")
	private String marketManNo;

	/**
	 * 团队分配名单(以“+”分割)
	 */
	@TableField(value="team_emp_names")
	private String teamEmpNames;
	/**
	 * 团队分配名单工号(以“+”分割)
	 */
	@TableField(value="team_emp_nos")
	private String teamEmpNos;

	/**
	 * 团队分配比例(以“+”分割)
	 */
	@TableField(value="team_emp_rates")
	private String teamEmpRates;

	/**
	 * 主调工号
	 */
	@TableField(value="master_no")
	private String masterNo;

	/**
	 * 副调工号
	 */
	@TableField(value="assistant_no")
	private String assistantNo;

	/**
	 * 是否追溯联动
	 */
	@TableField(value="is_retrospect_link")
	private Boolean isRetrospectLink;

	/**
	 * 联动贷款客户编号
	 */
	@TableField(value="retrospect_link_no")
	private String retrospectLinkNo;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getScoreDataId() {
		return scoreDataId;
	}

	public String getTeamEmpNames() {
		return teamEmpNames;
	}

	public void setTeamEmpNames(String teamEmpNames) {
		this.teamEmpNames = teamEmpNames;
	}

	public void setScoreDataId(Long scoreDataId) {
		this.scoreDataId = scoreDataId;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public Double getRateScore() {
		return rateScore;
	}

	public void setRateScore(Double rateScore) {
		this.rateScore = rateScore;
	}

	public String getRateSharemanNo() {
		return rateSharemanNo;
	}

	public void setRateSharemanNo(String rateSharemanNo) {
		this.rateSharemanNo = rateSharemanNo;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Long getFallDept() {
		return fallDept;
	}

	public void setFallDept(Long fallDept) {
		this.fallDept = fallDept;
	}

	public String getMarketManNo() {
		return marketManNo;
	}

	public void setMarketManNo(String marketManNo) {
		this.marketManNo = marketManNo;
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

	public String getMasterNo() {
		return masterNo;
	}

	public void setMasterNo(String masterNo) {
		this.masterNo = masterNo;
	}

	public String getAssistantNo() {
		return assistantNo;
	}

	public void setAssistantNo(String assistantNo) {
		this.assistantNo = assistantNo;
	}

	public Boolean getIsRetrospectLink() {
		return isRetrospectLink;
	}

	public void setIsRetrospectLink(Boolean isRetrospectLink) {
		this.isRetrospectLink = isRetrospectLink;
	}

	public String getRetrospectLinkNo() {
		return retrospectLinkNo;
	}

	public void setRetrospectLinkNo(String retrospectLinkNo) {
		this.retrospectLinkNo = retrospectLinkNo;
	}

}
