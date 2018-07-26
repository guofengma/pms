package com.teamtek.admin.biz.vo.template;

import java.util.List;

/**
 * @Description: 请求的规则模板
 * @author shenzhihao
 * @date 2018年3月15日 上午9:12:23
 */
public class ReqRuleTemplate {

	/**
	 * 模板ID
	 */
	private Long templateId;

	/**
	 * 模板名称
	 */
	private String templateName;

	/**
	 * 岗位ID
	 */
	private Long posId;

	/**
	 * 规则模板配置
	 */
	private List<ReqRuleTemplateConfigure> confs;

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

	public List<ReqRuleTemplateConfigure> getConfs() {
		return confs;
	}

	public void setConfs(List<ReqRuleTemplateConfigure> confs) {
		this.confs = confs;
	}
}
