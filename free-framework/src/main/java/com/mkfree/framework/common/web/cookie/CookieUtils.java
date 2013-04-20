package com.mkfree.framework.common.web.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public static boolean checkCookieIsExist(HttpServletRequest req, String cookieName) {
		String value = CookieUtils.getCookieValue(req, cookieName);
		if (value != null && value != "") {
			return true;
		}
		return false;
	}

	/**
	 * 通过cookieName清空cookie
	 * 
	 * @param req
	 * @param res
	 * @param cookieName
	 */
	public static void flushCookie(HttpServletRequest req, HttpServletResponse res, String cookieName, String domain) {
		flushCookie(req, res, cookieName, domain, null);
	}

	/**
	 * 通过cookieName清空cookie
	 * 
	 * @param req
	 * @param res
	 * @param cookieName
	 *            cookie名
	 * @param domain
	 *            域名
	 * @param path
	 *            cookie 路径
	 */
	public static void flushCookie(HttpServletRequest req, HttpServletResponse res, String cookieName, String domain, String path) {
		if (checkCookieValueIsNull(req, cookieName))
			return;
		Cookie cookie = getCookie(req, cookieName);
		cookie.setValue(null);
		cookie.setDomain(domain);
		cookie.setMaxAge(-1);// 清空cookie生命
		if (path != null) {
			cookie.setPath(path);
		}
		res.addCookie(cookie);

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
