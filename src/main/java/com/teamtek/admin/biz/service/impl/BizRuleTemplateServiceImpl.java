package com.teamtek.admin.biz.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.teamtek.admin.biz.dao.BizGroupProjectConfigureDao;
import com.teamtek.admin.biz.dao.BizGroupQuoteProjectConfigureDao;
import com.teamtek.admin.biz.dao.BizProjectConfigureDao;
import com.teamtek.admin.biz.dao.BizRuleDetailDao;
import com.teamtek.admin.biz.dao.BizRuleTemplateDao;
import com.teamtek.admin.biz.dao.BizSubProjectConfigureDao;
import com.teamtek.admin.biz.dao.BizTemplateMainProjectZoneDao;
import com.teamtek.admin.biz.entity.BizGroupProjectConfigure;
import com.teamtek.admin.biz.entity.BizGroupQuoteProjectConfigure;
import com.teamtek.admin.biz.entity.BizProjectConfigure;
import com.teamtek.admin.biz.entity.BizRuleDetail;
import com.teamtek.admin.biz.entity.BizRuleTemplate;
import com.teamtek.admin.biz.entity.BizSubProjectConfigure;
import com.teamtek.admin.biz.entity.BizTemplateMainProjectZone;
import com.teamtek.admin.biz.entity.constant.Rule;
import com.teamtek.admin.biz.entity.constant.RuleConfigureConstant;
import com.teamtek.admin.biz.entity.constant.RuleType;
import com.teamtek.admin.biz.entity.constant.SubtotalType;
import com.teamtek.admin.biz.service.BizRuleTemplateService;
import com.teamtek.admin.biz.vo.template.ReqAbstProjectConfigure;
import com.teamtek.admin.biz.vo.template.ReqMultiProjectConfigure;
import com.teamtek.admin.biz.vo.template.ReqRuleTemplate;
import com.teamtek.admin.biz.vo.template.ReqRuleTemplateConfigure;
import com.teamtek.admin.biz.vo.template.ReqSigleProjecConfigure;
import com.teamtek.admin.sys.dao.SysPositionDao;
import com.teamtek.admin.sys.entity.SysPosition;
import com.teamtek.core.vo.Record;

