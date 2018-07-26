package com.teamtek.admin.score.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.teamtek.admin.biz.entity.BizProject;
import com.teamtek.admin.biz.entity.BizRuleTemplate;
import com.teamtek.admin.biz.service.BizProjectService;
import com.teamtek.admin.biz.service.BizRuleTemplateService;
import com.teamtek.admin.comm.util.ExcelConst;
import com.teamtek.admin.comm.util.MyExcelUtil;
import com.teamtek.admin.score.entity.ScoreBaseProject;
import com.teamtek.admin.score.entity.ScoreData;
import com.teamtek.admin.score.entity.ScoreData.ImportStatus;
import com.teamtek.admin.score.entity.ScoreMarketProject;
import com.teamtek.admin.score.service.ScoreBaseProjectService;
import com.teamtek.admin.score.service.ScoreDataService;
import com.teamtek.admin.score.service.ScoreMarketProjectService;
import com.teamtek.admin.score.vo.ScoreBaseProjectVo;
import com.teamtek.admin.score.vo.ScoreMarketProjectVo;
import com.teamtek.admin.sys.bo.ExcelImportLog;
import com.teamtek.admin.sys.entity.Dept;
import com.teamtek.admin.sys.service.DeptService;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;

/**
 * <p>
 * 业务数据管理表 前端控制器
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
@RestController
@RequestMapping("/admin/score/")
@MenuOrBtnAnnotation(menu = "业务数据管理", code = "score_data")
public class ScoreDataController extends BaseController<ScoreData> {
	@Autowired
	private ScoreDataService scoreDataService;
	@Autowired
	private BizProjectService bizProjectService;
	@Autowired
	private ScoreBaseProjectService scoreBaseProjectService;

	@Autowired
	private ScoreMarketProjectService scoreMarketProjectService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private BizRuleTemplateService bizRuleTemplateService;

	@RequestMapping("/datagrid")
	public ResponseEntity datagrid(@RequestBody Record param) {
		String current = param.get("current", "").toString();
		String size = param.get("size", "").toString();
		Validate.notNull(current, "current不能为空！");
		Validate.notNull(size, "size不能为空！");

		Page<Map<String, Object>> pages = scoreDataService.queryMapsPage(param);
		// super.createPaginationMapResult(new DataGrid<Map<String,
		// Object>>(pages));
		return ResponseEntity.ok().put("data",
				super.createPaginationMapResult(new DataGrid<Map<String, Object>>(pages)));
	}

	/**
	 * 删除 (注：此删除 数据量较大 谨慎操作)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delData")
	public ResponseEntity del(@RequestBody ScoreData data) {
		Map<String, Object> map = new HashMap<>();
		map.put("score_data_id", data.getId());
		this.scoreBaseProjectService.deleteByMap(map);
		this.scoreMarketProjectService.deleteByMap(map);

		this.scoreDataService.deleteById(data.getId());

		return ResponseEntity.ok();
	}

	/**
	 * 删除 基础项目业务数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delBase")
	public ResponseEntity del(@RequestBody ScoreBaseProject data) {
		this.scoreBaseProjectService.deleteById(data.getId());
		return ResponseEntity.ok();
	}

	/**
	 * 删除 营销项目业务数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delMarket")
	public ResponseEntity del(@RequestBody ScoreMarketProject data) {
		this.scoreMarketProjectService.deleteById(data.getId());
		return ResponseEntity.ok();
	}

	/**
	 * 返回 模板select 下拉框 数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/queryRuleTemplateSelectData")
	public ResponseEntity queryRuleTemplateSelectData() {
		List<BizRuleTemplate> allTemplates = this.bizRuleTemplateService.selectByMap(null);
		// List<Record> selects=new ArrayList<>(allTemplates.size());
		// for(BizRuleTemplate t:allTemplates){
		// Record r = new Record();
		// r.set("value", t.getId());
		// r.set("label", t.getName());
		// selects.add(r);
		// }
		return ResponseEntity.ok().putData(allTemplates);
	}

	/**
	 * 业务数据批量导入
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/import")
	public ResponseEntity bizDataImport(@RequestBody Record param,
			@Value("${server.fileUpload.rootPath}") String rootPath) {
		String path = param.get("file_path", "").toString();
		String ruleTemplateId = param.get("ruleTemplateId", "").toString();
		// String importNo = param.get("importNo", "").toString();
		// Validate.notBlank(path, "file_path不能为空");
		Validate.notBlank(ruleTemplateId, "ruleTemplateId不能为空");
		// 基础项目积分数据导入
		List<ScoreBaseProjectVo> list = null;
		String fileRealPath = rootPath + "/" + path;
		// String fileRealPath = "c:/积分导入模板(1).xlsx";
		File f = null;
		try {
			f = new File(fileRealPath);
			if (!f.exists()) {
				return ResponseEntity.error("业务数据导入失败，检查参数file_path是否正确！");
			}
			InputStream inputStream = new FileInputStream(f);
			list = MyExcelUtil.importExcel(0,2, ScoreBaseProjectVo.class, inputStream, "yyyy-MM-dd", 0);
		} catch (Exception e) {
			log.error("业务数据批量导入错误，请核对excel格式是否正确！");
			return ResponseEntity.error("业务数据批量导入错误，请核对excel格式是否正确！");
		}
		//////////////// 第一步 导入基础项目业务数据
		/** 0.整体验证 ***/
		// 验证借据号、落地部门、项目、产品名 不能为空、excel中 借据号不能重复、
		// 验证项目、产品是否存在
		// 验证 落地部门 是否存在
		String result = validateBaseData(list);
		if (result != null) {
			return ResponseEntity.error("业务数据批量导入错误：" + result);
		}
		// 验证借据号 是否已存在(与数据库对比)
		List<String> temps = loanNoIsExist(list);
		if (!temps.isEmpty()) {
			return ResponseEntity.error("业务数据批量导入错误，借据号已经存在：" + temps);
		}

		/*** 1.导入基础项目业务数据 ***/
		ScoreData sd = new ScoreData();
		sd.setImportStatus(ImportStatus.begin.getValue());
		sd.setImportNo(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		sd.setRuleTemplateId(Long.valueOf(ruleTemplateId));
		sd.setImportTime(new Date());
		this.baseService.insert(sd);
		ExcelImportLog excelLog = importBaseData(list, sd.getId());
		// 更新状态
		updateImportStatus(sd, ImportStatus.base_import_success);
		int importBaseSize = list.size();
		list = null;
		//////////////// 第二步 导入营销项目业务数据
		List<ScoreMarketProjectVo> smpvList = null;
		try {
			InputStream inputStream = new FileInputStream(f);
			smpvList = MyExcelUtil.importExcel(1,1, ScoreMarketProjectVo.class, inputStream, "yyyy-MM-dd", 0);
		} catch (Exception e) {
			log.error("基础项目业务数据导入成功，但营销项目导入失败，请检查营销项目excel表格式是否正确");
			// 更新状态
			updateImportStatus(sd, ImportStatus.base_success_and_market_fail);
			return ResponseEntity.error("基础项目业务数据导入成功，但营销项目导入失败，请检查营销项目excel表格式是否正确");
		}
		/** 0.整体验证 ***/
		// 验证借据号、落地部门、项目、产品名 不能为空、excel中 唯一码不能重复、
		// 验证项目、产品是否存在
		// 验证 落地部门 是否存在
		result = validateMarketData(smpvList);
		if (result != null) {
			updateImportStatus(sd, ImportStatus.base_success_and_market_fail);
			return ResponseEntity.error("基础项目业务数据导入成功，但营销项目导入失败：" + result);
		}
		if (!temps.isEmpty()) {
			// 更新状态
			updateImportStatus(sd, ImportStatus.base_success_and_market_fail);
			return ResponseEntity.error("基础项目业务数据导入成功，但营销项目导入失败，因为唯一码已经存在：" + temps);
		}
		/*** 1.导入营销项目业务数据 ***/
		importMarketData(smpvList, excelLog, sd.getId());
		// 更新状态
		//updateImportStatus(sd, ImportStatus.all_success);
		sd.setImportStatus(ImportStatus.all_success.getValue());
		sd.setMarketCount(smpvList.size());
		sd.setBaseCount(importBaseSize);
		this.baseService.updateById(sd);
		StringBuilder successProjectMsg = new StringBuilder("成功导入基础项目业务数据【");
		successProjectMsg.append(String.valueOf(importBaseSize));
		successProjectMsg.append("】条--");
		successProjectMsg.append("营销项目【");
		successProjectMsg.append(String.valueOf(smpvList.size()));
		successProjectMsg.append("】条");
		excelLog.setImportMsg(successProjectMsg.toString());
		if (excelLog.getHasWarn()) {
			log.warn("--业务数据导入成功，但存在警告:【{}】，服务器文件路径【{}】！！--", Arrays.toString(excelLog.getWarrns().toArray()),
					fileRealPath);
			// return ResponseEntity.ok("业务数据导入成功，但存在警告：" +
			// excelLog.getWarrns());
		}
		log.info("--业务数据导入成功，导入基础项目业务数据:【{}条】，营销项目：【{}条】，服务器文件路径【{}】！！--", importBaseSize, smpvList.size(),
				fileRealPath);
		return ResponseEntity.ok().putData(excelLog);
	}


	private void updateImportStatus(ScoreData sd, ImportStatus status) {
		// 更新状态
		sd.setImportStatus(status.getValue());
		this.baseService.updateById(sd);
	}

	/**
	 * 导入 营销 业务数据
	 * 
	 * @param smpvList
	 * @param excelLog
	 * @return
	 */
	private void importMarketData(List<ScoreMarketProjectVo> smpvList, ExcelImportLog excelLog, Long dataId) {
		for (ScoreMarketProjectVo c : smpvList) {
			ScoreMarketProject project = new ScoreMarketProject();
			project.setProductId(Long.valueOf(c.getProjectName()));
			project.setFallDept(Long.valueOf(c.getFallDept()));
			project.setBizAmount(c.getBizAmount());
			project.setCustomerName(c.getCustomerName());
			project.setIdCard(c.getIdCard());
			project.setLoanNo(c.getLoanNo());
			project.setMarketManNo(c.getMarketManNo());
			project.setScoreDataId(dataId);
			project.setUnit(c.getUnit());
			project.setScoreAmount(c.getScoreAmount());
			project.setBizUnit(c.getBizUnit());
			project.setRemark(c.getRemark());
			project.setLoanDate(c.getLoanDate());
			this.scoreMarketProjectService.insert(project);
		}
	}


	/**
	 * 营销项目验证
	 * 
	 * @param list
	 * @return
	 */
	private String validateMarketData(List<ScoreMarketProjectVo> list) {
		String res = null;
		//Map<String, Integer> temp = new HashMap<>();
		// 项目名 --id
		Map<String, Long> nameIdMap = this.bizProjectService.selectByMap(null).stream()
				.collect(Collectors.toMap(BizProject::getProjectName, BizProject::getId));
		// 部门名--id
		Map<String, Long> deptNameIdMap = this.deptService.selectList(null).stream()
				.collect(Collectors.toMap(Dept::getDeptName, Dept::getId));
		
	//	List<String> bizUnitList = ExcelConst.BizUnit.getTitleList();
		for (ScoreMarketProjectVo bo : list) {
			if (StringUtils.isBlank(bo.getProjectName())) {
				res = "产品名称不能为空";
				break;
			}
			if (!nameIdMap.containsKey(bo.getProjectName())) {
				res = "产品【" + bo.getProjectName() + "】不存在";
				break;
			}
			bo.setProjectName(String.valueOf(nameIdMap.get(bo.getProjectName())));

			if (StringUtils.isBlank(bo.getFallDept())) {
				res = "落地部门不能为空";
				break;
			}
			// 落地部门名 转 id
			if (!deptNameIdMap.containsKey(bo.getFallDept())) {
				res = "落地部门【" + bo.getFallDept() + "】不存在";
				break;
			}
//			if (!NumberUtils.isParsable(bo.getBizAmount())) {
//				res = "业务量【" + bo.getBizAmount() + "】应该是数字";
//				break;
//			}
//			if (!bizUnitList.contains(bo.getBizUnit())) {
//				res = "业务量单位【" + bo.getBizUnit() + "】错误，应该是【元，万元，项】";
//				break;
//			}
//			bo.setBizUnit(ExcelConst.BizUnit.getValueByTitle(bo.getBizUnit()).toString());
			bo.setFallDept(String.valueOf(deptNameIdMap.get(bo.getFallDept())));
			//temp.put(bo.getUniqueCode(), 1);
		}
		return res;
	}

	/**
	 * 初步验证 excel中 项目名、项目类型 、产品的所属项目 是否为空 、 验证项目、产品是否存在 验证 落地部门 是否存在
	 * 
	 * @param list
	 * @return
	 */
	private String validateBaseData(List<ScoreBaseProjectVo> list) {
		String res = null;
		Map<String, Integer> temp = new HashMap<>();
		// 项目名 --id
		Map<String, Long> nameIdMap = this.bizProjectService.selectByMap(null).stream()
				.collect(Collectors.toMap(BizProject::getProjectName, BizProject::getId));
		for (ScoreBaseProjectVo bo : list) {
			if (StringUtils.isBlank(bo.getLoanNo())) {
				res = "借据号不能为空";
				break;
			}
			if (temp.containsKey(bo.getLoanNo())) {
				res = "excel中借据号【" + bo.getLoanNo() + "】有重复";
			}
			if (StringUtils.isBlank(bo.getProjectName())) {
				res = "项目名称不能为空";
				break;
			}
			if (!nameIdMap.containsKey(bo.getProjectName())) {
				res = "项目【" + bo.getProjectName() + "】不存在";
				break;
			}
			// 项目名 转 id
			bo.setProjectName(String.valueOf(nameIdMap.get(bo.getProjectName())));

			temp.put(bo.getLoanNo(), 1);
		}

		return res;
	}

	/**
	 * 验证excel中loanNo借据号 是否在数据库中已经存在
	 * 
	 * @param list
	 * @return
	 */
	private List<String> loanNoIsExist(List<ScoreBaseProjectVo> list) {
		List<String> loanNos = this.scoreDataService.queryAllLoanNos();
		List<String> exists = list.stream().filter(c -> loanNos.contains(c.getLoanNo()))
				.map(ScoreBaseProjectVo::getLoanNo).collect(Collectors.toList());
		return exists;
	}

	private ExcelImportLog importBaseData(List<ScoreBaseProjectVo> list, Long dataId) {
		ExcelImportLog excellog = new ExcelImportLog();

		for (ScoreBaseProjectVo c : list) {
			ScoreBaseProject project = new ScoreBaseProject();
			project.setProjectId(Long.valueOf(c.getProjectName()));
			project.setFallDept(getCurrUser().getDeptId());
			project.setScoreDataId(dataId);
			project.setAssistantNo(c.getAssistantNo());
			project.setBizAmount(c.getBizAmount());
			project.setScoreDataId(dataId);
			project.setIdCard(c.getId_card());
			project.setLoanNo(c.getLoanNo());
			project.setLoanRate(c.getLoanRate());
			project.setMarketManNo(c.getMarketManNo());
			project.setMasterNo(c.getMasterNo());
			project.setRateSharemanNo(c.getRateSharemanNo());
			project.setStandardRate(c.getStandardRate());
			project.setUnit(c.getUnit());
			project.setLoanDate(c.getLoanDate());
			project.setTeamEmpNos(c.getTeamEmpNos());
			project.setTeamEmpRates(c.getTeamEmpRates());
			project.setCustomer(c.getCustomer());
			project.setRateScore(c.getRatescore());
			project.setSaveOrAdd(c.getSaveOrAdd());
			project.setTeamEmpNames(c.getTeamEmpNames());
			// 是否追溯联动贷款
			if (ExcelConst.YesOrNo.yes.title().equals(c.getIsRetrospectLink())) {
				project.setIsRetrospectLink(true);
				project.setRetrospectLinkNo(c.getRetrospectLinkNo());
			} else {
				project.setIsRetrospectLink(false);
			}
			this.scoreBaseProjectService.insert(project);
		}

		return excellog;
	}

//	private Double strToDouble(String value) {
//		BigDecimal bs = new BigDecimal(value);
//		//return bs.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
//		return bs.doubleValue();
//	}

	@Override
	protected JSONObject toJSONMap(Map<String, Object> d) {
		JSONObject json = super.toJSONMap(d);
		if (d.get("import_time") != null) {
			json.put("import_time", DateFormatUtils.format((Date) d.get("import_time"), "yyyy-MM-dd HH:mm"));
		}
		if (d.get("import_status") != null) {
			json.put("import_status",
					ScoreData.ImportStatus.getTitleByValue(Integer.valueOf(d.get("import_status").toString())));
		}

		return json;
	}

}
