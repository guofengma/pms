package com.teamtek.admin.biz.vo.template;

/**
 * @Description: 单项目配置
 * @author shenzhihao
 * @date 2018年3月12日 下午1:33:21
 */
public class ReqSigleProjecConfigure extends ReqAbstProjectConfigure {

	/**
	 * 项目ID
	 */
	private Long projectId;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
}
