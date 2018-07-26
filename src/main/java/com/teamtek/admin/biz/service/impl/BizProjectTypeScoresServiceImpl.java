package com.teamtek.admin.biz.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.biz.dao.BizDeptScoresDao;
import com.teamtek.admin.biz.dao.BizProjectTypeScoresDao;
import com.teamtek.admin.biz.dao.BizTotalScoresDao;
import com.teamtek.admin.biz.entity.BizDeptScores;
import com.teamtek.admin.biz.entity.BizProjectTypeScores;
import com.teamtek.admin.biz.entity.BizTotalScores;
import com.teamtek.admin.biz.service.BizProjectTypeScoresService;
import com.teamtek.core.vo.Record;

/**
 * <p>
 * 项目类型每个月分的积分 服务实现类
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
@Service
public class BizProjectTypeScoresServiceImpl extends ServiceImpl<BizProjectTypeScoresDao, BizProjectTypeScores>
		implements BizProjectTypeScoresService {
	/**
	 * @Fields log : 子类公用的日志记录器
	 */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BizProjectTypeScoresDao bizProjectTypeScoresDao;
	@Autowired
	private BizTotalScoresDao bizTotalScoresDao;
	@Autowired
	private BizDeptScoresDao bizDeptScoresDao;

	@Override
	public List<Record> selectByCurrMonth(String queryDateStr) {
		return bizProjectTypeScoresDao.selectByCurrMonth(queryDateStr);
	}

	/**
	 * 积分结余（总积分结余、项目类型积分结余、机构积分结余）
	 */
	@Override
	@Transactional
	public void surplusScores(Date month) {
		/************* 华丽的分割线***** 1、结余总积分 *****************************/
		Map<String, Object> map = new HashMap<>();
		map.put("curr_month", month);
		List<BizTotalScores> dbTotalScores = this.bizTotalScoresDao.selectByMap(map);
		if (dbTotalScores.size() == 0 || dbTotalScores.size() > 1) {
			log.error("总积分结余出现错误，月份：【{}】总积分数据记录不唯一", DateFormatUtils.format(month, "yyyy-MM-dd"));
			throw new RuntimeException("总积分结余出现错误，月份：【" + DateFormatUtils.format(month, "yyyy-MM-dd") + "】总积分数据记录不唯一");
		}
		BizTotalScores dbTotal = dbTotalScores.get(0);
		Double scoreToNext = scoresToSub(dbTotal.getTotalScore(), dbTotal.getFinishScore());

		// 1.1、更新当月 结余 信息
		dbTotal.setScoreToNext(scoreToNext);
		this.bizTotalScoresDao.updateById(dbTotal);

		// 下月 积分 查询条件
		Map<String, Object> map2 = new HashMap<>();
		map2.put("curr_month", DateUtils.addMonths(month, 1));
		// 1.2、更新到下月 结余 信息
		List<BizTotalScores> dbNextTotalScores = this.bizTotalScoresDao.selectByMap(map2);
		if (dbNextTotalScores.size() > 1) {
			log.error("总积分 结余出现错误，月份：【{}】总积分数据记录不唯一", DateFormatUtils.format(month, "yyyy-MM-dd"));
			throw new RuntimeException("总积分结余出现错误，月份：【" + DateFormatUtils.format(month, "yyyy-MM-dd") + "】总积分数据记录不唯一");
		}
		// 1.2.1、数据库 还没 下月总积分 信息
		if (dbNextTotalScores.size() == 0) {
			// 如果有结余 则 结余到下月
			if (scoreToNext > 0) {
				BizTotalScores total = new BizTotalScores();
				total.setTotalScore(scoreToNext);
				total.setScoreFromLast(scoreToNext);
				total.setCurrMonth(DateUtils.addMonths(month, 1));
				this.bizTotalScoresDao.insert(total);
			}
		} else { // 1.2.2、数据库 已经 存在 下月总积分 信息 ----更新
			BizTotalScores dbNextTotal = dbNextTotalScores.get(0);
			dbNextTotal.setTotalScore(scoresToAdd(dbNextTotal.getCurrScore(), scoreToNext));
			dbNextTotal.setScoreFromLast(scoreToNext);
			this.bizTotalScoresDao.updateById(dbNextTotal);
		}
		/************* 华丽的分割线*****2、结余 项目类型 积分 *****************************/
		List<BizProjectTypeScores> typeScores = this.bizProjectTypeScoresDao.selectByMap(map);
		for (BizProjectTypeScores type : typeScores) {
			scoreToNext = scoresToSub(type.getTotalScore(), type.getFinishScore());
			type.setScoreToNext(scoreToNext);
			// 更新当月
			this.bizProjectTypeScoresDao.updateById(type);

			// 更新下月
			map2.put("project_type_id", type.getId());
			List<BizProjectTypeScores> dbNextTypeScores = this.bizProjectTypeScoresDao.selectByMap(map2);
			if (dbNextTypeScores.size() > 1) {
				log.error("项目类型积分结余出现错误，月份：【{}】项目类型【id:{}】积分数据记录不唯一", DateFormatUtils.format(month, "yyyy-MM-dd"),
						type.getId());
				throw new RuntimeException(
						"项目类型积分结余出现错误，月份：【" + DateFormatUtils.format(month, "yyyy-MM-dd") + "】项目类型积分数据记录不唯一");
			}
			// 数据库 还没 下月 该项目类型积分 信息 ----添加
			if (dbNextTypeScores.size() == 0) {
				// 如果有结余 则 结余到下月
				if (scoreToNext > 0) {
					BizProjectTypeScores insertType = new BizProjectTypeScores();
					insertType.setProjectTypeId(type.getId());
					insertType.setTotalScore(scoreToNext);
					insertType.setScoreFromLast(scoreToNext);
					insertType.setCurrMonth(DateUtils.addMonths(month, 1));
					this.bizProjectTypeScoresDao.insert(insertType);
				}
			} else { // 数据库 已经 存在 该项目类型积分 信息 ----更新
				BizProjectTypeScores dbNextType = dbNextTypeScores.get(0);
				dbNextType.setTotalScore(scoresToAdd(dbNextType.getCurrScore(), scoreToNext));
				dbNextType.setScoreFromLast(scoreToNext);
				this.bizProjectTypeScoresDao.updateById(dbNextType);
			}
		}
		/************* 华丽的分割线*****3、结余 部门 积分 *****************************/
		List<BizDeptScores> deptScores = this.bizDeptScoresDao.selectByMap(map);
		for (BizDeptScores dept : deptScores) {
			scoreToNext = scoresToSub(dept.getTotalScore(), dept.getFinishScore());
			dept.setScoreToNext(scoreToNext);
			this.bizDeptScoresDao.updateById(dept);

			map2.put("project_type_id", dept.getProjectTypeId());
			map2.put("dept_id", dept.getDeptId());
			List<BizDeptScores> dbNextDeptScores = this.bizDeptScoresDao.selectByMap(map2);
			if (dbNextDeptScores.size() > 1) {
				log.error("部门积分结余出现错误，月份：【{}】部门【id:{}】积分数据记录不唯一", DateFormatUtils.format(month, "yyyy-MM-dd"),
						dept.getDeptId());
				throw new RuntimeException(
						"部门积分结余出现错误，月份：【" + DateFormatUtils.format(month, "yyyy-MM-dd") + "】部门积分数据记录不唯一");
			}
			// 数据库 还没 下月 该部门积分 信息
			if (dbNextDeptScores.size() == 0) {
				// 如果有结余 则 结余到下月
				if (scoreToNext > 0) {
					BizDeptScores insert = new BizDeptScores();
					insert.setDeptId(dept.getDeptId());
					insert.setProjectTypeId(dept.getProjectTypeId());
					insert.setTotalScore(scoreToNext);
					insert.setScoreFromLast(scoreToNext);
					insert.setCurrMonth(DateUtils.addMonths(month, 1));
					this.bizDeptScoresDao.insert(insert);
				}
			} else { // 数据库 已经 存在 下月部门积分 信息 ----更新
				BizDeptScores dbNextDept = dbNextDeptScores.get(0);
				dbNextDept.setTotalScore(scoresToAdd(dbNextDept.getCurrScore(), scoreToNext));
				dbNextDept.setScoreFromLast(scoreToNext);
				this.bizDeptScoresDao.updateById(dbNextDept);
			}
		}
	}

	/**
	 * total-finished
	 * @param total
	 * @param finished
	 * @return
	 */
	private Double scoresToSub(Double total, Double finished) {
		total = total == null ? 0D : total;
		finished = finished == null ? 0D : finished;
		BigDecimal bs = new BigDecimal(total);
		BigDecimal bp = new BigDecimal(finished);
		BigDecimal bResult = bs.subtract(bp);
		bResult = bResult.setScale(2, BigDecimal.ROUND_DOWN);
		return bResult.doubleValue();
	}
	
	/**
	 * value1+value2
	 * @param total
	 * @param finished
	 * @return
	 */
	private Double scoresToAdd(Double value1, Double value2) {
		value1 = value1 == null ? 0D : value1;
		value2 = value2 == null ? 0D : value2;
		BigDecimal bs = new BigDecimal(value1);
		BigDecimal bp = new BigDecimal(value2);
		BigDecimal bResult = bs.add(bp);
		bResult = bResult.setScale(2, BigDecimal.ROUND_DOWN);
		return bResult.doubleValue();
	}

}
