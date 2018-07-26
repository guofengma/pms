package com.teamtek.rule.total

import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.math.NumberUtils

class RateAddSubScore {
	/**
	 * *总积分生成规则：
	 * 1.利率满P1每超过Y1加Z1分，每降低Y2减Z2分
	 * @param p  利率
	 * @param p1
	 * @param y1
	 * @param y2
	 * @param z1
	 * @param z2
	 * @return
	 */
	def static score(p,p1,y1,y2,z1,z2){
		String err=null
		boolean t=true;
		if(!(y1&&y2&&p&&p1&&z1&&z2)){
			err="参数不能为空"
			t=false
		}
		def result = [
			'result':t,
			'integral':{
				def data=0
				if(t) {
					if(p>p1){
						def dp= (p-p1)/y1 as int
						BigDecimal bs=new BigDecimal(dp);
						BigDecimal bp=new BigDecimal(z1);

						BigDecimal bResult=bs.multiply(bp);
						bResult = bResult.setScale(2,BigDecimal.ROUND_HALF_UP);

						data=bResult.doubleValue()
					}
					if(p<p1){
						def dp= (p-p1)%y2 as int

						BigDecimal bs=new BigDecimal(dp);
						BigDecimal bp=new BigDecimal(z2);

						BigDecimal bResult=bs.multiply(bp);
						bResult = bResult.setScale(2,BigDecimal.ROUND_HALF_UP);

						data=bResult.doubleValue()
					}
				}
				data
			}(),
			'msg':t?'取值成功':err
		]
	}
}
