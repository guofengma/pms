package com.teamtek.rule.single

import org.apache.commons.lang3.math.NumberUtils

class SingleRule1 {
	/**
	 * 单项目计分规则：
	 * 1.完成得X分
	 * @param finished 是否完成
	 * @param x 完成得分
	 * @return
	 */
	def static score(boolean finished,x){
		String fail="不满足加分规则(项目没有完成)";
		String success="满足加分规则(项目已完成)";
		if(x){
			finished=false
			fail='入参有误（x应该为数字）'
		}
		def result = [
			'result':finished,
			'integral':{
				if(finished) {
					x
				}else {
					0
				}
			}(),
			'msg':finished?success:fail
		]
	}
	
}
