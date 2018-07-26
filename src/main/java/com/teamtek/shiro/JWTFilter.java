package com.teamtek.shiro;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teamtek.admin.sys.entity.SysLog;
import com.teamtek.admin.sys.entity.SysUser;
import com.teamtek.shiro.util.JWTUtil;
import com.teamtek.shiro.util.ShiroUtils;
import com.teamtek.shiro.util.TempUtil;
import com.teamtek.shiro.util.JWTUtil.JwtVerifyResult;

/**
 * 验证access_token
 * 
 * @author Administrator
 *
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);
	private static final String JWT_VERIFY_FAIL_TYPE = "jwt_verify_fail_type";

	/**
	 * 系统日志 只记录 重要操作的 登录、登出 已经在 SysLoginController中 作过处理 此处不用 添加
	 */
	private final static Map<String, String> url_mappings = new HashMap<String, String>();
	{
		//系统管理
		url_mappings.put("/admin/sys/user/resetPwd", "重置密码");
		url_mappings.put("/admin/sys/user/editPwd", "修改密码");
		url_mappings.put("/admin/sys/user/userImport", "用户导入");
		url_mappings.put("/admin/sys/user/del", "用户删除");
		
		//业务配置管理
		url_mappings.put("/admin/biz/project/projectImport", "项目导入");
		url_mappings.put("/admin/biz/project_type/surplusScores", "当月结余");

		//积分数据管理
		url_mappings.put("/admin/score/import", "导入业务数据");
		url_mappings.put("/admin/score/delData", "业务数据删除");
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 获取Authorization字段
		// System.out.println(this.getSubject(httpServletRequest,
		// response).isAuthenticated());
		if (super.isAccessAllowed(request, response, mappedValue)) {
//			 String token =
//			 httpServletRequest.getParameter(JWTUtil.ACCESS_TOKEN_CODE);
			String token = httpServletRequest.getHeader(JWTUtil.ACCESS_TOKEN_CODE);

			if (token != null) {
				String username = JWTUtil.getUsername(token);

				JwtVerifyResult verify = JWTUtil.verify(token, username);
				if (verify == JwtVerifyResult.pass) {
					recordLogs(httpServletRequest);
					return true;
				} else {
					httpServletRequest.setAttribute(JWT_VERIFY_FAIL_TYPE, verify);
				}
				return false;
			} else {
				logger.info("*****非法token************************");
				return false;
			}
		} else {
			return false;
		}
	}

	private void recordLogs(HttpServletRequest request) {
		if (url_mappings.containsKey(request.getRequestURI())) {
			// 非匿名登录
			SysUser currUser = ShiroUtils.getAdminEntity();
			if ( currUser!= null) {
				// 系统日志
				SysLog slog = new SysLog(currUser.getUserId(), currUser.getName(), currUser.getUsername(), new Date(),
						url_mappings.get(request.getRequestURI()), request.getRequestURI());
				TempUtil.sysLogService.insert(slog);
				logger.info("系统日志记录--操作【{}】--操作人【{}】--操作人id【{}】", url_mappings.get(request.getRequestURI()), currUser.getName(), currUser.getUserId());
			}

		}

	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		logger.warn("*****token没有验证通过********{}", httpServletRequest.getAttribute(JWT_VERIFY_FAIL_TYPE));
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		JwtVerifyResult verify = (JwtVerifyResult) httpServletRequest.getAttribute(JWT_VERIFY_FAIL_TYPE);
		if (verify == JwtVerifyResult.fail_expired) {
			httpServletResponse.sendRedirect("/token_expired");
			return false;
		}
		// httpServletResponse.sendRedirect("/auth_fail");
		httpServletRequest.getRequestDispatcher("/auth_fail").forward(httpServletRequest, response);
		return false;
	}
}
