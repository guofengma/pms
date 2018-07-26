package com.teamtek.rule.single

import org.apache.commons.lang3.math.NumberUtils

class SingleRule2 {
	/**
	 * 单项目计分规则：
	 * 2.销售额Y1以上Y2以下得X分（Y2设为空则只算Y1以上，Y1为空同理）
	 * @param finished 是否完成
	 * @param x 完成得分
	 * @return
	 */
	def static score(s,y1,y2,x){

		String msg="满足加分规则";
		//是否可以得分
		boolean obtain=true;

		def result = [

			'integral':{
				if(s<=y2||s>=y1){
					//y1 y2都不为空
					if(y1&&y2){
						if(s<=y2&&s>=y1){
							return x
						}else{
							obtain=false
							msg='不满足加分规则'
							return 0
						}
					}
					//y1 y2有一个为空
					return x;
				}else{
					obtain=false
					msg='不满足加分规则'
					0
				}

			}(),
			'result':obtain,
			'msg':msg
		]
	}
}
