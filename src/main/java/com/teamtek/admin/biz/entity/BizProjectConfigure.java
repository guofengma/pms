package com.teamtek.admin.biz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 项目规则配置表
 * </p>
 *
 * @author syj
 * @since 2018-03-15 20:45:37
 */
@TableName("biz_project_configure")
public class BizProjectConfigure implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 项目ID
	 */
	@TableField(value="project_id")
	private Long projectId;

	/**
	 * 规则详情ID
	 */
	@TableField(value="rule_detail_id")
	private Long ruleDetailId;

	/**
	 * 主项目区域ID
	 */
	@TableField(value="zone_id")
	private Long zoneId;
	/**
	 * 配置类型
	 */
	@TableField(value="configure_type")
	private Integer configureType;
	
	/**
	 * 组合项目规则配置ID
	 */
	@TableField(value="group_project_configure_id")
	private Long groupProjectConfigureId;
	
	/**
	 * 组合项目引用规则配置ID
	 */
	@TableField(value="group_quote_project_configure_id")
	private Long groupQuoteProjectConfigureId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getRuleDetailId() {
		return ruleDetailId;
	}

	public void setRuleDetailId(Long ruleDetailId) {
		this.ruleDetailId = ruleDetailId;
	}

	public Long getZoneId() {
		return zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public Integer getConfigureType() {
		return configureType;
	}

	public void setConfigureType(Integer configureType) {
		this.configureType = configureType;
	}

	public Long getGroupProjectConfigureId() {
		return groupProjectConfigureId;
	}

	public void setGroupProjectConfigureId(Long groupProjectConfigureId) {
		this.groupProjectConfigureId = groupProjectConfigureId;
	}

	public Long getGroupQuoteProjectConfigureId() {
		return groupQuoteProjectConfigureId;
	}

	public void setGroupQuoteProjectConfigureId(Long groupQuoteProjectConfigureId) {
		this.groupQuoteProjectConfigureId = groupQuoteProjectConfigureId;
	}
}
