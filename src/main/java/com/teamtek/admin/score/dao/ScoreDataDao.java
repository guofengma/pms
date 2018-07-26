package com.teamtek.admin.score.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.teamtek.admin.score.entity.ScoreData;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
public interface ScoreDataDao extends BaseMapper<ScoreData> {

	List<Map<String, Object>> queryMapsPage(Map<String,Object> param);
	
	Integer queryMapsPageTotal(Map<String,Object> param);
}