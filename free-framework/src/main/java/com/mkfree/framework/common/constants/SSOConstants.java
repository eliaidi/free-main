package com.mkfree.framework.common.constants;

import com.mkfree.framework.common.spring.KPropertyPlaceholderConfigurer;

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
	// sso 服务器 ip
	public final static String SSO_IP = KPropertyPlaceholderConfigurer.getStringValue("sso.ip");
	// sso 服务器 prot
	public final static int SSO_PORT = KPropertyPlaceholderConfigurer.getIntValue("sso.port");
	// 用户ticket(票据)
	public final static String SSO_TICKET = "sso_ticket";
	// 用户
	public final static String SSO_USER = "sso_user";
	// session id
	public final static String SESSIONID = "sessionid";

}
