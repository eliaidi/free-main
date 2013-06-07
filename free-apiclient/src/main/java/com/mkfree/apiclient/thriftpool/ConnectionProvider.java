package com.mkfree.apiclient.thriftpool;

import org.apache.thrift.transport.TFramedTransport;

public interface ConnectionProvider {

	public TFramedTransport getConnention();

	public void returnConnection(TFramedTransport framedTransport);

}
