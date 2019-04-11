package com.etop.management.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopNotice;
import com.etop.management.bean.EtopUser;
import com.github.pagehelper.Page;

/**
 * 
 * 用户DAO
 * @author shixianjie
 * 上午10:36:33
 */
public interface UserDao {
	
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
	 * 获取用户详细信息（根据公司）
	 * @param userName
	 * @return
	 */
	EtopUser getUserInfoByCompanyId(String companyId);
	
	/**
	 * 获取用户详细信息（手机）
	 * @param userName
	 * @return
	 */
	EtopUser getUserInfoByMobile(String mobile);
	
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
	 * 修改密码
	 * @param user
	 */
	void changePasswordById(EtopUser user);
	
	/**
	 * 根据角色ID查询用户
	 * @param roleIds
	 * @return
	 */
	List<EtopUser> getUserListByRoleIds(List<String> roleIds);

	//根据员工账号获取角色信息
	EtopUser searchByAccount(String account);

	//获取企业信息
	Page<EtopUser> selectComp(@Param("parkId") String parkId, @Param("receiverType") String receiverType, @Param("companyName") String companyName);
	//获取园区或园区组管理员信息
	Page<EtopUser> selectByAdminOrStaff(@Param("parkId") String parkId, @Param("receiverType") String receiverType, @Param("companyName") String companyName);
	//获取员工信息
	Page<EtopUser> selectStaff(@Param("parkId") String parkId, @Param("receiverType") String receiverType, @Param("companyName") String companyName);

	List<EtopNotice> selectUser(@Param("parkId") String parkId);

	EtopUser getUserNum(@Param("parkId") String parkId);
	
	/**
	 * 保存激活码
	 * @param map 
	 */
	void saveVcode(Map<String, Object> map);
	
	/**
	 * 获取激活码
	 * @param map
	 * @return
	 */
	Date getAcode(Map<String, String> map);

	

	
}
