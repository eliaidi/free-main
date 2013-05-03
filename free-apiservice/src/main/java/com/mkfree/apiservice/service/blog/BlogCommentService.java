package com.mkfree.apiservice.service.blog;

import java.util.List;

import com.mkfree.apithrift.vo.BlogCommentVO;

public interface BlogCommentService {

	/**
	 * 保存一个评论
	 * 
	 * @return
	 */
	public BlogCommentVO save(BlogCommentVO eneity);

	/**
	 * 通过博客id去查询对应的博客回复
	 * 
	 * @param postId
	 * @return
	 */
	public List<BlogCommentVO> findByPostsId(String postId);

	/**
	 * 查询最新评论
	 * 
	 * @param num 获取条数
	 * @return
	 */
	public List<BlogCommentVO> findOrderByCreateTime(int num);
}
