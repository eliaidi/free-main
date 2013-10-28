package com.mkfree.apiservice.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.thrift.TException;
import org.junit.Test;

import com.mkfree.apithrift.vo.AppUploadAttachmentVO;

public class AppUploadAttachmentServiceTest extends BaseTest {

	@Test
	public void saveAppUploadAttachment() {
		try {
			FileInputStream fis = new FileInputStream("/tmp/a.txt");
			// 得到文件通道
			FileChannel fc = fis.getChannel();
			// 分配与文件尺寸等大的缓冲区
			ByteBuffer fileByteBuffer = ByteBuffer.allocate((int) fc.size());
			fc.read(fileByteBuffer);
			fileByteBuffer.rewind();

			String appName = "blog-post";
			String originName = "aaa.txt";

			AppUploadAttachmentVO id = apiService.saveAppUploadAttachment(fileByteBuffer, appName, originName, userId, userIp);
			System.out.println(id);
		} catch (TException | IOException e) {
			e.printStackTrace();
		}
	}

}
