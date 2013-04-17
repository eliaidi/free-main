package com.mkfree.apiservice.domain;


public class BlogUrlPermission {

	private BlogUrl blogUrl;
	private SysPermission permission;

	public BlogUrl getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(BlogUrl blogUrl) {
		this.blogUrl = blogUrl;
	}

	public SysPermission getPermission() {
		return permission;
	}

	public void setPermission(SysPermission permission) {
		this.permission = permission;
	}
}
