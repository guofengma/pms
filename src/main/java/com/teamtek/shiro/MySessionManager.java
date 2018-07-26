package com.teamtek.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teamtek.shiro.util.JWTUtil;

public class MySessionManager extends DefaultWebSessionManager {
	 private static final Logger log = LoggerFactory.getLogger(MySessionManager.class);  
	  
	    /** 
	     * 重写获取sessionId的方法调用当前Manager的获取方法 
	     * 
	     * @param request 
	     * @param response 
	     * @return 
	     */  
	    @Override  
	    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {  
	    	Serializable referencedSessionId = this.getReferencedSessionId(request, response);
	    	
	    	if(referencedSessionId==null){

	    		//注意：
		    	//如果请求头中没有session_code  那就从cookie中获取jsessionId  
		    	//此jsessionid是用于 登陆监控平台  
	    		//监控平台  不是前后端分离
	    		referencedSessionId=super.getSessionId(request, response);
	    		log.info("*******druid监控 ,从cookie获取session_code:{}", referencedSessionId);
	    	}
	        return referencedSessionId;  
	    }  
	  
	    /** 
	     * 获取sessionId从请求中 
	     * 该方法是用于  前后端分离    
	     * @param request 
	     * @param response 
	     * @return 
	     */  
	    private Serializable getReferencedSessionId(ServletRequest request, ServletResponse response) {  
	        
//	        String id = WebUtils.toHttp(request).getParameter("session_code");  
//            if (id == null) {  
//            	log.info("session_code为空！");
//             // throw new RuntimeException("code不能为空！"); 
//            } 
//	    	
	    	
	    	//String token = WebUtils.toHttp(request).getParameter(JWTUtil.ACCESS_TOKEN_CODE);  
	    	String token = WebUtils.toHttp(request).getHeader(JWTUtil.ACCESS_TOKEN_CODE);  
	    	String id=null;
	    	if(StringUtils.isNotBlank(token)){
	    		id = JWTUtil.getSessionCode(token);
	    	}
	    	log.info("*********从token获取session_code:{}", id);
	    	
	        if (id != null) {  
	            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);  
	            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);  
	        } 
	        return id;  
	    }  
}
