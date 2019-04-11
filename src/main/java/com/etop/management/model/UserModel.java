package com.etop.management.model;

import java.util.List;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Role;

public class UserModel {
	
	private EtopUser userInfo;
	
	private List<Role> roleInfo;

	public EtopUser getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(EtopUser userInfo) {
		this.userInfo = userInfo;
	}

	public List<Role> getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(List<Role> roleInfo) {
		this.roleInfo = roleInfo;
	}
	
	
}
