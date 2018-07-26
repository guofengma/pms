package com.teamtek.rule.score

import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.math.NumberUtils

class ScoreRule1 {
	def static final int type_min=0;
	def static final int type_max=1;
	/**
	 * 取分规则:
	 * 1.多项取最值
	 * type=Rule1.type_min 取最小值
	 * type=Rule1.type_max 取最大值
	 * @param list
	 * @return
	 */
	def static score(List<Double> list,int type){
		String err=null
		String msg=null
		boolean t=true;
		int len=list.size();
		if(len==0){
			err="参数不能为空"
			t=false
		}
		def result = [
			'result':t,
			'integral':{
				if(t) {
					if(type==ScoreRule1.type_max){
						def max=Collections.max(list)
						msg='取最大值成功'
						return max
					}else{
						def min=Collections.min(list)
						msg='取最小值成功'
						return min
					}
				}else {
					0
				}
			}(),
			'msg':t?msg:err
		]
	}
}
