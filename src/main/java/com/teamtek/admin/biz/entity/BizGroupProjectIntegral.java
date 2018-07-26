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
 * 组合项目规则积分表
 * </p>
 *
 * @author syj
 * @since 2018-03-15 20:45:37
 */
@TableName("biz_group_project_integral")
public class BizGroupProjectIntegral implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 组合项目规则配置ID
	 */
	@TableField(value="group_project_configure_id")
	private Long groupProjectConfigureId;

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

	public Long getGroupProjectConfigureId() {
		return groupProjectConfigureId;
	}

	public void setGroupProjectConfigureId(Long groupProjectConfigureId) {
		this.groupProjectConfigureId = groupProjectConfigureId;
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

	public BigDecimal getAdjustedIntegral() {
		return adjustedIntegral;
	}

	public void setAdjustedIntegral(BigDecimal adjustedIntegral) {
		this.adjustedIntegral = adjustedIntegral;
	}
}
