package com.mkfree.framework.common.web.session;

import javax.servlet.http.HttpServletRequest;

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

}
