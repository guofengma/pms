package com.teamtek.admin.sys.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @ClassName: SysMenu
 * @Description: 菜单表实体
 * @author shenzhihao
 * @email zhihao@teamtek.cn
 * @date 2017年12月28日 上午10:01:47
 *
 */
@TableName("sys_menu")
public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum MenuType {
		directory(0, "目录","ios-folder"), 
		menu(1, "菜单","ios-list-outline"), 
		button(2, "按钮","");
		private int value;
		private final String title;
		//icon 菜单、目录 默认图标
		//按钮 无图标
		private final String icon;

		MenuType(int value, String title,String icon) {
			this.value = value;
			this.title = title;
			this.icon=icon;
		}


		public String getIcon() {
			return icon;
		}


		public String getTitle() {
			return title;
		}

		public int value() {
			return value;
		}

		public static MenuType getEnumByValue(int value) {
			for (MenuType e : MenuType.values()) {
				if (e.value() == value) {
					return e;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			MenuType e = getEnumByValue(value);
			return e == null ? null : e.title;
		}
	}

	
	//目录、菜单 默认 icon 
//	public static final String DEFAULT_ICON = "ios-folder";
//	public static final int directory = 0;
//	public static final int menu = 1;
//	public static final int button = 2;

	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long menuId;
	/**
	 * 菜单ID
	 */
	@TableField
	private String id;

	/**
	 * 父菜单ID，一级菜单为0
	 */
	@TableField
	private String pId;

	/**
	 * 父菜单名称
	 */
	@TableField(exist = false)
	private String parentName;

	/**
	 * 菜单名称
	 */
	@TableField
	private String name;

	/**
	 * 菜单URL
	 */
	@TableField
	private String url;
	/**
	 * 状态 0：禁用 1：正常
	 */
	@TableField
	@NotNull(message = "状态不能为空")
	private Integer status;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	@TableField
	private String perms;

	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	@TableField
	private Integer type;

	/**
	 * 菜单图标 (注意：存放的是iview 的icon 并不是 ztree的icon)
	 */
	@TableField
	private String iviewIcon;

	/**
	 * 排序
	 */
	@TableField
	private Integer orderNum;

	/**
	 * ztree属性
	 */
	@TableField(exist = false)
	private Boolean open;
	@TableField(exist = false)
	private Boolean checked;
	@TableField(exist = false)
	private Boolean drag;
	@TableField(exist = false)
	private List<SysMenu> children = Collections.emptyList();

	public SysMenu() {
	}

	public SysMenu(String name, String url, String perms, Integer type) {
		this.name = name;
		this.url = url;
		this.perms = perms;
		this.type = type;
	}

	public SysMenu(String id, String pId, String name, String perms, Integer orderNum, Integer type) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.perms = perms;
		this.orderNum = orderNum;
		this.type = type;
	}

	public Boolean getDrag() {
		return drag;
	}

	public void setDrag(Boolean drag) {
		this.drag = drag;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public String getIviewIcon() {
		return iviewIcon;
	}

	public void setIviewIcon(String iviewIcon) {
		this.iviewIcon = iviewIcon;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "{id:" + id + ", pId:" + pId + ", name:" + name + ", url:" + url + ", perms:" + perms + ", type:" + type
				+ "}";
	}


}
