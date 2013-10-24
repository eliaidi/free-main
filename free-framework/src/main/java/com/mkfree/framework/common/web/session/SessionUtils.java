package com.mkfree.framework.common.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.Assert;

/**
 * session 工具类
 * 
 * @author oyhk
 * 
 *         2012-12-17下午8:33:58
 */
public class SessionUtils {

	/**
	 * 通过名称去获取客户端session
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	public static Object getSessionValue(HttpServletRequest req, String name) {
		Assert.notNull(req, "Request must not be null");
		Object value = req.getSession().getAttribute(name);
		if (value != null) {
			return value;
		}
		return null;
	}

	/**
	 * 判断session 是否存在
	 * 
	 * @param req
	 * @param name
	 * @return 存在返回true 否则
	 */
	public static boolean isExist(HttpServletRequest req, String name) {
		Object value = req.getSession().getAttribute(name);
		if (value == null) {
			return false;
		}
		return true;
	}

	/**
	 * 添加一个属性值到session中
	 * 
	 * @param req
	 * @param name
	 * @param value
	 */
	public static void addSession(HttpServletRequest req, String name, String value) {
		req.getSession().setAttribute(name, value);
	}

}
