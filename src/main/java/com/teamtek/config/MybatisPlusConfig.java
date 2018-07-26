package com.teamtek.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.teamtek.core.datasource.DruidProperties;

@Configuration
@EnableTransactionManagement(order = 10) // 由于引入多数据源，所以让spring事务的aop要在多数据源切换aop的后面
@MapperScan(value={"com.teamtek.admin.**.dao*"})
public class MybatisPlusConfig {
	@Autowired
	DruidProperties druidProperties;

	/**
	 * druid监控 登陆配置
	 * @return
	 */
	@Bean
	public ServletRegistrationBean druidServletRegistrationBean() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		// 白名单：
		// servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
		// permitted to view this page.
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", druidProperties.getMonitor_username());
		servletRegistrationBean.addInitParameter("loginPassword", druidProperties.getMonitor_password());
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "true");
		return servletRegistrationBean;
	}

	/**
	 * 注册DruidFilter拦截
	 * 
	 * @return FilterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean druidFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		Map<String, String> initParams = new HashMap<String, String>();
		// 设置忽略请求
		initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.setInitParameters(initParams);
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	/**
	 * 默认数据源
	 * 
	 * @return
	 */
	private DruidDataSource defaultDatasource() {
		DruidDataSource dataSource = new DruidDataSource();
		druidProperties.config(dataSource);

		try {
			List<Filter> proxyFilters = new ArrayList<Filter>();
			WallFilter statFilter = new WallFilter();
			WallConfig config = new WallConfig();
			config.setMultiStatementAllow(true); // 批量操作
			statFilter.setConfig(config);
			proxyFilters.add(statFilter);
			dataSource.setProxyFilters(proxyFilters);
			dataSource.setFilters(druidProperties.getFilters());
		} catch (SQLException e) {
		}
		dataSource.setConnectionProperties(druidProperties.getConnectionProperties());
		return dataSource;
	}

	/**
	 * 单数据源连接池配置
	 */
	@Bean
	public DruidDataSource singleDatasource() {
		return this.defaultDatasource();
	}

	/**
	 * mybatis-plus分页插件
	 * 
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setDialectType("mysql");
		return paginationInterceptor;
	}

}
