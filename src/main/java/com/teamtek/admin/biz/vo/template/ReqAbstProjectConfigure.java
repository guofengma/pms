package com.teamtek.admin.biz.vo.template;

import com.teamtek.admin.biz.entity.BizRuleDetail;

public abstract class ReqAbstProjectConfigure {
	/**
	 * 主区域ID
	 */
	protected Long mainZoneId;

	/**
	 * 接收的规则实体
	 */
	protected BizRuleDetail ruleDetail;

	public Long getMainZoneId() {
		return mainZoneId;
	}

	public void setMainZoneId(Long mainZoneId) {
		this.mainZoneId = mainZoneId;
	}

	public BizRuleDetail getRuleDetail() {
		return ruleDetail;
	}

	public void setRuleDetail(BizRuleDetail ruleDetail) {
		this.ruleDetail = ruleDetail;
	}
}
