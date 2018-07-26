package com.teamtek.admin.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IEnum;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @ClassName: SysUser
 * @Description: 用户表实体
 * @author shenzhihao
 * @email zhihao@teamtek.cn
 * @date 2017年12月28日 上午10:02:43
 *
 */
@TableName("sys_user")
public class SysUser implements Serializable {

	public enum Type implements IEnum {
		a, b;

		@Override
		public Serializable getValue() {
			return this.ordinal();
		}

	}

	private static final long serialVersionUID = 1L;

	private Type type;
	/**
	 * 用户ID
	 */
	@TableId(type = IdType.AUTO)
	private Long userId;

	@TableField
	private String userNo;

	/**
	 * 用户登录名
	 */
	@TableField
	@NotEmpty(message = "用户名不能为空")
	private String username;

	/**
	 * 用户真实姓名
	 */
	@TableField
	private String name;
	/**
	 * 密码
	 */
	@TableField
	private String password;

	/**
	 * 性别
	 * 
	 * @see com.teamtek.admin.sys.entity.constant.CommConstant.Sex
	 */
	@TableField
	@NotNull(message = "性别不能为空")
	private Integer sex;

	/**
	 * 邮箱
	 */
	@TableField
	@Email
	private String email;

	/**
	 * 手机号
	 */
	@TableField
	private String mobile;

	/**
	 * 最后登录时间
	 */
	@TableField
	private Date lastLoginTime;

	/**
	 * 最后登录IP
	 */
	@TableField
	private String lastLoginIp;

	/**
	 * 头像缩略图地址
	 */
	private String avatarUrl;

	/**
	 * 状态
	 * 
	 * @see com.teamtek.admin.sys.entity.constant.CommConstant.UserStatus
	 */
	@TableField
	@NotNull(message = "状态不能为空")
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField
	private Date createTime;

	@TableField
	private Long deptId;

	@TableField
	private String workPhone;

	@TableField
	private Date birthDate;

	@TableField(exist = false)
	private List<SysPosition> positions;

	public SysUser() {
		super();
	}

	public SysUser(String username, String name) {
		super();
		this.username = username;
		this.name = name;
	}

	public List<SysPosition> getPositions() {
		return positions;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public void setPositions(List<SysPosition> positions) {
		this.positions = positions;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
