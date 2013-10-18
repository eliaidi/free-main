package com.mkfree.apiservice.service.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.AccessAnalysisDao;
import com.mkfree.apiservice.domain.AccessAnalysis;
import com.mkfree.apiservice.service.common.AccessAnalysisService;

/**
 * 博客访问分析 业务层
 * 
 * @author oyhk
 * 
 *         2013-10-18 下午5:13:10
 */
@Service("accessAnalysisService")
public class AccessAnalysisServiceImpl implements AccessAnalysisService {

	public String save(AccessAnalysis entity) {
		return accessAnalysisDao.save(entity).getId();
	}

	@Autowired
	private AccessAnalysisDao accessAnalysisDao;
}
