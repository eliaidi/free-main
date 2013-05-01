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

	public void search() {

	}

	@Autowired
	private ApiServiceImpl apiService;
}
