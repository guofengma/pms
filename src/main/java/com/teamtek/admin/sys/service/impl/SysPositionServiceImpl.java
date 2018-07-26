package com.teamtek.admin.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.sys.dao.BizPositionProjectDao;
import com.teamtek.admin.sys.dao.SysPositionDao;
import com.teamtek.admin.sys.entity.BizPositionProject;
import com.teamtek.admin.sys.entity.SysPosition;
import com.teamtek.admin.sys.service.SysPositionService;

/**
 * <p>
 * 岗位表  服务实现类
 * </p>
 *
 * @author syj
 * @since 2018-02-27 09:53:52
 */
@Service
public class SysPositionServiceImpl extends ServiceImpl<SysPositionDao, SysPosition> implements SysPositionService {

	@Autowired
	private BizPositionProjectDao bizPositionProjectDao;
	@Autowired
	private SysPositionDao sysPositionDao;
	
	@Override
	@Transactional
	public void positionProjectConfig(Long positionId, String[] linkIds, String[] baseIds) {
	
		//删除该岗位 关联的 所有项目（老数据）
		Map<String, Object> map=new HashMap<>();
		map.put("position_id", positionId);
		this.bizPositionProjectDao.deleteByMap(map);
		//1.岗位  关联   联动项目
		if(ArrayUtils.isNotEmpty(linkIds)){
			for(String linkId:linkIds){
				BizPositionProject link=new BizPositionProject();
				link.setPositionId(positionId);
				link.setProjectId(Long.valueOf(linkId));
				link.setType(BizPositionProject.Type.link.getValue());
				this.bizPositionProjectDao.insert(link);
			}
		}
		//2.岗位  关联   基础项目
		if(ArrayUtils.isNotEmpty(baseIds)){
			for(String linkId:linkIds){
				BizPositionProject base=new BizPositionProject();
				base.setPositionId(positionId);
				base.setProjectId(Long.valueOf(linkId));
				base.setType(BizPositionProject.Type.base.getValue());
				this.bizPositionProjectDao.insert(base);
			}
		}
	}

	@Override
	public List<SysPosition> selectPositionsByUserId(Long userId) {
		return 	this.sysPositionDao.selectPositionsByUserId(userId);
	}
	
}
