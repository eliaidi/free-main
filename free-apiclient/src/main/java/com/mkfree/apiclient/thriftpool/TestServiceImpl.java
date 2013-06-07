package com.mkfree.apiclient.thriftpool;

import org.apache.thrift.TException;

import com.mkfree.apiclient.thriftpool.TestService.Iface;

public class TestServiceImpl implements Iface {

	@Override
	public String getName() throws TException {
		return "oyhk";
	}

}
