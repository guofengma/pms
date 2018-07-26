package com.teamtek.admin.biz.vo.template;

/**
 * @Description: 多项目配置
 * @author shenzhihao
 * @date 2018年3月12日 下午1:33:39
 */
public class ReqMultiProjectConfigure extends ReqAbstProjectConfigure {
	/**
	 * 多个联动项目ID
	 */
	private String linkProjectIds;

	public String getLinkProjectIds() {
		return linkProjectIds;
	}

	public void setLinkProjectIds(String linkProjectIds) {
		this.linkProjectIds = linkProjectIds;
	}

}
