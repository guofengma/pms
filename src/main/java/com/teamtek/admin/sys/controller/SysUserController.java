package com.teamtek.admin.sys.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sargeraswang.util.ExcelUtil.ExcelLogs;
import com.sargeraswang.util.ExcelUtil.ExcelUtil;
import com.teamtek.admin.comm.util.MyExcelUtil;
import com.teamtek.admin.score.vo.ScoreMarketProjectVo;
import com.teamtek.admin.sys.bo.ExcelImportLog;
import com.teamtek.admin.sys.bo.SysUserBo;
import com.teamtek.admin.sys.entity.Dept;
import com.teamtek.admin.sys.entity.SysRole;
import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.admin.sys.entity.SysUserRole;
import com.teamtek.admin.sys.entity.constant.CommConstant;
import com.teamtek.admin.sys.entity.constant.IviewTreeVo;
import com.teamtek.admin.sys.service.DeptService;
import com.teamtek.admin.sys.service.SysRoleService;
import com.teamtek.admin.sys.service.SysUserRoleService;
import com.teamtek.admin.sys.service.SysUserService;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.utils.BeanTranUtil;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;
import com.teamtek.shiro.Constant;
import com.teamtek.shiro.EhCacheNames;
import com.teamtek.shiro.util.EhcacheUtil;
import com.teamtek.shiro.util.ShiroUtils;

