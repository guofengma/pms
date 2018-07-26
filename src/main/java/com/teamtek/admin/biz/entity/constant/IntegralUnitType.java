package com.teamtek.admin.biz.entity.constant;

/**
 * @Description: 积分单位类别
 * @author shenzhihao
 * @date 2018年3月6日 下午5:02:03
 */
public enum IntegralUnitType {

	/**
	 * 无/空白
	 */
	NONE(0, "无"),
	/**
	 * 百元
	 */
	BAI(1, "百元"),
	/**
	 * 笔
	 */
	BI(2, "笔"),
	/**
	 * 户
	 */
	HU(3, "户"),
	/**
	 * 件
	 */
	JIAN(4, "件"),
	/**
	 * 台
	 */
	TAI(5, "台"),
	/**
	 * 万元
	 */
	WAN(6, "万元"),
	/**
	 * 万元/月
	 */
	WAN_YUE(7, "万元/月"),
	/**
	 * 项
	 */
	XIANG(8, "项"),
	/**
	 * 张
	 */
	ZHANG(9, "张");

	private int id;
	private String title;

	private IntegralUnitType(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public static IntegralUnitType getEnumById(int id) {
		for (IntegralUnitType i : IntegralUnitType.values()) {
			if (id == i.getId()) {
				return i;
			}
		}
		return null;
	}
	
	public static String getTitleById(int id) {
		IntegralUnitType i = getEnumById(id);
		return i == null ? null : i.getTitle();
	}
	
}
