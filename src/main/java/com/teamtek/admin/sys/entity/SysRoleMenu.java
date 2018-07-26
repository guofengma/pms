package com.teamtek.admin.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @ClassName: SysRoleMenu
 * @Description: 角色•菜单 关联表实体
 * @author shenzhihao
 * @email zhihao@teamtek.cn
 * @date 2017年12月28日 上午10:02:30
 *
 */
@TableName("sys_role_menu")
public class SysRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 角色ID
	 */
	@TableField
	private Long roleId;

	/**
	 * 菜单code
	 */
	@TableField
	private Long menuId;

	public SysRoleMenu() {
	}

	public SysRoleMenu( Long roleId, Long menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}


	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", roleId:" + roleId + ", menuId:" + menuId + "}";
	}

}
