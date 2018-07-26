package com.teamtek.admin.sys.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.sys.dao.AppDao;
import com.teamtek.admin.sys.entity.ApplicationList;
import com.teamtek.admin.sys.service.AppService;

/**
 * <p>
 * 系统日志表  服务实现类
 * </p>
 *
 * @author syj
 * @since 2018-03-12 15:57:00
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppDao, ApplicationList> implements AppService {
	
}
