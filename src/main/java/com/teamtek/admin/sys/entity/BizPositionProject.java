package com.teamtek.admin.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.teamtek.core.vo.Record;

/**
 * <p>
 * 岗位 项目 中间表
 * </p>
 *
 * @author syj
 * @since 2018-03-06 14:14:06
 */
@TableName("biz_position_project")
public class BizPositionProject implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public enum Type {
		base(0, "基础项目"), 
		link(1, "联动项目"), 
		;
		private final int value;

		private final String title;

		private Type(int value, String title) {
			this.value = value;
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		public int getValue() {
			return value;
		}

		public static Type getEnumByValue(int value) {
			for (Type e : Type.values()) {
				if (e.getValue() == value) {
					return e;
				}
			}
			return null;
		}

		public static String getTitleByValue(int value) {
			Type e = getEnumByValue(value);
			return e == null ? null : e.title;
		}
		public static List<Record> getRecordList() {
			List<Record> status=new ArrayList<>(BizPositionProject.Type.values().length);
			Record record=null;
			for(Type s:BizPositionProject.Type.values()){
				record = new Record();
				record.put(s.getValue()+"", s.getTitle());
				status.add(record);
			}
			return status;
		}
		
		

	}

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 岗位id
	 */
	@TableField(value="position_id")
	private Long positionId;

	/**
	 * 项目id（基础项目）
	 */
	@TableField(value="project_id")
	private Long projectId;

	/**
	 *  @see com.teamtek.admin.biz.entity.BizPositionProject.Type
	 */
	private Integer type;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
