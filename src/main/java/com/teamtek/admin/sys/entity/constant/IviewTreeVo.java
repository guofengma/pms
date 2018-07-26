package com.teamtek.admin.sys.entity.constant;

import java.io.Serializable;
import java.util.List;

/**
 * iview ztree bean
 * 
 * @author syj
 * @createTime：2018年2月11日 下午3:12:32
 */
public class IviewTreeVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;// 部门 id
	private String title;
	private Boolean expand = Boolean.TRUE;
	private Boolean checked = Boolean.FALSE;
	private Boolean disableCheckbox = Boolean.FALSE;
	private Boolean disabled = Boolean.FALSE;

	private Long recordId;// 部门 积分 的id
	private List<IviewTreeVo> children;

	// 一下字段用于 积分管理中机构对应的积分
	private Double finishScore;

	private Double currScore;
	/**
	 * 该月份总的(等于 curr_socre+score_from_last）
	 */
	private Double totalScore;
	/**
	 * 从上月结余的积分
	 */
	private Double scoreFromLast;
	/**
	 * 结余到下月的积分
	 */
	private Double scoreToNext;

	public Double getCurrScore() {
		return currScore;
	}

	public Boolean getDisableCheckbox() {
		return disableCheckbox;
	}

	public void setDisableCheckbox(Boolean disableCheckbox) {
		this.disableCheckbox = disableCheckbox;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
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

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getExpand() {
		return expand;
	}

	public void setExpand(Boolean expand) {
		this.expand = expand;
	}

	public List<IviewTreeVo> getChildren() {
		return children;
	}

	public void setChildren(List<IviewTreeVo> children) {
		this.children = children;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

}
