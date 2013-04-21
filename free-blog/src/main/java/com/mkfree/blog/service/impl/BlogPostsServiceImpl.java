package com.mkfree.blog.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkfree.blog.dao.BlogPostsDao;
import com.mkfree.blog.domain.BlogPost;
import com.mkfree.blog.service.BlogPostsService;
import com.mkfree.framework.common.page.Pagination;
import com.mkfree.framework.common.web.html.HtmlUtils;

@Service("blogPostsService")
public class BlogPostsServiceImpl implements BlogPostsService {

	@Autowired
	private BlogPostsDao blogPostsDao;

	@Override
	public BlogPost findById(String id) {
		return blogPostsDao.findById(id);
	}

	@Override
	public List<BlogPost> findPostsBytype(int type, int startIndex, int number, int length) {
		List<BlogPost> lists = null;
		lists = blogPostsDao.findPostsBytype(type, startIndex, number);
		if (length > 0) {
			for (int i = 0; i < lists.size(); i++) {
				String title = lists.get(i).getTitle();
				lists.get(i).setTitle((title.length() > length) ? title.substring(0, length) + "..." : title);
			}
		}
		return lists;
	}

	@Override
	public Pagination<BlogPost> getPage(int pageNo, int pageSize) {
		Pagination<BlogPost> pages = this.blogPostsDao.getPage(pageNo, pageSize);
		for (int i = 0; i < pages.getDatas().size(); i++) {
			String title = pages.getDatas().get(i).getTitle();
			pages.getDatas().get(i).setTitle(title.length() > 50 ? title.substring(0, 50) + "..." : title);
		}
		return pages;
	}

	@Override
	public BlogPost getUpNextPosts(int type, String postsid) {
		return blogPostsDao.getUpNextPosts(type, postsid);
	}

	@Override
	public BlogPost getUpNextPosts(int type, String postsid, String userid) {
		return null;
	}

	@Override
	public BlogPost save(BlogPost entity) {
		return blogPostsDao.save(entity);
	}

	@Override
	public void update(String id, Map<String, Object> params) {
		BlogPost bp = blogPostsDao.findById(id);
		if (bp.getSummary() == null && bp.getSummary().trim().length() == 0) {
			String content = (String) params.get("content");
			if (content != null && content.trim().length() > 0) {
				params.put("sumary", HtmlUtils.filterHtmlCode(content, 240));
			}
		}
		blogPostsDao.update(id, params);
	}

}
