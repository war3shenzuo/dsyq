package com.etop.management.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import java.util.stream.Collectors;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Func;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.dao.FuncDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.dao.RoleDao;
import com.etop.management.dao.UserDao;
import com.etop.management.service.RoleService;
import com.etop.management.util.UserInfoUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RoleServiceImpl implements RoleService {

	
	@Resource
	RoleDao roledao;
	
	@Resource
	FuncDao funcdao;
	
	@Resource
	UserDao userdao;
	
	@Resource
	ParkGroupDao parkGroupDao;
	
	@Override
	public List<Role> getRoleList(Role role) {
		
		List<Role> list =  new ArrayList<Role>();
		
		role.setParkGroupId(UserInfoUtil.getUserParkInfo().getParkGroupId());
		
		list = roledao.getRoleList(role);
		
		return list;
	}

	@Override
	public Role getRoleInfo(String roleId) {
		
		Role role = roledao.getRoleInfo(roleId);
		
		return role;
	}

	@Override
	public void addRole(Role role) {
		
		role.setId(UUID.randomUUID().toString());
		role.setCreateAt(new Date());
		
		if(parkGroupDao.getParkGroupInfo(role.getParkId())!=null){
			role.setRoleType("1");
		}else{
			role.setRoleType("2");
		}
		
		roledao.addRole(role);
		
	}

	@Override
	public void updateRole(Role role) {
		
		roledao.updateRole(role);
		
	}


	@Override
	public void activeOrCloseRole(Role role) {
		
		roledao.activeOrCloseRole(role);
		
	}


	@Override
	public void addFuncForRole(String funcIds, String roleId) {
		
		
		List<Func> funcs= funcdao.getFuncListByRoleId(Arrays.asList(new String[]{roleId}));
		
		List<String> alreadyFuncIds=funcs.stream().map(f->{return f.getId();}).collect(Collectors.toList());
		
		List<String> deleteList=funcs
								.stream()
								.filter(func-> funcIds.indexOf(func.getId()) <0)
								.map(func->{return func.getId();})
								.collect(Collectors.toList());
		
		//清空所有功能
		//delFuncForRole(roleId);
		if(deleteList.size()>0){
			Map<String,Object> param= new HashMap<String, Object>();
			param.put("roleId", roleId);
			param.put("funcIds", deleteList);
			roledao.delFuncForRoles(param);
		}
		
		String[] funcId = funcIds.split(",");
		List<String> funcIdList = new ArrayList<String>(Arrays.asList(funcId));
	
		//去除已经存在的
		List<String> newfuncIdList=funcIdList.stream().filter(id-> !alreadyFuncIds.contains(id)).collect(Collectors.toList());
		//查询所有子功能的父ID
		List<String> parentIdList = funcdao.getfuncParentId(funcIdList);
		//合并
		newfuncIdList.addAll(parentIdList);
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		Map<String,String> map = null;
		
		for(String f : newfuncIdList){
			map = new HashMap<String,String>();
			map.put("id",UUID.randomUUID().toString());
			map.put("funcId", f);
			map.put("roleId", roleId);
			map.put("isRead", "0");
			
			list.add(map);
		}
		
		if(list.size()>0){

			roledao.addFuncForRole(list);
			
		}
		
	}

	@Override
	public void delFuncForRole(String roleId) {

		roledao.delFuncForRole(roleId);
		
	}
	

	@Override
	public ResultType addUserForRole(String userId, String roleId) {
		
		String[] userIds = userId.split(",");
		
		for(String u:userIds){
			
			for(Role r: roledao.getUserRoleList(u)){
				if(roleId.equals(r.getId())){
					
					return ResultType.getFail("角色不能重复绑定同一用户");
				}
			}
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("id",UUID.randomUUID().toString());
			map.put("userId", u);
			map.put("roleId", roleId);
			
			roledao.addUserForRole(map);
		}
		
		return ResultType.getSuccess("绑定成功");
		
	}

	@Override
	public void delUserForRole(String roleId, String userId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("userId", userId);
		map.put("roleId", roleId);

		roledao.delUserForRole(map);
	}

	@Override
	public void copyRoleFunc(String olaRoleId, String parkId,String parentId) {
		
		//获取旧角色Id
		Role role = roledao.getRoleInfo(olaRoleId);
		
		String newRoleId = UUID.randomUUID().toString();
		role.setId(newRoleId);
		role.setCreateAt(new Date());
		role.setParkId(parkId);
		role.setParentId(parentId);
		//创建新的角色
		roledao.addRole(role);
		
		//获取旧角色功能
		List<String> roleIds = new ArrayList<String>();
		roleIds.add(olaRoleId);
		List<Func> funclist = funcdao.getFuncListByRoleId(roleIds);
		
		if(funclist!=null && funclist.size()>0){
			//整理新角色功能数据
			List<Map<String,String>> newRoles = new ArrayList<Map<String,String>>();
			Map<String,String> map =null;
			for(Func func:funclist){
				
				map = new HashMap<String, String>();
				map.put("id", UUID.randomUUID().toString());
				map.put("funcId", func.getId());
				map.put("roleId", newRoleId);
				map.put("isRead", func.getIsRead());
				
				newRoles.add(map);
			}
			
			//新角色增加功能
			roledao.addFuncForRole(newRoles);
		}
		
	}
	
	public String getParentRoleId(String parkId){
		//获取新园区的父节点
		String parentId = roledao.getParentRole(parkId);
		
		return parentId;
		
	}


	@Override
	public void addReadFuncForRole(String funcIds, String roleId) {
		
		//清空所有功能
		roledao.delReadFuncForRole(roleId);
		
		String[] funcId = funcIds.split(",");
		
		List<String> funclist = new ArrayList<String>();
		
		for(String f : funcId){
			
			funclist.add(f);
		}
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("funcId", funclist);
		map.put("roleId", roleId);
		

		roledao.addReadFuncForRole(map);
		
	}

	@Override
	public List<Role> getUserRoleList(String userId) {
		
		return roledao.getUserRoleList(userId);
	}
	
	@Override
	public List<EtopUser> getJurisdiction(String parkId,String juriId){
		
		Map<String,String> mapParam = new HashMap<String,String>();
		mapParam.put("parkId", parkId);
		mapParam.put("juriId", juriId);
		
		List<String> roles =  roledao.getJurisdiction(mapParam);
		
		if(roles!=null && roles.size()>0){
			
			return userdao.getUserListByRoleIds(roles);
		}
		
		return null;
		
	}
	
	@Override
	public EtopUser getSysJurisdiction(){
		
		EtopUser user =  new EtopUser();
		
		user.setId("1");;
		
		return user;
		
	}
	
	@Override
	public Boolean isHaveJurisdiction(String roleId,String juriId){
		
		Role role = roledao.getRoleInfo(roleId);
		
		if(role!=null){
			
			String ju = role.getRoleApproval();
			
			return ju==null?Boolean.FALSE: ju.contains(juriId);
			
		}
		
		return Boolean.FALSE;
		
	}
	
	@Override
	public Boolean isHaveJurisdiction(List<Role> roles,String juriId){
		
		if(roles!=null){
			
			for(Role r: roles){
				
					String ju = r.getRoleApproval();
					
					if( ju==null?Boolean.FALSE: ju.contains(juriId)){
						
						return Boolean.TRUE;
					}
				}
		}
		
		return Boolean.FALSE;
		
	}
}
