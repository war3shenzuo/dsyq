package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.Func;
import com.etop.management.bean.Role;
import com.etop.management.dao.FuncDao;
import com.etop.management.service.FuncService;
import com.etop.management.util.UserInfoUtil;

/**
 * 
 * 功能服务
 * 
 * @author shixianjie 上午10:52:19
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class FuncServiceImpl implements FuncService {

	@Resource
	FuncDao funcdao;
	
	private final static String dfuncId = "b10000,b10001,b10002,b20000,b20001,b20002,b20003,b20004,b30000,b30001,b30002";


	@Override
	public List<Func> getFuncList() throws Exception {
		
		List<Func> list = new ArrayList<Func>();
		
		list = funcdao.getFuncList();
		
		return list;
	}

	@Override
	public Func getFuncInfo(String funcId) throws Exception {

		return funcdao.getFuncInfo(funcId);
	}

	@Override
	public void addFunc(Func func) throws Exception {

		func.setId(UUID.randomUUID().toString());

		funcdao.addFunc(func);
	}

	@Override
	public void updateFunc(Func func) throws Exception {

		funcdao.updateFunc(func);

	}

	@Override
	public void activeOrClosePark(Func func) throws Exception {
		
		funcdao.activeOrClosePark(func);

	}

	@Override
	public List<Func> getFuncListByRoleId(String roleId) throws Exception {
		
		List<String> roleIds = new ArrayList<String>();
		
		roleIds.add(roleId);
		
		return funcdao.getFuncListByRoleId(roleIds);
	}
	
	@Override
	public List<Func> getFuncListByRoleIdNoParentId(String roleId) throws Exception {
		
		List<String> roleIds = new ArrayList<String>();
		
		roleIds.add(roleId);
		
		return funcdao.getFuncListByRoleIdNoParentId(roleIds);
	}

	@Override
	public List<Func> getFuncListRead(String roleId) throws Exception {
		
		List<String> roleIds = new ArrayList<String>();
		
		roleIds.add(roleId);
		
		return funcdao.getFuncListRead(roleIds);
	}

	@Override
	public boolean isReadOnly(String url) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		for(Role r:UserInfoUtil.getUserRoleInfo()){
			map.put("roleId",r.getId());
			map.put("url", url);
			
			if(Func.WRITE.equals(funcdao.getFuncisRead(map))){
				
				return Boolean.FALSE;
			}
			
		}
		
		return Boolean.TRUE;
	}

	@Override
	public List<Func> getTemporaryFuncList() {
		
		List<Func> list = funcdao.getFuncList();
		
		// 角色绑定功能
		List<Func> list2 = new ArrayList<Func>();
		
		for (Func f : list) {
			if (dfuncId.contains(f.getId())){
				list2.add(f);
			}
		}
		
		return list2;
	}

	@Override
	public List<Func> getFuncParkGroupList() {
		
		List<Func> list = funcdao.getFuncParkGroupList();
		
		return list;
		
	}



}
