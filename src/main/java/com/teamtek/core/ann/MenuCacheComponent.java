package com.teamtek.core.ann;

import java.util.concurrent.ConcurrentHashMap;

import com.teamtek.admin.sys.entity.SysMenu;
/**
 * 系统菜单、按钮缓存（系统模块核心功能，严禁开发人员 对集合内元素手动修改）
 * 系统启动时自动进行初始化
 * 
 * @author Administrator
 *
 */
public class MenuCacheComponent {

	public final static ConcurrentHashMap<String, SysMenu> menu= new ConcurrentHashMap<String, SysMenu>();
	//public final static SortedMap<String, Menu> menu = Collections.synchronizedSortedMap(new TreeMap<>());
	
}
