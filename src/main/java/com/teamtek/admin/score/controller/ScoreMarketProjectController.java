package com.teamtek.admin.score.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.teamtek.admin.comm.util.ExcelConst;
import com.teamtek.admin.score.entity.ScoreBaseProject;
import com.teamtek.admin.score.entity.ScoreMarketProject;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;
import com.teamtek.shiro.Constant;

/**
 * <p>
 * （营销项目） 前端控制器
 * </p>
 *
 * @author syj
 * @since 2018-03-14 14:40:51
 */
@RestController
@RequestMapping("/admin/score/market_project")
@RequiresPermissions("market_score_data")
@MenuOrBtnAnnotation(menu = "营销业务数据管理", code = "market_score_data")
public class ScoreMarketProjectController extends BaseController<ScoreMarketProject> {
	@Resource
	private Constant constant;

	/**
	 * SELECT smp.*, (SELECT p.project_name FROM biz_project p WHERE
	 * smp.project_id=p.id) projectName, (SELECT d.dept_name FROM sys_dept d
	 * WHERE smp.fall_dept=d.id) deptName
	 * 
	 * FROM score_market_project smp
	 */
	public ResponseEntity datagrid(@RequestBody Record param) {
		Object current = param.get("current");
		Object size = param.get("size");
		Validate.notNull(current, "current不能为空！");
		Validate.notNull(size, "size不能为空！");
		Wrapper<ScoreMarketProject> wrapper = new EntityWrapper<>();
		Page<ScoreMarketProject> page = new Page<ScoreMarketProject>(Integer.valueOf(current.toString()),
				Integer.valueOf(size.toString()));
		StringBuilder sb = new StringBuilder(" score_market_project.*,");
		sb.append(
				" (SELECT p.project_name FROM biz_project p WHERE score_market_project.product_id=p.id) projectName, ");
		sb.append(" (SELECT d.dept_name FROM sys_dept d WHERE score_market_project.fall_dept=d.id) fallDeptName, ");
		sb.append(" (SELECT sd.import_no FROM score_data sd WHERE sd.id=score_market_project.score_data_id) importNo ");
		wrapper.setSqlSelect(sb.toString());

		String userNo = param.get("userNo", "").toString();

		// 当前用户是普通用户
		if (StringUtils.isNotBlank(userNo)) {
			wrapper.like("loan_no", userNo).or().like("market_man_no", userNo);
		}
		wrapper.orderBy("loan_date", false);
		Page<Map<String, Object>> pages = baseService.selectMapsPage(page, wrapper);
		return ResponseEntity.ok().putData(super.createPaginationMapResult(new DataGrid<Map<String, Object>>(pages)));
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/toEdit")
	public ResponseEntity toEdit(@RequestBody ScoreMarketProject data) {
		ScoreMarketProject db = this.baseService.selectById(data.getId());
		return ResponseEntity.ok().putData(db);
	}
	@RequestMapping(value = "/doEdit")
	public ResponseEntity doEdit(@RequestBody ScoreMarketProject data) {
//		Validate.notNull(data.getIdCard(), "");
//		Validate.notNull(data.getBizAmount(), "");
//		Validate.notNull(data.getFallDept(), "");
//		Validate.notNull(data.getLoanDate(), "");
//		Validate.notNull(data.getMarketManNo(), "");
//		Validate.notNull(data.getUniqueCode(), "");
//		Validate.notNull(data.get, "");
		ScoreMarketProject db = this.baseService.selectById(data.getId());
		BeanUtils.copyProperties(data, db, "id");
		baseService.updateById(data);
		return ResponseEntity.ok();
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/del")
	public ResponseEntity del(@RequestBody ScoreMarketProject data) {
		baseService.deleteById(data.getId());
		return ResponseEntity.ok();
	}

	@Override
	protected JSONObject toJSONMap(Map<String, Object> d) {
		JSONObject json = super.toJSONMap(d);
		if (d.get("loan_date") != null) {
			json.put("loan_date", DateFormatUtils.format((Date) d.get("loan_date"), "yyyy-MM-dd"));
		}
		return json;
	}
}
