package com.mkfree.framework.common.service;

import java.util.Map;

import com.mkfree.framework.common.mongodb.MongodbDao;

/**
 * @author hk
 * 
 *         2012-11-10 上午8:45:11 最基础的服务层接口类,封装一些最常用的方法
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected MongodbDao<T> mongodbDao;

	/**
	 * 保存一个实体
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public T save(T entity) {
		return mongodbDao.save(entity);
	}

	/**
	 * 通过ID去获取实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public T findById(String id) {
		return mongodbDao.findById(id);
	}

	/**
	 * 通过ID去删除一个实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public void removeByid(String id) {
		mongodbDao.removeById(id);
	}

	/**
	 * 通过ID去更新实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public void updateFirst(String id, Map<String, Object> params) {
		mongodbDao.updateFirst(id, params);
	}
}
