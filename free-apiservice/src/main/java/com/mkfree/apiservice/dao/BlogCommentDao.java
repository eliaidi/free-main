package com.mkfree.apiservice.dao;

import java.util.List;

import com.mkfree.apiservice.domain.BlogComment;

/**
 * 博客评论mongodb操作类
 * 
 * @author hk
 * 
 *         2013-2-2 下午5:25:38
 */
public interface BlogCommentDao {
	/**
	 * 保存一个评论
	 * 
	 * @return
	 */
	public BlogComment save(BlogComment entity);

	/**
	 * 通过博客id去查询对应的博客回复
	 * 
	 * @param postsId
	 * @return
	 */
	public List<BlogComment> findByPostsId(String postsId);

	/**
	 * 查找最新评论
	 * 
	 * @param num 条数
	 * @return
	 */
	public List<BlogComment> findOrderByCreateTime(int num);
}
