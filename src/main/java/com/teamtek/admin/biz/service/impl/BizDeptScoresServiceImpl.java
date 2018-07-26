package com.teamtek.admin.biz.service.impl;

import com.teamtek.admin.biz.dao.BizDeptScoresDao;
import com.teamtek.admin.biz.entity.BizDeptScores;
import com.teamtek.admin.biz.service.BizDeptScoresService;
import com.teamtek.core.vo.Record;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 机构（部门）积分 服务实现类
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
@Service
public class BizDeptScoresServiceImpl extends ServiceImpl<BizDeptScoresDao, BizDeptScores>
		implements BizDeptScoresService {

	@Autowired
	private BizDeptScoresDao bizDeptScoresDao;

	@Override
	public List<Record> selectByCurrMonthAndProjectTypeId(Map<String,Object> param) {
		return bizDeptScoresDao.selectByCurrMonthAndProjectTypeId(param);
	}

}
