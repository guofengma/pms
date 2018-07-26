package com.teamtek.admin.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.core.dao.BaseDao;

public interface SysUserDao extends BaseDao<SysUser>{
	/**
	 * 根据用户名，查询系统用户
	 * 
	 * @param username
	 *            用户名
	 * @return 用户名
	 */
	SysUser queryByUserName(String username);

	List<String> queryAllUserNames();

	List<SysUser> selectBySex(@Param("sex") Integer i, @Param("name")String name);
}
