package com.teamtek.admin.score.service;

import com.teamtek.admin.score.entity.ScoreMarketProject;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
/**
 * <p>
 * 服务类
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
public interface ScoreMarketProjectService extends IService<ScoreMarketProject> {


	List<String> queryAllUniqueCodes();
	
}