/** 
* @Description:  规则模板表 服务实现类
* @author shenzhihao 
* @date 2018年3月16日 上午10:15:22 
*/
@Service
public class BizRuleTemplateServiceImpl extends ServiceImpl<BizRuleTemplateDao, BizRuleTemplate>
		implements BizRuleTemplateService {

	@Autowired
	private BizRuleTemplateDao ruleTemplateDao;
	@Autowired
	private BizRuleDetailDao ruleDetailDao;;
	@Autowired
	private BizProjectConfigureDao projectConfigureDao;
	@Autowired
	private BizTemplateMainProjectZoneDao templateMainProjectZoneDao;
	@Autowired
	private BizGroupProjectConfigureDao groupProjectConfigureDao;
	@Autowired
	private BizGroupQuoteProjectConfigureDao groupQuoteProjectConfigureDao;
	@Autowired
	private SysPositionDao positionDao;
	@Autowired
	private BizSubProjectConfigureDao subProjectConfigureDao;
	
	
	@Override
	public List<Record> findByPage(RowBounds rowBounds, Wrapper<BizRuleTemplate> wrapper) {
		List<BizRuleTemplate> list = ruleTemplateDao.selectPage(rowBounds, wrapper);
		for (BizRuleTemplate template : list) {
			Record data = this.installTemplateData(template);
			Set<String> baseProjectNameSet = new HashSet<>();
			Set<String> linkProjectNameSet = new HashSet<>();
			
			//TODO 待完成
			this.installMainZoneData(baseProjectNameSet,linkProjectNameSet,template);
		}

		return null;
	}
	
	/**
	 * 组装模板主区域数据
	 * @param linkProjectNameSet 
	 * @param baseProjectNameSet 
	 * @param templateData
	 */
	private List<Record> installMainZoneData(Set<String> baseProjectNameSet, Set<String> linkProjectNameSet, BizRuleTemplate template) {
		if (template == null) {
			return null;
		}

		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("rule_template_id", template.getId());
		List<BizTemplateMainProjectZone> list = templateMainProjectZoneDao.selectByMap(columnMap);
		
		List<Record> records = new ArrayList<>();
		
		for (BizTemplateMainProjectZone zone : list) {
			Record r = new Record();
			r.put("mainZoneId", zone.getId());
			r.put("sigleConf", this.installSigleConfData(baseProjectNameSet,zone));
			
		}
		
		return records;
	}
		
	/**
	 * 组装单个项目配置的数据
	 * @param baseProjectNameSet
	 * @param mainZone
	 * @return
	 */
	private List<Record> installSigleConfData(Set<String> baseProjectNameSet, BizTemplateMainProjectZone mainZone) {
		if (mainZone == null) {
			return null;
		}

		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("zone_id", mainZone.getId());
		List<BizProjectConfigure> list = projectConfigureDao.selectByMap(columnMap);

		Set<Long> baseProjectIdSet = new HashSet<>();

		for (BizProjectConfigure conf : list) {
			if (conf.getProjectId() != null && conf.getProjectId() > 0) {
				baseProjectIdSet.add(conf.getProjectId());
			}

			conf.getRuleDetailId();

		}
		return null;
	}
	
	
	
	/**
	 * 组装模板数据
	 * @param template
	 * @return
	 */
	private Record installTemplateData(BizRuleTemplate template){
		if(template == null){
			return null;
		}
		
		Record r = new Record();
		
		r.put("templateId", template.getId());
		r.put("templateName", template.getName());
		
		Long posId = template.getPosId();
		if (posId != null && posId > 0) {
			SysPosition position = positionDao.selectById(posId);
			if(StringUtils.isNotEmpty(position.getTitle())){
				r.put("posName", position.getTitle());
			}
		}
		r.put("templateType", RuleConfigureConstant.TemplateType.getTitleByValue(template.getType()));
		r.put("publishStatus", RuleConfigureConstant.PublishStatus.getTitleByValue(template.getPublishStatus()));
		return r;
	}


	@Override
	@Transactional
	public void addOrUpdate(ReqRuleTemplate data) {
		// 保存规则模板
		BizRuleTemplate template = this.saveRuleTemplate(data);

		// 保存规则配置
		List<ReqRuleTemplateConfigure> list = data.getConfs();
		for (ReqRuleTemplateConfigure r : list) {

			// 若没有基础项目配置直接跳过
			ReqSigleProjecConfigure baseConf = r.getBase_conf();
			if (baseConf == null || baseConf.getProjectId() == null || baseConf.getProjectId() <= 0) {
				continue;
			}

			// 增加主项目区域和基础规则配置
			BizTemplateMainProjectZone mainZone = this.saveMainZoneAndBaseConf(template.getId(), baseConf);

			// 增加单项目计分
			this.saveProjectConfigureGroup(mainZone, r.getLink_rule_confs());

			// 增加积分小计
			this.saveSubProjectConfigureGroup(SubtotalType.STAGE.getId(),mainZone, r.getSub_confs());
			// 增加总积分
			this.saveSubProjectConfigureGroup(SubtotalType.TOTAL.getId(),mainZone, r.getTotal_confs());

			// 增加取分规则配置
			this.saveManyProjectConfigureGroup(mainZone, r.getLink_integral_confs());
		}
	}


	
	/**
	 * 保存多项目组规则配置
	 * @param mainZone
	 * @param list
	 */
	private void saveManyProjectConfigureGroup(BizTemplateMainProjectZone mainZone, List<ReqMultiProjectConfigure> list){
		for (ReqMultiProjectConfigure conf : list) {
			//保存规则
			BizRuleDetail ruleDetail = this.saveRuleDetail(conf);
			this.saveManyProjectConfigure(mainZone, ruleDetail, conf);
		}
	}
	
	/**
	 * 保存多项目规则配置
	 * @param mainZone
	 * @param rule
	 * @param conf
	 */
	private void saveManyProjectConfigure(BizTemplateMainProjectZone mainZone, BizRuleDetail ruleDetail,
			ReqMultiProjectConfigure conf) {
		// 验证
		if (StringUtils.isBlank(conf.getLinkProjectIds()) || conf.getRuleDetail() == null) {
			return;
		}

		String[] linkProjectIds = conf.getLinkProjectIds().split(",");

		// 判断规则类型
		RuleType ruleType = Rule.getRuleType(ruleDetail.getRuleId().intValue());

		switch (ruleType) {
			case LIMIT_SCORE:
				this.saveGroupProjectConfigure(linkProjectIds,mainZone,ruleDetail);
				break;
			case SINGLE_SCORE:
				this.saveGroupQuoteProjectConfigure(linkProjectIds,mainZone,ruleDetail);
				break;
	
			default:
				break;
		}

	}
	
	/**
	 * 保存组合项目规则
	 * @param linkProjectIds
	 * @param mainZone
	 * @param ruleDetail
	 */
	private void saveGroupProjectConfigure(String[] linkProjectIds,BizTemplateMainProjectZone mainZone,BizRuleDetail ruleDetail){
		Map<String, Object> columnMap = new HashMap<>();
		for (String id : linkProjectIds) {
			columnMap.put("project_id", id);
			columnMap.put("zone_id", mainZone.getId());

			List<BizProjectConfigure> list = projectConfigureDao.selectByMap(columnMap);

			if (CollectionUtils.isNotEmpty(list)) {
				BizProjectConfigure sigleConfigure  = list.get(0);
				
				BizGroupProjectConfigure groupConfigure = new BizGroupProjectConfigure();
				groupConfigure.setRuleDetailId(ruleDetail.getId());
				groupConfigure.setZoneId(mainZone.getId());
				groupProjectConfigureDao.insert(groupConfigure);
				
				sigleConfigure.setGroupProjectConfigureId(groupConfigure.getId());
				projectConfigureDao.updateById(sigleConfigure);
			}
		}
	}
	
	/**
	 * 保存组合引用项目规则
	 * @param linkProjectIds
	 * @param mainZone
	 * @param ruleDetail
	 */
	private void saveGroupQuoteProjectConfigure(String[] linkProjectIds,BizTemplateMainProjectZone mainZone,BizRuleDetail ruleDetail){
		Map<String, Object> columnMap = new HashMap<>();
		for (String id : linkProjectIds) {
			columnMap.put("project_id", id);
			columnMap.put("zone_id", mainZone.getId());

			List<BizProjectConfigure> list = projectConfigureDao.selectByMap(columnMap);
			if (CollectionUtils.isNotEmpty(list)) {
				BizProjectConfigure sigleConfigure  = list.get(0);
				
				BizGroupQuoteProjectConfigure groupQuoteConfigure = new BizGroupQuoteProjectConfigure();
				groupQuoteConfigure.setRuleDetailId(ruleDetail.getId());
				groupQuoteConfigure.setZoneId(mainZone.getId());
				
				groupQuoteProjectConfigureDao.insert(groupQuoteConfigure);
				
				sigleConfigure.setGroupQuoteProjectConfigureId(groupQuoteConfigure.getId());
				projectConfigureDao.updateById(sigleConfigure);
			}
		}
	}
	
	/**
	 * 保存单个配置组
	 * @param mainZone
	 * @param list
	 */
	private void saveProjectConfigureGroup(BizTemplateMainProjectZone mainZone, List<ReqSigleProjecConfigure> list) {
		for (ReqSigleProjecConfigure conf : list) {
			//保存规则
			BizRuleDetail ruleDetail = this.saveRuleDetail(conf);
			//保存单项目规则配置
			this.saveProjectConfigure(RuleConfigureConstant.ProjectConfigureType.OTHER_CONF.getValue(),mainZone, ruleDetail, conf);
		}
	}
	
	
	/**
	 * 保存小计项目规则配置组
	 * @param i 
	 * @param mainZone
	 * @param list
	 */
	private void saveSubProjectConfigureGroup(int subtotalType, BizTemplateMainProjectZone mainZone, List<ReqSigleProjecConfigure> list) {
		for (ReqSigleProjecConfigure conf : list) {
			//保存规则
			BizRuleDetail ruleDetail = this.saveRuleDetail(conf);
			//保存单项目规则配置
			this.saveSubProjectConfigure(subtotalType,mainZone, ruleDetail, conf);
		}
	}
	
	/**
	 * 保存小计项目规则配置
	 * @param subtotalType
	 * @param mainZone
	 * @param ruleDetail
	 * @param conf
	 */
	private void saveSubProjectConfigure(int subtotalType, BizTemplateMainProjectZone mainZone, BizRuleDetail ruleDetail,
			ReqSigleProjecConfigure conf) {
		BizSubProjectConfigure subProjectConfigure = new BizSubProjectConfigure();
		subProjectConfigure.setZoneId(mainZone.getId());
		subProjectConfigure.setProjectId(conf.getProjectId());
		subProjectConfigure.setRuleDetailId(ruleDetail.getId());
		subProjectConfigure.setType(subtotalType);
		subProjectConfigureDao.insert(subProjectConfigure);
	}

	/**
	 * 保存单个配置
	 * @param template
	 * @param confs
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void saveOneConfigure(BizTemplateMainProjectZone mainZone, ReqSigleProjecConfigure conf){
		if (conf == null || conf.getRuleDetail() == null) {
			return;
		}
		//保存规则
		BizRuleDetail ruleDetail = this.saveRuleDetail(conf);
		//保存单项目规则配置
		this.saveProjectConfigure(RuleConfigureConstant.ProjectConfigureType.BASE_CONF.getValue(),mainZone, ruleDetail, conf);
	}
	
	/**
	 * 保存项目规则配置
	 * @param configureType 规则配置类型
	 * @param mainZone
	 * @param rule
	 * @param conf
	 */
	private void saveProjectConfigure(int configureType, BizTemplateMainProjectZone mainZone,BizRuleDetail ruleDetail,ReqSigleProjecConfigure conf){
		BizProjectConfigure newConf = new BizProjectConfigure();
		newConf.setProjectId(conf.getProjectId());
		newConf.setRuleDetailId(ruleDetail.getId());
		newConf.setZoneId(mainZone.getId());
		projectConfigureDao.insert(newConf);
	}
	
	/**
	 * 保存主区域和基础配置
	 * @param templateId 规则模板ID
	 * @param baseConf 基础配置
	 * @return 
	 */
	private BizTemplateMainProjectZone saveMainZoneAndBaseConf(Long templateId, ReqSigleProjecConfigure baseConf) {
		Long mainZoneId = baseConf.getMainZoneId();
		BizTemplateMainProjectZone mainZone = null;
		
		if (mainZoneId == null || mainZoneId <= 0) {
			mainZone = this.addMainProjectZone(templateId,baseConf.getProjectId());
			this.saveOneConfigure(mainZone, baseConf);
		}else{
			mainZone = templateMainProjectZoneDao.selectById(mainZoneId);
			
			//删除主区域所有的配置
			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("zone_id", mainZone.getId());
			
			projectConfigureDao.deleteByMap(columnMap);
			groupProjectConfigureDao.deleteByMap(columnMap);
			groupQuoteProjectConfigureDao.deleteByMap(columnMap);
		}
		
		return mainZone;
	}



	/**
	 * 添加模板主区域
	 * @param templateId
	 * @param baseProjectId
	 * @param mainZoneid
	 * @return
	 */
	private BizTemplateMainProjectZone addMainProjectZone(Long templateId,Long baseProjectId){
		BizTemplateMainProjectZone mainZone = new BizTemplateMainProjectZone();
		mainZone = new BizTemplateMainProjectZone();
		mainZone.setMainPorjectId(baseProjectId);
		mainZone.setRuleTemplateId(templateId);
		mainZone.setConfigureStatus(RuleConfigureConstant.ConfigureStatus.DONE.getValue());
		templateMainProjectZoneDao.insert(mainZone);
		return mainZone;
	}



	/**
	 * 保存规则模板
	 * @param data
	 * @return
	 */
	private BizRuleTemplate saveRuleTemplate(ReqRuleTemplate data){
		Long templateId = data.getTemplateId();
		BizRuleTemplate template;
		
		if(templateId !=null && templateId >0){
			//更新规则模板
			template = ruleTemplateDao.selectById(templateId);
			template.setName(data.getTemplateName());
			template.setPosId(data.getPosId());
			ruleTemplateDao.updateAllColumnById(template);
		}else{
			// 增加规则模板
			template = new BizRuleTemplate();
			template.setName(data.getTemplateName());
			template.setPosId(data.getPosId());
			template.setType(RuleConfigureConstant.TemplateType.ORIGINAL.getValue());
			template.setPublishStatus(RuleConfigureConstant.PublishStatus.NO_PUBLISH.getValue());
			template.setCreateTime(new Date());
			ruleTemplateDao.insert(template);
		}
		return template;
	}



	/**
	 * 保存规则详情
	 * @param conf
	 * @return
	 */
	private BizRuleDetail saveRuleDetail(ReqAbstProjectConfigure conf) {
		BizRuleDetail ruleDetail = conf.getRuleDetail();
		ruleDetailDao.insert(ruleDetail);
		return ruleDetail;
	}
	
}
