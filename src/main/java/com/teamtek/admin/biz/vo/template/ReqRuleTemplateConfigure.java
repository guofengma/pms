package com.teamtek.admin.biz.vo.template;

import java.util.List;

/**
 * @Description: 规则模板配置
 * @author shenzhihao
 * @date 2018年3月12日 上午11:30:06
 */
public class ReqRuleTemplateConfigure {

	/**
	 * 基础项目规则配置
	 */
	private ReqSigleProjecConfigure base_conf;

	/**
	 * (联动项目)取分规则配置
	 */
	private List<ReqMultiProjectConfigure> link_integral_confs;

	/**
	 * (联动项目)单项目规则配置
	 */
	private List<ReqSigleProjecConfigure> link_rule_confs;
	/**
	 * 积分小计规则配置
	 */
	private List<ReqSigleProjecConfigure> sub_confs;
	/**
	 * 总积分规则配置
	 */
	private List<ReqSigleProjecConfigure> total_confs;

	public ReqSigleProjecConfigure getBase_conf() {
		return base_conf;
	}

	public void setBase_conf(ReqSigleProjecConfigure base_conf) {
		this.base_conf = base_conf;
	}

	public List<ReqMultiProjectConfigure> getLink_integral_confs() {
		return link_integral_confs;
	}

	public void setLink_integral_confs(List<ReqMultiProjectConfigure> link_integral_confs) {
		this.link_integral_confs = link_integral_confs;
	}

	public List<ReqSigleProjecConfigure> getLink_rule_confs() {
		return link_rule_confs;
	}

	public void setLink_rule_confs(List<ReqSigleProjecConfigure> link_rule_confs) {
		this.link_rule_confs = link_rule_confs;
	}

	public List<ReqSigleProjecConfigure> getSub_confs() {
		return sub_confs;
	}

	public void setSub_confs(List<ReqSigleProjecConfigure> sub_confs) {
		this.sub_confs = sub_confs;
	}

	public List<ReqSigleProjecConfigure> getTotal_confs() {
		return total_confs;
	}

	public void setTotal_confs(List<ReqSigleProjecConfigure> total_confs) {
		this.total_confs = total_confs;
	}
}
