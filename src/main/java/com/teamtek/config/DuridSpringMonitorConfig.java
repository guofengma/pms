package com.teamtek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.spring.stat.BeanTypeAutoProxyCreator;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.baomidou.mybatisplus.service.IService;

/**
 * 监控spring Service接口
 * 
 * @author Administrator
 *
 */
@Configuration
public class DuridSpringMonitorConfig {

	@Bean("druid-stat-interceptor")
	public DruidStatInterceptor druidInterceptor() {
		return new DruidStatInterceptor();
	}

	@Bean("druid-type-proxyCreator")
	public BeanTypeAutoProxyCreator genProxyCreator() {
		BeanTypeAutoProxyCreator beanTypeAutoProxyCreator = new BeanTypeAutoProxyCreator();
		beanTypeAutoProxyCreator.setTargetBeanType(IService.class);
		beanTypeAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");
		return beanTypeAutoProxyCreator;
	}

//	@Bean("druid-stat-pointcut")
//	public JdkRegexpMethodPointcut genProxyCreator2() {
//		JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
//		jdkRegexpMethodPointcut.setPatterns("com.teamtek.admin.sys.service.impl.*");
//		return jdkRegexpMethodPointcut;
//	}
//
//	@Bean
//	public Advisor adviceAdvisor(){
//		DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
//		defaultPointcutAdvisor.setPointcut(genProxyCreator2());
//		defaultPointcutAdvisor.setAdvice(druidInterceptor());
//		return defaultPointcutAdvisor;
//	}

}
