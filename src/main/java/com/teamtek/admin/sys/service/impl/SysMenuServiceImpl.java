package com.teamtek.admin.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.sys.dao.SysMenuDao;
import com.teamtek.admin.sys.dao.SysRoleMenuDao;
import com.teamtek.admin.sys.entity.SysMenu;
import com.teamtek.admin.sys.service.SysMenuService;
import com.teamtek.admin.sys.service.SysUserService;
import com.teamtek.core.ann.MenuCacheComponent;
import com.teamtek.shiro.Constant;
import com.teamtek.shiro.EhCacheNames;
import com.teamtek.shiro.util.EhcacheUtil;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

	// @Override
	// @Transactional
	// public void deleteDataById(Long primaryKey) {
	//
	// this.deleteById(primaryKey);
	//
	// deleteChilds(primaryKey);
	//
	// }
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Resource
	private EhcacheUtil ehcacheUtil;
	@Resource
	private Constant constant;

	@Autowired
	SysUserService sysUserService;

	/**
	 * 删除子菜单、菜单下的按钮
	 * 
	 * @param primaryKey
	 */
	private void deleteChilds(String pId) {
		Map<String, Object> columnMap = new HashMap<String, Object>(1);
		columnMap.put("p_id", pId);
		List<SysMenu> childs = this.selectByMap(columnMap);
		if (childs != null && childs.size() > 0) {
			for (SysMenu menu : childs) {
				this.deleteById(menu.getMenuId());// 删除按钮
				Map<String, Object> map = new HashMap<>(1);
				columnMap.put("menu_id", menu.getMenuId());
				this.sysRoleMenuDao.deleteByMap(map);// 删除按钮对应角色
				deleteChilds(menu.getpId());
			}
		}
	}

	@Override
	@Transactional
	public void updateBatchMenus(String[] ids, String[] pIds, String[] names, String[] types, String[] permss,String[] orderNums) {
		// List<SysMenu> dbMenu = this.selectList(new Wrapper<SysMenu>() {
		//
		// private static final long serialVersionUID = 1L;
		//
		// @Override
		// public String getSqlSegment() {
		// return " where type=1 or type=0 ";
		// }
		// });
		EntityWrapper<SysMenu> wrapper = new EntityWrapper<>();
		wrapper.where("type={0}", SysMenu.MenuType.directory.value()).or("type={0}", SysMenu.MenuType.menu.value());
		List<SysMenu> dbMenu = this.selectList(wrapper);
		List<String> temp = new ArrayList<>();
		for (SysMenu menu : dbMenu) {
			if (ArrayUtils.contains(permss, menu.getPerms())) {// 1.更新(只更新菜单)
				temp.add(menu.getPerms());
				int index = ArrayUtils.indexOf(permss, menu.getPerms());
				menu.setId(ids[index]);
				menu.setpId(pIds[index]);
				menu.setName(names[index]);
				menu.setOrderNum(Integer.valueOf(orderNums[index]));
				
				Integer menuType = Integer.valueOf(types[index]);
				menu.setType(menuType);
				menu.setPerms(permss[index]);
				if(StringUtils.isBlank(menu.getIviewIcon())){
					menu.setIviewIcon(SysMenu.MenuType.getEnumByValue(menuType).getIcon());
				}
				this.updateById(menu);
			} else {// 2.删除(删除菜单，以及菜单下的所有按钮)
				this.deleteById(menu.getMenuId());// 2.1删除菜单
				deleteChilds(menu.getId());// 2.2删除菜单下按钮
				// 2.3删除menu对应的角色授权
				Map<String, Object> columnMap = new HashMap<>(1);
				columnMap.put("menu_id", menu.getMenuId());
				this.sysRoleMenuDao.deleteByMap(columnMap);
			} 
		}

		// 3.添加(只添加菜单)
		String[] tempPermss = new String[temp.size()];
		temp.toArray(tempPermss);
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isBlank(ids[i])) {
				break;
			}

			if (ArrayUtils.contains(tempPermss, permss[i])) {// 前面操作步骤1 已经更新过
																// 因此这里跳过
				continue;
			}
			Integer type = Integer.valueOf(types[i]);
			SysMenu sysMenu = new SysMenu(ids[i], pIds[i], names[i], permss[i], null, type);
			sysMenu.setIviewIcon(SysMenu.MenuType.getEnumByValue(type).getIcon());
			sysMenu.setOrderNum(Integer.valueOf(orderNums[i]));
			this.insert(sysMenu);
		}
	}

	@Override
	@Transactional
	public void updateCrruMenuBtn(SysMenu menu) {
		Map<String, Object> map = new HashMap<>(2);
		map.put("type", SysMenu.MenuType.button.value());
		map.put("p_id", menu.getId());
		// 该菜单保存到数据库中的 所有按钮
		List<SysMenu> dbMenuBtns = this.selectByMap(map);
		Map<String, SysMenu> temp = new HashMap<>();
		for (SysMenu m : dbMenuBtns) {
			temp.put(m.getPerms(), m);
		}
		// 该菜单实际 所有按钮
		List<SysMenu> btns = MenuCacheComponent.menu.get(menu.getPerms()).getChildren();// 从缓存加载該菜单下所有按钮
		for (SysMenu m : btns) {
			if (temp.containsKey(m.getPerms())) {// 更新
				SysMenu dbBtn = temp.get(m.getPerms());
				dbBtn.setName(m.getName());
				dbBtn.setPerms(m.getPerms());
				dbBtn.setId(m.getId());
				this.updateById(dbBtn);
				temp.remove(m.getPerms());// 用于删除
			} else {// 添加
				SysMenu btn = new SysMenu(m.getId(), menu.getId(), m.getName(), m.getPerms(), null, m.getType());// 按鈕不用
																													// 排序
				this.insert(btn);
			}
		}
		for (Map.Entry<String, SysMenu> en : temp.entrySet()) {
			this.deleteById(en.getValue().getMenuId());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysMenu> getUserMenuList(Long userId) {
		String cacheName = EhCacheNames.menuCacheName + userId;
		Object object = ehcacheUtil.get(EhcacheUtil.ADMINMENUEHCACHENAME, cacheName);
		if (null != object) {
			if (object instanceof List) {
				logger.info("用户菜单列表从EhCache缓存拿值");
				return (List<SysMenu>) object;
			}
		}
		// 系统管理员，拥有最高权限
		if (userId.equals(constant.adminId)) {
			Wrapper<SysMenu> wrapper=new EntityWrapper<>();
			wrapper.orderBy("order_num", true);
			List<SysMenu> allMenuList = this.selectList(wrapper);
			ehcacheUtil.put(EhcacheUtil.ADMINMENUEHCACHENAME, cacheName, allMenuList);
			return allMenuList;
		}
		// 用户菜单列表
		List<SysMenu> allMenuList = sysUserService.queryAllPerms(userId);
		ehcacheUtil.put(EhcacheUtil.ADMINMENUEHCACHENAME, cacheName, allMenuList);
		return allMenuList;
	}

}
