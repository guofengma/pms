package com.teamtek.admin.comm.util;

import java.util.ArrayList;
import java.util.List;
/**
 * excel 下拉框常量值
 * @author syj
 *@createTime：2018年3月26日 下午4:27:00
 */
public interface ExcelConst {
	/**
	 * true or false
	 * @author syj
	 *@createTime：2018年3月14日 上午11:47:50
	 */
	public enum YesOrNo {

		yes("是"),
		no("否");
		private final String title;

		private YesOrNo( String title) {
			this.title = title;
		}

		public String title() {
			return title;
		}
		
		public static List<String> getTitleList() {
			List<String> list = new ArrayList<>(YesOrNo.values().length);
			for (YesOrNo s : YesOrNo.values()) {
				list.add(s.title());
			}
			return list;
		}

	}
	
	public enum BizUnit {

		yuan(1,"元"),
		wan_yuan(2,"万元"),
		xiang(3,"项"),;
		private final Integer value;
		private final String title;

		private BizUnit(Integer value, String title) {
			this.title = title;
			this.value = value;
		}

		public Integer value() {
			return value;
		}


		public String title() {
			return title;
		}
		
		public static List<String> getTitleList() {
			List<String> list = new ArrayList<>(YesOrNo.values().length);
			for (BizUnit s : BizUnit.values()) {
				list.add(s.title());
			}
			return list;
		}
		
		public static Integer getValueByTitle(String title) {
			for (BizUnit s : BizUnit.values()) {
				if(s.title().equals(title)){
					return s.value();
				}
			}
			return null;
		}
		
		public static String getTitleByValue(Integer value) {
			for (BizUnit s : BizUnit.values()) {
				if(s.value().equals(value)){
					return s.title();
				}
			}
			return null;
		}

	}

}
