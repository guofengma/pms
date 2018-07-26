package com.teamtek.admin.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
* @ClassName: SysRole
* @Description: 角色表实体
* @author shenzhihao 
* @email zhihao@teamtek.cn
* @date 2017年12月28日 上午10:02:18
*
*/ 
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField
    private String roleName;

    /**
     * 备注
     */
    @TableField
    private String remark;

    /**
     * 创建时间
     */
    @TableField
    private Date createTime;

    @TableField(exist = false)
    private List<Long> menuIdList;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public List<Long> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}

	@Override
	public String toString() {
		return "{roleId:" + roleId + ", roleName:" + roleName + ", remark:" + remark + ", createTime:" + createTime
				+ ", menuIdList:" + menuIdList + "}";
	}
    
    
}
