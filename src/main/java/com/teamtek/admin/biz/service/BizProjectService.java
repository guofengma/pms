package com.teamtek.admin.biz.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.teamtek.admin.biz.entity.BizProject;
import com.teamtek.admin.biz.vo.ProductAndParentVo;
import com.teamtek.core.vo.Record;
/**
 * <p>
 * 服务类
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
public interface BizProjectService extends IService<BizProject> {

	/**
	 * 查询所有的项目、项目名称
	 * @return
	 */
	List<String> queryAllProjectNames();

	/**
	 * 删除项目  （包括项目下关联 的产品）
	 * @param id
	 */
	void deleteDateById(Long id);

	void deleteBatchDataIds(List<String> idList);
	/**
	 * 查询 所有产品的名称 及产品对应的  项目名称
	 * {
	 * 	productName：‘’，
	 * parentProjectName：‘’
	 * }
	 * @return
	 */
	List<ProductAndParentVo> queryAllProductNameAndParentName();

	/**
	 * @param projectName  eq
	 * @param isProduct
	 * @return
	 */
	List<BizProject> queryByProjectName(String projectName);

	
}
