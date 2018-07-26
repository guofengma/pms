package com.teamtek.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.teamtek.shiro.JWTFilter;
import com.teamtek.shiro.MySessionManager;
import com.teamtek.shiro.UserRealm;
import com.teamtek.shiro.util.JWTUtil;

import net.sf.ehcache.CacheManager;

/**
 * Shiro 配置
 * 
 */
@Configuration
public class ShiroConfiguration {

	private Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 * 
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 * 
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		logger.info("ShiroConfiguration.shiroFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		Map<String, Filter> filterMap = new HashMap<>();
		//自定义jwt 认证过滤器
		filterMap.put("jwt", new JWTFilter());
		shiroFilterFactoryBean.setFilters(filterMap);
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		//filterChainDefinitionMap.put("/admin/sys/logout", "logout");

		// 配置记住我或认证通过可以访问的地址
		//filterChainDefinitionMap.put("/admin/index", "user");

		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/admin/sys/login", "anon");
		filterChainDefinitionMap.put("/admin/comm/excel/**", "anon");
		//filterChainDefinitionMap.put("/admin/sys/123", "jwt,authc");//ceshi
		//filterChainDefinitionMap.put("/admin/score/**", "anon");//文件上传公共组件
		filterChainDefinitionMap.put("/admin/123", "anon");//druid监控平台跳过
		filterChainDefinitionMap.put("/druid/**", "anon");//druid监控平台跳过
		filterChainDefinitionMap.put("/lody/**", "anon");//javamelody监控平台跳过
		filterChainDefinitionMap.put("/admin/**","jwt,authc");//项目资源路径
		
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/auth_fail");
		// 登录成功后要跳转的链接
		//shiroFilterFactoryBean.setSuccessUrl("/admin/sys/main");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/auth_fail");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager(EhCacheManager cacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(getUserRealm());
		// 注入缓存管理器;
		securityManager.setCacheManager(cacheManager);// 这个如果执行多次，也是同样的一个对象;

		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * 自定义session管理
	 * @return
	 */
	@Bean
	public MySessionManager sessionManager() {
		MySessionManager mySessionManager = new MySessionManager();
		mySessionManager.setGlobalSessionTimeout(JWTUtil.EXPIRE_TIME+300000);
		return mySessionManager;
	}

	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean
	public UserRealm getUserRealm() {
		UserRealm myShiroRealm = new UserRealm();
		return myShiroRealm;
	}

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * 给Shiro配置 Ehcache缓存
	 * @param cacheManager
	 * @return
	 */
	@Bean
	public EhCacheManager ehCacheManager(CacheManager cacheManager) {
//		CacheManager cacheManager = CacheManager.getCacheManager("es");
//		if (cacheManager == null) {
//			try {
//				cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:ehcache-shiro.xml"));
//			} catch (IOException e) {
//				throw new RuntimeException("initialize cacheManager failed");
//			}
//		}
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManager(cacheManager);
		return ehCacheManager;

		// logger.info("ShiroConfiguration.getEhCacheManager()");
		// EhCacheManager cacheManager = new EhCacheManager();
		// cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		// return cacheManager;

	}
	


}
