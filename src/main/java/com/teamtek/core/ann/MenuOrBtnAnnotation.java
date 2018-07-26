package com.teamtek.core.ann;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 自定义注解  用于解析菜单url  按钮url
 * @author Administrator
 *
 */
@Target({ ElementType.METHOD,ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MenuOrBtnAnnotation {
	/**
	 * 菜单名称
	 * @return
	 */
	String menu() default "";
	/**
	 * 按钮名称（优先级 比 btnTitle高）
	 * 按钮的化可以用 @see com.teamtek.core.ann.BtnValConstant  也可自定义
	 * 
	 * @return
	 */
	BtnConstant btn() default BtnConstant.default_btn;//按钮
	
	/**
	 * 按钮  当btn存在 不为 def_btn时  以btn为主
	 * 当 btn为def_btn时  以 btnTitle为主
	 * @return
	 */
	String btnTitle() default "";
	/**
	 * 菜单和按钮code  必须唯一
	 * @return
	 */
	String code() default "";//
	
}
