package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;

/**
 * 
 * 角色服务
 * @author shixianjie
 * 下午3:18:56
 */
public interface RoleService {
	
	/**
	 * 获取所有的角色列表
	 * @return
	 */
	public List<Role> getRoleList(Role role);
	
	/**
	 * 获取角色详细信息
	 * @param roleId
	 * @return
	 */
	public Role getRoleInfo(String roleId);
	
	/**
	 * 新增角色
	 * @param role
	 */
	public void addRole(Role role);
	
	/**
	 * 修改角色
	 * @param role
	 */
	public void updateRole(Role role);
	
	/**
	 * 赋值角色权限
	 * @param olaRoleId 被拷贝的角色ID
	 * @param role 新角色信息
	 */
	public void copyRoleFunc(String olaRoleId, String parkId,String parentId);
	
	/**
	 * 获取父节点
	 * @param parkId
	 * @return
	 */
	public String getParentRoleId(String parkId);
	
	/**
	 * 激活/关闭角色
	 * @param role
	 */
	public void activeOrCloseRole(Role role);
	
	/**
	 * 角色添加功能
	 * @param funcId 功能ID
	 * @param roleId 角色ID
	 */
	public void addFuncForRole(String funcId, String roleId);
	
	/**
	 * 角色删除功能
	 * @param funcId 功能ID
	 * @param roleId 角色ID
	 */
	public void delFuncForRole(String roleId);
	
	/**
	 * 角色新增用户
	 * @param userId
	 * @param roleId
	 */
	public ResultType addUserForRole(String userId, String roleId);
	
	/**
	 * 角色删除用户
	 * @param roleId
	 * @param userId
	 */
	public void delUserForRole(String roleId, String userId);
	
	/**
	 * 角色新增读写的功能
	 * @param funcId
	 * @param roleId
	 */
	public void addReadFuncForRole(String funcId, String roleId);
	
	/**
	 * 获得用户的角色
	 * @param userId
	 * @return
	 */
	public List<Role> getUserRoleList(String userId);
	
	/**
	 * 根据园区ID和权限编号 获得对应角色下的所有用户
	 * @param parkId 园区ID
	 * @param juriId 权限编号(ROLE.JAVA里)
	 * @return 用户集合（如果没有返回null）
	 */
	List<EtopUser> getJurisdiction(String parkId, String juriId);
	
	EtopUser getSysJurisdiction();
	
	/**
	 * 判断角色是否有权限
	 * @param roleId 角色Id
	 * @param juriId 权限编号(ROLE.JAVA里)
	 * @return 
	 */
	Boolean isHaveJurisdiction(String roleId, String juriId);

	Boolean isHaveJurisdiction(List<Role> role, String juriId);

	

}
