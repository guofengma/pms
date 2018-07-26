package com.teamtek.rule.total

class TotalRule2 {
	/**
	 * 总积分生成规则：
	 * 2.销售额满Y 则得S×P分
	 * （S可为积分小计，若Y为0，则总得S×P分，即可实现续贷业务积分折算）
	 * 
	 * @param s
	 * @param p
	 * @param sy 销售额
	 * @param y 
	 * @return
	 */
	def static score(s,p,sy,y){
		String err=null
		boolean t=true;
		if(!(s&&p&&y&&sy)){
			err="参数不能为空"
			t=false
		}
		def result = [
			'result':t,
			'integral':{
				def data=0
				if(t) {
					if(sy>=y){
						BigDecimal bs=new BigDecimal(s);
						BigDecimal bp=new BigDecimal(p);

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
