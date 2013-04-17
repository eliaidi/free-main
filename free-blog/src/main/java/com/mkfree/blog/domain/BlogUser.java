package com.mkfree.blog.domain;

import java.util.Date;

/**
 * 博客用户实体
 * 
 * @author hk 2012-11-1 下午10:12:23
 */
public class BlogUser {

	private String id;// id
	private String account;// 用户帐号
	private String password;// 用户密码
	private String displayName;// 用户真实姓名
	private String nick;// 博客用户昵称
	private String email;// 博客用户邮箱
	private int sex;// 博客用户性别(-1:未知,0:男,1女)
	private int age;// 博客用户年龄(-1:未知)
	private int status;// 博客用户状态 (0:禁用,1:正常, ...)
	private String autograph;// 博客用户个性签名
	private Date createTime;// 博客用户创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
