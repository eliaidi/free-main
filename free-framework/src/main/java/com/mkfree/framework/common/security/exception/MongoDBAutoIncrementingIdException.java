package com.mkfree.framework.common.security.exception;

/**
 * 获取mongodb自动递增id，异常
 * 
 * @author oyhk
 * 
 *         2013-12-23 下午5:34:41
 */
@SuppressWarnings("serial")
public class MongoDBAutoIncrementingIdException extends Exception {

	public MongoDBAutoIncrementingIdException() {
		super();
	}

	public MongoDBAutoIncrementingIdException(String msg) {
		super("获取mongodb自动递增id异常，请检查你的" + msg + "的seq是否存在...");
	}
}
