package com.mkfree.blog.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mkfree.framework.common.redis.RedisService;

/**
 * 暂时做sso filter 到时候一定要更改
 * 
 * @author hk
 * 
 *         2012-12-19 下午11:09:00
 */
public class FrontContextInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		/*
		 * if(CookieUtils.checkCookieValueIsNull(request,
		 * SSOConstants.SSO_TICKET) &&
		 * CookieUtils.checkCookieValueIsNull(request,
		 * SSOConstants.SESSIONID)){//cookie -->> sso_ticket,sessionid不存在
		 * request.getSession().removeAttribute(SSOConstants.SSO_USER); }else{
		 * SysUser userSession = (SysUser) SessionUtils.getSessionValue(request,
		 * SSOConstants.SSO_USER);//user session 存在 if(userSession != null){
		 * }else{ if(CookieUtils.checkCookieValueIsNull(request,
		 * SSOConstants.SESSIONID)){//user sessionid 不存在
		 * System.out.println("user sessionid 不存在");
		 * if(CookieUtils.checkCookieValueIsNull(request,
		 * SSOConstants.SSO_TICKET)){ System.out.println("数据库查询验证"); }
		 * }else{//cookie user sessionId 存在 String sessionId =
		 * CookieUtils.getCookieValue(request,SSOConstants.SESSIONID); byte[]
		 * objectBytes = redisService.get(sessionId.getBytes()); SysUser user =
		 * (SysUser) SerializeUtil.unserialize(objectBytes); if(user ==
		 * null){//redis user session 已经失效
		 * if(CookieUtils.checkCookieValueIsNull(request,
		 * SSOConstants.SSO_TICKET)){ System.out.println("数据库查询验证"); } }else{
		 * request.getSession().setAttribute(SSOConstants.SSO_USER, user); }
		 * 
		 * } } }
		 */return true;
	}

	@Autowired
	@Qualifier("redisService")
	private RedisService redisService;
}
