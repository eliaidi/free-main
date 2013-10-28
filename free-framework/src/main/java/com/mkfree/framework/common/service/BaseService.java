package com.mkfree.framework.common.service;

import java.util.Map;

/**
 * @author hk
 * 
 *         2012-11-10 上午8:45:11 最基础的服务层接口类,封装一些最常用的方法
 */
public interface BaseService<T> {

	/**
	 * 保存一个实体
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity);

	/**
	 * 通过ID获取实体
	 * 
	 * @param id
	 * @return
	 */
	public T findById(String id);

	/**
	 * 通过ID去删除一个实体
	 * 
	 * @param id
	 * @return
	 */
	public void removeByid(String id);

	/**
	 * 通过ID去更新实体
	 * 
	 * @param id
	 * @param params
	 *            这个是更新条件
	 */
	public void updateFirst(String id, Map<String, Object> params);

}
