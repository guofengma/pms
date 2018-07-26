package com.teamtek.admin.biz.dao;

import java.util.Date;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.teamtek.admin.biz.entity.BizTotalScores;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
public interface BizTotalScoresDao extends BaseMapper<BizTotalScores> {

	/**
	 * 当月中积分  结余
	 * @param month
	 */
	void surplusTotalScores(Date month);

}