package com.teamtek.admin.sys.bo;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.sargeraswang.util.ExcelUtil.ExcelCell;

/**
 * @ClassName: SysUser
 * @Description: 导入用户表临时实体
 * @author shenzhihao
 * @email zhihao@teamtek.cn
 * @date 2017年12月28日 上午10:02:43
 *
 */
public class SysUserBo {
	/**
	 * 工号
	 */
	@ExcelCell(index = 0)
	private String userNo;
	/**
	 * 用户真实姓名
	 */
	@ExcelCell(index = 1)
	private String name;

	/**
	 * 用户登录名
	 */
	@ExcelCell(index = 2)
	private String username;

	/**
	 * 性别
	 * 
	 * @see com.teamtek.admin.sys.entity.constant.CommConstant.Sex
	 */
	@ExcelCell(index = 3)
	private String sex;

	/**
	 * 邮箱
	 */
	@ExcelCell(index = 4)
	private String email;

	/**
	 * 手机号
	 */
	@ExcelCell(index = 5)
	private String mobile;
	@ExcelCell(index = 6)
	private String workPhone;
	@ExcelCell(index = 7)
	private String deptId;

	@ExcelCell(index = 8)
	private Date birthDate;

	public String getUsername() {
		if (username != null)
			return username.trim();
		return username;
	}

	public String getUserNo() {
		return userNo == null ? null : userNo.trim();
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		if (name != null)
			return name.trim();
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
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

	public String getDeptId() {
		return deptId == null ? null : deptId.trim();
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
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
