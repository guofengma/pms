package com.teamtek.rule.single

import org.apache.commons.lang3.math.NumberUtils

class SingleRule3 {
	/**
	 *  单项目计分规则：
	 * 3.每单得X分
	 * @param s 完成单数
	 * @param x
	 * @return
	 */
	def static score( s, x){
		boolean t=true;
		String err=null;
		//验证参数
		if(!(s&&x)){
			t=false;
			err="加分失败（输入参数应该为数字）"
		}

		def result = [
			'result':t,
			'integral':{
				def data=0
				if(t) {
					BigDecimal bs=new BigDecimal(s);
					BigDecimal bp=new BigDecimal(x);
					BigDecimal bResult=bs.multiply(bp);
					bResult = bResult.setScale(2,BigDecimal.ROUND_HALF_UP);
					data=bResult.doubleValue()
				}
				data
			}(),
			'msg':t?'加分成功':err
		]
	}

}
