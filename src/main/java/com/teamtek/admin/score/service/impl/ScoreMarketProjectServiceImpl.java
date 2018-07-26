package com.teamtek.admin.score.service.impl;

import com.teamtek.admin.score.entity.ScoreMarketProject;
import com.teamtek.admin.score.dao.ScoreMarketProjectDao;
import com.teamtek.admin.score.service.ScoreMarketProjectService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 积分 数据导入 （营销项目）  服务实现类
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
@Service
public class ScoreMarketProjectServiceImpl extends ServiceImpl<ScoreMarketProjectDao, ScoreMarketProject> implements ScoreMarketProjectService {

	@Autowired
	private ScoreMarketProjectDao scoreMarketProjectDao;
	@Override
	public List<String> queryAllUniqueCodes() {
		// TODO Auto-generated method stub
		return scoreMarketProjectDao.queryAllUniqueCodes();
	}
	
}
