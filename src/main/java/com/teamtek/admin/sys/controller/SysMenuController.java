package com.teamtek.admin.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.teamtek.admin.sys.entity.SysMenu;
import com.teamtek.admin.sys.service.SysMenuService;
import com.teamtek.core.ann.MenuCacheComponent;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;
import com.teamtek.shiro.util.EhcacheUtil;

@RestController
@RequestMapping("/admin/menu")
@RequiresPermissions("menu")
@MenuOrBtnAnnotation(menu = "菜单管理", code = "menu")
public class SysMenuController extends BaseController<SysMenu> {

	@Autowired
	private SysMenuService sysMenuService;
	@Resource
	private EhcacheUtil ehcacheUtil;
	/**
	 * 保存菜单
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/save")
	public ResponseEntity save(@RequestBody Record param) {

		String[] ids = param.get("id", "").toString().split(",");
		String[] pIds = param.get("pId", "").toString().split(",");
		String[] names = param.get("name", "").toString().split(",");
		String[] types = param.get("type", "").toString().split(",");
		String[] orderNums = param.get("orderNum", "").toString().split(",");
		String[] permss = param.get("perms", "").toString().split(",");// perms对应类名注解@MenuOrBtnAnnotation中的code
																		// 在这里当作权限码是使用
		if (!ArrayUtils.isSameLength(ids, pIds) || !ArrayUtils.isSameLength(ids, names)
				|| !ArrayUtils.isSameLength(ids, permss)|| !ArrayUtils.isSameLength(ids, orderNums) || !ArrayUtils.isSameLength(ids, types)) {
			throw new RuntimeException("菜单保存出错，查看入参长度是否一致！");
		}

		// 1.菜单 更新
		this.sysMenuService.updateBatchMenus(ids, pIds, names, types, permss,orderNums);

		Map<String, Object> map = new HashMap<>(1);
		map.put("type", SysMenu.MenuType.menu.value());
		List<SysMenu> dbMenu = this.sysMenuService.selectByMap(map);

		// 2.按鈕更新
		for (SysMenu menu : dbMenu) {
			if(menu.getPerms().startsWith("directory")){
				continue;
			}
			this.sysMenuService.updateCrruMenuBtn(menu);
		}

		//系统模块已经改变 资源权限也可能发生变化  ，需 清空所有缓存
		ehcacheUtil.removeAllMenuCache();
		return ResponseEntity.ok();
	}

	private static final String system_module_root="功能模块";//保存在系统缓存中
	private static final String system_menu_root="系统菜单";//保存在数据库中
	/**
	 * 获取菜单 树
	 * 
	 * @return
	 */
	@RequestMapping("/getSystemMenuTree")
	public ResponseEntity getSystemMenuTree() {
		Wrapper<SysMenu> wrapper=new EntityWrapper<>();
		wrapper.where("type={0}", SysMenu.MenuType.menu.value()).or("type={0}", SysMenu.MenuType.directory.value());
		wrapper.orderBy("order_num", true);
		List<SysMenu> tree = this.baseService.selectList(wrapper);
		for(SysMenu menu:tree){
			if(menu.getType()==SysMenu.MenuType.directory.value()){
				menu.setOpen(true);
			}
		}
		SysMenu root = new SysMenu("0", "0", system_menu_root,null, null, null);
		root.setDrag(false);
		root.setOpen(true);
		tree.add(root);
		return ResponseEntity.ok().putData(tree);
	}

	/**
	 * 从缓存 获取模块菜单 树
	 * @param type
	 * @return
	 */
	@RequestMapping("/getMenuTree")
	public ResponseEntity getMenuTree( String type) {
		//先加载数据库 中已有菜单
		Map<String,Object> map=new HashMap<>();
		map.put("type", SysMenu.MenuType.menu.value());
		List<SysMenu> dbMenuList = this.baseService.selectByMap(map);
		
		Record temp = new Record();
		for(SysMenu dbMenu:dbMenuList){
			temp.put(dbMenu.getPerms(), dbMenu);
		}
		
		List<SysMenu> menuTree=new ArrayList<SysMenu>(MenuCacheComponent.menu.size()+1);
		SysMenu root = new SysMenu("0", "0", system_module_root,null, null, null);
		root.setDrag(false);
		root.setOpen(true);
		menuTree.add(root);
		for (Map.Entry<String, SysMenu> entry : MenuCacheComponent.menu.entrySet()) {
			if(temp.containsKey(entry.getKey())){
				continue;
			}
			SysMenu sysMenu = new SysMenu();
			BeanUtils.copyProperties(entry.getValue(), sysMenu, "children");
			sysMenu.setpId("0");
			menuTree.add(sysMenu);
		}
		return ResponseEntity.ok().putData(menuTree);
	}
	

	/**
	 * 编辑目录和菜单  icon图标、名字
	 * @return
	 */
	@RequestMapping("/toEdit")
	public ResponseEntity toEdit(@RequestParam(name = "menuId") Long menuId) {
		SysMenu data = this.baseService.selectById(menuId);
		return ResponseEntity.ok().putData(data);
	}
	@RequestMapping("/doEdit")
	public ResponseEntity doEdit(@RequestBody SysMenu data) {
		Validate.notNull(data.getMenuId(), "menuId不能为空！");
		SysMenu db = this.baseService.selectById(data.getMenuId());
		db.setName(data.getName());
		db.setIviewIcon(data.getIviewIcon());
		this.baseService.updateById(db);
		return ResponseEntity.ok();
	}
	
}
