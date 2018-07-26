package com.teamtek.admin.sys.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author syj
 * @since 2018-03-12 15:57:00
 */
@TableName("sys_log")
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户ID
	 */
	@TableField(value = "user_id")
	private Long userId;

	/**
	 * 用户真实姓名
	 */
	private String name;

	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 操作时间
	 */
	@TableField(value = "oper_time")
	private Date operTime;

	/**
	 * 操作行为
	 */
	@TableField(value = "oper_desc")
	private String operDesc;

	/**
	 * 操作url
	 */
	@TableField(value = "oper_url")
	private String operUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public String getOperDesc() {
		return operDesc;
	}

	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}

	public String getOperUrl() {
		return operUrl;
	}

	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}

	public SysLog(Long userId, String name, String username, Date operTime, String operDesc, String operUrl) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.operTime = operTime;
		this.operDesc = operDesc;
		this.operUrl = operUrl;
	}

	public SysLog() {
		super();
	}

}
