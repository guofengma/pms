package com.teamtek.admin.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.sys.dao.SysRoleDao;
import com.teamtek.admin.sys.dao.SysRoleMenuDao;
import com.teamtek.admin.sys.entity.SysRole;
import com.teamtek.admin.sys.service.SysRoleService;
import com.teamtek.shiro.util.EhcacheUtil;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Resource
	private EhcacheUtil ehcacheUtil;

	@Override
	@Transactional
	public void deleteDataById(Long primaryKey) {
		this.deleteById(primaryKey);
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("role_id", primaryKey);
		this.sysRoleMenuDao.deleteByMap(columnMap);

		//   清空所有缓存
		ehcacheUtil.removeAllMenuCache();
	}

}
