package rule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankRuleTest {

	public static void main(String[] args) {
		//System.out.println(socre.get("integral"));
		 //System.out.println(Rule2.socre(100, 99.99999999999999999999999, 200, 0.59999999));
		// System.out.println(Rule3.socre(3.55955544226685989,3.55955544226685989));
		 //System.out.println(Rule3.socre(23.3,10));
		 
		 //取最值
//		 List<Double> list = new ArrayList<>();
//		 list.add(1D);
//		 list.add(12312D);
//		 list.add(523D);
//		 list.add(52D);
//		 list.add(54D);
//		 list.add(131313.0005);
//		System.out.println(Rule1.socre(list,Rule1.getType_min()));
		 
		//总积分生成规则：
		// 1.利率满P1每超过Y1加Z1分，每降低Y2减Z2分
		 //System.out.println(TotalRule1.score(0.05, 0.031, 0.002, 0.002, 0.111, 0.1));
		// System.out.println(TotalRule2.score(12.3, 3, 40, 40));
//		BigDecimal bs=new BigDecimal(12.333);
//		BigDecimal bp=new BigDecimal(3);
//		
//		BigDecimal bResult=bs.multiply(bp);
//		 BigDecimal setScale1 = bResult.setScale(2,BigDecimal.ROUND_HALF_UP);
//		System.out.println(setScale1.doubleValue());
		 
	//	 System.out.println(SingleRule7.score(100, 0.1, 30, 30));
		

		//		 Object validate2 =SingleRule5.score(100, 90, 200, 0.59999999);
//		 System.out.println(validate2);
		 //System.out.println(strToDouble("1.2fsf"));
//		List<Double> list=Arrays.asList(123D,32423.3356D,3534.266D);
//		
//		
//		Object score = ScoreRule1.score(list, 1);
		
		Double d1 = 1.234;
		Double d2 = 1.223;
		Double d3 = 3.222;
		Double d4 = 2.232;
		
		List<Double> doublelist = new ArrayList<>();
		doublelist.add(d1);
		doublelist.add(d2);
		doublelist.add(d3);
		doublelist.add(d4);
		Double max = Collections.max(doublelist);
		System.out.println(max);
	}
	
	private static Double strToDouble(String value) {
		BigDecimal bs =null;
		try {
			bs=new BigDecimal(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bs=new BigDecimal(0);
		}
		BigDecimal setScale = bs.setScale(2, BigDecimal.ROUND_DOWN);
		return setScale.doubleValue();
	}
}
