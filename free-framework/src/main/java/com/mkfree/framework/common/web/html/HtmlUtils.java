package com.mkfree.framework.common.web.html;

/**
 * html 工具类(继承了org.springframework.web.util.HtmlUtils)拥有更强大的功能
 * 
 * @author hk
 * 
 *         2012-12-28 下午8:42:50
 */
public class HtmlUtils extends org.springframework.web.util.HtmlUtils {

	/**
	 * 过虑html代码,获取内容,并且可以截取所需要的长度
	 * 
	 * @param input
	 *            html内容
	 * @param length
	 *            长度
	 * @return
	 */
	public static String filterHtmlCode(String input, int length) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		str = str.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
		int len = str.length();
		if (len <= length) {
			return str;
		} else {
			str = str.substring(0, length);
			str += "......";
		}
		return str;
	}

	/**
	 * 过虑html代码,获取内容
	 * 
	 * @param input
	 *            html内容
	 * @return
	 */
	public static String filterHtmlCode(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// 去掉所有html元素,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
		str = str.replaceAll("[(/>)<]", "");
		str = str.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "");
		return str;
	}

	/**
	 * 防止xss攻击..暂时只支持script的脚本,不断完善吧.
	 * 
	 * @param input
	 *            html代码
	 * @return
	 */
	public static String avoidXSS(String input) {
		input = input.replaceAll("</.*?script.*?>", "&lt;/script&gt");
		input = input.replaceAll("<.*?script.*?>", "&lt;script&gt");
		return input;
	}
}
