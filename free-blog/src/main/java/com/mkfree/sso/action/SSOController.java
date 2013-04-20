package com.mkfree.sso.action;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mkfree.apiclient.sso.SSOClient;
import com.mkfree.apithrift.SSOUserVO;
import com.mkfree.framework.common.constants.BlogConstants;
import com.mkfree.framework.common.constants.SSOConstants;
import com.mkfree.framework.common.redis.RedisService;
import com.mkfree.framework.common.utils.SerializeUtil;
import com.mkfree.framework.common.web.cookie.CookieUtils;

/**
 * sso前端控制器,基于thrift服务协议开发
 * 
 * @author hk
 * 
 *         2012-11-3 上午11:33:41
 */
@Controller
public class SSOController {

	/**
	 * 用户登录
	 * 
	 * @param account
	 * @param password
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/sso/login", method = RequestMethod.POST)
	public String login(@RequestParam("account") String account, @RequestParam("password") String password, HttpServletRequest req, HttpServletResponse res) {
		try {
			SSOUserVO ssoUserVO = SSOClient.login(account, password);
			if (ssoUserVO != null) {
				if (ssoUserVO.getSuccess() == 1) {
					// sessionid用于做session共享
					String sessionid = ssoUserVO.getAccount() + new Random().nextLong();
					byte[] sso_user_key = sessionid.getBytes("utf-8");
					byte[] sso_user_value = SerializeUtil.serialize(ssoUserVO);
					redisService.set(sso_user_key, sso_user_value, 1800);
					Cookie cookie = new Cookie(SSOConstants.SSO_TICKET, ssoUserVO.getTicketValue());
					cookie.setDomain(SSOConstants.MKFREECOM);
					cookie.setPath("/");
					res.addCookie(cookie);
					Cookie cookieSessionId = new Cookie(SSOConstants.SESSIONID, sessionid);
					cookieSessionId.setDomain(SSOConstants.MKFREECOM);
					cookieSessionId.setPath("/");
					res.addCookie(cookieSessionId);
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String backUrl = req.getHeader("referer");
		return "redirect:" + backUrl;
	}

	/**
	 * 用户退出登录
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	@RequestMapping(value = "/sso/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().invalidate();// 清空Session
		CookieUtils.flushCookie(req, res, SSOConstants.SESSIONID, SSOConstants.MKFREECOM, "/");
		CookieUtils.flushCookie(req, res, SSOConstants.SSO_TICKET, SSOConstants.MKFREECOM, "/");
		return "redirect:" + BlogConstants.MKFREE_BLOG_URL;
	}

	@Autowired
	private RedisService redisService;
}