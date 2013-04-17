package com.mkfree.framework.common.utils;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

/**
 * 实体类的ID生成器,所有的实体基于他
 * 
 * @author hk
 * 
 *         2012-10-24 下午10:58:26
 */
public abstract class IDEntity implements Serializable {

	private static final long serialVersionUID = -8672247388900072163L;

	@Id
	private String id;// mongodb id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDEntity other = (IDEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
