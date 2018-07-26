package com.teamtek.admin.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.teamtek.admin.sys.entity.SysRole;

public interface SysRoleService extends IService<SysRole>{

	void deleteDataById(Long primaryKey);

}
