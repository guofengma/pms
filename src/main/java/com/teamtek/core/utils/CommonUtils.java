package com.teamtek.core.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

    /**
     * 判断对象是否为空
     * 
     * @param paramObject
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object paramObject) {
	if (paramObject == null)
	    return true;
	if ((paramObject instanceof String))
	    return StringUtils.isBlank(paramObject.toString());
	Object localObject;
	if ((paramObject instanceof Collection)) {
	    localObject = (Collection) paramObject;
	    return ((Collection) localObject).size() == 0;
	}
	if ((paramObject instanceof Map)) {
	    localObject = (Map) paramObject;
	    return ((Map) localObject).size() == 0;
	}
	if (paramObject.getClass().isArray())
	    return Array.getLength(paramObject) == 0;
	return false;
    }
}
