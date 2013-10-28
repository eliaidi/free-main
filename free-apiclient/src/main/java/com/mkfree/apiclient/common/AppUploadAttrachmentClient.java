package com.mkfree.apiclient.common;

import java.nio.ByteBuffer;

import org.apache.thrift.TException;
import org.apache.thrift.transport.TTransport;

import com.mkfree.apiclient.BaseClient;
import com.mkfree.apithrift.ApiService;
import com.mkfree.apithrift.vo.AppUploadAttachmentVO;

public class AppUploadAttrachmentClient {

	public static AppUploadAttachmentVO saveAppUploadAttachment(ByteBuffer fileByteBuffer, String appName, String originName, String userId, String userIp) {
		AppUploadAttachmentVO result = null;
		try {
			long start = System.currentTimeMillis();
			TTransport transport = BaseClient.getTransport();
			ApiService.Client client = BaseClient.getClient(transport);
			result = client.saveAppUploadAttachment(fileByteBuffer, appName, originName, userId, userIp);
			transport.close();// 关闭资源
			System.out.println("SysUserClient.saveAppUploadAttachment 耗时：" + (System.currentTimeMillis() - start));
		} catch (TException x) {
			x.printStackTrace();
		}
		return result;
	}

}
