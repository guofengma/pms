package com.teamtek.admin.score.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.score.dao.ScoreBaseProjectDao;
import com.teamtek.admin.score.dao.ScoreDataDao;
import com.teamtek.admin.score.entity.ScoreData;
import com.teamtek.admin.score.service.ScoreDataService;
import com.teamtek.core.vo.Record;

/**
 * <p>
 * 业务数据管理表  服务实现类
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
@Service
public class ScoreDataServiceImpl extends ServiceImpl<ScoreDataDao, ScoreData> implements ScoreDataService {

	@Autowired
	private ScoreBaseProjectDao scoreBaseProjectDao;
	@Autowired
	private ScoreDataDao scoreDataDao;
	@Override
	public List<String> queryAllLoanNos() {
		return scoreBaseProjectDao.queryAllLoanNos();
	}
	@Override
	public Page<Map<String, Object>> queryMapsPage(Record param) {
		Integer total = scoreDataDao.queryMapsPageTotal(param);
		List<Map<String, Object>> records=Collections.emptyList();
		Integer current = Integer.valueOf(param.get("current").toString());
		Integer size = Integer.valueOf(param.get("size").toString());
		if(total!=null&&total>0){
			param.set("current", (current-1)*size);
			records= scoreDataDao.queryMapsPage(param);
		}
		Page<Map<String, Object>> pages=new Page<>(current, size);
		pages.setTotal(total);
		pages.setRecords(records);
		return pages;
	}
	
}
