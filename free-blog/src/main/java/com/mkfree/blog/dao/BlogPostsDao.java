package com.mkfree.blog.dao;

import java.util.List;
import java.util.Map;

import com.mkfree.blog.domain.BlogPost;
import com.mkfree.framework.common.page.Pagination;

/**
 * 博客mongodb 操作 DAO
 * 
 * @author hk
 * 
 *         2013-2-2 上午9:59:52
 */
public interface BlogPostsDao {

	/**
	 * 通过ID去获取实体
	 * 
	 * @param id
	 * @return
	 */
	public BlogPost findById(String id);

	/**
	 * 通过博客文章ID获取上一篇,下一篇文章
	 * 
	 * @param type
	 *            类型..1:上一篇,0:下一篇
	 * @param postsid
	 *            博客ID
	 * @return
	 */
	public BlogPost getUpNextPosts(int type, String postsid);

	/**
	 * 通过类型去查找博客文章
	 * 
	 * @param type
	 *            类型 1.hot(热门) 2.top(置顶) 3.views(浏览次数高) 4.head(头) 5.lists(列表) 6.new(最新博客)
	 * @param number
	 *            查多少条(数量)
	 * @return
	 */
	public List<BlogPost> findPostsBytype(int type, int startIndex, int number);

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
}
