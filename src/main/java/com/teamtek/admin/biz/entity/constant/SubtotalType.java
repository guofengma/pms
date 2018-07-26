package com.teamtek.admin.biz.entity.constant;

/** 
* @Description:  小计类型
* @author shenzhihao 
* @date 2018年3月19日 下午4:33:14 
*/
public enum SubtotalType {

	/**
	 *积分小计 
	 */
	STAGE(1, "积分小计"), 
	/**
	 * 总计
	 */
	TOTAL(2, "总计");

	private int id;
	private String name;

	private SubtotalType(int id, String name) {
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
