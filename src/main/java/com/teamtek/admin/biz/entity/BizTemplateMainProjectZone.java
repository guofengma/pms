package com.teamtek.admin.biz.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 模板主项目区域表
 * </p>
 *
 * @author syj
 * @since 2018-03-14 18:03:33
 */
@TableName("biz_template_main_project_zone")
public class BizTemplateMainProjectZone implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 规则模板ID
	 */
	@TableField(value="rule_template_id")
	private Long ruleTemplateId;

	/**
	 * 主项目ID
	 */
	@TableField(value="main_porject_id")
	private Long mainPorjectId;

	/**
	 * 0:无配置;1:已配置
	 */
	@TableField(value="configure_status")
	private Integer configureStatus;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRuleTemplateId() {
		return ruleTemplateId;
	}

	public void setRuleTemplateId(Long ruleTemplateId) {
		this.ruleTemplateId = ruleTemplateId;
	}

	public Long getMainPorjectId() {
		return mainPorjectId;
	}

	public void setMainPorjectId(Long mainPorjectId) {
		this.mainPorjectId = mainPorjectId;
	}

	public Integer getConfigureStatus() {
		return configureStatus;
	}

	public void setConfigureStatus(Integer configureStatus) {
		this.configureStatus = configureStatus;
	}

}
