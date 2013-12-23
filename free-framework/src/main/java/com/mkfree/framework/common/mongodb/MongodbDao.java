package com.mkfree.framework.common.mongodb;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mkfree.framework.common.page.Pagination;
import com.mkfree.framework.common.security.exception.MongoDBAutoIncrementingIdException;
import com.mongodb.WriteResult;

/**
 * mongodb　基础操作类
 * 
 * @author oyhk
 * 
 *         2013-1-22下午5:28:26
 */
public abstract class MongodbDao<T> implements BaseDao {

	/**
	 * 获取某个表的自动递增id
	 * 
	 * @param domain 代表那个实体类或者mongodb表名
	 * @return
	 * @throws MongoDBAutoIncrementingIdException
	 */
	@Override
	public String getAutoIncrementingId(String domain) throws MongoDBAutoIncrementingIdException {
		Query query = new Query();
		query.addCriteria(new Criteria().and("_id").is(domain));
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		Update update = new Update();
		update.inc("seq", 1);
		CounterTools counterTools = mongoTemplate.findAndModify(query, update, options, CounterTools.class);
		if (counterTools == null) {
			throw new MongoDBAutoIncrementingIdException(domain);
		}
		return counterTools.getSeq().toString();
	}

	/**
	 * 通过条件查询,查询分页结果
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param query
	 * @return
	 */
	public Pagination<T> getPage(int pageNo, int pageSize, Query query) {
		long totalCount = this.mongoTemplate.count(query, this.getEntityClass());
		Pagination<T> page = new Pagination<T>(pageNo, pageSize, totalCount);
		query.skip(page.getFirstResult());// skip相当于从那条记录开始
		query.limit(pageSize);// 从skip开始,取多少条记录
		List<T> datas = this.find(query);
		page.setDatas(datas);
		return page;
	}

	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	public List<T> find(Query query) {
		return mongoTemplate.find(query, this.getEntityClass());
	}

	/**
	 * 通过条件查询实体(集合)
	 * 
	 * @param query
	 */
	public List<T> find(Query query, String collectionName) {
		return mongoTemplate.find(query, this.getEntityClass(), collectionName);
	}

	/**
	 * 通过一定的条件查询一个实体
	 * 
	 * @param query
	 * @return
	 */
	public T findOne(Query query) {
		return mongoTemplate.findOne(query, this.getEntityClass());
	}

	/**
	 * 查询出所有数据
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return this.mongoTemplate.findAll(getEntityClass());
	}

	/**
	 * 查询并且修改记录
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	public T findAndModify(Query query, Update update) {
		return this.mongoTemplate.findAndModify(query, update, this.getEntityClass());
	}

	/**
	 * 按条件查询,并且删除记录
	 * 
	 * @param query
	 * @return
	 */
	public T findAndRemove(Query query) {
		return this.mongoTemplate.findAndRemove(query, this.getEntityClass());
	}

	/**
	 * 通过ID获取记录
	 * 
	 * @param id
	 * @return
	 */
	public T findById(String id) {
		return mongoTemplate.findById(id, this.getEntityClass());
	}

	/**
	 * 通过ID获取记录,并且指定了集合名(表的意思)
	 * 
	 * @param id
	 * @param collectionName 集合名
	 * @return
	 */
	public T findById(String id, String collectionName) {
		return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
	}

	/**
	 * 通过ID去更新对象
	 * 
	 * @param id
	 * @param params 需要更新的字段
	 * @return
	 */
	public WriteResult updateFirst(String id, Map<String, Object> params) {
		Query query = new Query();
		return this.updateFirst(query.addCriteria(new Criteria().and("id").is(id)), params);
	}

	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param params 准备更新的参数 ex: title 这个是标题.. params.put("title","要修改的标题");
	 * @return
	 */
	public WriteResult updateFirst(Query query, Map<String, Object> params) {
		WriteResult writeResult = mongoTemplate.updateFirst(query, this.getUpdate(params), this.getEntityClass());
		return writeResult;
	}

	/**
	 * 通过条件查询出多条记录(包含一条),全部修改(感觉很少用到),可以叫批量修改
	 * 
	 * @param query
	 * @param params 准备更新的参数 ex: title 这个是标题.. params.put("title","要修改的标题");
	 */
	public WriteResult updateMulti(Query query, Map<String, Object> params) {
		WriteResult writeResult = this.mongoTemplate.updateMulti(query, this.getUpdate(params), this.getEntityClass());
		return writeResult;
	}

	/**
	 * 通过条件查询出多条记录,全部修改,并且如果存在有新数据,也会同时插入到mongodb中..(简单说:更新或插入数据)
	 * 
	 * @param query
	 * @param params 准备更新的参数 ex: title 这个是标题.. params.put("title","要修改的标题");
	 */
	public WriteResult upsert(Query query, Map<String, Object> params) {
		WriteResult writeResult = this.mongoTemplate.upsert(query, this.getUpdate(params), this.getEntityClass());
		return writeResult;
	}

	/**
	 * 通过ID去删除一个对象
	 * 
	 * @param id
	 */
	public void removeById(String id) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.and("id").is(id);
		this.remove(query);
	}

	/**
	 * 直接删除对象
	 * 
	 * @param entity
	 */
	public void remove(T entity) {
		this.mongoTemplate.remove(entity);
	}

	/**
	 * 删除指定表的记录(对象)
	 * 
	 * @param entity
	 * @param collection
	 */
	public void remove(T entity, String collection) {
		this.mongoTemplate.remove(entity, collection);
	}

	/**
	 * 通过查询条件,删除对象
	 * 
	 * @param query
	 */
	public void remove(Query query) {
		this.mongoTemplate.remove(query, this.getEntityClass());
	}

	/**
	 * 保存一个对象到mongodb
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity) {
		mongoTemplate.save(entity);
		return entity;
	}

	/**
	 * 通过更新参数,再组装成一个update
	 * 
	 * @param params ex: title 这个是标题.. params.put("title","要修改的标题");
	 * @return
	 */
	protected Update getUpdate(Map<String, Object> params) {
		Update update = new Update();
		Set<String> keys = params.keySet();
		Iterator<String> iterator = keys.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			update.set(key, params.get(key));
		}
		return update;
	}

	/**
	 * 获取需要操作的实体类class
	 * 
	 * @return
	 */
	protected abstract Class<T> getEntityClass();

	/**
	 * 注入mongodbTemplate
	 * 
	 * @param mongoTemplate
	 */
	protected abstract void setMongoTemplate(MongoTemplate mongoTemplate);

	/**
	 * spring mongodb　集成操作类　
	 */
	protected MongoTemplate mongoTemplate;
}
