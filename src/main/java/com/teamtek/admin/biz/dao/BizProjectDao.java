package com.teamtek.admin.biz.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.teamtek.admin.biz.entity.BizProject;
import com.teamtek.admin.biz.vo.ProductAndParentVo;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
public interface BizProjectDao extends BaseMapper<BizProject> {

	List<String> queryAllProjectNames();
	/**
	 * 查询 所有产品的名称 及产品对应的  项目名称
	 * {
	 * 	productName：‘’，
	 * parentProjectName：‘’
	 * }
	 * @return
	 */
	List<ProductAndParentVo> queryAllProductNameAndParentName();

}