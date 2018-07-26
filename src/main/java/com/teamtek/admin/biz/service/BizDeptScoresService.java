package com.teamtek.admin.biz.service;

import com.teamtek.admin.biz.entity.BizDeptScores;
import com.teamtek.core.vo.Record;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
/**
 * <p>
 * 服务类
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
public interface BizDeptScoresService extends IService<BizDeptScores> {
	/**
	 * 按月查询  部门（机构）  对应 积分  currMonth, projectTypeId
	 * @param queryDateStr 格式  yyyy-MM-01  比如2000-08-01  表示 8月份
	 * @return
	 */
	List<Record> selectByCurrMonthAndProjectTypeId(Map<String,Object> param);
	
}
