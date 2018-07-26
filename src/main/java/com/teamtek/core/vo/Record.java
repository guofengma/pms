package com.teamtek.core.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
* @ClassName: Record
* @Description: JSON的通用传输对象 
* @author shenzhihao 
* @email zhihao@teamtek.cn
* @date 2018年1月2日 下午5:17:38
*
*/ 
public class Record extends LinkedHashMap<String, Object> implements
		Serializable {

	private static final long serialVersionUID = 1L;
	
	public static Record createNewRecord(String key,Object value){
		Record r = new Record();
		r.put(key, value);
		return r;
	}
	
	public void remove(String... keys) {
		try {
			for (int i = 0; i < keys.length; i++) {
				this.remove(keys[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保留指定key
	 * 
	 * @param keys
	 */
	public void Retention(String... keys) {
		try {

			List<String> keyList = Arrays.asList(keys);

			Iterator<String> iter = this.keySet().iterator();

			List<String> retentionKeyList = new ArrayList<String>();

			while (iter.hasNext()) {

				Object key = iter.next();

				if (!keyList.contains(key.toString())) {

					retentionKeyList.add(key.toString());
				}
			}

			for (int i = 0; i < retentionKeyList.size(); i++) {
				this.remove(retentionKeyList.get(i).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object get(String key, Object defaultValue) {
		try {
			Object value = this.get(key);
			if(value == null){
				return defaultValue;
			}else{
				if("null".equals(value.toString().toLowerCase())){
					return defaultValue;
				}
			}
			return this.get(key);
		} catch (Exception ex) {
			return defaultValue;
		}
	}

	public void set(String key, Object value) {

		try {

			this.remove(key);

			this.put(key, value);

		} catch (Exception ex) {

		}
	}

}
