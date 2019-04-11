package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.Role;

public interface RoleDao {
	
	/**
	 * 查询角色列表
	 * @param role
	 * @return
	 */
	List<Role> getRoleList(Role role);
	
	/**
	 * 获得角色详细信息
	 * @param roleId
	 * @return
	 */
	Role getRoleInfo(String roleId);
	
	/**
	 * 新增角色
	 * @param role
	 */
	void addRole(Role role);
	
	/**
	 * 修改角色
	 * @param role
	 */
	void updateRole(Role role);
	
	/**
	 * 激活/关闭角色
	 * @param role
	 */
	void activeOrCloseRole(Role role);
	
	/**
	 * 角色新增功能
	 * @param map
	 */
	void addFuncForRole(List<Map<String, String>> map);
	
	/**
	 * 角色删除功能
	 * @param map
	 */
	void delFuncForRole(String roleId);
	
	int delFuncForRoles(Map<String,Object> param);
	
	/**
	 * 角色新增用户
	 * @param map
	 */
	void addUserForRole(Map<String, String> map);
	
	/**
	 * 角色删除用户
	 * @param map
	 */
	void delUserForRole(Map<String, String> map);

	/**
	 * 获取用户的角色
	 * @return
	 */
	List<Role> getUserRoleList(String userId);
	
	/**
	 * 查询父角色
	 * @param olaRoleId
	 */
	String getParentRole(String olaRoleId);
	
	/**
	 * 添加已读权限
	 * @param map
	 */
	void addReadFuncForRole(Map<String, Object> map);
	
	/**
	 * 删除角色所有已读权限
	 * @param roleId
	 */
	void delReadFuncForRole(String roleId);
	
	
	
	/**
	 * 创建园区默认角色
	 * @param role
	 */
	void addDefaultRole(Role role);
	
	/**
	 * 根据父ID查询子角色
	 * @param roleParam
	 * @return
	 */
	List<Role> getRoleListByParentId(Role roleParam);
	
	/**
	 * 获取园区有某个权限的角色ID
	 * @param mapParam
	 * @return
	 */
	List<String> getJurisdiction(Map<String, String> mapParam);
	




}
