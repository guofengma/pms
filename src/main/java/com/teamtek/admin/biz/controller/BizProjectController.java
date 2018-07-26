package com.teamtek.admin.biz.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.teamtek.admin.biz.entity.BizProject;
import com.teamtek.admin.biz.entity.BizProjectType;
import com.teamtek.admin.biz.service.BizProjectService;
import com.teamtek.admin.biz.service.BizProjectTypeService;
import com.teamtek.admin.biz.vo.BizProjectVo;
import com.teamtek.admin.comm.util.ExcelConst;
import com.teamtek.admin.comm.util.MyExcelUtil;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;

/**
 * <p>
 * 项目管理 前端控制器
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
@RestController
@RequestMapping("/admin/biz/project")
@RequiresPermissions("project")
@MenuOrBtnAnnotation(menu = "项目管理", code = "project")
public class BizProjectController extends BaseController<BizProject> {
	@Autowired
	private BizProjectTypeService bizProjectTypeService;
	@Autowired
	private BizProjectService bizProjectService;

	/**
	 * 分页查询
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ResponseEntity list(@RequestBody Record param) {

		Object current = param.get("current");
		Object size = param.get("size");
		Validate.notNull(current, "current不能为空！");
		Validate.notNull(size, "size不能为空！");
		Wrapper<BizProject> wrapper = new EntityWrapper<>();
		Page<BizProject> page = new Page<BizProject>(Integer.valueOf(current.toString()),
				Integer.valueOf(size.toString()));
		StringBuilder sb = new StringBuilder(" biz_project.*,");
		sb.append(
				" (SELECT bpt.type_name FROM biz_project_type bpt  WHERE bpt.id=biz_project.project_type_id) projectType ");
		wrapper.setSqlSelect(sb.toString());
		wrapper.eq("is_product", 0);
		if (param.get("projectName") != null) {
			wrapper.like("project_name", param.get("projectName").toString());
		}
		String status = param.get("status", "").toString();
		if (StringUtils.isNotBlank(status)) {
			wrapper.eq("status", Integer.valueOf(status.toString()));
		}
		String type = param.get("type", "").toString();
		if (StringUtils.isNotBlank(type)) {
			wrapper.eq("project_type_id", Long.valueOf(type.toString()));
		}
		wrapper.orderBy("create_time", false);
		Page<Map<String, Object>> pages = baseService.selectMapsPage(page, wrapper);
		// super.createPaginationMapResult(new DataGrid<Map<String,
		// Object>>(pages));
		return ResponseEntity.ok().put("data",
				super.createPaginationMapResult(new DataGrid<Map<String, Object>>(pages)));
	}

	/**
	 * 用于查询条件 状态 list
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/getStatusList")
	public ResponseEntity getStatusList() {
		List<Record> statusList = BizProject.Status.getVoList();
		List<BizProjectType> typeList = this.bizProjectTypeService.selectByMap(null);
		Record r = new Record();
		r.set("statusList", statusList);
		r.set("typeList", typeList);
		return ResponseEntity.ok().putData(r);
	}

	/**
	 * 添加项目
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public ResponseEntity toAdd() {
		List<BizProjectType> allType = this.bizProjectTypeService.selectByMap(null);

		Record data = new Record();
		data.put("projectTypeList", allType);
		data.put("projectStatusList", BizProject.Status.getRecordList());
		return ResponseEntity.ok().putData(data);
	}

	@RequestMapping(value = "/doAdd")
	public ResponseEntity doAdd(@RequestBody BizProject data) {
		Validate.notNull(data.getProjectName(), "项目名不能为空");

		data.setCreateTime(new Date());
		if (StringUtils.isNotBlank(data.getProjectNum())) {
			Map<String, Object> map = new HashMap<String, Object>(1);
			map.put("project_num", data.getProjectNum());
			List<BizProject> selectByMap = baseService.selectByMap(map);
			if (selectByMap.size() > 0) {
				return ResponseEntity.error("项目编号【" + data.getProjectNum() + "】已经存在，请改写为别的编号！");
			}
		}
		data.setProjectName(data.getProjectName().trim());

		List<BizProject> dbProjects = this.bizProjectService.queryByProjectName(data.getProjectName());

		if (dbProjects != null && dbProjects.size() > 0) {
			return ResponseEntity.error("项目名【" + data.getProjectName() + "】已经存在");
		}
		if (StringUtils.isBlank(data.getProjectNum())) {
			data.setProjectNum(null);
		}
		baseService.insert(data);
		return ResponseEntity.ok();
	}

	// @RequestMapping(value = "/queryProducts")
	// public ResponseEntity queryProducts(@RequestBody BizProject data) {
	// Map<String, Object> map=new HashMap<>(1);
	// map.put("project_id", data.getId());
	// List<BizProduct> products = this.bizProductService.selectByMap(map);
	// return ResponseEntity.ok().putData(products);
	// }
	/**
	 * 添加或修改项目 下的 产品
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateProduct")
	public ResponseEntity saveOrUpdateProduct(@RequestBody BizProject data) {
		Validate.notNull(data.getProjectName(), "产品名称projectName不能为空！！！");
		data.setProjectName(data.getProjectName().trim());

		// 项目下的 产品 名 不能重复
		List<BizProject> dbProjects = this.bizProjectService.queryByProjectName(data.getProjectName());
		if (dbProjects.size() > 1) {
			return ResponseEntity.error("产品名【" + data.getProjectName() + "】已经存在");
		}
		// 更新
		if (data.getId() != null) {
			if (dbProjects.size() == 1) {
				BizProject db = dbProjects.get(0);
				// 若果 不一个 产品 但名字相同
				if ((!db.getId().equals(data.getId())) && data.getProjectName().equals(db.getProjectName())) {
					return ResponseEntity.error("产品名【" + data.getProjectName() + "】已经存在");
				}
			}
			BizProject db = this.bizProjectService.selectById(data.getId());
			db.setProjectName(data.getProjectName());
			db.setProjectRemark(data.getProjectRemark());
			db.setLastEditTime(new Date());
			db.setUpperLimit(data.getUpperLimit());
			db.setUnit(data.getUnit());
			this.bizProjectService.updateById(db);
		} else {
			if (dbProjects.size() == 1) {
				return ResponseEntity.error("产品名【" + data.getProjectName() + "】已经存在");
			}
			// 添加
			Validate.notNull(data.getpId(), "pId不能为空！！！");
			BizProject parent = this.baseService.selectById(data.getpId());
			if (StringUtils.isBlank(data.getProjectNum()))
				data.setProjectNum(null);

			data.setCreateTime(new Date());
			data.setIsProduct(true);
			data.setProjectTypeId(parent.getProjectTypeId());
			this.bizProjectService.insert(data);

			if (!parent.getHasProduct()) {
				parent.setHasProduct(true);
				this.baseService.updateById(parent);
			}
		}
		return ResponseEntity.ok();
	}

	/**
	 * 删除产品
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/delProduct")
	public ResponseEntity delProduct(@RequestBody BizProject data) {
		Validate.notNull(data.getId(), "产品id不能为空！！！");
		BizProject dbProduct = this.bizProjectService.selectById(data.getId());

		Long pId = dbProduct.getpId();
		this.bizProjectService.deleteById(dbProduct);

		Wrapper<BizProject> wrapper = new EntityWrapper<>();
		wrapper.eq("p_id", pId);
		int productCount = this.bizProjectService.selectCount(wrapper);
		// 项目没有产品了
		if (productCount == 0) {
			BizProject dbPro = this.baseService.selectById(pId);
			dbPro.setHasProduct(false);
			this.baseService.updateById(dbPro);
		}

		return ResponseEntity.ok();
	}

	/**
	 * 修改项目
	 * 
	 * @param data
	 * @return
	 */

	@RequestMapping(value = "/doEdit")
	public ResponseEntity doEdit(@RequestBody BizProject data) {
		Validate.notNull(data.getProjectName(), "项目名不能为空");
		data.setProjectName(data.getProjectName().trim());
		List<BizProject> dbProjects = this.bizProjectService.queryByProjectName(data.getProjectName());
		int size = dbProjects.size();
		if (size > 1) {
			return ResponseEntity.error("项目名【" + data.getProjectName() + "】已经存在");
		}
		if (size == 1) {
			BizProject db = dbProjects.get(0);
			// 如果不同的项目id 名字相同
			if ((!data.getId().equals(db.getId())) && data.getProjectName().equals(db.getProjectName())) {
				return ResponseEntity.error("项目名【" + data.getProjectName() + "】已经存在");
			}
		}

		BizProject db = this.baseService.selectById(data.getId());
		if (StringUtils.isNotBlank(data.getProjectNum()) && !data.getProjectNum().equals(db.getProjectNum())) {
			Map<String, Object> map = new HashMap<String, Object>(1);
			map.put("project_num", data.getProjectNum());
			List<BizProject> selectByMap = baseService.selectByMap(map);
			if (selectByMap.size() > 0) {
				return ResponseEntity.error("项目编号【" + data.getProjectNum() + "】已经存在，请改写为别的编号！");
			}
		}

		BeanUtils.copyProperties(data, db, "id", "createTime", "isProduct", "pId");
		db.setLastEditTime(new Date());

		if (StringUtils.isBlank(data.getProjectNum())) {
			db.setProjectNum(null);
		}
		baseService.updateById(db);
		return ResponseEntity.ok();
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/del")
	public ResponseEntity del(@RequestBody BizProject data) {
		bizProjectService.deleteDateById(data.getId());
		log.info("删除项目id【{}】,删除人【{}】【userId：{}】", data.getId(), getCurrUser().getName(), getCurrUser().getUserId());
		return ResponseEntity.ok();
	}

	/**
	 * 根据项目id获取 产品
	 * 
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/getProductsByProjectId")
	public ResponseEntity getProductsByProjectId(@RequestBody Record param) {
		String projectId = param.get("projectId", "").toString();
		Validate.notBlank(projectId, "projectId不能为空");
		Wrapper<BizProject> wrapper = new EntityWrapper<>();
		wrapper.eq("p_id", Long.valueOf(projectId));
		wrapper.orderBy("create_time", false);
		List<BizProject> products = this.bizProjectService.selectList(wrapper);

		Map<Long, String> typeIdNameMap = this.bizProjectTypeService.selectByMap(null).stream()
				.collect(Collectors.toMap(BizProjectType::getId, BizProjectType::getTypeName));
		List<JSONObject> jsonObjectList = new ArrayList<>(products.size());
		for (BizProject t : products) {
			JSONObject json = this.toJSON(t);
			if (typeIdNameMap.containsKey(t.getProjectTypeId())) {
				json.put("projectType", typeIdNameMap.get(t.getProjectTypeId()));
			}
			if (t.getCreateTime() != null)
				json.put("createTime", DateFormatUtils.format(t.getCreateTime(), "yyyy-MM-dd"));
			if (t.getStatus() != null) {
				json.put("status", BizProject.Status.getTitleByValue(t.getStatus()));
			}
			jsonObjectList.add(json);
		}
		return ResponseEntity.ok().putData(jsonObjectList);
	}

	@RequestMapping(value = "/123")
	public ResponseEntity test3() {
		return ResponseEntity.ok().putData(this.baseService.selectMaps(null));
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delBatch")
	public ResponseEntity delBatch(@RequestBody Record param) {
		String ids = param.get("ids", "").toString();
		Validate.notBlank(ids, "ids不能为空！");
		String[] primaryKeys = ids.split(",");
		List<String> idList = Arrays.asList(primaryKeys);
		bizProjectService.deleteBatchDataIds(idList);

		log.info("删除项目ids【{}】,删除人【{}】【userId：{}】", ids, getCurrUser().getName(), getCurrUser().getUserId());
		return ResponseEntity.ok();
	}

	/**
	 * 项目批量导入
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/projectImport")
	public ResponseEntity projectImport(@RequestBody Record param,
			@Value("${server.fileUpload.rootPath}") String rootPath) {
		// public ResponseEntity
		// projectImport(@Value("${server.fileUpload.rootPath}") String
		// rootPath) {
		String path = param.get("file_path", "").toString();
		Validate.notBlank(path, "file_path不能为空");
		List<BizProjectVo> list = null;
		String fileRealPath = rootPath + "/" + path;
		// String fileRealPath = "c:/import_project.xls";
		try {
			File f = new File(fileRealPath);
			if (!f.exists()) {
				return ResponseEntity.error("项目导入失败，检查参数file_path是否正确！");
			}
			InputStream inputStream = new FileInputStream(f);
			list = MyExcelUtil.importExcel(0, 1, BizProjectVo.class, inputStream, "yyyy/MM/dd", 0);
		} catch (Exception e) {
			return ResponseEntity.error("项目批量导入错误，请核对excel格式是否正确！");
		}

		/********* 0.整体验证 ***********************************/
		// 验证项目名、类型不能为空、验证项目编号（编号选填 若果填写的话 不能有重复）
		String result = validateProjectData(list);
		if (result != null) {
			return ResponseEntity.error("项目批量导入错误：" + result);
		}
		// 验证项目名、产品 名是否重复
		List<String> temps = projectNameIsRepet(list);
		if (!temps.isEmpty()) {
			return ResponseEntity.error("项目批量导入错误，项目名存在重复：" + temps);
		}
		// 验证项目名、产品 是否已存在
		temps = projectNameIsExist(list);
		if (!temps.isEmpty()) {
			return ResponseEntity.error("项目批量导入错误，项目名已经存在：" + temps);
		}

		/********* 2.导入项目数据 ***********************************/
		// 2.1、导入项目数据
		importProjectdata(list);
		
		StringBuilder successProjectMsg = new StringBuilder("成功导入项目个数【");
		successProjectMsg.append(String.valueOf(list.size()));
		successProjectMsg.append("】");

		log.info("--项目导入成功，导入个数:【{}】，服务器文件路径【{}】！！--", list.size(), fileRealPath);
		return ResponseEntity.ok().putData(successProjectMsg.toString());
	}

	/**
	 * 验证excel中项目名称是否重复
	 * 
	 * @param list
	 * @return
	 */
	private List<String> projectNameIsRepet(List<BizProjectVo> list) {
		Map<String, List<BizProjectVo>> collect = list.stream()
				.collect(Collectors.groupingBy(BizProjectVo::getProjectName));
		List<String> names = new ArrayList<>();
		for (Map.Entry<String, List<BizProjectVo>> en : collect.entrySet()) {
			if (en.getValue().size() > 1) {
				names.add(en.getKey());
			}
		}
		return names;
	}

	/**
	 * 验证excel中项目名称 是否在数据库中已经存在
	 * 
	 * @param list
	 * @return
	 */
	private List<String> projectNameIsExist(List<BizProjectVo> list) {
		List<String> names = this.bizProjectService.queryAllProjectNames();
		List<String> exists = list.stream().filter(c -> names.contains(c.getProjectName()))
				.map(BizProjectVo::getProjectName).collect(Collectors.toList());
		return exists;
	}

	private void importProjectdata(List<BizProjectVo> list) {

		list.stream().forEach(c -> {
			BizProject project = new BizProject();
			project.setProjectName(c.getProjectName());
			project.setProjectRemark(c.getProjectRemark());
			project.setProjectNum(c.getProjectNum());
			project.setUnit(c.getUnit());
			project.setUpperLimit(c.getUpperLimit());
			project.setCreateTime(c.getCreateTime());
			project.setProjectTypeId(Long.valueOf(c.getProjectTypeId()));
			project.setStatus(Integer.valueOf(c.getStatus()));
			this.baseService.insert(project);

		});
	}

	/**
	 * 初步验证 excel中 项目名、项目类型 、产品的所属项目 是否为空 项目编号 如果填写的话 不能 出现重复
	 * 
	 * @param list
	 * @return
	 */
	private String validateProjectData(List<BizProjectVo> list) {
		String res = null;
		Map<String, Integer> temp = new HashMap<>();
		List<String> projectNums = this.baseService.selectList(null).stream().filter(c -> c.getProjectNum() != null)
				.map(BizProject::getProjectNum).collect(Collectors.toList());
		
		List<BizProjectType> Typlist=this.bizProjectTypeService.selectByMap(null);
		Map<String, Long> typenameIdMap =Typlist.stream()
				.collect(Collectors.toMap(BizProjectType::getTypeName, BizProjectType::getId));
		for (BizProjectVo bo : list) {
			if (StringUtils.isBlank(bo.getProjectName())) {
				res = "项目名称不能为空";
				break;
			}
			if (StringUtils.isBlank(bo.getProjectTypeId())) {
				res = "项目类型不能为空";
				break;
			}

			if (StringUtils.isNotBlank(bo.getProjectNum())) {
				if (temp.containsKey(bo.getProjectNum())) {
					res = "项目编号【" + bo.getProjectNum() + "】存在重复";
					break;
				}
				if (projectNums.contains(bo.getProjectNum())) {
					res = "项目编号【" + bo.getProjectNum() + "】已经存在";
					break;
				}
				temp.put(bo.getProjectNum(), 1);
			}

			// 项目类型
			if (StringUtils.isNotBlank(bo.getProjectTypeId())) {
				if (typenameIdMap.containsKey(bo.getProjectTypeId())) {
					bo.setProjectTypeId(String.valueOf(typenameIdMap.get(bo.getProjectTypeId())));
				} else {
					List<String> typeNames = Typlist.stream().map(BizProjectType::getTypeName).collect(Collectors.toList());
					res = "项目类型【"+bo.getProjectTypeId()+"】不存在，应该为："+typeNames;
					log.warn("---------项目类型：【{}】不存在------", bo.getProjectTypeId());
					break;
				}
			}else{
				res = "项目类型不能为空";
				break;
			}

			// 项目状态
			Integer status = BizProject.Status.getValueByTitle(bo.getStatus());
			if (status == null) {
				res = MessageFormat.format("状态格式错误:{0}，应该为：【运行中，暂停中，已结束】", bo.getStatus());
				break;
			}
			bo.setStatus(String.valueOf(status));
		}
		return res;
	}

	@Override
	protected JSONObject toJSONMap(Map<String, Object> d) {
		JSONObject json = super.toJSONMap(d);
		if (d.get("create_time") != null)
			json.put("create_time", DateFormatUtils.format((Date) d.get("create_time"), "yyyy-MM-dd"));
		if (d.get("status") != null) {
			int status = Integer.valueOf(d.get("status").toString()).intValue();
			json.put("status", BizProject.Status.getTitleByValue(status));
		}
		if ((Boolean) d.get("has_product")) {
			json.put("has_product", ExcelConst.YesOrNo.yes.title());
		} else {
			json.put("has_product", ExcelConst.YesOrNo.no.title());
		}

		return json;
	}

}
