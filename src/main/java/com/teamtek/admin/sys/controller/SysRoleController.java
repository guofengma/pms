package com.teamtek.admin.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.teamtek.admin.sys.entity.SysMenu;
import com.teamtek.admin.sys.entity.SysRole;
import com.teamtek.admin.sys.entity.SysRoleMenu;
import com.teamtek.admin.sys.entity.constant.IviewTreeVo;
import com.teamtek.admin.sys.service.SysMenuService;
import com.teamtek.admin.sys.service.SysRoleMenuService;
import com.teamtek.admin.sys.service.SysRoleService;
import com.teamtek.admin.sys.service.SysUserService;
import com.teamtek.core.ann.BtnConstant;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.utils.BeanTranUtil;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;
import com.teamtek.shiro.Constant;
import com.teamtek.shiro.util.EhcacheUtil;

/**
 * 角色管理
 * 
 * @MenuOrBtnAnnotation写在类上 代表菜单，写在方法上 代表按钮 其中code用于shiro权限验证，必须保持唯一
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/sys/role")
@MenuOrBtnAnnotation(menu = "角色管理", code = "role")
public class SysRoleController extends BaseController<SysRole> {

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Resource
	private EhcacheUtil ehcacheUtil;
	@Resource
	private Constant constant;

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * size 每页数据量 current 当前页 分页查询
	 */
	public ResponseEntity datagrid(@RequestBody Record param) {
		// @RequestParam(name = "size", required = true) Integer size,
		// @RequestParam(name = "current", required = true) Integer current,
		// @RequestParam(name = "search", required = false) String search

		Validate.notNull(param.get("current"), "current不能为空");
		Validate.notNull(param.get("size"), "size不能为空");
		Integer current = Integer.valueOf(param.get("current").toString());
		Integer size = Integer.valueOf(param.get("size").toString());

		Wrapper<SysRole> ew = new EntityWrapper<>();
		String roleName = param.get("roleName", "").toString();
		if (StringUtils.isNotBlank(roleName)) {
			ew.like("role_name", roleName);
		}
		ew.orderBy("create_time", false);
		Page<SysRole> page = new Page<SysRole>(current, size);
		baseService.selectPage(page, ew);
		return ResponseEntity.ok().putData(super.createPaginationResult(new DataGrid<SysRole>(page)));
	}

	/*
	 * 添加
	 */
	@RequiresPermissions("role_add")
	@RequestMapping(value = "/add")
	@MenuOrBtnAnnotation(btn = BtnConstant.add, code = "role_add")
	public ResponseEntity saveOrUpdate(@RequestBody SysRole role) {
		// SysRole role = BeanTranUtil.map2Bean(param, SysRole.class);
		Validate.notBlank(role.getRoleName().trim(), "角色名称不能为空");

		Wrapper<SysRole> wrapper = new EntityWrapper<>();
		wrapper.eq("role_name", role.getRoleName().trim());
		List<SysRole> data = this.baseService.selectList(wrapper);

		if (data != null) {
			int size = data.size();
			if (size > 1) {
				return ResponseEntity.error("角色名：【" + role.getRoleName() + "】已经存在，请改用其他角色名！ ");
			}
			if (size == 1) {
				if (!data.get(0).getRoleId().equals(role.getRoleId())) {
					return ResponseEntity.error("角色名：【" + role.getRoleName() + "】已经存在，请改用其他角色名！ ");
				}
			}
		}
		// 添加
		if (role.getRoleId() == null) {
			role.setCreateTime(new Date());
			this.baseService.insert(role);
			// 更新
		} else {
			SysRole db = this.baseService.selectById(role.getRoleId());
			db.setRemark(role.getRemark());
			db.setRoleName(role.getRoleName());
			this.baseService.updateById(db);
		}

		return ResponseEntity.ok();
	}

	@RequiresPermissions("role_edit")
	@RequestMapping(value = "/toEdit", method = { RequestMethod.GET })
	@MenuOrBtnAnnotation(btn = BtnConstant.edit, code = "role_edit")
	public ResponseEntity toEdit(@RequestParam(name = "id") Long id) {
		SysRole role = this.baseService.selectById(id);
		return ResponseEntity.ok().putData(role);
	}

	@RequiresPermissions("role_edit")
	@RequestMapping(value = "/doEdit", method = { RequestMethod.POST })
	// @MenuOrBtnAnnotation(btn = BtnConstant.edit, code = "role_update")
	public ResponseEntity doEdit(@RequestBody Record param) {

		SysRole role = BeanTranUtil.map2Bean(param, SysRole.class);
		SysRole db = this.baseService.selectById(role.getRoleId());
		BeanUtils.copyProperties(role, db, "roleId", "createTime");
		sysRoleService.updateById(db);
		return ResponseEntity.ok();
	}

	@RequiresPermissions("role_del")
	@RequestMapping("/del")
	@MenuOrBtnAnnotation(btn = BtnConstant.del, code = "role_del")
	public ResponseEntity del(@RequestBody SysRole role) {
		Validate.notNull(role.getRoleId(), "roleId 不能为空");
		sysRoleService.deleteDataById(role.getRoleId());
		return ResponseEntity.ok();
	}

	/**
	 * 给角色授予资源权限(授予 菜单 和按钮 权限)
	 * 
	 * @return
	 */
	@RequestMapping("/roleResourceAuth")
	public ResponseEntity resourcesAuth(@RequestBody Record param) {
		// Map<String, Object> columnMap=new HashMap<String, Object>();
		// columnMap.put("role_id", roleId);
		// List<SysRoleMenu> roleMenus =
		// this.sysRoleMenuService.selectByMap(columnMap);//查找当前角色拥有的权限
		String roleId = param.get("roleId", "").toString();
		Validate.notBlank(roleId, "角色id不能为空");
		Wrapper<SysRoleMenu> wrapper = new EntityWrapper<>();
		wrapper.setSqlSelect("menu_id");
		wrapper.eq("role_id", roleId);

		List<Object> selectObjs = sysRoleMenuService.selectObjs(wrapper);

		Long[] menuids = new Long[selectObjs.size()];
		selectObjs.toArray(menuids);

		List<SysMenu> tree = null;
		if (constant.adminId.equals(getCurrUser().getUserId())) {
			tree = this.sysMenuService.selectList(null);
		} else {
			tree = this.sysUserService.queryAllPerms(getCurrUser().getUserId());
		}
		for (SysMenu menu : tree) {
			if (ArrayUtils.contains(menuids, menu.getMenuId())) {
				menu.setChecked(true);
			}
			if (menu.getType() == SysMenu.MenuType.directory.value()) {
				menu.setOpen(true);
			}
		}

		SysMenu root = new SysMenu("0", "0", "所有资源权限", null, null, null);
		root.setOpen(true);
		tree.add(root);
		return ResponseEntity.ok().putData(tree);
	}

	/**
	 * 保存 角色授予资源权限(授予 菜单 和按钮 权限)
	 * 
	 * @param primaryKeys
	 * @return
	 */
	@RequestMapping(value = "/doRoleResourceAuth", method = { RequestMethod.POST })
	@MenuOrBtnAnnotation(btnTitle = "资源授权", code = "role_resource_auth")
	public ResponseEntity doResourcesAuth(@RequestBody Record param) {
		String roleId = param.get("roleId", "").toString();
		Validate.notEmpty(roleId, "角色id不能为空");
		String menuIdStr = param.get("menuId", "").toString();
		// 不为空 更新
		if (StringUtils.isNotBlank(menuIdStr)) {
			String[] menuIds = param.get("menuId", "").toString().split(",");
			this.sysRoleMenuService.updateByMenuIds(menuIds, Long.valueOf(roleId));
			// 为空 说明 当前角色 没有任何资源权限
		} else {
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("role_id", roleId);
			this.sysRoleMenuService.deleteByMap(columnMap);
		}

		// 角色资源权限发生改变 清空所有资源缓存
		ehcacheUtil.removeAllMenuCache();
		return ResponseEntity.ok();
	}

	/**
	 * 生成机构树
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/roleTree")
	@RequiresPermissions("role")
	public ResponseEntity roleTree() {
		List<SysRole> list = this.baseService.selectByMap(null);
		List<IviewTreeVo> voTree = new ArrayList<>(list.size());
		IviewTreeVo vo = null;
		for (SysRole r : list) {
			vo = new IviewTreeVo();
			vo.setId(r.getRoleId());
			vo.setTitle(r.getRoleName());
			voTree.add(vo);
		}
		return ResponseEntity.ok().putData(voTree);
	}

	protected JSONObject toJSON(SysRole d) {
		JSONObject json = super.toJSON(d);
		if (d.getCreateTime() != null)
			json.put("createTime", DateFormatUtils.format(d.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		return json;
	}
}
