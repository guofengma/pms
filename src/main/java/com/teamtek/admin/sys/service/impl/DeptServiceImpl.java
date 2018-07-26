package com.teamtek.admin.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.biz.entity.BizDeptScores;
import com.teamtek.admin.biz.service.BizDeptScoresService;
import com.teamtek.admin.sys.dao.DeptDao;
import com.teamtek.admin.sys.entity.Dept;
import com.teamtek.admin.sys.service.DeptService;

/**
 * <p>
 * 部门表  服务实现类
 * </p>
 *
 * @author syj
 * @since 2018-02-06 10:29:50
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

	@Autowired
	private BizDeptScoresService bizDeptScoresService;
	@Override
	@Transactional
	public void deteleDataByBatchIds(List<String> asList) {
		Wrapper<BizDeptScores> wrapper=new EntityWrapper<>();
		wrapper.in("dept_id", asList);
		this.bizDeptScoresService.delete(wrapper);
		this.deleteBatchIds(asList);
	}
	
}
