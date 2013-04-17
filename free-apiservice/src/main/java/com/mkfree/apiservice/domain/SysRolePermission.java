package com.mkfree.apiservice.domain;

import com.mkfree.framework.common.utils.IDEntity;

public class SysRolePermission extends IDEntity {

	private SysRole role;
	private SysPermission permission;

	public SysRole getRole() {
		return role;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}

	public SysPermission getPermission() {
		return permission;
	}

	public void setPermission(SysPermission permission) {
		this.permission = permission;
	}
}
