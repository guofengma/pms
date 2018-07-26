package com.teamtek.core.vo;

public enum MyHttpStatus {

	OK(200, "OK"),

	/**
	 * 未授权：没有与服务器认证成功就访问服务器资源
	 */
	UNAUTHORIZED(401, "没有认证授权通过，检查token是否过期，是否合法"),
	
	//AUTHORIZE_FAIL(402, "授权失败"),

	/**
	 * 权限不足：认证成功，但访问了没有权限的接口
	 */
	PERMISSION_DENIED(403, "权限不足"), 
	
	NOT_FOUND(404, "请求资源不存在"),

	JWT_TOKEN_TIMEOUT(405, "会话超时,请重新登陆"),
	
	REQUEST_PARAMS_ILLEGAL(406, "请求参数有误"),
	
	REFRESH_TOKEN_FAILED(407, "token刷新失败！"),

	INTERNAL_SERVER_ERROR(500, "服务器错误");

	private final int value;

	private final String title;

	MyHttpStatus(int value, String title) {
		this.value = value;
		this.title = title;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Return a string representation of this status code.
	 */

	public static MyHttpStatus valueOf(int statusCode) {
		for (MyHttpStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("没有匹配的状态码 [" + statusCode + "]");
	}

}
