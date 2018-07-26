package com.teamtek.rule.subtotal

import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.math.NumberUtils

class SubTotalRule1 {
	/**
	 *积分小计规则
	 * 1.X分封顶
	* @param y 得分   比如 当 y=5 x=4  那么返回 4
	 * @param x  分封顶分
	 */
	def static score(y,x){
		String err=null
		boolean t=true;
		if(!(y&&x)){
			err="参数不能为空"
			t=false
		}
		def result = [
			'result':t,
			'integral':{
				if(t) {
					if(y>x){
						return x
					}else{
						return y
					}
				}else {
					0
				}
			}(),
			'msg':t?'取值成功':err
		]
	}
}
