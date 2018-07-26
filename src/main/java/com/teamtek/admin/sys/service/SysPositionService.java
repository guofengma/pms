package com.teamtek.admin.sys.service;

import com.teamtek.admin.sys.entity.SysPosition;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
/**
 * <p>
 * 服务类
 * </p>
 *
 * @author syj
 * @since 2018-02-27 09:53:52
 */
public interface SysPositionService extends IService<SysPosition> {

	/**
	 * 给岗位配置 基础项目、联动项目
	 * @param positionId
	 * @param linkIds
	 * @param baseIds
	 */
	void positionProjectConfig(Long positionId, String[] linkIds, String[] baseIds);

	/**
	 * 查询用户 的岗位
	 * @param userId
	 * @return
	 */
	List<SysPosition> selectPositionsByUserId(Long userId);
	
}
