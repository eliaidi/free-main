package com.mkfree.framework.common.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mkfree.framework.common.utils.IDEntity;

@SuppressWarnings("serial")
@Document(collection = "counterTools")
public class CounterTools extends IDEntity {

	/**
	 * 自动递增 计数字段
	 */
	private volatile Long seq;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

}
