package com.teamtek.admin.biz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 规则详情表
 * </p>
 *
 * @author syj
 * @since 2018-03-15 20:45:37
 */
@TableName("biz_rule_detail")
public class BizRuleDetail implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 规则类别ID
	 */
	@TableField(value="rule_id",strategy=FieldStrategy.NOT_NULL)
	private Integer ruleId;

	/**
	 * 获得积分数(X为积分)
	 */
	private Double integral;

	/**
	 * 比例(P为比例)
	 */
	private Double scale;
	
	/**
	 * 左比例
	 */
	@TableField(value="left_scale")
	private Double leftScale;
	
	/**
	 * 右比例
	 */
	@TableField(value="right_scale")
	private Double rightScale;

	/**
	 * 左区间数(Y为区间,Y1为左区间数)
	 */
	@TableField(value="left_section")
	private Double leftSection;

	/**
	 * 右区间数(Y为区间,Y2为右区间数)
	 */
	@TableField(value="rigth_section")
	private Double rigthSection;

	/**
	 * 首加减分(Z为加减分)
	 */
	@TableField(value="first_add_sub")
	private Double firstAddSub;
	/**
	 * 次加减分
	 */
	@TableField(value="second_add_sub")
	private Double secondAddSub;

	/**
	 * 销售额达标条件(S为销售额)
	 */
	private Double sales;
	
	/**
	 * 基础项目的销售额
	 */
	private Double baseProjectSales;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
	}

	public Double getLeftScale() {
		return leftScale;
	}

	public void setLeftScale(Double leftScale) {
		this.leftScale = leftScale;
	}

	public Double getRightScale() {
		return rightScale;
	}

	public void setRightScale(Double rightScale) {
		this.rightScale = rightScale;
	}

	public Double getLeftSection() {
		return leftSection;
	}

	public void setLeftSection(Double leftSection) {
		this.leftSection = leftSection;
	}

	public Double getRigthSection() {
		return rigthSection;
	}

	public void setRigthSection(Double rigthSection) {
		this.rigthSection = rigthSection;
	}

	public Double getFirstAddSub() {
		return firstAddSub;
	}

	public void setFirstAddSub(Double firstAddSub) {
		this.firstAddSub = firstAddSub;
	}

	public Double getSecondAddSub() {
		return secondAddSub;
	}

	public void setSecondAddSub(Double secondAddSub) {
		this.secondAddSub = secondAddSub;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	public Double getBaseProjectSales() {
		return baseProjectSales;
	}

	public void setBaseProjectSales(Double baseProjectSales) {
		this.baseProjectSales = baseProjectSales;
	}
}
