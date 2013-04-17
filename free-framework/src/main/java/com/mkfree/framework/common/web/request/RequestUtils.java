package com.mkfree.framework.common.web.request;

import javax.servlet.http.HttpServletRequest;

/**
 * request 工具类
 * @author oyhk
 *
 * 2012-12-18下午5:15:34
 */
public class RequestUtils {

	/**
	 * 检查参数值是否为空
	 * @param paramName
	 * @return
	 */
	public static boolean checkParamValueIsBlank(HttpServletRequest req,String paramName){
		String value = getParamValue(req,paramName);
		if(value != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取整型的值,如果获取不了,返回-1
	 * @param req
	 * @param name
	 * @return
	 */
	public static int getParamIntValue(HttpServletRequest req, String name){
		String value = getParamValue(req, name);
		if(value != null){
			return Integer.parseInt(value);
		}
		return -1;
	}
	
	/**
	 * 通过参数名,去获取请求参数值
	 * @param name
	 * @return
	 */
	public static String getParamValue(HttpServletRequest req, String name){
		String value =req.getParameter(name);
		if(value != null){
			return value;
		}
		return null;
	}
}
