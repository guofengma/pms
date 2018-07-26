package com.teamtek.rule.single

import org.apache.commons.lang3.math.NumberUtils

class SingleRule7 {

	/**
	 * 单项目计分规则：
	 * 7.销售额不满Y扣S×P，最多扣Z分（Z为空则为无限制）
	 * @param s 销售额
	 * @param p
	 * @param y 
	 * @param z
	 * @return
	 */
	def static score(s,p,y,z){
		String err=null
		String msg='扣分成功'

		boolean t=true;
		if(!(s&&p&&y)){
			err="参数不能为空"
			t=false
		}
		def result = [
			'result':t,
			'integral':{
				def data=0
				if(t) {
					if(s<y){
						BigDecimal bs=new BigDecimal(s);
						BigDecimal bp=new BigDecimal(p);

						BigDecimal bResult=bs.multiply(bp);
						bResult = bResult.setScale(2,BigDecimal.ROUND_HALF_UP);

						data=bResult.doubleValue()
						if(z){
							if(data>z){
								data=z
							}
						}
					}else{
						msg='销售额达标，没有扣分'
					}
				}
				data
			}(),
			'msg':t?msg:err
		]
	}
}
