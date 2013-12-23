package com.mkfree.apiservice.dao;

import java.util.List;
import java.util.Map;

import com.mkfree.apiservice.domain.BlogPost;
import com.mkfree.framework.common.mongodb.BaseDao;
import com.mkfree.framework.common.page.Pagination;

/**
 * 博客mongodb 操作 DAO
 * 
 * @author hk
 * 
 *         2013-2-2 上午9:59:52
 */
public interface BlogPostsDao extends BaseDao {

	/**
	 * 通过ID去获取实体
	 * 
	 * @param id
	 * @return
	 */
	public BlogPost findById(String id);

	/**
	 * 通过多个ID查找多条记录
	 * 
	 * @param ids
	 * @return
	 */
	public List<BlogPost> findByIds(List<String> ids);

	/**
	 * 获取全部博客文章
	 * 
	 * @return
	 */
	public List<BlogPost> findAll();

	/**
	 * 通过博客文章ID获取上一篇,下一篇文章
	 * 
	 * @param type 类型..1:上一篇,0:下一篇
	 * @param postsid 博客ID
	 * @return
	 */
	public BlogPost getUpNextPosts(int type, String postsid);

	/**
	 * 通过博客文章ID获取上一篇,下一篇文章,并且获取指定用户
	 * 
	 * @param type 类型..1:上一篇,0:下一篇
	 * @param postsid 博客ID
	 * @param userId 用户ID
	 * @return
	 */
	public BlogPost getUpNextPosts(int type, String postsid, String userId);

	/**
	 * 通过类型去查找博客文章
	 * 
	 * @param type 类型 1.hot(热门) 2.top(置顶) 3.views(浏览次数高) 4.head(头) 5.lists(列表) 6.new(最新博客)
	 * @param number 查多少条(数量)
	 * @return
	 */
	public List<BlogPost> findBytype(int type, int startIndex, int number);

	/**
	 * 获取用户博客数 并且分页
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userId
	 * @return
	 */
	public Pagination<BlogPost> getPostPageByUserId(int pageNo, int pageSize, String userId);

	/**
	 * 获取分页记录
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination<BlogPost> getPage(int pageNo, int pageSize);

	/**
	 * 保存一个实体
	 * 
	 * @param entity
	 * @return
	 */
	public BlogPost save(BlogPost entity);

	/**
	 * 通过ID保存一个更新
	 * 
	 * @param id
	 * @param params
	 */
	public void update(String id, Map<String, Object> params);

	/**
	 * 通过userid查询所有博客文章
	 * 
	 * @param userId
	 * @return
	 */
	public List<BlogPost> findByUserId(String userId);

	/**
	 * 获取用户的文章总数
	 * 
	 * @param userId
	 * @return
	 */
	public long findTotalByUserId(String userId);
}
