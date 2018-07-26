package com.teamtek.admin.biz.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.teamtek.admin.biz.entity.constant.RuleType;
import com.teamtek.core.vo.Record;

/** 
* @Description:规则  
* @author shenzhihao 
* @date 2018年3月5日 下午2:17:24 
*/
public enum Rule {

	/**
	 * 多项取最高分
	 */
	MULTIPLE_MAX_SCORE(1, RuleType.LIMIT_SCORE, "多项取最高分", "多项取最高分"),
	/**
	 * 多项取最低分
	 */
	MULTIPLE_MIN_SCORE(2, RuleType.LIMIT_SCORE, "多项取最低分", "多项取最低分"),
	/**
	 * 单项X分封顶
	 */
	MULTIPLE_TOP_SCORE(3, RuleType.LIMIT_SCORE, "单项X分封顶", "多项取最高分"),
	/**
	 * 完成得X分
	 */
	GET_SCORE(4, RuleType.SINGLE_SCORE, "完成得X分", "完成得X分"),
	/**
	 * 销售额Y1以上Y2以下得X分（Y2设为空则只算Y1以上，Y1为空同理）
	 */
	SECTION_SCORE(5, RuleType.SINGLE_SCORE, "销售额Y1以上Y2以下得X分（Y2设为空则只算Y1以上，Y1为空同理）", "销售额Y1以上Y2以下得X分（Y2设为空则只算Y1以上，Y1为空同理"),
	/**
	 * 每单得X分
	 */
	EVERY_SINGLE_SCORE(6, RuleType.SINGLE_SCORE, "每单得X分", "每单得X分"),
	/**
	 * 完成得S×P分
	 */
	FINISH_SCORE(7, RuleType.SINGLE_SCORE, "完成得S×P分", "完成得S×P分"),
	/**
	 * 销售额S在Y1以上Y2以下得S×P分
	 */
	SECTION_SCORE2(8, RuleType.SINGLE_SCORE, "销售额S在Y1以上Y2以下得S×P分", "销售额S在Y1以上Y2以下得S×P分"),
	/**
	 * 每单得S×P分
	 */
	EVERY_SINGLE_SCORE2(9, RuleType.SINGLE_SCORE, "每单得S×P分", "每单得S×P分"),
	/**
	 * 销售额不满Y扣S×P，最多扣Z分（Z为空则为无限制）
	 */
	SUB_SCORE(10, RuleType.SINGLE_SCORE, "销售额不满Y扣S×P，最多扣Z分（Z为空则为无限制）", "销售额不满Y扣S×P，最多扣Z分（Z为空则为无限制）"),

	/**
	 * X分封顶
	 */
	SINGLE_TOP_SCORE(11, RuleType.SUBTOTAL_SCORE, "X分封顶", "X分封顶"),

	/**
	 * S×P封顶以及X分封顶
	 */
	TOP_SCORE(12, RuleType.SUBTOTAL_SCORE, "S×P封顶以及X分封顶", "S×P封顶以及X分封顶"),

	/**
	 * 主副调比例，主调为S×P1副调为S×P2
	 */
	PRIMARY_SECONDARY_SCALING_SCORE(13, RuleType.SUBTOTAL_SCORE, "主副调比例，主调为S×P1副调为S×P2", "主副调比例，主调为S×P1副调为S×P2"),

	/**
	 * 利率满P1每超过Y1加Z1分，每降低Y2减Z2分
	 */
	RATE_ADD_SUB_SCORE(14, RuleType.TOTAL_SCORE, "利率满P1每超过Y1加Z1分，每降低Y2减Z2分", "利率满P1每超过Y1加Z1分，每降低Y2减Z2分"),

	/**
	 * 销售额满Y 则得S×P分（S为积分小计，若Y为0，则总得S×P分）
	 */
	SECTION_SCORE3(15, RuleType.TOTAL_SCORE, "销售额满Y 则得S×P分（S为积分小计，若Y为0，则总得S×P分）", "销售额满Y 则得S×P分（S为积分小计，若Y为0，则总得S×P分）");

	private int id;
	private RuleType ruleTypes;
	private String name;
	private String remark;


	private Rule(int id, RuleType ruleTypes, String name, String remark) {
		this.id = id;
		this.ruleTypes = ruleTypes;
		this.name = name;
		this.remark = remark;
	}

	/**
	 * 获取所有规则数据
	 * @return
	 */
	public static List<Record> getAll() {
		List<Record> list = new ArrayList<>();
		for(Rule r : Rule.values()){
			Record record = new Record();
			record.put("rid", r.getId());
			record.put("typeName", r.getRuleTypes().getName());
			record.put("name", r.getName());
			record.put("remark", r.getRemark());
			list.add(record);
		}
		return list;
	}
	
	/**
	 * 根据规则类别ID查询
	 * @param ruleTypeId 规则类别ID
	 * @return
	 */
	public static Rule getEnumByRuleTypeId(int ruleTypeId) {
		for (Rule r : Rule.values()) {
			if (ruleTypeId == r.getRuleTypes().getId()) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * 根据名称或者描述查询
	 * @return
	 */
	public static List<Rule> getEnumByNameOrMark(String s) {
		if (StringUtils.isBlank(s)) {
			return null;
		}

		List<Rule> list = new ArrayList<>();
		for (Rule r : Rule.values()) {
			if(StringUtils.indexOfAny(r.getName(), s) !=-1){
				list.add(r);
				continue;
			}
			if(StringUtils.indexOfAny(r.getRemark(), s)!=-1){
				list.add(r);
			}
		}
		return list;
	}
	
	public int getId() {
		return id;
	}

	public RuleType getRuleTypes() {
		return ruleTypes;
	}

	public String getName() {
		return name;
	}

	public String getRemark() {
		return remark;
	}
}
