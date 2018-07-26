package com.teamtek.admin.sys.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamtek.admin.comm.upload.filepath.ExcelDownloadPath;
import com.teamtek.admin.comm.util.MyExcelUtil;
import com.teamtek.admin.score.vo.ScoreBaseProjectVo;
import com.teamtek.admin.sys.entity.ApplicationList;
import com.teamtek.admin.sys.entity.Dept;
import com.teamtek.admin.sys.entity.SysLog;
import com.teamtek.admin.sys.entity.SysMenu;
import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.admin.sys.entity.SysUser.Type;
import com.teamtek.admin.sys.service.AppService;
import com.teamtek.admin.sys.service.SysLogService;
import com.teamtek.admin.sys.service.SysMenuService;
import com.teamtek.admin.sys.service.SysUserService;
import com.teamtek.core.controller.AbstController;
import com.teamtek.core.vo.MyHttpStatus;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;
import com.teamtek.shiro.EhCacheNames;
import com.teamtek.shiro.JWTToken;
import com.teamtek.shiro.util.EhcacheUtil;
import com.teamtek.shiro.util.JWTUtil;
import com.teamtek.shiro.util.ShiroUtils;

/**
 * 管理员登陆 认证、token刷新
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin")
public class SysLoginController extends AbstController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysLogService sysLogService;
	@Resource
	private EhcacheUtil ehcacheUtil;

	/**
	 * 管理员登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param captcha
	 *            验证码
	 * @return Map
	 * @throws IOException
	 */
	@RequestMapping(value = "/sys/login")
	public ResponseEntity login(@RequestBody Record param) {

		// @RequestParam已经有非空验证
		// Validate.notNull(param.get("username"),"登陆名不能为空");
		// Validate.notNull(param.get("password"),"登陆名不能为空");

		String id = SecurityUtils.getSubject().getSession().getId().toString();
		// 该token发给client
		String jwt_token = JWTUtil.sign(param.get("username").toString(), id);
		// String jwt_token = JWTUtil.sign(username, id);
		// 该token用于验证
		String auth_token = JWTUtil.sign(param.get("username").toString(), id, param.get("password").toString());
		// String auth_token = JWTUtil.sign(username, id,password);
		if (jwt_token == null || auth_token == null) {
			throw new RuntimeException("jwt token签名错误！");
		}
		JWTToken token = new JWTToken(auth_token);
		// 提交给realm进行登入，如果错误他会抛出异常并被捕获
		ShiroUtils.getSubject().login(token);
		// 系统日志
		SysLog slog = new SysLog(ShiroUtils.getUserId(), ShiroUtils.getAdminEntity().getName(),
				ShiroUtils.getAdminEntity().getUsername(), new Date(), "登录", "/admin/sys/login");
		this.sysLogService.insert(slog);

		return ResponseEntity.ok().put(JWTUtil.ACCESS_TOKEN_CODE, jwt_token).setExpiresTime(JWTUtil.EXPIRE_TIME)
				.putData(ShiroUtils.getAdminEntity());
	}

	/**
	 * 用户登陆验证通过后进入该 uri
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/main")
	public ResponseEntity main() {
		// @RequestBody Record param
		// Long userId = ShiroUtils.getUserId();
		// Validate.notNull(param.get("userId"),"用户id不能为空");
		// List<SysMenu> menuList =
		// sysMenuService.getUserMenuList(Long.valueOf(param.get("userId").toString()));//
		// 查询用户的菜单、目录、按钮
		List<SysMenu> menuList = sysMenuService.getUserMenuList(ShiroUtils.getUserId());// 查询用户的菜单、目录、按钮

		// List<SysMenu> btnList = new ArrayList<>();
		List<String> btnList = new ArrayList<>();
		List<SysMenu> temp = new ArrayList<>();

		boolean isExistDirectory = false;
		// 分离出按钮
		for (SysMenu menu : menuList) {
			if (menu.getType() == SysMenu.MenuType.button.value()) {
				// btnList.add(menu);
				btnList.add(menu.getPerms());
			} else {
				// 菜单 目录
				temp.add(menu);
			}
			if (menu.getType() == SysMenu.MenuType.directory.value()) {
				isExistDirectory = true;
			}
		}

		Record data = new Record();
		data.put("btnList", btnList);
		// 分离 出 菜单和目录
		// 存在目录
		if (isExistDirectory) {
			List<SysMenu> menuNavTree = new ArrayList<>();
			menuNavTree = toNavTree(temp, "0");
			data.put("menuList", menuNavTree);
			// 不存在目录
		} else {
			data.put("menuList", temp);
		}

		return ResponseEntity.ok().put("data", data);
	}
	private List<SysMenu> toNavTree(List<SysMenu> tree, String id) {

		List<SysMenu> retList = new ArrayList<>();

		for (SysMenu m : tree) {

			if (m.getpId().equals(id)) {

				if (!m.getId().equals(id)) {

					List<SysMenu> children = toNavTree(tree, m.getId());

					if (children.size() > 0) {
						m.setOpen(true);
						m.setChildren(children);
					}
				}
				retList.add(m);
			}
		}
		return retList;
	}

	/**
	 * 管理员退出
	 *
	 * @return String
	 * @throws Exception
	 */
	@RequestMapping(value = "/sys/logout")
	public ResponseEntity logout() {
		if (ShiroUtils.isLogin()) {
			// 系统日志
			SysLog slog = new SysLog(ShiroUtils.getUserId(), ShiroUtils.getAdminEntity().getName(),
					ShiroUtils.getAdminEntity().getUsername(), new Date(), "登出", "/admin/sys/logout");
			this.sysLogService.insert(slog);

			String cacheName = EhCacheNames.menuCacheName + ShiroUtils.getUserId();
			ehcacheUtil.remove(EhcacheUtil.ADMINMENUEHCACHENAME, cacheName);
			log.info("管理员退出，清空用户菜单列表Cache");
			ShiroUtils.logout();

		}
		return ResponseEntity.ok();
	}

	/**
	 * 刷新access_token
	 * 
	 * @return
	 */
	@RequestMapping(value = "/sys/refresh_token")
	public ResponseEntity refreshToken(HttpServletRequest request) {
		// 从请求头获取access_token
		String access_token = request.getHeader(JWTUtil.ACCESS_TOKEN_CODE);

		// String access_token =
		// request.getParameter(JWTUtil.ACCESS_TOKEN_CODE);

		String username = JWTUtil.getUsername(access_token);
		String sessionCode = JWTUtil.getSessionCode(access_token);

		SysUser queryByUserName = this.sysUserService.queryByUserName(username);
		if (queryByUserName != null) {
			return ResponseEntity.ok().put(JWTUtil.ACCESS_TOKEN_CODE, JWTUtil.sign(username, sessionCode))
					.setExpiresTime(JWTUtil.EXPIRE_TIME);
		}

		return ResponseEntity.error(MyHttpStatus.REFRESH_TOKEN_FAILED);
	}
	@Autowired
	private AppService appService;
	
	@RequestMapping(value = "/123")
	public ResponseEntity sgadg() {
	//	InputStream in = getClass().getResourceAsStream("/旧SSGF项目立项申请数据.xlsx");
//		InputStream in = getClass().getResourceAsStream("/SSGF遗漏数据.xls");
//		List<ApplicationList> importExcel = MyExcelUtil.importExcel(0,1, ApplicationList.class, in, "yyyy-MM-dd", 0);
//		importExcel.stream().forEach(c->c.setIsEnable("1"));
//		appService.insertBatch(importExcel);
	//	List<ApplicationList> selectList = appService.selectList(null);
	//	this.sysUserService.insert(user);
		
		//List<SysUser> selectBySex = this.sysUserService.selectBySex(2,"赵信");
		SysUser usr = new SysUser();
		usr.setName("hhhhh");
		usr.setUsername("ffsf");
		usr.setType(Type.a);
		this.sysUserService.insert(usr);
		
		return ResponseEntity.ok().putData(usr);
	}
}
