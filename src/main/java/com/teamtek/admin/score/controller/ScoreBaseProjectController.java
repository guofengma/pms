package com.teamtek.admin.score.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;
import com.teamtek.shiro.Constant;

/**
 * <p>
 * 积分 数据导入 （基础项目） 前端控制器
 * </p>
 *
 * @author syj
 * @since 2018-03-14 14:40:51
 */
@RestController
@RequestMapping("/admin/score/base_project")
@RequiresPermissions("base_score_data")
@MenuOrBtnAnnotation(menu = "基础业务数据管理", code = "base_score_data")
public class ScoreBaseProjectController extends BaseController<ScoreBaseProject> {
	/**
	 * 分页 SELECT sbp.*, (SELECT p.project_name FROM biz_project p WHERE
	 * sbp.project_id=p.id) projectName, (SELECT p.project_name FROM biz_project
	 * p WHERE sbp.product_id=p.id) productName FROM score_base_project sbp
	 */

	public ResponseEntity datagrid(@RequestBody Record param) {
		Object current = param.get("current");
		Object size = param.get("size");
		Validate.notNull(current, "current不能为空！");
		Validate.notNull(size, "size不能为空！");
		Wrapper<ScoreBaseProject> wrapper = new EntityWrapper<>();
		Page<ScoreBaseProject> page = new Page<ScoreBaseProject>(Integer.valueOf(current.toString()),
				Integer.valueOf(size.toString()));
		StringBuilder sb = new StringBuilder(" score_base_project.*,");
		sb.append(" (SELECT p.project_name FROM biz_project p WHERE score_base_project.project_id=p.id) projectName,");
		sb.append(" (SELECT d.dept_name FROM sys_dept d WHERE score_base_project.fall_dept=d.id) fallDeptName, ");
		sb.append(" (SELECT sd.import_no FROM score_data sd WHERE sd.id=score_base_project.score_data_id) importNo ");
		wrapper.setSqlSelect(sb.toString());

		String userNo = param.get("userNo", "").toString();
		wrapper.like("loan_no", userNo).or().like("market_man_no", userNo).or().like("master_no", userNo).or()
				.like("assistant_no", userNo).or().like("rate_shareman_no", userNo);
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
	public ResponseEntity toEdit(@RequestParam(name = "id") Long id) {
		ScoreBaseProject data = this.baseService.selectById(id);
		return ResponseEntity.ok().putData(data);
	}

	@RequestMapping(value = "/doEdit")
	public ResponseEntity doEdit(@RequestBody ScoreBaseProject data) {
		baseService.updateById(data);
		return ResponseEntity.ok();
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/del")
	public ResponseEntity del(@RequestBody ScoreBaseProject data) {
		baseService.deleteById(data.getId());
		return ResponseEntity.ok();
	}

	@Override
	protected JSONObject toJSONMap(Map<String, Object> d) {
		JSONObject json = super.toJSONMap(d);
		if (d.get("loan_date") != null) {
			json.put("loan_date", DateFormatUtils.format((Date) d.get("loan_date"), "yyyy-MM-dd"));
		}
		if (d.get("is_retrospect_link") != null) {
			if ((Boolean) d.get("is_retrospect_link")) {
				json.put("is_retrospect_link", ExcelConst.YesOrNo.yes.title());
			} else {
				json.put("is_retrospect_link", ExcelConst.YesOrNo.no.title());
			}
		}

		return json;
	}
}
