package com.teamtek.admin.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.sys.dao.SysMenuDao;
import com.teamtek.admin.sys.dao.SysUserDao;
import com.teamtek.admin.sys.dao.SysUserPositionDao;
import com.teamtek.admin.sys.dao.SysUserRoleDao;
import com.teamtek.admin.sys.entity.SysMenu;
import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.admin.sys.entity.SysUserPosition;
import com.teamtek.admin.sys.entity.SysUserRole;
import com.teamtek.admin.sys.entity.constant.CommConstant;
import com.teamtek.admin.sys.service.SysUserService;
import com.teamtek.shiro.Constant;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Resource
	private Constant constant;
	@Autowired
	private SysUserPositionDao sysUserPositionDao;
	@Override
	@Transactional
	public void saveData(SysUser user, String roleIds) {
		this.insert(user);
		saveUserRole(roleIds,user.getUserId());
	}

	private void saveUserRole(String roleIds,Long userId) {
		if (StringUtils.isNoneBlank(roleIds)) {
			String[] codes = roleIds.split(",");

			for (String code : codes) {
				SysUserRole userRole = new SysUserRole();
				userRole.setRoleId(Long.valueOf(code));
				userRole.setUserId(userId);
				sysUserRoleDao.insert(userRole);
			}

		}
	}

	@Override
	@Transactional
	public void updateData(SysUser user, String roleIds) {
		SysUser db = this.selectById(user.getUserId());
		BeanUtils.copyProperties(user, db, "createTime", "lastLoginTime", "userId", "password","status","userNo");
		this.updateById(db);

		// 先删除 老 数据
		Map<String, Object> columnMap = new HashMap<>(1);
		columnMap.put("user_id", db.getUserId());
		this.sysUserRoleDao.deleteByMap(columnMap);

		saveUserRole(roleIds,db.getUserId());
	}

	@Override
	@Transactional
	public void deleteDataById(Long primaryKey) {
//		this.deleteById(primaryKey);
//		Map<String, Object> columnMap = new HashMap<>(1);
//		columnMap.put("user_id", primaryKey);
//		this.sysUserRoleDao.deleteByMap(columnMap);
		
		//添加  删除标志
		SysUser db = this.selectById(primaryKey);
		db.setStatus(CommConstant.UserStatus.del.getValue());
		this.updateById(db);
	}

	@Override
	@Transactional
	public void deleteBatchDataByIds(String[] primaryKeys) {
//		String[] ids = primaryKeys.split(",");
//		List<String> idList = Arrays.asList(ids);
//		this.deleteBatchIds(idList);
//
//		this.sysUserRoleDao.deleteByUserIds(ids);
		
		//添加  删除标志
		for(String id:primaryKeys){
			Long pk = Long.valueOf(id);
			//最高管理员账户不能被删除
			if(pk.equals(constant.adminId)){
				continue;
			}
			this.deleteDataById(pk);
		}

	}

	@Override
	@Transactional
	public void userAuthRole(String userId, String roleIds) {
		//先删除老的权限
		Map<String, Object> columnMap = new HashMap<>(1);
		columnMap.put("user_id", userId);
		this.sysUserRoleDao.deleteByMap(columnMap);
		
		if (StringUtils.isBlank(roleIds)) {
			String[] rIds = roleIds.split(",");
			for(String rid:rIds){
				SysUserRole sur=new SysUserRole(Long.valueOf(userId),Long.valueOf(rid));
				this.sysUserRoleDao.insert(sur);
			}
		}

	}

	@Override
	public List<SysMenu> queryAllPerms(Long userId) {
		Map<String, Object> columnMap = new HashMap<>(1);
		columnMap.put("user_id", userId);
		this.sysUserRoleDao.selectByMap(columnMap);
		Wrapper<SysMenu> wrapper=new EntityWrapper<>();
		StringBuilder sb =new StringBuilder(" menu_id in ( ");
		sb.append(" SELECT DISTINCT(rm.menu_id) FROM sys_role_menu rm  ");
		sb.append(" WHERE rm.role_id in (SELECT ur.role_id FROM sys_user_role ur WHERE ur.user_id={0}) ");
		sb.append(" ) ");
		wrapper.where(sb.toString(), userId);
//		Wrapper<SysMenu> wrapper2=new Wrapper<SysMenu>() {
//			
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public String getSqlSegment() {
//				StringBuilder sb =new StringBuilder(" WHERE menu_id in ( ");
//				sb.append(" SELECT DISTINCT(rm.menu_id) FROM sys_role_menu rm  ");
//				sb.append(" WHERE rm.role_id in (SELECT ur.role_id FROM sys_user_role ur WHERE ur.user_id="+userId+") ");
//				sb.append(" ) ");
//				return sb.toString();
//			}
//		};
		wrapper.orderBy("order_num", true);
		List<SysMenu> selectList = this.sysMenuDao.selectList(wrapper);
		
		return selectList;
	}

	@Override
	public SysUser queryByUserName(String username) {
		return this.baseMapper.queryByUserName(username);
	}

	@Override
	public List<String> queryAllUserNames() {
		return this.sysUserDao.queryAllUserNames();
	}

	@Override
	@Transactional
	public void updateUserPosition(Long userId, String positionIdsStr) {
		//删除
		Wrapper<SysUserPosition> wrapper=new EntityWrapper<>();
		wrapper.eq("user_id", userId);
		this.sysUserPositionDao.delete(wrapper);
		if(StringUtils.isNotBlank(positionIdsStr)){
			//添加新的岗位
			String[] positionIds=positionIdsStr.split(",");
			for(String pid:positionIds){
				SysUserPosition sup=new SysUserPosition();
				sup.setPositionId(Long.valueOf(pid));
				sup.setUserId(userId);
				this.sysUserPositionDao.insert(sup);
			}
		}
		
		
		
	}

	@Override
	public List<SysUser> selectBySex(Integer i, String name) {
		// TODO Auto-generated method stub
		return this.sysUserDao.selectBySex(i,name);
	}

}
