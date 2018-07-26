package com.teamtek.admin.score.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 积分 数据导入 （营销项目）
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
@TableName("score_market_project")
public class ScoreMarketProject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 业务数据id
	 */
	@TableField(value = "score_data_id")
	private Long scoreDataId;
	/**
	 * 放款时间
	 */
	@TableField(value = "loan_date")
	private Date loanDate;

	/**
	 * 落地部门
	 */
	@TableField(value = "fall_dept")
	private Long fallDept;

	/**
	 * 项目名称
	 */
	@TableField(value = "product_id")
	private Long productId;

	/**
	 * 
	 */
	@TableField(value = "customer_name")
	private String customerName;

	/**
	 * 客户身份证/营业执照号码
	 */
	@TableField(value = "id_card")
	private String idCard;

	/**
	 * 营销人员工号
	 */
	@TableField(value = "market_man_no")
	private String marketManNo;

	/**
	 * 借据号（属于联动填写,非联动项目不填）
	 */
	@TableField(value = "loan_no")
	private String loanNo;
	/**
	 * 积分量
	 */
	@TableField(value = "score_amount")
	private String scoreAmount;
	/**
	 * 积分量的单位(个等)
	 */
	private String unit;

	/**
	 * 业务数量(有可能是个 区间 故这里存为string)
	 */
	@TableField(value = "biz_amount")
	private String bizAmount;

	/**
	 * 业务量 的单位
	 */
	@TableField(value = "biz_unit")
	private String bizUnit;

	@TableField(value = "remark")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getScoreAmount() {
		return scoreAmount;
	}

	public void setScoreAmount(String scoreAmount) {
		this.scoreAmount = scoreAmount;
	}

	public Long getScoreDataId() {
		return scoreDataId;
	}

	public void setScoreDataId(Long scoreDataId) {
		this.scoreDataId = scoreDataId;
	}

	public String getBizUnit() {
		return bizUnit;
	}

	public void setBizUnit(String bizUnit) {
		this.bizUnit = bizUnit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setBizAmount(String bizAmount) {
		this.bizAmount = bizAmount;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Long getFallDept() {
		return fallDept;
	}

	public void setFallDept(Long fallDept) {
		this.fallDept = fallDept;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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
		return marketManNo;
	}

	public void setMarketManNo(String marketManNo) {
		this.marketManNo = marketManNo;
	}

	public String getLoanNo() {
		return loanNo;
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


}
