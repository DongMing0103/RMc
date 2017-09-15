package com.hd.kzscrm.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取ApplicationContext,调用方法SpringContextUtil.getContext();
 * @author 黄霄仪
 *
 */
public class AppUtil implements ApplicationContextAware {
	//SPRING的上下文文件，spring/ApplicationContext.xml
	private static ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		applicationContext=context;
	}
	
	/**
	 * 获取SPRING的上下文
	 * 
	 * @return
	 */
	public static ApplicationContext getContext(){
		return applicationContext;
	}
	/**
	 * 根据Bean的class文件获取Bean对象
	 * @param cls
	 * @return
	 */
	public static <T> T getBean(Class<T> cls){
		return applicationContext.getBean(cls);
	}
	
	/**
	 * 根据BeanId获取Bean对象
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId){
		return applicationContext.getBean(beanId);
	}
	

}
