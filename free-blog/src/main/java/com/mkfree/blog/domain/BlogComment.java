package com.mkfree.blog.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 博客评论实体类
 * 
 * @author hk
 * 
 *         2013-2-2 上午10:06:41
 */
@SuppressWarnings("serial")
@Document(collection = "blogComments")
public class BlogComment extends IDEntity {

	private String content; // 评论内容
	private Date createTime; // 评论时间
	private String userId;// 用户id 如果userId为-1:游客 0:匿名用户 (nick 也会跟着更改)
	private String postsId;// 博客id
	private String nick; // 用户昵称
	private String replyIp;// 回复者的IP地址

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPostsId() {
		return postsId;
	}

	public void setPostsId(String postsId) {
		this.postsId = postsId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getReplyIp() {
		return replyIp;
	}

	public void setReplyIp(String replyIp) {
		this.replyIp = replyIp;
	}

}
