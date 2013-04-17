package com.mkfree.framework.common.spring;

/**
 * 用于异步请求,给客户端,返回数据
 * 
 * @author hk
 * 
 *         2013-2-2 下午4:59:49
 */
public class CallBackJson {

	private boolean success = false;// 异步请求是否成功,默认false -->失败
	private Object obj;// 返回的对象转换成json

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
