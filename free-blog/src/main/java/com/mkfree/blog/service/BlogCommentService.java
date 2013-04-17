package com.mkfree.blog.service;

import java.util.List;

import com.mkfree.blog.domain.BlogComment;

public interface BlogCommentService {

	/**
	 * 保存一个评论
	 * 
	 * @return
	 */
	public BlogComment save(BlogComment eneity);

	/**
	 * 通过博客id去查询对应的博客回复
	 * 
	 * @param postsId
	 * @return
	 */
	public List<BlogComment> findByPostsId(String postsId);
}
