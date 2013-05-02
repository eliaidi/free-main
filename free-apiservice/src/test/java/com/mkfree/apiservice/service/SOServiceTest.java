package com.mkfree.apiservice.service;

import org.apache.thrift.TException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkfree.apiservice.ApiServiceImpl;

public class SOServiceTest extends BaseTest {

	@Test
	public void deleteIndexByType() {
		try {
			String indexName = "blog";
			String type = "post";
			int result = 1;
			result = apiService.deleteIndexByType(indexName, type);
			Assert.assertEquals(result, 1);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void createIndex() {
		try {
			apiService.createIndex();
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void search() {
		try {
			apiService.search("休闲(mkfree)博客开发文档---介绍-作者(oyhk)---第一章", 0);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private ApiServiceImpl apiService;
}
