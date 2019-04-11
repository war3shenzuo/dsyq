package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.Func;

/**
 * 
 * 功能服务
 * @author shixianjie
 * 下午2:58:00
 */
public interface FuncService {
	/**
	 * 获取所有功能
	 * @return
	 */
	List<Func> getFuncList() throws Exception;
	
	/**
	 * 获得功能详细信息
	 * @param funcId
	 * @return
	 * @throws Exception 
	 */
	Func getFuncInfo(String funcId) throws Exception;

	/**
	 * 添加功能
	 * @param func 
	 * @throws Exception 
	 */
	void addFunc(Func func) throws Exception;
	
	/**
	 * 修改功能
	 * @param func
	 */
	void updateFunc(Func func) throws Exception;
	
	/**
	 * 删除功能
	 * @param funcId
	 * @throws Exception
	 */
	void activeOrClosePark(Func func) throws Exception;
	
	/**
	 * 获取角色的功能
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<Func> getFuncListByRoleId(String roleId) throws Exception;
	
	/**
	 * 获取角色的功能 不需要父ID
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<Func> getFuncListByRoleIdNoParentId(String roleId) throws Exception;
	
	/**
	 * 获取可读的功能
	 * @param roleId
	 * @return
	 * @throws Exception 
	 */
	List<Func> getFuncListRead(String roleId) throws Exception;
	
	/**
	 * 判断页面是否只读
	 * @param url
	 * @return
	 */
	boolean isReadOnly(String url) ;
	
	/**
	 * 获得临时园区组的功能列表
	 * @return
	 */
	List<Func> getTemporaryFuncList();
	
	/**
	 * 获得增势园区组的功能列表
	 * @return
	 */
	List<Func> getFuncParkGroupList();



}
