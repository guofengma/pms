package com.teamtek.admin.biz.entity.constant;

/**
 * @Description: 规则模板公共常量
 * @author shenzhihao
 * @date 2018年3月6日 下午3:25:17
 */
public interface RuleTemplateConstant {

	/**
	 * @Description: 配置状态
	 * @author shenzhihao
	 * @date 2018年3月6日 下午4:28:04
	 */
	public enum ConfigureStatus {
		/**
		 * 没有配置(默认)
		 */
		NO(0, ""),
		/**
		 * 已配置
		 */
		DONE(1, "已配置");

		private int value;
		private String title;

		private ConfigureStatus(int value, String title) {
			this.value = value;
			this.title = title;
		}

		public int getValue() {
			return value;
		}

		public String getTitle() {
			return title;
		}

		public static ConfigureStatus getEnumByValue(int value) {
			for (ConfigureStatus c : ConfigureStatus.values()) {
				if (value == c.getValue()) {
					return c;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			ConfigureStatus c = getEnumByValue(value);
			return c == null ? null : c.getTitle();
		}

	}

	/**
	 * @Description: 模板类型
	 * @author shenzhihao
	 * @date 2018年3月6日 下午4:28:12
	 */
	public enum TemplateType {
		ORIGINAL(0, "正本"), 
		COPY(1, "副本");

		private int value;
		private String title;

		private TemplateType(int value, String title) {
			this.value = value;
			this.title = title;
		}

		public int getValue() {
			return value;
		}

		public String getTitle() {
			return title;
		}

		public static TemplateType getEnumByValue(int value) {
			for (TemplateType t : TemplateType.values()) {
				if (value == t.getValue()) {
					return t;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			TemplateType t = getEnumByValue(value);
			return t == null ? null : t.getTitle();
		}
	}

	/**
	 * @Description: 发布状态
	 * @author shenzhihao
	 * @date 2018年3月6日 下午4:29:04
	 */
	public enum PublishStatus {
		NO_PUBLISH(0, "没发布"), 
		PUBLISHED(1, "已发布");

		private int value;
		private String title;

		private PublishStatus(int value, String title) {
			this.value = value;
			this.title = title;
		}

		public int getValue() {
			return value;
		}

		public String getTitle() {
			return title;
		}
		
		public static PublishStatus getEnumByValue(int value) {
			for (PublishStatus p : PublishStatus.values()) {
				if (value == p.getValue()) {
					return p;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			PublishStatus p = getEnumByValue(value);
			return p == null ? null : p.getTitle();
		}
		
	}

}
