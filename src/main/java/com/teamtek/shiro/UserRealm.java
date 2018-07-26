package com.teamtek.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import com.teamtek.admin.sys.entity.SysMenu;
import com.teamtek.admin.sys.entity.SysPosition;
import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.admin.sys.entity.constant.CommConstant;
import com.teamtek.shiro.util.JWTUtil;
import com.teamtek.shiro.util.TempUtil;

/**
 * Shiro认证
 */
public class UserRealm extends AuthorizingRealm {
	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("授权(验证权限时调用)");
		// String username = JWTUtil.getUsername(principals.toString());
		// SysUser user = TempUtil.sysUserService.queryByUserName(username);
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		Long userId = user.getUserId();

		// 系统管理员，拥有最高权限
		List<SysMenu> menuList = Collections.emptyList();
		if (userId.equals(TempUtil.constant.adminId)) {
			menuList = TempUtil.sysMenuService.selectByMap(null);
		} else {
			menuList = TempUtil.sysUserService.queryAllPerms(userId);
		}
		List<String> permsList = new ArrayList<>(menuList.size());
		for (SysMenu menu : menuList) {
			permsList.add(menu.getPerms());
		}
		// 用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  {
		logger.info("认证(登录时调用)");
		String jwttoken = (String) token.getCredentials();
		// 解密获得username，用于和数据库进行对比
		String username = JWTUtil.getUsername(jwttoken);
		String password = JWTUtil.getPwd(jwttoken);
		if (username == null) {
			throw new UnknownAccountException("账号不能为空");
		}
		SysUser user = TempUtil.sysUserService.queryByUserName(username);
		if (user == null) {
			throw new UnknownAccountException("账号不存在");
		}
		if(user.getStatus().intValue()==CommConstant.UserStatus.user_disable.getValue()){
			throw new RuntimeException("该账号已被禁用，请联系管理员启用账号！");
		}
		if(user.getStatus().intValue()==CommConstant.UserStatus.del.getValue()){
			throw new RuntimeException("该账号已被删除，请联系管理员恢复账号！");
		}
		// 密码错误
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		//验证通过  获取当前用户 岗位信息
		List<SysPosition> postions=TempUtil.sysPositionService.selectPositionsByUserId(user.getUserId());
		user.setPositions(postions);
		return new SimpleAuthenticationInfo(user, jwttoken, getName());

	}

}
