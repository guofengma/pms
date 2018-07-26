package com.teamtek.admin.score.dao;

import com.teamtek.admin.score.entity.ScoreBaseProject;

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
public interface ScoreBaseProjectDao extends BaseMapper<ScoreBaseProject> {

	List<String> queryAllLoanNos();

}