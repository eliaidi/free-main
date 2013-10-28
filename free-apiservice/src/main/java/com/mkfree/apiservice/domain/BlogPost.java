package com.mkfree.apiservice.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 博客实体类
 * 
 * @author hk 2012-11-1 下午10:55:38
 */
@SuppressWarnings("serial")
@Document(collection = "blogPosts")
public class BlogPost extends IDEntity {

	private String title;// 博客标题
	private String summary;// 博客摘要
	private String content;// 博客内容
	private int views;// 博客浏览次数
	private int replies;// 博客回复数
	private String postsIp;// 博客发布,用户IP
	private Date createTime;// 博客发布时间
	private String blogNick;// 博客发布人昵称(冗余数据)
	private String blogCategory;// 博客所属分类
	private String userId;// 用户发的博客
	private int type;// 默认(无状态):0 博客类型 1.hot(热门) 2.top(置顶) 3.head(头) 4.精华 5.博客专题
	private Date updateTime;// 博客更新时间
	// 下面是专题字段
	private String topicId;// 博客属于那个专题
	private String parentId;// 添加了博客专题,需要有博客的父id,用于做博客的目录

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getReplies() {
		return replies;
	}

	public void setReplies(int replies) {
		this.replies = replies;
	}

	public String getPostsIp() {
		return postsIp;
	}

	public void setPostsIp(String postsIp) {
		this.postsIp = postsIp;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getBlogNick() {
		return blogNick;
	}

	public void setBlogNick(String blogNick) {
		this.blogNick = blogNick;
	}

	public String getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(String blogCategory) {
		this.blogCategory = blogCategory;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
