package com.teamtek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.social.TwitterAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebClientAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: 应用启动类
 * @author shenzhihao
 * @date 2017年12月25日 下午3:12:48
 */
@SpringBootApplication(exclude = { TwitterAutoConfiguration.class, WebSocketAutoConfiguration.class,
		WebClientAutoConfiguration.class })
@ComponentScan(basePackages = { "com.teamtek" })
public class AppStarter {

	public static void main(String[] args) {
		SpringApplication.run(AppStarter.class, args);
	}
}
