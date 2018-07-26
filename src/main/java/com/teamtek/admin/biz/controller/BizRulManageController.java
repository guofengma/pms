package com.teamtek.admin.biz.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamtek.admin.biz.entity.constant.Rule;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.vo.ResponseEntity;

/** 
* @Description:  规则管理
* @author shenzhihao 
* @date 2018年3月1日 下午6:17:48 
*/
@RestController
@RequestMapping("/admin/biz/rule_manage")
@RequiresPermissions("rule_manage")
@MenuOrBtnAnnotation(menu = "规则管理", code = "rule_manage")
public class BizRulManageController{
	
	@RequestMapping(value = "/list")
	public ResponseEntity list(){
		return ResponseEntity.ok().put("data", Rule.getAll());
	}
}
