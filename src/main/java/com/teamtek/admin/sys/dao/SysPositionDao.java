package com.teamtek.admin.sys.dao;

import com.teamtek.admin.sys.entity.SysPosition;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author syj
 * @since 2018-02-27 09:53:52
 */
public interface SysPositionDao extends BaseMapper<SysPosition> {

	List<SysPosition> selectPositionsByUserId(Long userId);

}