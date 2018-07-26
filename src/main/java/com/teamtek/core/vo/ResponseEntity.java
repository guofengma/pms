package com.teamtek.core.vo;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: ResponseEntity
 * @Description:
 * @author shenzhihao
 * @email zhihao@teamtek.cn
 * @date 2017年12月29日 下午3:39:12
 *
 */
public class ResponseEntity extends JSONObject {
	private static final long serialVersionUID = 1L;
	public static final String RESULT_CODE = "code";
	//access_token有效时间
	public static final String EXPIRES_TIME_CODE = "expires_time";
	/**
	 * 自定义msg描述（给用户看）
	 */
	public static final String RESULT_MSG = "msg";
	/**
	 * 系统错误、异常信息描述（给开发人员看，系统抛出的错误，异常信息）
	 */
	public static final String SYS_ERR_MSG = "sys_err_msg";
	
	public static final String DATA_CODE = "data";
	
	
	
	public ResponseEntity() {
		put(RESULT_CODE, MyHttpStatus.OK.value());
	}
	public static ResponseEntity error(String msg) {
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.put(RESULT_CODE, 500);
		responseEntity.put(RESULT_MSG, msg);
		return responseEntity;
	}

	public static ResponseEntity error(MyHttpStatus status) {
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.put(RESULT_CODE, status.value());
		responseEntity.put(RESULT_MSG, status.getTitle());
		return responseEntity;
	}
	/**
	 * 
	 * @param status
	 * @param msg 系统抛出的异常错误信息
	 * @return
	 */
	public static ResponseEntity error(MyHttpStatus status, String sysErrMsg) {
		ResponseEntity error = error(status);
		error.put(SYS_ERR_MSG, sysErrMsg);
		return error;
	}
	/**
	 * 
	 * @param status
	 * @param msg 自定义的信息
	 * @return
	 */
	public static ResponseEntity ok(String msg) {
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.put(RESULT_MSG, msg);
		return responseEntity;
	}

	public static ResponseEntity ok(Map<String, Object> map) {
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.putAll(map);
		return responseEntity;
	}

	public static ResponseEntity ok() {
		return new ResponseEntity();
	}

	public ResponseEntity putData(Object value) {
		super.put(DATA_CODE, value);
		return this;
	}
	
	public ResponseEntity put(String key,Object value) {
		super.put(key, value);
		return this;
	}
	/**
	 * 设置 access_token有效期（单位毫秒）
	 * @param value
	 * @return
	 */
	public ResponseEntity setExpiresTime(long value) {
		super.put(EXPIRES_TIME_CODE, value);
		return this;
	}
	
	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("hhh", "sfsa");
		
		System.out.println(ResponseEntity.ok());
		System.out.println(json.toJSONString());
	}
}
