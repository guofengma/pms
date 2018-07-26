package com.teamtek.rule.subtotal

import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.math.NumberUtils

class SubTotalRule3 {
	/**
	 * 积分小计规则
	 * 3.主副调比例，主调为S×P1副调为S×P2
	 * @param s
	 * @param p1 主调
	 * @param p2 副调
	 * @return
	 */
	def static score(s,p1,p2){
		String err=null
		boolean t=true;
		if(!(s&&p1&&p2)){
			err="参数不能为空"
			t=false
		}
		def result = [
			'result':t,
			'integral_1':{
				def data=0
				if(t) {
					BigDecimal bs=new BigDecimal(s);
					BigDecimal bp=new BigDecimal(p1);
					BigDecimal bResult=bs.multiply(bp);
					bResult = bResult.setScale(2,BigDecimal.ROUND_HALF_UP);
					data=bResult.doubleValue()
				}else {
					0
				}
			}(),
			'integral_2':{
				def data=0
				if(t) {
					BigDecimal bs=new BigDecimal(s);
					BigDecimal bp=new BigDecimal(p2);
					BigDecimal bResult=bs.multiply(bp);
					bResult = bResult.setScale(2,BigDecimal.ROUND_HALF_UP);
					data=bResult.doubleValue()
				}else {
					0
				}
			}(),
			'msg':t?'取值成功':err
		]
	}
}
