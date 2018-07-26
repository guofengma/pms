package com.teamtek.admin.score.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * <p>
 * 业务数据管理表
 * </p>
 *
 * @author syj
 * @since 2018-03-14 11:36:18
 */
@TableName("score_data")
public class ScoreData implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum ImportStatus {
		begin(0, "开始导入"), base_import_success(1, "基础项目业务数据导入成功"), base_success_and_market_fail(2,
				"基础项目业务数据导入成功，但营销项目业务数据导入失败"), all_success(3, "业务数据导入成功");
		private final int value;
		private final String title;

		private ImportStatus(int value, String title) {
			this.value = value;
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		public int getValue() {
			return value;
		}

		public static ImportStatus getEnumByValue(int value) {
			for (ImportStatus e : ImportStatus.values()) {
				if (e.getValue() == value) {
					return e;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			ImportStatus e = getEnumByValue(value);
			return e == null ? null : e.title;
		}

		public static List<String> getTitleList() {
			List<String> list = new ArrayList<>(ImportStatus.values().length);
			for (ImportStatus s : ImportStatus.values()) {
				list.add(s.getTitle());
			}
			return list;
		}
	}

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 模板id
	 */
	@TableField(value = "rule_template_id")
	private Long ruleTemplateId;

	/**
	 * 导入编号
	 */
	@TableField(value = "import_no")
	private String importNo;

	/**
	 * 导入状态 @see com.teamtek.admin.score.entity.ScoreData.ImportStatus
	 */
	@TableField(value = "import_status")
	private Integer importStatus;

	@TableField(value = "import_time")
	private Date importTime;
	/**
	 * 导入的基本项目业务 数据条数
	 */
	@TableField(value = "base_count")
	private Integer baseCount;
	/**
	 * 导入的营销项目业务 数据条数
	 */
	@TableField(value = "market_count")
	private Integer marketCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public Long getRuleTemplateId() {
		return ruleTemplateId;
	}

	public void setRuleTemplateId(Long ruleTemplateId) {
		this.ruleTemplateId = ruleTemplateId;
	}

	public String getImportNo() {
		return importNo;
	}

	public void setImportNo(String importNo) {
		this.importNo = importNo;
	}

	public Integer getImportStatus() {
		return importStatus;
	}

	public void setImportStatus(Integer importStatus) {
		this.importStatus = importStatus;
	}

	public Integer getBaseCount() {
		return baseCount;
	}

	public void setBaseCount(Integer baseCount) {
		this.baseCount = baseCount;
	}

	public Integer getMarketCount() {
		return marketCount;
	}

	public void setMarketCount(Integer marketCount) {
		this.marketCount = marketCount;
	}

}
