package com.mkfree.apiservice.domain;

import com.mkfree.framework.common.utils.IDEntity;

public class BlogCategory extends IDEntity {

	private static final long serialVersionUID = -2693627908597566896L;

	private String name;// 博客分类名称
	private String description;// 博客分类描述
	private BlogUser blogUser;// 用户博客分类

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BlogUser getBlogUser() {
		return blogUser;
	}

	public void setBlogUser(BlogUser blogUser) {
		this.blogUser = blogUser;
	}

}
