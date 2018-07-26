package com.teamtek.admin.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.sys.dao.SysRoleMenuDao;
import com.teamtek.admin.sys.entity.SysRoleMenu;
import com.teamtek.admin.sys.service.SysRoleMenuService;

@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {

	@Override
	@Transactional
	public void updateByMenuIds(String[] menuIds, Long roleId) {
		
		Map<String, Object> columnMap = new HashMap<>(1);
		columnMap.put("role_id", roleId);
		List<SysRoleMenu> dbRoleMenus = this.selectByMap(columnMap);

		Map<Long, Object> map = new HashMap<>();
		for (SysRoleMenu db : dbRoleMenus) {
			if (ArrayUtils.contains(menuIds, db.getMenuId())) {// 授予过 则跳过
				map.put(db.getMenuId(), db);
				continue;
			}
			this.deleteById(db.getId());
		}
		
		for (String menuId : menuIds) {
			if(map.containsKey(menuId)){
				continue;
			}
			SysRoleMenu entity = new SysRoleMenu(roleId, Long.valueOf(menuId));
			this.insert(entity);
		}
		
	}
}
