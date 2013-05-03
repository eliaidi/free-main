package com.mkfree.apiservice.service.blog.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.apiservice.dao.BlogCommentDao;
import com.mkfree.apiservice.domain.BlogComment;
import com.mkfree.apiservice.service.blog.BlogCommentService;
import com.mkfree.apithrift.vo.BlogCommentVO;
import com.mkfree.framework.common.constants.BlogConstants;
import com.mkfree.framework.common.spring.KBeanUtils;
import com.mkfree.framework.common.utils.date.VpsTimeUtil;
import com.mkfree.framework.common.web.html.HtmlUtils;

@Service("blogCommentService")
public class BlogCommentServiceImpl implements BlogCommentService {
	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(BlogCommentServiceImpl.class);

	@Override
	public List<BlogCommentVO> findByPostsId(String postId) {
		List<BlogComment> blogComments = blogCommentDao.findByPostsId(postId);
		return copyList(blogComments);
	}

	@Override
	public BlogCommentVO save(BlogCommentVO commentVO) {
		if (commentVO == null) {
			return commentVO;
		}
		BlogComment blogComment = new BlogComment();
		if (StringUtils.isBlank(commentVO.getContent())) {
			return commentVO;
		} else if (StringUtils.isBlank(HtmlUtils.filterHtmlCode(commentVO.getContent()))) {
			return commentVO;
		} else {
			if (commentVO.getUserId() == null || commentVO.getUserId().trim().length() == 0) {// 游客
				commentVO.setUserId(BlogConstants.noLoginUserId);
				if (commentVO.getNick() == null || commentVO.getNick().trim().length() == 0) {// 有可能游客会填写自己的姓名
					blogComment.setNick(BlogConstants.noLoginUserNick);
				}
			} else if (commentVO.getUserId().equals(BlogConstants.anonymityUserId)) {// 匿名用户
				blogComment.setNick(BlogConstants.anonymityNetFriend);
			}
			blogComment.setPostsId(commentVO.getPostsId());
			blogComment.setUserId(commentVO.getUserId());
			blogComment.setReplyIp(commentVO.getReplyIp());
			// 下面都是登录用户了
			blogComment.setCreateTime(VpsTimeUtil.getVPSTime());
			// 防止黑客,xss攻击
			blogComment.setContent(HtmlUtils.avoidXSS(commentVO.getContent()));
			blogComment.setNick(HtmlUtils.avoidXSS(commentVO.getNick()));
			blogComment = blogCommentDao.save(blogComment);
		}
		KBeanUtils.copyProperties(blogComment, commentVO);
		return commentVO;
	}

	@Override
	public List<BlogCommentVO> findOrderByCreateTime(int num) {
		List<BlogComment> blogComments = blogCommentDao.findOrderByCreateTime(num);
		return copyList(blogComments);
	}

	/**
	 * 复制对象
	 * 
	 * @param blogComments
	 * @return
	 */
	private List<BlogCommentVO> copyList(List<BlogComment> blogComments) {
		List<BlogCommentVO> results = new ArrayList<BlogCommentVO>();
		for (int i = 0; i < blogComments.size(); i++) {
			BlogCommentVO blogCommentVO = new BlogCommentVO();
			BlogComment blogComment = blogComments.get(i);
			KBeanUtils.copyProperties(blogComment, blogCommentVO);
			results.add(blogCommentVO);
		}
		return results;
	}

	@Autowired
	private BlogCommentDao blogCommentDao;
}
