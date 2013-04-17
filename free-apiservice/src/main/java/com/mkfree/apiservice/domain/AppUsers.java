package com.mkfree.apiservice.domain;

import com.mkfree.framework.common.utils.IDEntity;

/**
 * 用于开启网站的其他应用的用户,基于sysUser 对应,现在从2开始... 2:blog 3:...以后会有更多的应用就对应添加
 * 
 * @author hk
 * 
 *         2013-1-3 下午8:30:01
 */
public class AppUsers extends IDEntity {

	private static final long serialVersionUID = -1294506308830882922L;
	private int app;// 2:blog......以后可能会添加更多的应用
	private int sysUser;// 系统的user,其他应用的用户基于他来产生
	private int appUserid;

	public int getApp() {
		return app;
	}

	public void setApp(int app) {
		this.app = app;
	}

	public int getSysUser() {
		return sysUser;
	}

	public void setSysUser(int sysUser) {
		this.sysUser = sysUser;
	}

	public int getAppUserid() {
		return appUserid;
	}

	public void setAppUserid(int appUserid) {
		this.appUserid = appUserid;
	}

}
