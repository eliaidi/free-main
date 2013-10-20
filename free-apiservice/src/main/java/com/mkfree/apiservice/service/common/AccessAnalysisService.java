package com.mkfree.apiservice.service.common;

import java.util.Map;

/**
 * 博客访问分析 业务层
 * 
 * @author oyhk
 * 
 *         2013-10-18 下午5:13:10
 */
public interface AccessAnalysisService {
	/**
	 * 保存一个访问分析日志
	 * 
	 * @param userSession 没有关闭浏览器的会话(唯一标识)
	 * @param blogUserId
	 * @param userIp
	 * @param referer
	 * @param uri
	 * @param type
	 * @return id
	 */
	public String save(String jsessionid, String fromUserId, String toUserId, String userIp, String referer, String uri, String browser, String os);

	/**
	 * 通过userid查找博客用户的访问次数(日,昨日,周,月,一共)
	 * 
	 * @param userId
	 * @return
	 */
	public Map<String, Long> findBlogAccessCount(String userId);
}
