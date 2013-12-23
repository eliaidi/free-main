package com.mkfree.framework.common.mongodb;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkfree.apiservice.service.BaseTest;
import com.mkfree.framework.common.security.exception.MongoDBAutoIncrementingIdException;

public class MongoDBAutoIncrementingIdTest extends BaseTest {

	@Test
	public void getAutoIncrementingIdTest() {
		try {
			counterToolsDao.getAutoIncrementingId("blogPosts");
		} catch (MongoDBAutoIncrementingIdException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	private CounterToolsDaoImpl counterToolsDao;
}
