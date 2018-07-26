package com.teamtek.core.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @ClassName: DruidProperties
 * @Description: Druid数据库数据源配置
 * @author shenzhihao
 * @email zhihao@teamtek.cn
 * @date 2017年12月28日 下午5:41:38
 *
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource", ignoreUnknownFields = false)
public class DruidProperties {

	private String driverClassName;

	private String dbUrl;

	private String username;

	private String password;

	private String monitor_username;

	private String monitor_password;
	/**
	 * @Fields initialSize : 连接池启动时的初始值
	 */
	private int initialSize;

	/**
	 * @Fields maxActive : 连接池的最大值
	 */
	private int maxActive;

	/**
	 * @Fields minIdle : 最小空闲值.当空闲的连接数少于阀值时，连接池就会预申请去一些连接，以免洪峰来时来不及申请
	 */
	private int minIdle;

	/**
	 * @Fields validationQuery : 给出一条简单的sql语句进行验证
	 */
	private String validationQuery;

	/**
	 * @Fields testOnBorrow : 在取出连接时进行有效验证
	 */
	private boolean testOnBorrow;

	private boolean testOnReturn;

	private boolean testWhileIdle;

	private int timeBetweenEvictionRunsMillis;

	private int minEvictableIdleTimeMillis;

	/**
	 * @Fields removeAbandoned : 对于长时间不使用的连接强制关闭
	 */
	private boolean removeAbandoned;

	/**
	 * @Fields removeAbandonedTimeout : 超过30分钟开始关闭空闲连接
	 */
	private int removeAbandonedTimeout;

	/**
	 * @Fields logAbandoned : 将当前关闭动作记录到日志
	 */
	private boolean logAbandoned;

	private String filters;

	private String connectionProperties;

	public void config(DruidDataSource dataSource) {
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minIdle);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setTestOnReturn(testOnReturn);
		dataSource.setTestWhileIdle(testWhileIdle);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		dataSource.setRemoveAbandoned(removeAbandoned);
		dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		dataSource.setLogAbandoned(logAbandoned);
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public String getMonitor_username() {
		return monitor_username;
	}

	public void setMonitor_username(String monitor_username) {
		this.monitor_username = monitor_username;
	}

	public String getMonitor_password() {
		return monitor_password;
	}

	public void setMonitor_password(String monitor_password) {
		this.monitor_password = monitor_password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public String getValidationQuery() {
		return validationQuery;
	}

	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	public boolean isRemoveAbandoned() {
		return removeAbandoned;
	}

	public void setRemoveAbandoned(boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	public int getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	public boolean isLogAbandoned() {
		return logAbandoned;
	}

	public void setLogAbandoned(boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

}
