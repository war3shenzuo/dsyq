package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.Func;

/**
 * 
 * 功能DAO
 * @author shixianjie
 * 上午10:51:12
 */
public interface FuncDao {
	
	/**
	 * 获取所有功能
	 * @return
	 */
	List<Func> getFuncList();
	
	/**
	 * 获取功能详细信息
	 * @param funcId
	 * @return
	 */
	Func getFuncInfo(String funcId);
	
	/**
	 * 新增功能
	 * @param func
	 */
	void addFunc(Func func);
	
	/**
	 * 修改功能
	 * @param func
	 */
	void updateFunc(Func func);
	
	/**
	 * 激活/删除功能
	 * @param func
	 */
	void activeOrClosePark(Func func);
	
	/**
	 * 获取角色的功能
	 * @return
	 */
	List<Func> getFuncListByRoleId(List<String> rolesId);
	
	/**
	 * 获取角色的功能 不返回ID
	 * @param roleIds
	 * @return
	 */
	List<Func> getFuncListByRoleIdNoParentId(List<String> roleIds);
	
	/**
	 * 获取可读的功能
	 * @param roleId
	 * @return
	 */
	List<Func> getFuncListRead(List<String> roleId);
	
	/**
	 * 此功能是否只读
	 * @param map
	 * @return
	 */
	String getFuncisRead(Map<String, String> map);
	
	/**
	 * 查询所有子功能的父ID
	 * @param List<String>
	 * @return
	 */
	List<String> getfuncParentId(List<String> funcIds);
	
	/**
	 * 获取企业的功能
	 * @return
	 */
	List<Func> getFuncListByCompany();
	
	
	/**
	 * 获取个人的功能
	 * @return
	 */
	List<Func> getFuncListByPersonal();

	List<Func> getFuncParkGroupList();
	
}
