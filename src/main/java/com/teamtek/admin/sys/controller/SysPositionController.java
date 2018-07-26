package com.teamtek.admin.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.teamtek.admin.biz.entity.BizProject;
import com.teamtek.admin.biz.service.BizProjectService;
import com.teamtek.admin.sys.entity.BizPositionProject;
import com.teamtek.admin.sys.entity.SysPosition;
import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.admin.sys.service.BizPositionProjectService;
import com.teamtek.admin.sys.service.SysPositionService;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author syj
 * @since 2018-02-27 09:53:52
 */
@RestController
@RequestMapping("/admin/sys/position")
// @RequiresPermissions("position")
@MenuOrBtnAnnotation(menu = "岗位管理", code = "position")
public class SysPositionController extends BaseController<SysPosition> {
	@Autowired
	private SysPositionService sysPositionService;

	@Autowired
	private BizProjectService bizProjectService;

	@Autowired
	private BizPositionProjectService bizPositionProjectService;

	/**
	 * 分页查询 查询条件 岗位名 title
	 */
	public ResponseEntity datagrid(@RequestBody Record param) {
		Wrapper<SysPosition> ew = new EntityWrapper<>();
		Validate.notNull(param.get("current"), "current不能为空");
		Validate.notNull(param.get("size"), "size不能为空");
		Integer current = Integer.valueOf(param.get("current").toString());
		Integer size = Integer.valueOf(param.get("size").toString());
		Page<SysPosition> page = new Page<SysPosition>(current, size);

		if (param.get("title") != null) {
			ew.like("title", param.get("title").toString().trim());
		}
		ew.orderBy("create_time", false);
		baseService.selectPage(page, ew);
		return ResponseEntity.ok().putData(super.createPaginationResult(new DataGrid<SysPosition>(page)));
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ResponseEntity add(@RequestBody SysPosition data) {
		data.setCreateTime(new Date());
		baseService.insert(data);
		return ResponseEntity.ok();
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toEdit")
	public ResponseEntity toEdit(@RequestParam(name = "id") Long id) {
		SysPosition data = this.baseService.selectById(id);
		return ResponseEntity.ok().putData(data);
	}

	@RequestMapping(value = "/doEdit")
	public ResponseEntity doEdit(@RequestBody SysPosition data) {
		SysPosition db = this.baseService.selectById(data.getId());
		BeanUtils.copyProperties(data, db, "id", "createTime");
		baseService.updateById(db);
		// 岗位变化 后 更新shiro中缓存的当前用户的岗位信息
		updateShiroUserPostions();
		return ResponseEntity.ok();
	}

	// 更新当前用户 对应的岗位
	private void updateShiroUserPostions() {
		SysUser user = getCurrUser();
		List<SysPosition> positions = this.sysPositionService.selectPositionsByUserId(user.getUserId());
		user.setPositions(positions);
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/del")
	public ResponseEntity del(@RequestBody SysPosition data) {
		baseService.deleteById(data.getId());
		// 岗位变化 后 更新shiro中缓存的当前用户的岗位信息
		updateShiroUserPostions();
		return ResponseEntity.ok();
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delBatch")
	public ResponseEntity delBatch(@RequestParam(name = "ids") String ids) {
		String[] primaryKeys = ids.split(",");
		List<String> idList = Arrays.asList(primaryKeys);
		baseService.deleteBatchIds(idList);

		// 岗位变化 后 更新shiro中缓存的当前用户的岗位信息
		updateShiroUserPostions();
		return ResponseEntity.ok();
	}

	/**
	 * 获取所有岗位
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public ResponseEntity getAll() {
		List<SysPosition> selectByMap = this.baseService.selectByMap(null);
		return ResponseEntity.ok().putData(selectByMap);
	}

	/**
	 * 获取所有的项目(最后一级)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getAllProjects")
	public ResponseEntity getAllProjects() {
		List<BizProject> projects = this.bizProjectService.selectByMap(null).stream()
				.filter(c -> c.getIsProduct() || !c.getHasProduct()).collect(Collectors.toList());
		return ResponseEntity.ok().putData(projects);
	}

	/**
	 * 用于  岗位配置项目  回显
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toConfigProject")
	public ResponseEntity toConfigProject(@RequestBody SysPosition data) {
		SysPosition db = this.baseService.selectById(data.getId());
		Map<String, Object> map = new HashMap<>();
		map.put("project_id", db.getId());
		List<BizPositionProject> bpps = this.bizPositionProjectService.selectByMap(map);
		
		List<Long> baseIdList = bpps.stream().filter(c -> c.getType() == BizPositionProject.Type.base.getValue())
				.map(BizPositionProject::getProjectId).collect(Collectors.toList());
		List<Long> linkIdList = bpps.stream().filter(c -> c.getType() == BizPositionProject.Type.link.getValue())
				.map(BizPositionProject::getProjectId).collect(Collectors.toList());
		
		Record result = new Record();
		result.set("baseIdList", baseIdList);
		result.set("linkIdList", linkIdList);
		return ResponseEntity.ok().putData(result);
	}

	/**
	 * 项目配置 请求参数 { id:"",//岗位id linkIds:"",//配置的联动项目id 多个id以 ，分割
	 * baseIds:""//配置的基础项目id 多个id以 ，分割 }
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/projectConfig")
	public ResponseEntity projectConfig(@RequestBody Record param) {
		String id = param.get("id", "").toString();
		if (StringUtils.isBlank(id)) {	
			return ResponseEntity.error("岗位id不能为空");
		}
		Long positionId = Long.valueOf(id);
		String s = param.get("linkIds", "").toString();
		String s2 = param.get("baseIds", "").toString();

		String[] linkIds = null;
		String[] baseIds = null;
		if (StringUtils.isNotBlank(s)) {
			linkIds = s.split(",");
		}
		if (StringUtils.isNotBlank(s2)) {
			baseIds = s2.split(",");
		}

		this.sysPositionService.positionProjectConfig(positionId, linkIds, baseIds);

		return ResponseEntity.ok();
	}

	@Override
	protected JSONObject toJSON(SysPosition d) {
		JSONObject json = super.toJSON(d);
		if (d.getCreateTime() != null)
			json.put("createTime", DateFormatUtils.format(d.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		return json;
	}
}
