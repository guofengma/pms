package com.teamtek.shiro.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.teamtek.admin.sys.entity.SysUser;

/**
 *  Shiro工具类
 * @author Administrator
 *
 */
public class ShiroUtils {

	/**
	 * 获取Shiro Session
	 * 
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取Admin用户
	 * 
	 * @return SysAdminEntity
	 */
	public static SysUser getAdminEntity() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 获取Admin用户ID
	 * 
	 * @return Admin用户ID
	 */
	public static Long getUserId() {
		return getAdminEntity().getUserId();
	}

	/**
	 * 设置Shiro属性
	 * 
	 * @param key
	 *            Object
	 * @param value
	 *            Object
	 */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 获取Shiro属性
	 * 
	 * @param key
	 *            Object
	 * @return Object
	 */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 获取是否已登录
	 * 
	 * @return boolean
	 */
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	/**
	 * 登出
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}


}
