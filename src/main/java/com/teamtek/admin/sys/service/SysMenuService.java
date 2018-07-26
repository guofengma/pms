package com.teamtek.admin.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.teamtek.admin.sys.entity.SysMenu;

public interface SysMenuService extends IService<SysMenu> {

//	void deleteDataById(Long primaryKey);

	/**
	 * 
	 * @param ids ztree 节点id
	 * @param pIds ztree 节点pid
	 * @param names ztree 节点名称
	 * @param types
	 * @param permss
	 */
	void updateBatchMenus(String[] ids, String[] pIds, String[] names, String[] types, String[] permss,String[] orderNums);

	void updateCrruMenuBtn(SysMenu menu);

	 List<SysMenu> getUserMenuList(Long userId);
}
