package com.mkfree.framework.sso;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mkfree.apithrift.SSOUserVO;
import com.mkfree.framework.common.constants.SSOConstants;
import com.mkfree.framework.common.redis.RedisService;
import com.mkfree.framework.common.spring.util.SpringUtil;
import com.mkfree.framework.common.utils.SerializeUtil;
import com.mkfree.framework.common.web.cookie.CookieUtils;
import com.mkfree.framework.common.web.session.SessionUtils;

/**
 * sso 服务器的过虑器,验证现在个sso流程(不断强化,修改)
 * 
 * @author hk
 * 
 *         2012-12-29 上午4:05:44
 */
public class SSOFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		this.ssoFilterService(request);

		chain.doFilter(request, response);
	}

	/**
	 * sso 过虑的核心流程
	 * 
	 * @param request
	 */
	private void ssoFilterService(HttpServletRequest request) {
		// cookie sso_ticket,sessionid 存在,那么user session 都会存在,那么不作处理,飘过
		if (this.checkTicketAndSessionIdisExist(request))
			return;
		// 当user session 存在,证明已经登录 ,不作处理,飘过
		if (!this.checkUserSessionisExist(request))
			return;
		// 当sessionid 存在,执行下面处理
		if (!this.checkCookieSessionIdisExist(request)) {
			this.userSessionidExist(request);
		}
	}

	/**
	 * 当cookie sessionid时,通过sessionid去Redis 获取数据,作Session 共享
	 * 
	 * @param request
	 */
	private void userSessionidExist(HttpServletRequest request) {
		String sessionId = CookieUtils.getCookieValue(request, SSOConstants.SESSIONID);
		byte[] objectBytes = redisService.get(sessionId.getBytes());
		SSOUserVO user = (SSOUserVO) SerializeUtil.unserialize(objectBytes);
		if (user == null) {// redis user session 已经失效
			if (CookieUtils.checkCookieValueIsNull(request, SSOConstants.SSO_TICKET)) {
				System.out.println("数据库查询验证");
			}
		} else {
			request.getSession().setAttribute(SSOConstants.SSO_USER, user);
		}
	}

	/**
	 * 检查cookie sessionid 是否存在
	 * 
	 * 如果不存在,那么进一步检查cookie ticket ,再做下一步操作
	 * 
	 * @param request
	 * @return 为空返回true;
	 */
	private boolean checkCookieSessionIdisExist(HttpServletRequest request) {
		// cookie sessionid null
		if (CookieUtils.checkCookieValueIsNull(request, SSOConstants.SESSIONID)) {
			return true;
		}
		return false;
	}

	/**
	 * 检查cookie ticket 是否存在
	 * 
	 * @param request
	 * @return 不存在返回true
	 */
	private boolean checkCookieTicketisExist(HttpServletRequest request) {
		if (CookieUtils.checkCookieValueIsNull(request, SSOConstants.SSO_TICKET)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断httpSession 中 sso_user session 是否存在
	 * 
	 * @param request
	 * @return 为空返回 true
	 */
	private boolean checkUserSessionisExist(HttpServletRequest request) {
		SSOUserVO userSession = (SSOUserVO) SessionUtils.getSessionValue(request, SSOConstants.SSO_USER);
		if (userSession == null) {
			return true;
		} else {
			System.out.println("userSession exist 那么我不做不处理");
		}

		return false;
	}

	/**
	 * 检查cookie ticket sessionid 是否为空
	 * 
	 * @param request
	 * @return 为空返回true
	 */
	private boolean checkTicketAndSessionIdisExist(HttpServletRequest request) {
		if (CookieUtils.checkCookieValueIsNull(request, SSOConstants.SSO_TICKET)
				&& CookieUtils.checkCookieValueIsNull(request, SSOConstants.SESSIONID)) {
			request.getSession().removeAttribute(SSOConstants.SSO_USER);
			return true;
		}
		return false;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {

	}

	private RedisService redisService = (RedisService) SpringUtil.getBean("redisService");
}
