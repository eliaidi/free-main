package com.mkfree.apiservice.service.so;

import java.util.List;

import com.mkfree.apithrift.BlogPostVO;

public interface SOService {

	/**
	 * 创建索引
	 * 
	 * @return
	 */
	public String createIndex();

	public List<BlogPostVO> search(String q);
}
