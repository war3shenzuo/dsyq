package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopNotice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;



/**
 * 
 * 内部账户管理
 * @author shixianjie
 * 下午3:19:21
 */
public interface UserService {
	
	/**
	 * 获取用户列表
	 * @param user
	 * @return
	 * @throws Exception
	 */
	EtopPage<EtopUser> getUserList(EtopUser user) throws Exception;

	EtopPage<EtopUser> getReceiverByParkId(String receiverType, String companyName, String parkId, Integer offset, Integer limit);
	
	/**
	 * 获取用户详细信息
	 * @param userId 
	 * @return
	 */
	EtopUser getUserInfo(String userId)throws Exception;
	
	/**
	 * 新增系统用户
	 * @param user
	 * @return 
	 */
	ResultType addUserInfo(EtopUser user,String roleId) throws Exception;
	
	/**
	 * 更新用户信息
	 * @param userId
	 */
	void updateUserInfo(EtopUser user)throws Exception;
	
	/**
	 * 展示中心账户管理
	 * @param user
	 */
	void updateUser(EtopUser user,EtopCompanyEmployees Employees);
	
	/**
	 * 激活/关闭用户
	 * @param user
	 */
	void activeOrCloseUser(EtopUser user)throws Exception;
	
	/**
	 * 修改密码
	 * @param user
	 * @param type 修改密码的类型 reset 重置, update 修改
	 * @throws Exception
	 */
	ResultType changePassword(EtopUser user, String type)throws Exception;
	
	/**
	 * 根据角色ID查询用户
	 * @return
	 * @throws Exception
	 */
	List<EtopUser> getUserListByRoleIds(List<String> roleIds)throws Exception;

	/**
	 * 根据员工账号获取员工id
	 * @param account
	 * @return
	 */
	EtopUser searchByAccount(String account);
	
	/**
	 * 根据用户名获取用户详细信息
	 * @param userName
	 * @return 
	 * @throws Exception 
	 */
	EtopUser getUserInfoByUserName(String userName) throws Exception;
	/**
	 * 根据公司ID获取用户详细信息
	 * @param userName
	 * @return 
	 * @throws Exception 
	 */
	EtopUser getUserInfoByCompanyId(String companyId) throws Exception;
	
	/**
	 * 注册账号
	 * @param newUser
	 * @return
	 */
	ResultType registerUser(EtopUser newUser,String acode) throws Exception;

	List<EtopNotice> checkAllIds(String parkId);

	//判断用户权限（添加公司员工信息入口）
	Boolean getUserPer();

}
