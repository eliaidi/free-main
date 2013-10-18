package com.mkfree.blog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mkfree.apiclient.common.AccessAnalysisClient;
import com.mkfree.framework.common.redis.RedisService;
import com.mkfree.framework.common.web.session.SessionUtils;
import com.mkfree.framework.sso.SSOFilter;

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
		String visitorArtifactId = (String) SessionUtils.getSessionValue(request, SSOFilter.visitorArtifactId);
		String userId = "-1";
		String userIp = "127.0.0.1";
		String referer = "http://blog.mkfree.com/";
		String uri = "http://blog.mkfree.com/posts/32";
		int type = 1;
		AccessAnalysisClient.saveAccessAnalysis(visitorArtifactId, userId, userIp, referer, uri, type);
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Autowired
	@Qualifier("redisService")
	private RedisService redisService;
}
