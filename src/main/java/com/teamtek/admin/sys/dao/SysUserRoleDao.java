package com.teamtek.admin.sys.dao;

import com.teamtek.admin.sys.entity.SysUserRole;
import com.teamtek.core.dao.BaseDao;

public interface SysUserRoleDao extends BaseDao<SysUserRole>{

	void deleteByUserIds();

	void deleteByUserIds(String[] userIds);

	
}
