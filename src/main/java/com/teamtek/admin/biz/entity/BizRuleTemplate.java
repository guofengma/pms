package com.teamtek.admin.biz.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 规则模板表
 * </p>
 *
 * @author syj
 * @since 2018-03-14 18:03:33
 */
@TableName("biz_rule_template")
public class BizRuleTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 模板ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 模板名称
	 */
	private String name;

	/**
	 * 0:正本;1:副本
	 */
	private Integer type;

	/**
	 * 0:未发布;1:已发布
	 */
	@TableField(value="publish_status")
	private Integer publishStatus;

	/**
	 * 岗位ID
	 */
	@TableField(value="pos_id")
	private Long posId;

	/**
	 * 考核系数
	 */
	@TableField(value="assess_coefficient")
	private Double assessCoefficient;

	/**
	 * 创建时间
	 */
	@TableField(value="create_time")
	private Date createTime;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

	public Double getAssessCoefficient() {
		return assessCoefficient;
	}

	public void setAssessCoefficient(Double assessCoefficient) {
		this.assessCoefficient = assessCoefficient;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
