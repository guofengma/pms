package com.teamtek.admin.biz.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.teamtek.admin.biz.entity.BizDeptScores;
import com.teamtek.core.vo.Record;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
public interface BizDeptScoresDao extends BaseMapper<BizDeptScores> {

	/**
	 * String currMonth,Long projectTypeId
	 * @param param
	 * @return
	 */
	List<Record> selectByCurrMonthAndProjectTypeId(Map<String,Object> param);

}