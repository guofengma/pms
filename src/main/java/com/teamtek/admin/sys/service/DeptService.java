package com.teamtek.admin.sys.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.teamtek.admin.sys.entity.Dept;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author syj
 * @since 2018-02-06 10:29:50
 */
public interface DeptService extends IService<Dept> {

	/**
	 * 批量删除部门（机构）  
	 * 并 把部门 对应的 积分删除（也可以不删除，这里主要考虑到后期会积攒大量垃圾数据影响性能，所以删除）
	 * @param asList
	 */
	void deteleDataByBatchIds(List<String> asList);
	
}
