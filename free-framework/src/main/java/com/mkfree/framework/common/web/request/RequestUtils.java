package com.mkfree.framework.common.web.request;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * request 工具类
 * 
 * @author oyhk
 * 
 *         2012-12-18下午5:15:34
 */
public class RequestUtils {

	/**
	 * 检查参数值是否为空
	 * 
	 * @param paramName
	 * @return
	 */
	public static boolean checkParamValueIsBlank(HttpServletRequest req, String paramName) {
		String value = getParamValue(req, paramName);
		if (value != null) {
			return true;
		}
		return false;
	}

	/**
	 * 获取整型的值,如果获取不了,返回-1
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	public static int getParamIntValue(HttpServletRequest req, String name) {
		String value = getParamValue(req, name);
		if (value != null) {
			return Integer.parseInt(value);
		}
		return -1;
	}

	/**
	 * 通过参数名,去获取请求参数值
	 * 
	 * @param name
	 * @return
	 */
	public static String getParamValue(HttpServletRequest req, String name) {
		String value = req.getParameter(name);
		if (value != null) {
			return value;
		}
		return null;
	}

	/**
	 * 获取访问来源
	 * 
	 * @param req
	 * @return
	 */
	public static String getReferer(HttpServletRequest req) {
		return req.getHeader("referer");
	}

	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}

}
