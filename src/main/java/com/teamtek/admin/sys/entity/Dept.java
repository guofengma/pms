package com.teamtek.admin.sys.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author syj
 * @since 2018-02-06 10:25:48
 */
@TableName("sys_dept")
public class Dept implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 部门码
	 */
	@TableField(value = "dept_code")
	private String deptCode;

	/**
	 * 部门名称
	 */
	@TableField(value = "dept_name")
	private String deptName;

	/**
	 * 父部门id
	 */
	@TableField(value = "parent_id")
	private Long parentId;

	/**
	 * 部门负责人id
	 */
	@TableField(value = "charge_man_id")
	private Long chargeManId;

	/**
	 * 部门电话
	 */
	@TableField(value = "link_phone")
	private String linkPhone;

	/**
	 * 部门email
	 */
	private String email;

	/**
	 * 世代部门码，以，分开
	 */
	private String ancestor;

	/**
	 * 当前部门所在级别
	 */
	private Integer lev;

	/**
	 * 部门信息
	 */
	private String remark;

	/**
	 * 
	 */
	@TableField(value = "create_time")
	private Date createTime;

	/**
	 * 
	 */
	@TableField(value = "last_edit_time")
	private Date lastEditTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getChargeManId() {
		return chargeManId;
	}

	public void setChargeManId(Long chargeManId) {
		this.chargeManId = chargeManId;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAncestor() {
		return ancestor;
	}

	public void setAncestor(String ancestor) {
		this.ancestor = ancestor;
	}

	public Integer getLev() {
		return lev;
	}

	public void setLev(Integer lev) {
		this.lev = lev;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
