package com.teamtek.admin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.teamtek.admin.sys.entity.SysRoleMenu;

public interface SysRoleMenuService extends IService<SysRoleMenu> {


	void updateByMenuIds(String[] menuIds, Long roleId);

}
