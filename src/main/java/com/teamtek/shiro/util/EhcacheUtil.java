package com.teamtek.shiro.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 *  EhCache 缓存工具类
 *  主要缓存 用户对应的 菜单、按钮(即：用户资源权限)
 *  当菜单、按钮、角色发生修改时  需要clearAll
 * @author Administrator
 *
 */
@Component
public class EhcacheUtil {
	/** 管理员菜单缓存 */
	public final static String ADMINMENUEHCACHENAME = "adminMenuCache";

	@Resource
	public CacheManager manager;

	/**
	 * 获得一个Cache
	 * 
	 * @param cacheName
	 *            缓存名
	 * @param key
	 *            缓存key
	 * @return Object
	 */
	public Object get(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			Element element = cache.get(key);
			if (element != null) {
				return element.getObjectValue();
			}
		}
		return null;
	}

	/**
	 * 添加一个Cache
	 * 
	 * @param cacheName
	 *            缓存名
	 * @param key
	 *            缓存key
	 * @param value
	 *            缓存value
	 */
	public void put(String cacheName, Object key, Object value) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			cache.put(new Element(key, value));
		}
	}

	/**
	 * 移除一个Cache
	 * 
	 * @param cacheName
	 *            缓存名
	 * @param key
	 *            缓存key
	 * @return boolean
	 */
	public boolean remove(String cacheName, Object key) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			return cache.remove(key);
		}
		return false;
	}
	/**
	 * 清除shiro菜单缓存
	 */
	public void removeAllMenuCache() {
		this.removeAllByCache(ADMINMENUEHCACHENAME);
	}
	/**
	 * 清除缓存
	 */
	public void removeAllByCache(String cacheName) {
		Cache cache = manager.getCache(cacheName);
		if (cache != null) {
			 cache.removeAll();
		}
	}
	/**
	 * 清除all缓存
	 */
	public void clearAll() {
		manager.clearAll();
	}
}
