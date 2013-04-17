package com.mkfree.blog.dao;

import java.util.List;

import com.mkfree.blog.domain.BlogComment;

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
}
