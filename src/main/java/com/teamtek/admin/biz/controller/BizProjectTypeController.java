package com.teamtek.admin.biz.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.teamtek.admin.biz.entity.BizDeptScores;
import com.teamtek.admin.biz.entity.BizProjectType;
import com.teamtek.admin.biz.entity.BizProjectTypeScores;
import com.teamtek.admin.biz.entity.BizTotalScores;
import com.teamtek.admin.biz.service.BizDeptScoresService;
import com.teamtek.admin.biz.service.BizProjectTypeScoresService;
import com.teamtek.admin.biz.service.BizTotalScoresService;
import com.teamtek.admin.sys.entity.Dept;
import com.teamtek.admin.sys.entity.constant.IviewTreeVo;
import com.teamtek.admin.sys.service.DeptService;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;

/**
 * <p>
 * 项目类型(积分管理) 前端控制器
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
@RestController
@RequestMapping("/admin/biz/project_type")
@RequiresPermissions("project_type")
@MenuOrBtnAnnotation(menu = "积分管理", code = "project_type")
public class BizProjectTypeController extends BaseController<BizProjectType> {
	@Autowired
	private DeptService deptService;

	@Autowired
	private BizProjectTypeScoresService bizProjectTypeScoresService;

	@Autowired
	private BizDeptScoresService bizDeptScoresService;

	@Autowired
	private BizTotalScoresService bizTotalScoresService;

	// /**
	// * 添加
	// * @return
	// */
	// @RequestMapping(value = "/add")
	// public ResponseEntity add(@RequestBody BizProjectType data) {
	// baseService.insert(data);
	// return ResponseEntity.ok();
	// }
	// /**
	// * 编辑
	// * @return
	// */
	// @RequestMapping(value = "/toEdit")
	// public ResponseEntity toEdit(@RequestParam(name = "id") Long id) {
	// BizProjectType data=this.baseService.selectById(id);
	// return ResponseEntity.ok().putData(data);
	// }
	/**
	 * 编辑总积分
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateTotalScore")
	public ResponseEntity doEditTotalScore(@RequestBody BizTotalScores data) {
		// Validate.notNull(data.getId(), "id不能为空");
		Validate.notNull(data.getCurrScore(), "总积分不能为空");
		Validate.notNull(data.getCurrMonth(), "currMonth不能为空（格式yyyy-MM-01）");
		// 编辑
		if (data.getId() != null) {
			BizTotalScores db = this.bizTotalScoresService.selectById(data.getId());
			db.setCurrScore(data.getCurrScore());
			db.setTotalScore(scoresToAdd(db.getCurrScore(), db.getScoreFromLast()));
			bizTotalScoresService.updateById(db);
		} else {
			// 新建
			data.setCurrMonth(DateUtils.truncate(data.getCurrMonth(), Calendar.MONTH));
			this.bizTotalScoresService.insert(data);
		}

		return ResponseEntity.ok();
	}
	/**
	 * value1+value2
	 * @param total
	 * @param finished
	 * @return
	 */
	private Double scoresToAdd(Double value1, Double value2) {
		value1 = value1 == null ? 0D : value1;
		value2 = value2 == null ? 0D : value2;
		BigDecimal bs = new BigDecimal(value1);
		BigDecimal bp = new BigDecimal(value2);
		BigDecimal bResult = bs.add(bp);
		bResult = bResult.setScale(2, BigDecimal.ROUND_CEILING);
		return bResult.doubleValue();
	}

	/**
	 * 编辑 项目类型 积分
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateProjectTypeScore")
	public ResponseEntity doEditProjectTypeScore(@RequestBody BizProjectTypeScores data) {
		// Validate.notNull(data.getId(), "id不能为空");
		Validate.notNull(data.getCurrScore(), "项目类型 积分不能为空");
		Validate.notNull(data.getCurrMonth(), "currMonth不能为空（格式yyyy-MM-01）");
		// 修改
		if (data.getId() != null) {
			BizProjectTypeScores db = this.bizProjectTypeScoresService.selectById(data.getId());
			db.setCurrScore(data.getCurrScore());
			db.setTotalScore(scoresToAdd(db.getCurrScore(), db.getScoreFromLast()));
			bizProjectTypeScoresService.updateById(db);
		} else {
			// 新建
			data.setCurrMonth(DateUtils.truncate(data.getCurrMonth(), Calendar.MONTH));
			bizProjectTypeScoresService.insert(data);
		}

		return ResponseEntity.ok();
	}

	/**
	 * 编辑 机构 积分
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateDeptScore")
	public ResponseEntity doEditDeptScore(@RequestBody BizDeptScores data) {
		// Validate.notNull(data.getId(), "id不能为空");
		Validate.notNull(data.getCurrScore(), "部门编辑积分不能为空");
		// 修改
		if (data.getId() != null) {
			BizDeptScores db = this.bizDeptScoresService.selectById(data.getId());
			db.setCurrScore(data.getCurrScore());
			db.setTotalScore(scoresToAdd(db.getCurrScore(), db.getScoreFromLast()));
			db.setLastEditTime(new Date());
			bizDeptScoresService.updateById(db);
		} else {
			// 新增
			Validate.notNull(data.getCurrMonth(), "currMonth不能为空（格式yyyy-MM-01）");
			data.setCurrMonth(DateUtils.truncate(data.getCurrMonth(), Calendar.MONTH));
			data.setTotalScore(data.getCurrScore());
			bizDeptScoresService.insert(data);
		}

		return ResponseEntity.ok();
	}

	/**
	 * 查询 参数 queryDate 格式 yyyy-MM-01
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ResponseEntity list(@RequestBody Record param) {
		//public ResponseEntity list() {

		String queryDateStr = param.get("queryDate", "").toString().trim();
		//String queryDateStr="2018-02-01";
		if (StringUtils.isNotBlank(queryDateStr)) {
			if (queryDateStr.length() != 10 || !queryDateStr.substring(8).equals("01")) {
				return ResponseEntity.error("查询参数应为 yyyy-MM-01,比如2018-12-01表示 12月份");
			}
		} else {
			// 默认当前月份
			queryDateStr = DateFormatUtils.format(new Date(), "yyyy-MM-01");
		}

		// 1.总积分
		Wrapper<BizTotalScores> w = new EntityWrapper<>();
		w.eq("curr_month", queryDateStr);
		BizTotalScores totalScores = bizTotalScoresService.selectOne(w);
		// 2.项目类型） 对应积分
		List<Record> projectTypeScores = this.bizProjectTypeScoresService.selectByCurrMonth(queryDateStr);

		// 3.机构树（部门树）
		List<Dept> depts = this.deptService.selectByMap(null);
		List<IviewTreeVo> voTree = toVoTree(depts, 0L).get(0).getChildren();
		
		for (Record typeScore : projectTypeScores) {
			List<IviewTreeVo> newTree = cloneVoTree(voTree);
			// 4.该项目类型 该月份 的 机构（部门） 对应积分
			Object id = typeScore.get("id");
			//4.1该项目类型  分配机构积分
			if(id!=null){
				Map<String,Object> param2=new HashMap<>();
				param2.put("currMonth", queryDateStr);
				param2.put("projectTypeId", Long.valueOf(typeScore.get("projectTypeId").toString()));
				List<Record> deptScores = this.bizDeptScoresService.selectByCurrMonthAndProjectTypeId(param2);
				// key为 部门id
				Map<Long, Record> temp = new HashMap<>();
				for (Record r : deptScores) {
					temp.put(Long.valueOf(r.get("deptId").toString()), r);
				}
				//给机构分配积分
				toScoresVoTree(newTree, temp);
			}
			typeScore.put("dept_scores", newTree);
		}
		// 封装数据
		Record data = new Record();
		data.put("total_scores", totalScores);
		data.put("project_type_scores", projectTypeScores);
		return ResponseEntity.ok().putData(data);
	}

	/**
	 * 深层克隆
	 * 
	 * @param voTree
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<IviewTreeVo> cloneVoTree(List<IviewTreeVo> voTree) {
		List<IviewTreeVo> newTree = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(voTree);
			ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			newTree = (List<IviewTreeVo>) ois.readObject();
		} catch (Exception e) {
			return Collections.emptyList();
		}
		return newTree;
	}

	private List<IviewTreeVo> toScoresVoTree(List<IviewTreeVo> tree, Map<Long, Record> temp) {
		for (IviewTreeVo m : tree) {
			if (temp.containsKey(m.getId())) {
				m.setFinishScore(Double.valueOf(temp.get(m.getId()).get("finishScore","0").toString()));
				m.setTotalScore(Double.valueOf(temp.get(m.getId()).get("totalScore","0").toString()));
				m.setCurrScore(Double.valueOf(temp.get(m.getId()).get("currScore","0").toString()));
				m.setScoreFromLast(Double.valueOf(temp.get(m.getId()).get("scoreFromLast","0").toString()));
				m.setScoreToNext(Double.valueOf(temp.get(m.getId()).get("scoreToNext","0").toString()));
				m.setRecordId(Long.valueOf(temp.get(m.getId()).get("id").toString()));
			}
			if (m.getChildren() != null && m.getChildren().size() > 0) {
				toScoresVoTree(m.getChildren(), temp);
			}
		}
		return tree;
	}

	private List<IviewTreeVo> toVoTree(List<Dept> tree, Long id) {
		List<IviewTreeVo> retList = new ArrayList<>();
		for (Dept m : tree) {
			if (m.getParentId().equals(id)) {
				IviewTreeVo vo = new IviewTreeVo();
				vo.setTitle(m.getDeptName());
				vo.setId(m.getId());
				if (!m.getId().equals(id)) {
					List<IviewTreeVo> children = toVoTree(tree, m.getId());
					if (children.size() > 0) {
						vo.setChildren(children);
					}
				}
				retList.add(vo);
			}
		}
		return retList;
	}
	/**
	 * 积分结余
	 * @param data
	 * @return
	 */
	
	@RequestMapping(value = "/surplusScores")
	public ResponseEntity surplusScores(@RequestBody Record param) {
		//public ResponseEntity surplusScores() {
		String queryDateStr = param.get("queryDate", "").toString().trim();
		//String queryDateStr="2018-02-01";
		if (StringUtils.isBlank(queryDateStr)) {
			return ResponseEntity.error("请选择需要积分结余的月份！");
		}else{
			if (queryDateStr.length() != 10 || !queryDateStr.substring(8).equals("01")) {
				return ResponseEntity.error("查询参数应为 yyyy-MM-01,比如2018-12-01表示 12月份");
			}
			Date queryMonth;
			try {
				queryMonth = DateUtils.truncate(DateUtils.parseDate(queryDateStr, "yyyy-MM-dd"), Calendar.MONTH);
			} catch (ParseException e) {
				throw new RuntimeException("月份格式错误");
			}
			this.bizProjectTypeScoresService.surplusScores(queryMonth);
			log.info("月份【{}】积分结余成功,结余人【{}】【userId：{}】", queryDateStr,getCurrUser().getName(), getCurrUser().getUserId());
		}
		return ResponseEntity.ok();
	}
}
