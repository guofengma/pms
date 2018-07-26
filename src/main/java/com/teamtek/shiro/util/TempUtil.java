package com.teamtek.shiro.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.teamtek.admin.sys.service.SysLogService;
import com.teamtek.admin.sys.service.SysMenuService;
import com.teamtek.admin.sys.service.SysPositionService;
import com.teamtek.admin.sys.service.SysUserService;
import com.teamtek.shiro.Constant;

/**
 */
@Component
public class TempUtil {

	public static SysMenuService sysMenuService;
	public static SysUserService sysUserService;
	public static SysPositionService sysPositionService;
	public static SysLogService sysLogService;
	public static Constant constant;

	@Resource
	public void setSysMenuService(SysMenuService sysMenuService) {
		TempUtil.sysMenuService = sysMenuService;
	}

	@Resource
	public void setSysLogService(SysLogService sysLogService) {
		TempUtil.sysLogService = sysLogService;
	}

	@Resource
	public void setSysUserService(SysUserService sysUserService) {
		TempUtil.sysUserService = sysUserService;
	}

	@Resource
	public void setSysUserService(SysPositionService sysPositionService) {
		TempUtil.sysPositionService = sysPositionService;
	}

	@Resource
	public void setConstant(Constant constant) {
		TempUtil.constant = constant;
	}

}
