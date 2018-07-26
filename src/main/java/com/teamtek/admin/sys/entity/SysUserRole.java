package com.teamtek.admin.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @ClassName: SysUserRole
 * @Description: 用户•角色 关联表实体
 * @author shenzhihao
 * @email zhihao@teamtek.cn
 * @date 2017年12月28日 上午10:02:53
 *
 */
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 用户ID
	 */
	@TableField
	private Long userId;

	/**
	 * 角色ID
	 */
	@TableField
	private Long roleId;

	public SysUserRole() {
	}

	public SysUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
