package com.teamtek.admin.biz.dao;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.teamtek.admin.biz.entity.BizProjectIntegral;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-03-15 20:45:37
 */
public interface BizProjectIntegralDao extends BaseMapper<BizProjectIntegral> {

	List<BizProjectIntegral> findByGroupConfigureId(Long groupConfigureId, String searchTime);

	List<BizProjectIntegral> findByGroupQuoteConfigureId(Long groupQuoteConfigureId, String searchTime);
	
	/**
	 *  根据主区域ID和查询日期查询
	 * @param mainZoneId 主区域ID
	 * @param searchDate
	 * @return
	 */
	List<BizProjectIntegral> findByMainZoneIdAndSearchDate(Long mainZoneId,String searchDate);

}