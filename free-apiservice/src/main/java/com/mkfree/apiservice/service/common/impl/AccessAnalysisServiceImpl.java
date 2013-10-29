package com.mkfree.apiservice.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.AccessAnalysisDao;
import com.mkfree.apiservice.domain.AccessAnalysis;
import com.mkfree.apiservice.service.common.AccessAnalysisService;
import com.mkfree.framework.common.utils.date.TimeUtils;

/**
 * 博客访问分析 业务层
 * 
 * @author oyhk
 * 
 *         2013-10-18 下午5:13:10
 */
@Service("accessAnalysisService")
public class AccessAnalysisServiceImpl implements AccessAnalysisService {
	@Autowired
	private AccessAnalysisDao accessAnalysisDao;

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
	public String save(String jsessionid, String fromUserId, String toUserId, String userIp, String referer, String uri, String browser, String os) {
		AccessAnalysis entity = new AccessAnalysis();
		entity.setJsessionid(jsessionid);
		entity.setFromUserId(fromUserId);
		entity.setToUserId(toUserId);
		entity.setUserIp(userIp);
		entity.setReferer(referer);
		entity.setUri(uri);
		entity.setBrowser(browser);
		entity.setOs(os);
		entity.setCreateTime(TimeUtils.getVPSTime());
		return accessAnalysisDao.save(entity).getId();
	}

	@Override
	public Map<String, Long> findBlogAccessCount(String userId) {
		Map<String, Long> blogAccessCount = new HashMap<String, Long>();
		long todayCount = accessAnalysisDao.findBlogTodayAcceccCountByUserId(userId);
		long yesterdayCount = accessAnalysisDao.findBlogYesterdayAcceccCountByUserId(userId);
		long weekCount = accessAnalysisDao.findBlogWeekAcceccCountByUserId(userId);
		long monthCount = accessAnalysisDao.findBlogMonthAcceccCountByUserId(userId);
		long allCount = accessAnalysisDao.findBlogAllAcceccCountByUserId(userId);
		blogAccessCount.put("todayCount", todayCount);
		blogAccessCount.put("yesterdayCount", yesterdayCount);
		blogAccessCount.put("weekCount", weekCount);
		blogAccessCount.put("monthCount", monthCount);
		blogAccessCount.put("allCount", allCount);
		return blogAccessCount;
	}

}
