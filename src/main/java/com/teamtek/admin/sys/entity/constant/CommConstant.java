package com.teamtek.admin.sys.entity.constant;

/**
 * 公共变量
 * 
 * @author Administrator
 *
 */
public interface CommConstant {
	/**
	 * 性别
	 * 
	 * @author Administrator
	 *
	 */
	public enum Sex {
		male(0, "男"), 
		female(1, "女"), 
		secret(2, "保密"),
		other(3, "未知");
		private int value;
		private String title;

		Sex(int value, String title) {
			this.value = value;
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		public int getValue() {
			return value;
		}
		public static Sex getEnumByTitle(String title) {
			for (Sex e : Sex.values()) {
				if (e.getTitle().equals(title)) {
					return e;
				}
			}
			return null;
		}
		public static Sex getEnumByValue(int value) {
			for (Sex e : Sex.values()) {
				if (e.getValue() == value) {
					return e;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			Sex e = getEnumByValue(value);
			return e == null ? null : e.title;
		}

		public static Integer getValueByTitle(String title) {
			Sex e = getEnumByTitle(title);
			return e == null ? null : e.value;
		}
	}

	/**
	 * 账户状态
	 * @author Administrator
	 *
	 */
	public enum UserStatus {
		user_disable(0, "禁用"),
		user_enable(1, "正常"),
		del(2, "删除");
		private int value;
		private String title;

		UserStatus(int value, String title) {
			this.value = value;
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		public int getValue() {
			return value;
		}

		public static UserStatus getEnumByValue(int value) {
			for (UserStatus e : UserStatus.values()) {
				if (e.getValue()== value) {
					return e;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			UserStatus e = getEnumByValue(value);
			return e == null ? null : e.title;
		}
	}

}
