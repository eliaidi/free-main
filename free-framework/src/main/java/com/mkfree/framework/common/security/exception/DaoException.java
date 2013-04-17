package com.mkfree.framework.common.security.exception;

public class DaoException extends Exception{

	public DaoException() {
		super();
	}
	public DaoException(String msg) {
		super(msg+"field don't not exist");
	}
}
