package com.teamtek.admin.biz.dao;

import com.teamtek.admin.biz.entity.BizProjectTypeScores;
import com.teamtek.core.vo.Record;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
public interface BizProjectTypeScoresDao extends BaseMapper<BizProjectTypeScores> {

	List<Record> selectByCurrMonth(String currMonth);

}