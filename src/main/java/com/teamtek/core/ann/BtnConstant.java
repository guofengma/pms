package com.teamtek.core.ann;

/**
 * 公共 常用按钮常量  可以补充
 * 注：对于按钮名字  复用率比较高的  可以 添加到此 枚举类中
 *    
 * @author Administrator
 *
 */
public enum BtnConstant {

	add("增加"),
	save("保存"),
	
	del("删除"),
	del_batch("批量删除"),
	
	edit("编辑"),
	modify("修改"), 
	update("更新"),
	
	select("查询"), 
	default_btn("默认按钮");
	private final String title;

	BtnConstant(String title) {
		this.title = title;
	}
	public String title() {
		return title;
	}
}
