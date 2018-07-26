package com.teamtek.rule.single

class SectionScore2 {

	/**
	 * 5.销售额S在Y1以上Y2以下得S×P分
	 * @param s
	 * @param y1
	 * @param y2
	 * @param p
	 * @return
	 */
	def static score(s,y1,y2,p){
		String fail_y1="不满足规则,销售额s(%s)在y1(%s)之下";
		String fail_y2="不满足规则,销售额s(%s)在y2(%s)之上";
		String success="满足规则,销售额s(%s)在y1(%s)之上，y2(%s)之下";
		
		def val = [
			'isAboveY1':s > y1?true:false,
			'isBelowY2':s <= y2?true:false
		]

		def result = [
			'result':(val.isAboveY1&&val.isBelowY2)?true:false,
			'integral':{
				def data=0
				if(val.isAboveY1&&val.isBelowY2) {
					BigDecimal bs=new BigDecimal(s);
					BigDecimal bp=new BigDecimal(p);
					BigDecimal bResult=bs.multiply(bp);
					bResult = bResult.setScale(2,BigDecimal.ROUND_HALF_UP);
					data=bResult.doubleValue()
				}
				data
			}(),
			'msg':{
				if(!val.isAboveY1) {
					return String.format(fail_y1, s,y1)
				}
				if(!val.isBelowY2) {
					return String.format(fail_y2, s,y2)
				}
				String.format(success, s,y1,y2)
				
			}()
		]
	}
	
}



