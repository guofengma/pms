package com.teamtek.admin.score.service;

import com.teamtek.admin.score.entity.ScoreData;
import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.core.vo.Record;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
/**
 * <p>
 * 服务类
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
public interface ScoreDataService extends IService<ScoreData> {

	/**
	 * 查询所有的 借据号
	 * @return
	 */
	List<String> queryAllLoanNos();

	Page<Map<String, Object>> queryMapsPage(Record param);


}
