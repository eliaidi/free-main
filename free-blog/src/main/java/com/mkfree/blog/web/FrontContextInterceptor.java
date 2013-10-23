package com.mkfree.blog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mkfree.apiclient.common.AccessAnalysisClient;
import com.mkfree.framework.common.constants.SSOConstants;
import com.mkfree.framework.common.web.request.RequestUtils;
import com.mkfree.framework.common.web.session.SessionUtils;
import com.mkfree.framework.sso.SSOFilter;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 暂时做博客访问分析
 * 
 * @author hk
 * 
 *         2012-12-19 下午11:09:00
 */
public class FrontContextInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		String userIp = RequestUtils.getIpAddr(request);
		String uri = request.getRequestURL().toString();
		// if (!SessionUtils.isExist(request, SSOFilter.JSESSIONURL + uri)) {
		// SessionUtils.addSession(request, SSOFilter.JSESSIONURL + uri, uri);
		if (!userIp.equals("127.0.0.1")) {
			if (uri.indexOf("posts") == -1) {
				String jsessinid = (String) SessionUtils.getSessionValue(request, SSOConstants.JSESSIONID);
				String fromUserId = "-1";// 由于现在没有注册用户,所有浏览用户默认都为-1
				String toUserId = null;
				String referer = RequestUtils.getReferer(request);
				String userAgent = request.getHeader("user-agent");
				// 获取用户的代理,浏览器跟操作系统
				UserAgent ua = UserAgent.parseUserAgentString(userAgent);
				String os = ua.getOperatingSystem().getName();
				String browser = ua.getBrowser().getName();
				AccessAnalysisClient.saveAccessAnalysis(jsessinid, fromUserId, toUserId, userIp, referer, uri, browser, os);
			}
		}
		// }
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	//
	// @Autowired
	// @Qualifier("redisService")
	// private RedisService redisService;
}
