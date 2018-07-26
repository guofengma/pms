package com.teamtek.admin.biz.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 小计规则详情表
 * </p>
 *
 * @author syj
 * @since 2018-03-19 16:45:13
 */
@TableName("biz_sub_project_configure")
public class BizSubProjectConfigure implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 规则ID
	 */
	@TableField(value="rule_detail_id")
	private Long ruleDetailId;

	/**
	 * 主项目区域ID
	 */
	@TableField(value="zone_id")
	private Long zoneId;

	/**
	 * 类型
	 */
	private Integer type;

	/**
	 * 项目ID
	 */
	@TableField(value="project_id")
	private Long projectId;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
