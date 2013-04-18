package com.mkfree.apiservice.service.so;

import com.mkfree.apithrift.SearchResultVO;

public interface SOService {

	/**
	 * 创建索引
	 * 
	 * @return
	 */
	public String createIndex();

	/**
	 * 搜索
	 * 
	 * @param q
	 * @return
	 */
	public SearchResultVO search(String q);
}
