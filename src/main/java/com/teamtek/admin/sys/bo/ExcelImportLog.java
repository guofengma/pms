package com.teamtek.admin.sys.bo;

import java.util.Set;
import java.util.TreeSet;

public class ExcelImportLog {

	private Set<String> warrns;

	private Boolean hasWarn = false;

	private String importMsg = "导入成功";

	public String getImportMsg() {
		return importMsg;
	}

	public void setImportMsg(String importMsg) {
		this.importMsg = importMsg;
	}

	public Set<String> getWarrns() {
		return warrns;
	}

	public void setWarrns(Set<String> warrns) {
		this.warrns = warrns;
	}

	public Boolean getHasWarn() {
		return hasWarn;
	}

	public void setHasWarn(Boolean hasWarn) {
		this.hasWarn = hasWarn;
	}

	public void addWarrn(String msg) {
		if (warrns == null) {
			warrns = new TreeSet<>();
		}
		warrns.add(msg);
	}

}
