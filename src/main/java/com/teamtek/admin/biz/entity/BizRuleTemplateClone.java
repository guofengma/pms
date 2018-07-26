package com.teamtek.admin.biz.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 规则模板克隆表
 * </p>
 *
 * @author syj
 * @since 2018-03-14 18:03:33
 */
@TableName("biz_rule_template_clone")
public class BizRuleTemplateClone implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 正本模板ID
	 */
	@TableField(value="original_rule_id")
	private Long originalRuleId;

	/**
	 * 副本模板ID
	 */
	@TableField(value="copy_rule_id")
	private Long copyRuleId;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOriginalRuleId() {
		return originalRuleId;
	}

	public void setOriginalRuleId(Long originalRuleId) {
		this.originalRuleId = originalRuleId;
	}

	public Long getCopyRuleId() {
		return copyRuleId;
	}

	public void setCopyRuleId(Long copyRuleId) {
		this.copyRuleId = copyRuleId;
	}

}
