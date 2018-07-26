package com.teamtek.admin.biz.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.teamtek.core.vo.Record;

/**
 * <p>
 * 项目管理
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
@TableName("biz_project")
public class BizProject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 项目状态
	 * 
	 * @author syj
	 * @createTime：2018年3月1日 下午12:02:01
	 */
	public enum Status {
		running(0, "运行中"), stopping(1, "暂停中"), end(2, "已结束");
		private final int value;

		private final String title;

		private Status(int value, String title) {
			this.value = value;
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		public int getValue() {
			return value;
		}

		public static Status getEnumByValue(int value) {
			for (Status e : Status.values()) {
				if (e.getValue() == value) {
					return e;
				}
			}
			return null;
		}

		public static Status getEnumByTitle(String title) {
			for (Status e : Status.values()) {
				if (e.getTitle().equals(title)) {
					return e;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			Status e = getEnumByValue(value);
			return e == null ? null : e.title;
		}

		public static Integer getValueByTitle(String title) {
			Status e = getEnumByTitle(title);
			return e == null ? null : e.getValue();
		}

		/**
		 * value--title map
		 * 
		 * @return
		 */
		public static List<Record> getRecordList() {
			List<Record> status = new ArrayList<>(BizProject.Status.values().length);
			Record record = null;
			for (Status s : BizProject.Status.values()) {
				record = new Record();
				record.put(s.getValue() + "", s.getTitle());
				status.add(record);
			}
			return status;
		}

		/**
		 * value--value map title--title map
		 * 
		 * @return
		 */
		public static List<Record> getVoList() {
			List<Record> status = new ArrayList<>(BizProject.Status.values().length);
			Record record = null;
			for (Status s : BizProject.Status.values()) {
				record = new Record();
				record.put("value", s.getValue());
				record.put("title", s.getTitle());
				status.add(record);
			}
			return status;
		}

		public static List<String> getTitleList() {
			List<String> list = new ArrayList<>(BizProject.Status.values().length);
			for (Status s : BizProject.Status.values()) {
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
	 * 项目编号
	 */
	@TableField(value = "project_num")
	private String projectNum;

	@TableField(value = "project_type_id")
	private Long projectTypeId;

	/**
	 * 项目名称
	 */
	@TableField(value = "project_name")
	private String projectName;

	/**
	 * 项目描述
	 */
	@TableField(value = "project_remark")
	private String projectRemark;

	/**
	 * 状态
	 * 
	 * @see com.teamtek.admin.biz.entity.BizProject.Status
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time")
	private Date createTime;

	/**
	 * 上次编辑时间
	 */
	@TableField(value = "last_edit_time")
	private Date lastEditTime;
	/**
	 * 是否是 产品 （默认是 项目）
	 */
	@TableField(value = "is_product")
	private Boolean isProduct = Boolean.FALSE;

	@TableField(value = "has_product")
	private Boolean hasProduct = Boolean.FALSE;
	/**
	 * 产品的项目id（当该记录是产品时，该字段才有用）
	 */
	@TableField(value = "p_id")
	private Long pId;
	/**
	 * 上限
	 */
	@TableField(value = "upper_limit")
	private Double upperLimit;
	/**
	 * 单位（个，项，元等）
	 */
	@TableField(value = "unit")
	private String unit;

	public Double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getHasProduct() {
		return hasProduct;
	}

	public void setHasProduct(Boolean hasProduct) {
		this.hasProduct = hasProduct;
	}

	public Boolean getIsProduct() {
		return isProduct;
	}

	public void setIsProduct(Boolean isProduct) {
		this.isProduct = isProduct;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public Long getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Long projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectRemark() {
		return projectRemark;
	}

	public void setProjectRemark(String projectRemark) {
		this.projectRemark = projectRemark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
