package com.teamtek.core.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.teamtek.core.vo.MyHttpStatus;
import com.teamtek.core.vo.ResponseEntity;

/**
 * 统一异常处理
 * 
 * @author Administrator
 *
 */
@RestControllerAdvice
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	// 捕捉其他所有异常
	@ExceptionHandler(Exception.class)
	//@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity globalException(HttpServletRequest request, Throwable ex) {
		logger.error(ex.getMessage(),ex);
		//认证失败异常
		if (ex instanceof UnknownAccountException//未知账号
				||ex instanceof AuthenticationException//认证错误
				||ex instanceof IncorrectCredentialsException//用户信息错误
				||ex instanceof UnauthenticatedException) {//未认证
			
			//return ResponseEntity.error(MyHttpStatus.AUTHORIZE_FAIL, ex.getMessage());
			String message=ex.getMessage();
			if(ex.getCause()!=null){
				message=ex.getCause().getMessage();
			}
			//return ResponseEntity.error(MyHttpStatus.AUTHORIZE_FAIL, message);
			return ResponseEntity.error(MyHttpStatus.UNAUTHORIZED, message);
		}
		
		
		//权限不足异常
		if (ex instanceof UnauthorizedException) {
			return ResponseEntity.error(MyHttpStatus.PERMISSION_DENIED,ex.getMessage());
		}
		//请求参数出错异常
		if (ex instanceof HttpMessageNotReadableException
				||ex instanceof MissingServletRequestParameterException) {
			return ResponseEntity.error(MyHttpStatus.REQUEST_PARAMS_ILLEGAL,ex.getMessage());
		}
		return ResponseEntity.error(MyHttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
	}
	
	// private HttpStatus getStatus(HttpServletRequest request) {
	// Integer statusCode = (Integer)
	// request.getAttribute("javax.servlet.error.status_code");
	// if (statusCode == null) {
	// return HttpStatus.INTERNAL_SERVER_ERROR;
	// }
	// return HttpStatus.valueOf(statusCode);
	// }
}
