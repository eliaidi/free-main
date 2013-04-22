package com.mkfree.framework.common.constants;

/**
 * sso Server Constants
 * 
 * @author hk
 * 
 *         2012-12-15 下午4:22:04
 */
public class SSOConstants {

	// 网站域名
	public final static String MKFREECOM = "mkfree.com";
	// 请求模式
	public final static String MODEL = "model";
	// 帐号
	public final static String ACCOUNT = "account";
	// 密码
	public final static String PASSWORD = "password";
	// 是否验证成功
	public final static String ISSUCCESS = "isSuccess";
	// 用户ticket(票据)
	public final static String SSO_TICKET = "sso_ticket";
	// 用户
	public final static String SSO_USER = "sso_user";

	public final static int SSO_USER_KEY_TIME = 30 * 60;// 默认半小时
	// session id
	public final static String SSO_SESSIONID = "sso_sessionid";
	// 登录用户cookie存活时间默认为7天
	public final static int COOKIE_LIVE_TIME = 7 * 24 * 60 * 60;

}
