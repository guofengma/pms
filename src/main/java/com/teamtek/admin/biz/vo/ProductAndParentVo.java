package com.teamtek.admin.biz.vo;

import java.io.Serializable;

public class ProductAndParentVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private String parentProjectName;

	private String other;

	public String getProductName() {
		return productName == null ? null : productName.trim();
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getParentProjectName() {
		return parentProjectName == null ? null : parentProjectName.trim();
	}

	public void setParentProjectName(String parentProjectName) {
		this.parentProjectName = parentProjectName;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
