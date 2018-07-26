package com.teamtek.core.ann;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teamtek.admin.sys.entity.SysMenu;

@Component
public class MyProcessor implements BeanPostProcessor {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private final static ConcurrentHashMap<String, Integer> temp = new ConcurrentHashMap<String, Integer>();

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		MenuOrBtnAnnotation menu_ann = AnnotationUtils.findAnnotation(bean.getClass(), MenuOrBtnAnnotation.class);
		if (menu_ann != null) {
			if (temp.containsKey(menu_ann.code())) {
				log.error("------------------菜单code{{}}有重复！！------------", menu_ann.code());
				throw new RuntimeException("菜单code有重复！");
			}
			RequestMapping menu_mapping = AnnotationUtils.findAnnotation(bean.getClass(), RequestMapping.class);
			String menu_url = "";
			if (menu_mapping != null) {
				menu_url = menu_mapping.value()[0];
			}
			SysMenu menu = new SysMenu(menu_ann.menu(), menu_url, menu_ann.code(), SysMenu.MenuType.menu.value());
			menu.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			
			log.info("****菜单:{{}} 初始化*****菜单perms（即code）:{}", menu.getName(), menu.getPerms());
			MenuCacheComponent.menu.put(menu_ann.code(), menu);// 菜单
			temp.put(menu_ann.code(), 1);
			// Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
			Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(bean.getClass());
			if (methods != null && methods.length > 0) {
				List<SysMenu> btns = new ArrayList<>(methods.length);
				for (Method method : methods) {
					MenuOrBtnAnnotation ann = AnnotationUtils.findAnnotation(method, MenuOrBtnAnnotation.class);
					if (null != ann) {
						if (temp.containsKey(ann.code())) {
							log.error("------------------按钮code{{}}有重复！！------------", ann.code());
							throw new RuntimeException("按钮code有重复！");
							// continue;
						}
						// 插入到数据中
						RequestMapping btn_mapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
						String btn_url = "";
						String btn_name = null;
						if (ann.btn() == BtnConstant.default_btn) {
							btn_name = ann.btnTitle();
						} else {
							btn_name = ann.btn().title();
						}
						if (btn_mapping != null) {
							btn_url = btn_mapping.value()[0];
						}
						if (StringUtils.isNoneBlank(btn_url) && StringUtils.isNoneBlank(menu_url)) {
							btn_url = menu_url + btn_url;
						}
						SysMenu btn = new SysMenu(btn_name, btn_url, ann.code(), SysMenu.MenuType.button.value());
						btn.setId(UUID.randomUUID().toString().replaceAll("-", ""));
						btn.setpId(menu.getId());
						btns.add(btn);
						log.info("---------按钮:{{}}初始化------按钮perms（即code）:{}", btn.getName(), btn.getPerms());
						temp.put(ann.code(), 1);
					}
				}
				menu.setChildren(btns);
				;// 菜单下的按钮
			}
		}

		return bean;
	}
}