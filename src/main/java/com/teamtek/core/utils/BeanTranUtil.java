package com.teamtek.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.teamtek.core.vo.Record;

public class BeanTranUtil {

    /**
     * 
     * 
     * Map转换层Bean，使用泛型免去了类型转换的麻烦。
     * 
     * @param <T>
     * @param map
     * @param class1
     * @return
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> class1) {
	T bean = null;
	try {
	    bean = class1.newInstance();
	    BeanUtils.populate(bean, map);
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (InvocationTargetException e) {
	    e.printStackTrace();
	}
	return bean;
    }

    
    public static <T> T transRecord2Bean(Record record, Class<T> obj) {
	T objInstance = null;
	if (record == null || obj == null) {
	    return null;
	}
	try {
	    objInstance = obj.newInstance();
	    BeanUtils.populate(objInstance, record);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return objInstance;
    }
}
