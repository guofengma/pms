package com.teamtek.rule.single

import org.apache.commons.lang3.math.NumberUtils

class SingleRule6 {
	
	/**
	 *  单项目计分规则：
	 * 6.每单得S×P分
	 * @param s
	 * @param p
	 * @param x 完成单数
	 * @return
	 */
	def static score(s,p,x){
		boolean t=true;
		String err=null;

		//验证参数
		if(!(s&&p&&x)){
			t=false;
			err="加分失败（输入参数应该为数字）"
		}

		def result = [
			'result':t,
			'integral':{
				def data=0
				if(t) {
					BigDecimal bs=new BigDecimal(s);
					BigDecimal bp=new BigDecimal(p);
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
