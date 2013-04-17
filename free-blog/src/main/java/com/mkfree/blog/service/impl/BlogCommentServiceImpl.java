package com.mkfree.blog.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.blog.action.BlogCommentController;
import com.mkfree.blog.dao.BlogCommentDao;
import com.mkfree.blog.domain.BlogComment;
import com.mkfree.blog.service.BlogCommentService;
import com.mkfree.framework.common.constants.BlogConstants;
import com.mkfree.framework.common.utils.VpsTimeUtil;
import com.mkfree.framework.common.web.html.HtmlUtils;

@Service("blogCommentService")
public class BlogCommentServiceImpl implements BlogCommentService {
	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(BlogCommentController.class);

	@Override
	public List<BlogComment> findByPostsId(String postsId) {
		return blogCommentDao.findByPostsId(postsId);
	}

	@Override
	public BlogComment save(BlogComment comment) {
		if (comment == null || comment.getContent() == null
				|| HtmlUtils.filterHtmlCode(comment.getContent().trim()).length() == 0) {
			// 为了容易找出错误,我添加了logger
			if (logger.isWarnEnabled()) {
				logger.warn("save(BlogComment) - hacker?cross vaild,deal with...."); //$NON-NLS-1$
			}
		} else {
			if (comment.getUserId() == null || comment.getUserId().trim().length() == 0) {// 游客
				comment.setUserId(BlogConstants.noLoginUserId);
				if (comment.getNick() == null || comment.getNick().trim().length() == 0) {// 有可能游客会填写自己的姓名
					comment.setNick(BlogConstants.noLoginUserNick);
				}
			} else if (comment.getUserId().equals(BlogConstants.anonymityUserId)) {// 匿名用户
				comment.setNick(BlogConstants.anonymityNetFriend);
			}
			// 下面都是登录用户了
			comment.setCreateTime(VpsTimeUtil.getVPSTime());
			// 防止黑客,xss攻击
			comment.setContent(HtmlUtils.avoidXSS(comment.getContent()));
			return blogCommentDao.save(comment);
		}
		return comment;
	}

	@Autowired
	private BlogCommentDao blogCommentDao;
}
