package com.teamtek.admin.biz.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 小计规则积分表
 * </p>
 *
 * @author syj
 * @since 2018-03-19 16:34:53
 */
@TableName("biz_sub_project_integral")
public class BizSubProjectIntegral implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 小计规则配置ID
	 */
	@TableField(value="sub_project_configure_id")
	private Long subProjectConfigureId;

	/**
	 * 用户ID
	 */
	@TableField(value="user_id")
	private Long userId;

	/**
	 * 当前月份
	 */
	@TableField(value="curr_date")
	private Date currDate;

	/**
	 * 规则得分
	 */
	private Double integral;

	/**
	 * 调整得分
	 */
	@TableField(value="adjusted_integral")
	private BigDecimal adjustedIntegral;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSubProjectConfigureId() {
		return subProjectConfigureId;
	}

	public void setSubProjectConfigureId(Long subProjectConfigureId) {
		this.subProjectConfigureId = subProjectConfigureId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public Double getIntegral() {
		return integral;
	}

	public void setIntegral(Double integral) {
		this.integral = integral;
	}

	public BigDecimal getAdjustedIntegral() {
		return adjustedIntegral;
	}

	public void setAdjustedIntegral(BigDecimal adjustedIntegral) {
		this.adjustedIntegral = adjustedIntegral;
	}
}
