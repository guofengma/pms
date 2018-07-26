package com.teamtek.core.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;
import com.teamtek.shiro.util.ShiroUtils;

/**
 * @ClassName: BaseController
 * @Description: Controller通用的转发处理
 * @author shenzhihao
 * @email zhihao@teamtek.cn
 * @date 2017年12月29日 上午11:17:59
 *
 */
public class BaseController<T extends Serializable> extends AbstController {

	@Autowired
	protected IService<T> baseService;

	@RequestMapping("/datagrid")
	public ResponseEntity datagrid(@RequestBody Record param) {
		// @RequestParam(name = "size", required = true) Integer size,
		// @RequestParam(name = "current", required = true) Integer current,
		// @RequestParam(name = "search", required = false) String search
		Wrapper<T> ew = new EntityWrapper<>();

		Validate.notNull(param.get("current"), "current不能为空");
		Validate.notNull(param.get("size"), "size不能为空");
		Integer current = Integer.valueOf(param.get("current").toString());
		Integer size = Integer.valueOf(param.get("size").toString());
		Page<T> page = new Page<T>(current, size);
		baseService.selectPage(page, ew);
		return ResponseEntity.ok().putData(createPaginationResult(new DataGrid<T>(page)));
	}

	/**
	 * 结果集为map时的 json转化
	 * 
	 * @param grid
	 * @return
	 */
	protected JSONObject createPaginationMapResult(DataGrid<Map<String, Object>> grid) {
		JSONObject datasObj = new JSONObject();

		int currPage = grid.getCurrPage();
		List<Map<String, Object>> list = grid.getList();
		JSONArray arrayData = new JSONArray();
		for (Map<String, Object> d : list) {
			JSONObject data = toJSONMap(d);
			arrayData.add(data);
		}

		datasObj.put("list", arrayData);
		datasObj.put("totalCount", grid.getTotalCount());
		datasObj.put("currPage", currPage);
		datasObj.put("pageSize", grid.getPageSize());
		datasObj.put("totalPage", grid.getTotalPage());
		return datasObj;
	}

	/**
	 * 结果集为javaBean时的转化
	 * 
	 * @param grid
	 * @return
	 */
	protected JSONObject createPaginationResult(DataGrid<T> grid) {
		JSONObject datasObj = new JSONObject();

		int currPage = grid.getCurrPage();
		List<T> list = grid.getList();
		JSONArray arrayData = new JSONArray();
		for (T d : list) {
			JSONObject data = toJSON(d);
			arrayData.add(data);
		}

		datasObj.put("list", arrayData);
		datasObj.put("totalCount", grid.getTotalCount());
		datasObj.put("currPage", currPage);
		datasObj.put("pageSize", grid.getPageSize());
		datasObj.put("totalPage", grid.getTotalPage());
		return datasObj;
	}

	protected JSONObject toJSONMap(Map<String, Object> d) {
		return (JSONObject) JSONObject.toJSON(d);
	}

	protected JSONObject toJSON(T d) {
		return (JSONObject) JSONObject.toJSON(d);
	}

	protected List<JSONObject> toJSONObjectList(List<T> list) {
		if(list.isEmpty()){
			return Collections.emptyList();
		}
		List<JSONObject> js=new ArrayList<>(list.size());
		for(T t:list){
			js.add(this.toJSON(t));
		}
		return js;
	}
	// @RequestMapping(value = "/del", method = { RequestMethod.POST })
	// public ResponseEntity del(Serializable primaryKey) {
	// baseService.deleteById(primaryKey);
	// return ResponseEntity.ok();
	// }
	//
	// @RequestMapping(value = "/delBatch", method = { RequestMethod.POST })
	// public ResponseEntity delBatch(@RequestParam(name = "pks") String
	// primaryKeys) {
	//
	// return ResponseEntity.ok();
	// }

	/**
	 * 获取当前用户
	 * @return
	 */
	protected SysUser getCurrUser(){
		return ShiroUtils.getAdminEntity();
	}
}
