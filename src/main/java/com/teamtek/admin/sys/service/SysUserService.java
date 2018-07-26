package com.teamtek.admin.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.teamtek.admin.sys.entity.SysMenu;
import com.teamtek.admin.sys.entity.SysUser;

public interface SysUserService extends IService<SysUser>{

	void saveData(SysUser user, String roleIds);

	void updateData(SysUser user, String roleIds);

	void deleteDataById(Long primaryKey);

	/**
	 * 批量删除（非物理删除） 多个 id以 逗号分隔
	 * @param primaryKeys
	 */
	void deleteBatchDataByIds(String[] primaryKeys);

	void userAuthRole(String userId, String roleIds);

	/**
	 * 查询用户 所有的权限资源 （菜单 和 按钮 ）
	 * @param userId
	 * @return
	 */
	List<SysMenu> queryAllPerms(Long userId);

	/**
	 * 根据登录名查询用户  （登录认证接口）
	 * @param username
	 * @return
	 */
	SysUser queryByUserName(String username);

	/**
	 * 查询所有  用户  的  登录名
	 * @return
	 */
	List<String> queryAllUserNames();

	/**
	 * 更新用户岗位
	 * @param userId
	 * @param positionIdsStr
	 */
	void updateUserPosition(Long valueOf, String positionIdsStr);

	List<SysUser> selectBySex(Integer i, String string);
	
	

}
