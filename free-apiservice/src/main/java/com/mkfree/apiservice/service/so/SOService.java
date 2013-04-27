package com.mkfree.apiservice.service.so;

import com.mkfree.apithrift.vo.SearchResultVO;

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
	public SearchResultVO search(String q, int startIndex);

	/**
	 * 通过索引类型删除,这类型下的所有索引
	 * 
	 * @param type
	 * @return
	 */
	public int deleteIndexByType(String indexName, String type);
}
