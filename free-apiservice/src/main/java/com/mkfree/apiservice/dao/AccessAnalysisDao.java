package com.mkfree.apiservice.dao;

import com.mkfree.apiservice.domain.AccessAnalysis;

/**
 * 博客访问分析 数据层操作
 * 
 * @author oyhk
 * 
 *         2013-10-18 下午4:46:41
 */
public interface AccessAnalysisDao {

	public AccessAnalysis save(AccessAnalysis entity);

	/**
	 * 查找今天博客访问次数
	 * 
	 * @param userId
	 * @return
	 */
	public long findBlogTodayAcceccCountByUserId(String userId);

	/**
	 * 查找昨天博客访问次数
	 * 
	 * @param userId
	 * @return
	 */
	public long findBlogYesterdayAcceccCountByUserId(String userId);

	/**
	 * 查找周博客访问次数
	 * 
	 * @param userId
	 * @return
	 */
	public long findBlogWeekAcceccCountByUserId(String userId);

	/**
	 * 查找月博客访问次数
	 * 
	 * @param userId
	 * @return
	 */
	public long findBlogMonthAcceccCountByUserId(String userId);

	/**
	 * 查找所有博客访问次数
	 * 
	 * @param userId
	 * @return
	 */
	public long findBlogAllAcceccCountByUserId(String userId);
}
