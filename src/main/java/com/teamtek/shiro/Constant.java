package com.teamtek.shiro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 常量
 * @author Administrator
 *
 */
@Component
@PropertySource("classpath:config.properties")
public class Constant {

	@Value("${database.adminId}")
	public Long adminId;

}
