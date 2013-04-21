package com.mkfree.framework.common.constants;

import com.mkfree.framework.common.spring.KPropertyPlaceholderConfigurer;

/**
 * 博客常量
 * 
 * @author hk
 * 
 *         2012-12-28 下午11:02:50
 */
public class BlogConstants {

	// 静态域名url
	public final static String MKFREE_STATIC_URL = KPropertyPlaceholderConfigurer.getStringValue("mkfree_static_url");
	// 博客域名url
	public final static String MKFREE_BLOG_URL = KPropertyPlaceholderConfigurer.getStringValue("mkfree_blog_url");
	// 错误页面url
	public final static String ERROR_HTML = "html/common/error.html";
	// 没登录用户的ID值,默认是-1
	public final static String noLoginUserId = "-1";
	// 游客
	public final static String noLoginUserNick = "游客";
	// 有些用户回复内容,可能不想给别人知道他的姓名,定义一个匿名用户ID
	public final static String anonymityUserId = "0";
	// 有些用户回复内容,可能不想给别人知道他的姓名,定义一个匿名网友
	public final static String anonymityNetFriend = "匿名网友";
}
