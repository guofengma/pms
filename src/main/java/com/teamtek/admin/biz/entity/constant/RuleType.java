package com.teamtek.admin.biz.entity.constant;

/**
 * @Description: 规则类型
 * @author shenzhihao
 * @date 2018年3月1日 下午4:22:28
 */
public enum RuleType {

	/**
	 * 取分规则(取极限分)
	 */
	LIMIT_SCORE(1, "取分规则"),
	/**
	 * 单项目计分规则
	 */
	SINGLE_SCORE(2, "单项目计分规则"),
	/**
	 * 积分小计规则
	 */
	SUBTOTAL_SCORE(3, "积分小计规则"),
	/**
	 * 总积分生成规则
	 */
	TOTAL_SCORE(4, "总积分生成规则");

	private int id;
	private String name;

	private RuleType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
