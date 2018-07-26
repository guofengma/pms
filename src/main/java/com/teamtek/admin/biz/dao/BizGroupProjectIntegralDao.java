package com.teamtek.admin.biz.dao;

import com.teamtek.admin.biz.entity.BizGroupProjectIntegral;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-03-15 20:45:37
 */
public interface BizGroupProjectIntegralDao extends BaseMapper<BizGroupProjectIntegral> {
	
	List<BizGroupProjectIntegral> findByMainZoneIdAndSearchDate(Long mainZoneId,String searchDate);
	
}