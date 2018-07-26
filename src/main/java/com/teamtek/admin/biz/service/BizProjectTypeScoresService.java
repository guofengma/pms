package com.teamtek.admin.biz.service;

import com.teamtek.admin.biz.entity.BizProjectTypeScores;
import com.teamtek.core.vo.Record;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
/**
 * <p>
 * 服务类
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
public interface BizProjectTypeScoresService extends IService<BizProjectTypeScores> {

	/**
	 * 按月查询  项目类型  对应 积分
	 * @param queryDateStr 格式  yyyy-MM-01  比如2000-08-01  表示 8月份
	 * @return
	 */
	List<Record> selectByCurrMonth(String queryDateStr);

	/**
	 * 积分结余（总积分结余、项目类型积分结余、机构积分结余）
	 * @param month
	 */
	void surplusScores(Date month);
	
}
