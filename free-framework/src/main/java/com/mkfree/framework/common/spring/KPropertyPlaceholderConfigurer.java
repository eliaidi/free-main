package com.mkfree.framework.common.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 扩展spring PropertyPlaceholderConfigurer 用于获取加载配置文件属性值
 * 
 * @author hk
 *
 * 2012-11-26 下午8:58:40
 */
public class KPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	private static Map<String,Object> ctxPropertiesMap;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		ctxPropertiesMap = new HashMap<String,Object>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	/**
	 * 通过属性名去获取属性值（int）
	 * @param name
	 * @return
	 */
	public static int getIntValue(String name){
		Object obj = getValue(name);
		if(obj != null){
			int value = Integer.parseInt((String) obj);
			return value;
		}
		return 0;
	}
	
	/**
	 * 通过属性名去获取属性值（String）
	 * @param name
	 * @return
	 */
	public static String getStringValue(String name){
		Object obj = getValue(name);
		if(obj != null){
			return (String) obj;
		}
		return null;
	}
	
	/**
	 * 获取加载的配置文件属性值
	 * @param name
	 * @return
	 */
	private static Object getValue(String name) {
		if (ctxPropertiesMap.size() <= 0) 
			return null; 
		return ctxPropertiesMap.get(name);
	}
	
}
