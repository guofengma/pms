package com.teamtek.admin.score.dao;

import com.teamtek.admin.score.entity.ScoreMarketProject;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
public interface ScoreMarketProjectDao extends BaseMapper<ScoreMarketProject> {

	List<String> queryAllUniqueCodes();

}