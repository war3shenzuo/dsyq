package com.etop.website.dao;

import java.util.List;

import com.etop.website.bean.EtopUser;

/**
 * 
 * 用户DAO
 * @author shixianjie
 * 上午10:36:33
 */
public interface WebUserDao {
	
	/**
	 * 查询所有用户信息
	 * @param user
	 * @return
	 */
	List<EtopUser> getUserList(EtopUser user);
	
	/***
	 * 获取用户详细信息
	 * @param userId
	 * @return
	 */
	EtopUser getUserInfo(String userId);
	
	/**
	 * 获取用户详细信息（根据用户名）
	 * @param userName
	 * @return
	 */
	EtopUser getUserInfoByUserName(String userName);
	
	/**
	 * 获取用户密码
	 * @param id
	 * @return
	 */
	EtopUser getUserPassword(String id);
	
	/**
	 * 新增用户
	 * @param user
	 */
	void addUserInfo(EtopUser user);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	void updateUserInfo(EtopUser user);
	
	/**
	 * 激活/关闭用户
	 * @param user
	 */
	void activeOrCloseUser(EtopUser user);
	
	/**
	 * 修改密码
	 * @param user
	 */
	void changePassword(EtopUser user);
	
	/**
	 * 根据角色ID查询用户
	 * @param roleIds
	 * @return
	 */
	List<EtopUser> getUserListByRoleIds(List<String> roleIds);

	
}
