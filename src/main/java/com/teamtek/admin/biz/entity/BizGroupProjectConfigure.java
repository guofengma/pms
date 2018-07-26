package com.teamtek.admin.biz.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 组合项目规则配置表
 * </p>
 *
 * @author syj
 * @since 2018-03-15 20:45:37
 */
@TableName("biz_group_project_configure")
public class BizGroupProjectConfigure implements Serializable {

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
}
