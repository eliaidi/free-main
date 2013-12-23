package com.mkfree.framework.common.mongodb;

import com.mkfree.framework.common.security.exception.MongoDBAutoIncrementingIdException;

/**
 * mongodb 基础DAO数据接口
 * 
 * @author oyhk
 * 
 *         2013-12-23 下午5:51:23
 */
public interface BaseDao {
	/**
	 * 获取某个表的自动递增id
	 * 
	 * @param domain 代表那个实体类或者mongodb表名
	 * @return
	 * @throws MongoDBAutoIncrementingIdException
	 */
	public String getAutoIncrementingId(String domain) throws MongoDBAutoIncrementingIdException;
}
