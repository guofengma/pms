package com.teamtek.admin.biz.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 机构（部门）积分
 * </p>
 *
 * @author syj
 * @since 2018-03-01 08:45:48
 */
@TableName("biz_dept_scores")
public class BizDeptScores implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 部门id
	 */
	@TableField(value = "dept_id")
	private Long deptId;

	/**
	 * 
	 */
	@TableField(value = "project_type_id")
	private Long projectTypeId;

	/**
	 * 月份
	 */
	@TableField(value = "curr_month")
	private Date currMonth;

	/**
	 * 该月份已完成
	 */
	@TableField(value = "finish_score")
	private Double finishScore;

	/**
	 * 编辑 当月积分
	 */
	@TableField(value = "curr_score")
	private Double currScore;
	/**
	 * 该月份总的(等于 curr_socre+score_from_last）
	 */
	@TableField(value = "total_score")
	private Double totalScore;
	/**
	 * 从上月结余的积分
	 */
	@TableField(value = "score_from_last")
	private Double scoreFromLast;
	/**
	 * 结余到下月的积分
	 */
	@TableField(value = "score_to_next")
	private Double scoreToNext;
	/**
	 * 
	 */
	@TableField(value = "last_edit_time")
	private Date lastEditTime;

	public Double getCurrScore() {
		return currScore;
	}

	public void setCurrScore(Double currScore) {
		this.currScore = currScore;
	}

	public Double getScoreFromLast() {
		return scoreFromLast;
	}

	public void setScoreFromLast(Double scoreFromLast) {
		this.scoreFromLast = scoreFromLast;
	}

	public Double getScoreToNext() {
		return scoreToNext;
	}

	public void setScoreToNext(Double scoreToNext) {
		this.scoreToNext = scoreToNext;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(Long projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public Date getCurrMonth() {
		return currMonth;
	}

	public void setCurrMonth(Date currMonth) {
		this.currMonth = currMonth;
	}

	public Double getFinishScore() {
		return finishScore;
	}

	public void setFinishScore(Double finishScore) {
		this.finishScore = finishScore;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
