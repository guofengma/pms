package com.teamtek.admin.biz.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.teamtek.admin.biz.entity.BizRuleTemplate;
import com.teamtek.admin.biz.vo.template.ReqRuleTemplate;
import com.teamtek.core.vo.Record;
/**
 * <p>
 * 服务类
 * </p>
 *
 * @author syj
 * @since 2018-03-14 18:03:33
 */
public interface BizRuleTemplateService extends IService<BizRuleTemplate> {

	/**
	 * 增加或更新规则模板
	 * @param data
	 */
	void addOrUpdate(ReqRuleTemplate data);

	/**
	 * 
	 * @param rowBounds
	 * @param wrapper
	 * @return
	 */
	List<Record> findByPage(RowBounds rowBounds, Wrapper<BizRuleTemplate> wrapper);
}
