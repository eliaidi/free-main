package com.mkfree.framework.sso;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mkfree.apiclient.sso.SSOClient;
import com.mkfree.apithrift.vo.SSOUserVO;
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

	public static final String JSESSIONURL = "jsessionurl:";

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		this.ssoFilterService(request, response);
		this.addVisitorArtifactIdToSession(request, response);
		chain.doFilter(request, response);
	}

	/**
	 * 添加访问者唯一标识到session
	 * 
	 * @param request
	 * @param response
	 */
	private void addVisitorArtifactIdToSession(HttpServletRequest request, HttpServletResponse response) {
		if (!SessionUtils.isExist(request, SSOConstants.JSESSIONID)) {
			SessionUtils.addSession(request, SSOConstants.JSESSIONID, SSOConstants.JSESSIONID + ":" + UUID.randomUUID());
		}
		String uri = request.getRequestURL().toString();
		if (!SessionUtils.isExist(request, SSOFilter.JSESSIONURL + uri)) {
			SessionUtils.addSession(request, SSOFilter.JSESSIONURL + uri, uri);
		}
	}

	/**
	 * sso 过虑的核心流程
	 * 
	 * @param request
	 */
	private void ssoFilterService(HttpServletRequest request, HttpServletResponse response) {
		// cookie sso_ticket,sessionid 存在,那么user session 都会存在,那么不作处理,飘过
		if (this.checkTicketAndSessionIdisExist(request))
			return;
		// 当user session 存在,证明已经登录 ,不作处理,飘过
		if (!this.checkUserSessionisExist(request))
			return;
		// 当sessionid 存在,执行下面处理
		if (!this.checkCookieSessionIdisExist(request)) {
			this.userSessionidExist(request, response);
		}
	}

	/**
	 * 当cookie sessionid时,通过sessionid去Redis 获取数据,作Session 共享
	 * 
	 * @param request
	 */
	private void userSessionidExist(HttpServletRequest request, HttpServletResponse response) {

		String sessionId = CookieUtils.getCookieValue(request, SSOConstants.SSO_SESSIONID);
		byte[] objectBytes = redisService.get(sessionId.getBytes());
		SSOUserVO user = (SSOUserVO) SerializeUtil.unserialize(objectBytes);
		if (user == null) {// redis user session 已经失效
			if (!CookieUtils.checkCookieValueIsNull(request, SSOConstants.SSO_TICKET)) {
				String ticketValue = CookieUtils.getCookieValue(request, SSOConstants.SSO_TICKET);
				this.loginByTicketValue(ticketValue, request, response);
			}
		} else {
			request.getSession().setAttribute(SSOConstants.SSO_USER, user);
		}

	}

	/**
	 * 如果用户的ticket存在,那么我获取ticket自动帮他登录,至于成功于否,看服务器检查ticket是否可以有效
	 * 
	 * @param ticketValue
	 * @param request
	 * @param response
	 */
	private void loginByTicketValue(String ticketValue, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 通过ticket向服务器,请求登录
			SSOUserVO ssoUserVO = SSOClient.loginByTicket(ticketValue);
			if (ssoUserVO != null) {
				if (ssoUserVO.getSuccess() == 1) {// 登录成功
					// sessionid用于做session共享
					String sessionid = ssoUserVO.getAccount() + new Random().nextLong();
					byte[] sso_user_key = sessionid.getBytes("utf-8");
					byte[] sso_user_value = SerializeUtil.serialize(ssoUserVO);
					redisService.set(sso_user_key, sso_user_value, SSOConstants.SSO_USER_KEY_TIME);
					Cookie cookieTicket = new Cookie(SSOConstants.SSO_TICKET, ssoUserVO.getTicketValue());
					cookieTicket.setDomain(SSOConstants.MKFREECOM);
					cookieTicket.setPath("/");
					cookieTicket.setMaxAge(SSOConstants.COOKIE_LIVE_TIME);// 存活时间默认7天
					response.addCookie(cookieTicket);
					Cookie cookieSessionId = new Cookie(SSOConstants.SSO_SESSIONID, sessionid);
					cookieSessionId.setDomain(SSOConstants.MKFREECOM);
					cookieSessionId.setPath("/");
					cookieSessionId.setMaxAge(SSOConstants.COOKIE_LIVE_TIME);
					response.addCookie(cookieSessionId);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
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
		if (CookieUtils.checkCookieValueIsNull(request, SSOConstants.SSO_SESSIONID)) {
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
		if (CookieUtils.checkCookieValueIsNull(request, SSOConstants.SSO_TICKET) && CookieUtils.checkCookieValueIsNull(request, SSOConstants.SSO_SESSIONID)) {
			request.getSession().removeAttribute(SSOConstants.SSO_USER);
			return true;
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {

	}

	private final RedisService redisService = (RedisService) SpringUtil.getBean("redisService");
}
