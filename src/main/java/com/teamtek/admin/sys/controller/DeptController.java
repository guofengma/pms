package com.teamtek.admin.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.teamtek.admin.sys.entity.Dept;
import com.teamtek.admin.sys.entity.constant.IviewTreeVo;
import com.teamtek.admin.sys.service.DeptService;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author syj
 * @since 2018-02-06 10:29:50
 */
@RestController
@RequestMapping("/admin/dept")
@RequiresPermissions("dept")
@MenuOrBtnAnnotation(menu = "机构管理", code = "dept")
public class DeptController extends BaseController<Dept> {
	@Autowired
	private DeptService deptService;
	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ResponseEntity addOrUpdate(@RequestBody Dept data) {
		Wrapper<Dept> wrapper=new EntityWrapper<>();
		wrapper.eq("dept_name", data.getDeptName());
		List<Dept> dbDepts = this.baseService.selectList(wrapper);
		//更新
		if(data.getId()!=null){
			if(dbDepts.size()>1){
				return ResponseEntity.error("机构名称【"+data.getDeptName()+"】已经存在");
			}
			if(dbDepts.size()==1&&!dbDepts.get(0).getId().equals(data.getId())){
				return ResponseEntity.error("机构名称【"+data.getDeptName()+"】已经存在");
			}
			Dept db = this.baseService.selectById(data.getId());
			db.setDeptName(data.getDeptName());
			db.setLastEditTime(new Date());
			this.baseService.updateById(db);
		//添加
		}else{
			if(!dbDepts.isEmpty()){
				return ResponseEntity.error("机构名称【"+data.getDeptName()+"】已经存在");
			}
			data.setCreateTime(new Date());
			baseService.insert(data);
		}
		return ResponseEntity.ok().put("id", data.getId());
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@RequestMapping(value = "/del")
	public ResponseEntity del(@RequestBody Record param) {
		Object object = param.get("id");
		Validate.notNull(object, "id不能为空");
		String[] ids = object.toString().split(",");
		deptService.deteleDataByBatchIds(Arrays.asList(ids));
		
		//baseService.deleteBatchIds(Arrays.asList(ids));
		return ResponseEntity.ok();
	}

	/**
	 * 生成机构树
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deptTree")
	public ResponseEntity deptTree() {
		List<Dept> list = this.baseService.selectByMap(null);
		List<IviewTreeVo> voTree = toVoTree(list, 0L);
		return ResponseEntity.ok().putData(voTree.get(0));
	}

	private List<IviewTreeVo> toVoTree(List<Dept> tree, Long id) {
		List<IviewTreeVo> retList = new ArrayList<>();
		for (Dept m : tree) {
			if (m.getParentId().equals(id)) {
				IviewTreeVo vo = new IviewTreeVo();
				vo.setTitle(m.getDeptName());
				vo.setId(m.getId());
				if(!m.getParentId().equals(0L)){//只展开 机构节点 
					vo.setExpand(false);
				}
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

}
