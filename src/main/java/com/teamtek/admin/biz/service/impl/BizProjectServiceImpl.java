package com.teamtek.admin.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.biz.dao.BizProjectDao;
import com.teamtek.admin.biz.entity.BizProject;
import com.teamtek.admin.biz.service.BizProjectService;
import com.teamtek.admin.biz.vo.ProductAndParentVo;

/**
 * <p>
 * 项目管理 服务实现类
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
@Service
public class BizProjectServiceImpl extends ServiceImpl<BizProjectDao, BizProject> implements BizProjectService {

	@Autowired
	private BizProjectDao bizProjectDao;

	@Override
	public List<String> queryAllProjectNames() {
//		Wrapper<BizProject> wrapper = new EntityWrapper<>();
//		wrapper.setSqlSelect("project_name");
//		if (isProduct) {
//			wrapper.eq("is_product", 1);
//		} else {
//			wrapper.eq("is_product", 0);
//		}
//		List<BizProject> selectList = this.selectList(wrapper);
//		//return bizProjectDao.queryAllProjectNames();
//		return selectList.stream().map(BizProject::getProjectName).collect(Collectors.toList());
		return bizProjectDao.queryAllProjectNames();
	}

	@Override
	@Transactional
	public void deleteDateById(Long id) {
		this.deleteById(id);
		// 删除子项目（产品）
		Map<String, Object> map = new HashMap<>(1);
		map.put("p_id", id);
		this.deleteByMap(map);

	}

	@Override
	@Transactional
	public void deleteBatchDataIds(List<String> idList) {
		for (String id : idList) {
			this.deleteDateById(Long.valueOf(id));
		}
	}

	@Override
	public List<ProductAndParentVo> queryAllProductNameAndParentName() {
		return bizProjectDao.queryAllProductNameAndParentName();
	}

	@Override
	public List<BizProject> queryByProjectName(String projectName) {
		Wrapper<BizProject> wrapper = new EntityWrapper<>();
		wrapper.eq("project_name", projectName);
		List<BizProject> selectList = this.selectList(wrapper);
		return selectList;
	}

}
