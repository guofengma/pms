package com.teamtek.core.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamtek.core.vo.MyHttpStatus;
import com.teamtek.core.vo.ResponseEntity;

/**
 *  错误控制器（主要为shiro验证失败跳转的uri
 * @author Administrator
 *
 */
@RestController
public class MainsiteErrorController implements ErrorController {
	private static final String ERROR_PATH = "/error";
	private static final Logger logger = LoggerFactory.getLogger(MainsiteErrorController.class);
	/**
	 * 服务器内部错误 500  404
	 * @return
	 */
	@RequestMapping(value = ERROR_PATH)
	public ResponseEntity handleError(HttpServletResponse response) {
		if(response.getStatus()==404){
			logger.error(MyHttpStatus.NOT_FOUND.getTitle());
			return ResponseEntity.error(MyHttpStatus.NOT_FOUND);
		}
		logger.error(MyHttpStatus.INTERNAL_SERVER_ERROR.getTitle());
		return ResponseEntity.error(MyHttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 授权失败 （如果token 验证结果为  JwtVerifyResult.fail_other,或者 shrio 权限没有验证通过  则跳入 此uri）
	 * @return
	 */
	@RequestMapping("/auth_fail")
	public ResponseEntity authFail() {
		logger.error(MyHttpStatus.UNAUTHORIZED.getTitle());
		return ResponseEntity.error(MyHttpStatus.UNAUTHORIZED);
	}
	/**
	 * token 过期  （如果token 验证结果为  JwtVerifyResult.fail_expired  则跳入 此uri）
	 * @return
	 */
	@RequestMapping("/token_expired")
	public ResponseEntity jwtExpired() {
		logger.error(MyHttpStatus.JWT_TOKEN_TIMEOUT.getTitle());
		return ResponseEntity.error(MyHttpStatus.JWT_TOKEN_TIMEOUT);
	}
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
