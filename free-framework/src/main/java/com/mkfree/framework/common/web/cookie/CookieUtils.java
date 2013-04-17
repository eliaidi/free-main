package com.mkfree.framework.common.web.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie 工具类
 * 
 * @author hk
 * 
 *         2012-12-16 下午3:05:21
 */
public class CookieUtils {

	/**
	 * 判断cookie 是否存在
	 * 
	 * @param req
	 * @param name
	 * @return
	 */
	public static boolean checkCookieIsExist(HttpServletRequest req, String name) {
		String value = CookieUtils.getCookieValue(req, name);
		if (value != null && value != "") {
			return true;
		}
		return false;
	}

	/**
	 * 通过cookieName 获取cookie
	 * 
	 * @param cookieName
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest req, String cookieName) {
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)) {
					return cookies[i];
				}
			}
		}
		return null;
	}

	/**
	 * 通过cookieName 获取cookieValue
	 * 
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest req, String cookieName) {
		Cookie cookie = getCookie(req, cookieName);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	/**
	 * 通过cookie name 判断当前cookie是否为空
	 * 
	 * @param req
	 * @param cookieName
	 * @return 为空返回true
	 */
	public static boolean checkCookieValueIsNull(HttpServletRequest req, String cookieName) {
		Cookie cookie = getCookie(req, cookieName);
		if (cookie != null) {
			if (cookie.getValue() != null && cookie.getValue() != "")
				return false;
		}
		return true;
	}
}
