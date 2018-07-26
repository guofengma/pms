package com.teamtek.core.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
* @ClassName: AbstController
* @Description: Controller公共组件
* @author shenzhihao 
* @email zhihao@teamtek.cn
* @date 2017年12月29日 上午11:40:14
*
*/ 
public abstract class AbstController {
    
    /**
    * @Fields log : 子类公用的日志记录器
    */ 
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    
    
    /**
     * 根据JSON字符串返回对应的Value
     * 
     * @param search
     *            要解析Json的字符串
     * @param keyNames
     *            查询的Names
     * @return Map<String, T>
     */
    @SuppressWarnings("unchecked")
    protected <T> Map<String, T> parseObject(String search, String... keyNames) {
	JSONObject parseObject = JSONArray.parseObject(search);
	if (null != parseObject && null != keyNames) {
	    Map<String, T> map = new HashMap<String, T>();
	    for (String key : keyNames) {
		map.put(key, (T) parseObject.get(key));
	    }
	    return map;
	}
	return null;
    }
}
