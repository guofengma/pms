package com.teamtek.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

/** 
* @Description:  FastJson配置
* @author shenzhihao 
* @date 2017年12月25日 下午3:24:58 
*/
@Configuration
public class AlibabaFastJsonConfig {
    
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
	FastJsonHttpMessageConverter4 fastConverter = new FastJsonHttpMessageConverter4();
	List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
	supportedMediaTypes.add(MediaType.parseMediaType("text/html;charset=UTF-8"));
	supportedMediaTypes.add(MediaType.parseMediaType("application/json"));
	fastConverter.setSupportedMediaTypes(supportedMediaTypes);
	FastJsonConfig fastJsonConfig = new FastJsonConfig();
	fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
	fastConverter.setFastJsonConfig(fastJsonConfig);
	return new HttpMessageConverters(fastConverter);
    }

}
