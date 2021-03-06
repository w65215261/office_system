package com.pmcc.soft.core.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

public class ApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;// 声明一个静态变量保存

	@Override
	public void setApplicationContext(ApplicationContext contex)
			throws BeansException {
		applicationContext = contex;
        String n = new String();
	}

	public static Object getBean(String serviceName) {
		return applicationContext.getBean(serviceName);
	}

}