/**
 * 用户管理
 * 
 * @MenuOrBtnAnnotation写在类上 代表菜单，写在方法上 代表按钮 其中code用于shiro权限验证，必须保持唯一
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin/sys/user")
@RequiresPermissions("user")
@MenuOrBtnAnnotation(menu = "用户管理", code = "user")
public class SysUserController extends BaseController<SysUser> {

	private static final String DEFAULT_PWD = DigestUtils.md5DigestAsHex("123456".getBytes());

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Resource
	private EhcacheUtil ehcacheUtil;
	@Resource
	private Constant constant;

	@RequestMapping(value = "/list")
	public ResponseEntity list(@RequestBody Record param) {

		Object current = param.get("current");
		Object size = param.get("size");
		Validate.notNull(current, "current不能为空！");
		Validate.notNull(size, "size不能为空！");
		Wrapper<SysUser> wrapper = new EntityWrapper<>();
		Page<SysUser> page = new Page<SysUser>(Integer.valueOf(current.toString()), Integer.valueOf(size.toString()));
		StringBuilder sb = new StringBuilder(" sys_user.*,");
		sb.append(" (SELECT role_name FROM sys_role r ");
		sb.append(" LEFT JOIN sys_user_role ur ON ur.role_id=r.role_id  ");
		sb.append(" WHERE ur.user_id=sys_user.user_id) role ");
		wrapper.setSqlSelect(sb.toString());

		if (param.get("username") != null) {
			wrapper.like("username", param.get("username").toString());
		}
		wrapper.lt("status", CommConstant.UserStatus.del.getValue());
		wrapper.orderBy("create_time", false);
		Page<Map<String, Object>> pages = sysUserService.selectMapsPage(page, wrapper);
		// super.createPaginationMapResult(new DataGrid<Map<String,
		// Object>>(pages));
		return ResponseEntity.ok().put("data",
				super.createPaginationMapResult(new DataGrid<Map<String, Object>>(pages)));
	}

	/*
	 * 添加
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	public ResponseEntity saveOrUpdate(@RequestBody Record param) {

		String birthDateStr = param.get("birthDate", "").toString();
		if (StringUtils.isNotBlank(birthDateStr)) {
			try {
				param.set("birthDate", DateUtils.parseDate(birthDateStr, "yyyy-MM-dd"));
			} catch (ParseException e) {
				throw new RuntimeException("birthDate格式应该为yyyy-MM-dd");
			}
		}

		SysUser user = BeanTranUtil.map2Bean(param, SysUser.class);

		// 多个roleid 以逗号分开
		String roleIds = param.get("roleIds").toString();
		if (user.getUserId() == null || user.getUserId().equals(0L)) {
			String userNo = param.get("userNo", "").toString();
			Validate.notBlank(userNo, "userNo不能为空");
			Map<String, Object> map = new HashMap<>();
			map.put("user_no", userNo);
			List<SysUser> dbUser = this.baseService.selectByMap(map);
			if (dbUser.size() > 0) {
				return ResponseEntity.error("用户工号【" + userNo + "】存在重复");
			}
			user.setCreateTime(new Date());
			// 账户默认 为正常状态
			user.setStatus(CommConstant.UserStatus.user_enable.getValue());
			if (StringUtils.isBlank(user.getPassword())) {
				user.setPassword(DEFAULT_PWD);
			}
			sysUserService.saveData(user, roleIds);
		} else {
			sysUserService.updateData(user, roleIds);
		}

		return ResponseEntity.ok();
	}

	/**
	 * 去编辑页面
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/toEdit")
	@RequiresPermissions("user")
	public ResponseEntity toEdit(@RequestBody SysUser user) {
		Validate.notNull(user.getUserId(), "userId不能为空");

		// 回显用户
		SysUser db = this.baseService.selectById(user.getUserId());
		// 回显角色树
		Map<String, Object> columnMap = new HashMap<String, Object>(1);
		columnMap.put("user_id", db.getUserId());
		List<SysUserRole> userRoles = sysUserRoleService.selectByMap(columnMap);
		Map<Long, Integer> temp = new HashMap<Long, Integer>(userRoles.size());
		String roleIdsStr = "";
		for (SysUserRole r : userRoles) {
			temp.put(r.getRoleId(), 1);
			roleIdsStr = r.getRoleId() + ",";
		}
		if (roleIdsStr.length() > 0) {
			roleIdsStr = roleIdsStr.substring(0, roleIdsStr.length() - 1);
		}
		List<SysRole> list = this.sysRoleService.selectByMap(null);
		List<IviewTreeVo> roleTree = new ArrayList<>(list.size());
		IviewTreeVo vo = null;
		for (SysRole r : list) {
			vo = new IviewTreeVo();
			vo.setId(r.getRoleId());
			vo.setTitle(r.getRoleName());
			if (temp.containsKey(r.getRoleId())) {
				vo.setChecked(true);
			}
			roleTree.add(vo);
		}
		// 回显部门树
		List<Dept> allDepts = this.deptService.selectByMap(null);
		List<IviewTreeVo> deptTree = toVoTree(allDepts, 0L, db.getDeptId());
		return ResponseEntity.ok().put("user", db).put("roleTree", roleTree).put("deptTree", deptTree).put("roleIdsStr",
				roleIdsStr);
	}

	private List<IviewTreeVo> toVoTree(List<Dept> tree, Long id, Long deptId) {
		List<IviewTreeVo> retList = new ArrayList<>();
		for (Dept m : tree) {
			if (m.getParentId().equals(id)) {
				IviewTreeVo vo = new IviewTreeVo();
				vo.setTitle(m.getDeptName());
				vo.setId(m.getId());
				
				if(!m.getParentId().equals(0L)){//只展开 机构节点 
					vo.setExpand(false);
				}
				if (m.getId().equals(deptId)) {
					vo.setChecked(true);
					vo.setExpand(true);
				}
				
				
				
				if (!m.getId().equals(id)) {
					List<IviewTreeVo> children = toVoTree(tree, m.getId(), deptId);
					if (children.size() > 0) {
						vo.setChildren(children);
//						vo.setDisableCheckbox(true);
//						vo.setDisabled(true);
					}
				}
				retList.add(vo);
			}
		}
		return retList;
	}
	@RequiresPermissions("user")
	@RequestMapping(value = "/doEdit")
	public ResponseEntity update(@RequestBody Record param) {

		SysUser user = BeanTranUtil.map2Bean(param, SysUser.class);
		// 多个roleid 以逗号分开
		String roleIds = param.get("roleId").toString();
		sysUserService.updateData(user, roleIds);

		resetUserMenuCache();
		return ResponseEntity.ok();
	}

	/**
	 * 删除 用户（ 不是物理删除）
	 * 
	 * @param user
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/del")
	public ResponseEntity del(@RequestBody SysUser user) {
		if (isSuperAdmin(user.getUserId())) {
			return ResponseEntity.error("最高管理员账户不能被删除！");
		}
		sysUserService.deleteDataById(user.getUserId());
		resetUserMenuCache();
		return ResponseEntity.ok();
	}

	private boolean isSuperAdmin(Long id) {
		if (constant.adminId.equals(id)) {
			return true;
		}
		return false;
	}

	/**
	 * 禁用 用户
	 * 
	 * @param user
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/disable")
	public ResponseEntity disable(@RequestBody SysUser user) {
		if (isSuperAdmin(user.getUserId())) {
			return ResponseEntity.error("最高管理员账户不能被禁用！");
		}
		SysUser db = sysUserService.selectById(user.getUserId());
		db.setStatus(CommConstant.UserStatus.user_disable.getValue());
		this.sysUserService.updateById(db);
		resetUserMenuCache();
		return ResponseEntity.ok();
	}

	/**
	 * 用户 状态恢复正常
	 * 
	 * @param user
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/enable")
	public ResponseEntity enable(@RequestBody SysUser user) {
		SysUser db = sysUserService.selectById(user.getUserId());
		db.setStatus(CommConstant.UserStatus.user_enable.getValue());
		this.sysUserService.updateById(db);
		resetUserMenuCache();
		return ResponseEntity.ok();
	}

	/**
	 * 批量删除 ids 多个以 , 分割
	 * 
	 * @param param
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/delBatch")
	public ResponseEntity delBatch(@RequestBody Record param) {
		String ids = param.get("ids", "").toString();
		Validate.notBlank(ids, "ids不能为空！");
		sysUserService.deleteBatchDataByIds(ids.split(","));
		resetUserMenuCache();
		return ResponseEntity.ok();
	}

	/**
	 * 用户关联岗位 --- 保存 请求参数： { userId:'', positionIds:'12,45'//多个岗位id以逗号分隔 }
	 * 
	 * @param param
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/updateUserPosition")
	public ResponseEntity updateUserPosition(@RequestBody Record param) {
		String userIdStr = param.get("userId", "").toString();
		String positionIdsStr = param.get("positionIds", "").toString();
		Validate.notBlank(userIdStr, "用户userId不能为空");

		this.sysUserService.updateUserPosition(Long.valueOf(userIdStr), positionIdsStr);
		return ResponseEntity.ok();
	}

	/**
	 * 清空用户缓存的 资源权限
	 */
	private void resetUserMenuCache() {
		// 清空用户 缓存的资源权限
		String cacheName = EhCacheNames.menuCacheName + ShiroUtils.getUserId();
		ehcacheUtil.remove(EhcacheUtil.ADMINMENUEHCACHENAME, cacheName);
	}

	/**
	 * 给用户授予角色
	 * 
	 * @param primaryKeys
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/userAuth")
	public ResponseEntity userAuthRole(@RequestParam(name = "userId") String userId,
			@RequestParam(name = "roleIds") String roleIds) {
		// roleIds多个roleid以逗号间隔
		Validate.notBlank(userId, "用戶id不能为空");
		sysUserService.userAuthRole(userId, roleIds);

		resetUserMenuCache();
		return ResponseEntity.ok();
	}

	/**
	 * 管理员重置密码
	 * 
	 * @param user
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/resetPwd")
	public ResponseEntity resetPwd(@RequestBody SysUser user) {
		Validate.notNull(user.getUserId(), "userId不能为空");
		SysUser dbUser = this.baseService.selectById(user.getUserId());
		dbUser.setPassword(DEFAULT_PWD);
		return ResponseEntity.ok();
	}

	/**
	 * 用户个人修改密码
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/editPwd")
	public ResponseEntity editPwd(@RequestBody Record param) {
		Validate.notNull(param.get("userId"), "userId不能为空");

		String oldPwd = param.get("oldPwd", "").toString();
		String newPwd = param.get("newPwd", "").toString();
		Validate.notBlank(oldPwd, "oldPwd不能为空");
		Validate.notBlank(newPwd, "newPwd不能为空");
		SysUser dbUser = this.baseService.selectById(Long.valueOf(param.get("userId").toString()));
		if (!dbUser.getPassword().equals(DigestUtils.md5DigestAsHex(oldPwd.getBytes()))) {
			return ResponseEntity.error("当前密码输入错误");
		}
		if (oldPwd.equals(newPwd)) {
			return ResponseEntity.ok("密码未改动");
		}
		dbUser.setPassword(DigestUtils.md5DigestAsHex(newPwd.getBytes()));
		this.baseService.updateById(dbUser);
		return ResponseEntity.ok("密码修改成功");
	}

	/**
	 * 用户批量导入
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "/userImport")
	public ResponseEntity userImport(@RequestBody Record param,
			@Value("${server.fileUpload.rootPath}") String rootPath) {
		String path = param.get("file_path", "").toString();
		Validate.notBlank(path, "file_path不能为空");
		List<SysUserBo> list = null;
		try {
			File f = new File(rootPath + "/" + path);
			if (!f.exists()) {
				return ResponseEntity.error("用户导入失败，检查参数file_path是否正确！");
			}
			InputStream inputStream = new FileInputStream(f);
			list = MyExcelUtil.importExcel(0,1, SysUserBo.class, inputStream, "yyyy/MM/dd", 0);
			//list = toBoList(importExcel);
		} catch (Exception e) {
			return ResponseEntity.error("用户批量导入错误，请核对excel格式是否正确！");
		}
		// 1.验证登录名、用户名、工号不能为空
		String result = validateUserData(list);
		if (result != null) {
			return ResponseEntity.error("用户批量导入错误：" + result);
		}
		// 2.验证登录名是否重复
		List<String> temps = usernameIsRepet(list);
		if (!temps.isEmpty()) {
			return ResponseEntity.error("用户批量导入错误，登录名存在重复：" + temps);
		}
		// 3. 验证登录名是否存在
		temps = usernameIsExist(list);
		if (!temps.isEmpty()) {
			return ResponseEntity.error("用户批量导入错误，登录名已经存在：" + temps);
		}
		// 4.导入数据
		importUserdata(list);
		StringBuilder successProjectMsg = new StringBuilder("成功导入用户数据【");
		successProjectMsg.append(String.valueOf(list.size()));
		successProjectMsg.append("】条");
		log.info("--用户导入成功，导入个数:【{}】，导入文件路径【{}】！！--", list.size(), path);
		return ResponseEntity.ok().putData(successProjectMsg.toString());
	}

	/**
	 * 验证用户名、登录名不能为空
	 * 
	 * @param list
	 * @return
	 */
	private String validateUserData(List<SysUserBo> list) {
		String res = null;
		List<String> dbUserNoList = this.baseService.selectList(null).stream().filter(c -> c.getUserNo() != null)
				.map(SysUser::getUserNo).collect(Collectors.toList());
		
		Map<String, Long> deptIdMap = this.deptService.selectByMap(null).stream()
				.collect(Collectors.toMap(Dept::getDeptName, Dept::getId));
		Map<String,Integer> temp=new HashMap<>();
		for (SysUserBo bo : list) {
			if (StringUtils.isBlank(bo.getName())) {
				res = "用户名不能为空";
				break;
			}
			if (StringUtils.isBlank(bo.getUsername())) {
				res = "登录名不能为空";
				break;
			}
			if(StringUtils.isBlank(bo.getUserNo())){
				res = "工号不能为空";
				break;
			}
			if(temp.containsKey(bo.getUserNo())){
				res = "excel中工号【"+bo.getUserNo()+"】有重复";
				break;
			}
			if(dbUserNoList.contains(bo.getUserNo())){
				res = "工号【"+bo.getUserNo()+"】有已经存在";
				break;
			}
			// 部门
			if (StringUtils.isNotBlank(bo.getDeptId())) {
				if (deptIdMap.containsKey(bo.getDeptId())) {
					bo.setDeptId(String.valueOf(deptIdMap.get(bo.getDeptId())));
				} else {
					res = MessageFormat.format("部门【{0}】不存在", bo.getDeptId());
					break;
				}
			}else{
				res = "部门不能为空";
				break;
			}
			// 性别
			Integer sex = CommConstant.Sex.getValueByTitle(bo.getSex());
			if (sex == null) {
				res = MessageFormat.format("性别格式错误，应为：【男，女，保密】】,错误格式为：【{}】", bo.getSex());
				break;
			} 
			bo.setSex(String.valueOf(sex));
			
			temp.put(bo.getUserNo(), 1);
		}
		return res;
	}

	/**
	 * 导入用户数据
	 * 
	 * @param list
	 * @return
	 */
	private void importUserdata(List<SysUserBo> list) {
		list.stream().forEach(c -> {
			SysUser user = boTransferBean(c);
			user.setMobile(c.getMobile());
			user.setDeptId(Long.valueOf(c.getDeptId()));
			user.setCreateTime(new Date());
			user.setBirthDate(c.getBirthDate());
			user.setSex(Integer.valueOf(c.getSex()));
			this.baseService.insert(user);
		});
	}

	private SysUser boTransferBean(SysUserBo c) {
		SysUser user = new SysUser(c.getUsername(), c.getName());
		user.setUserNo(c.getUserNo());
		user.setEmail(c.getEmail());
		user.setMobile(c.getMobile());
		user.setWorkPhone(c.getWorkPhone());
		// 创建时间(默认当前)
		user.setCreateTime(new Date());
		user.setPassword(DEFAULT_PWD);
		// 用户状态
		user.setStatus(CommConstant.UserStatus.user_enable.getValue());
		return user;
	}

	/**
	 * 验证用户登录名是否 已经在数据库存在
	 * 
	 * @param list
	 * @return 返回已经存在的登录名 为空表示登录名是唯一的
	 */
	private List<String> usernameIsExist(List<SysUserBo> list) {
		List<String> names = this.sysUserService.queryAllUserNames();
		List<String> exists = list.stream().filter(c -> names.contains(c.getUsername())).map(SysUserBo::getUsername)
				.collect(Collectors.toList());
		return exists;
	}

	/**
	 * 验证用户登录名是否有重复
	 * 
	 * @param list
	 * @return 返回重复的登录名 为空表示没有重复的
	 */
	private List<String> usernameIsRepet(List<SysUserBo> list) {
		Map<String, List<SysUserBo>> collect = list.stream().collect(Collectors.groupingBy(SysUserBo::getUsername));
		List<String> names = new ArrayList<>();
		for (Map.Entry<String, List<SysUserBo>> en : collect.entrySet()) {
			if (en.getValue().size() > 1) {
				names.add(en.getKey());
			}
		}
		return names;
	}

	// public static void main(String[] args) {
	// // List<SysUserBo> list = Arrays.asList(new SysUserBo("aa"), new
	// // SysUserBo("bb"), new SysUserBo("cc"),
	// // new SysUserBo("aa"), new SysUserBo("bb"));
	// // System.out.println(usernameIsRepet(list));
	//
	// // 1.验证登录名、用户名不能为空
	// // String result=validateUserData(list);
	// }

	protected JSONObject toJSONMap(Map<String, Object> d) {
		JSONObject json = super.toJSONMap(d);
		if (d.get("create_time") != null)
			json.put("create_time", DateFormatUtils.format((Date) d.get("create_time"), "yyyy-MM-dd HH:mm:ss"));
		if (d.get("status") != null) {
			int status = Integer.valueOf(d.get("status").toString()).intValue();
			json.put("status", CommConstant.UserStatus.getTitleByValue(status));
		}
		int sex = Integer.valueOf(d.get("sex").toString()).intValue();
		json.put("sex", CommConstant.Sex.getTitleByValue(sex));

		return json;
	}

	@Override
	protected JSONObject toJSON(SysUser d) {
		JSONObject json = super.toJSON(d);
		if (d.getCreateTime() != null)
			json.put("createTime", DateFormatUtils.format(d.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		return json;
	}
}
