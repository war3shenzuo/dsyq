package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Func;
import com.etop.management.bean.Park;
import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.ParkGroupPresentation;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.bean.Settled;
import com.etop.management.dao.FuncDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.dao.RoleDao;
import com.etop.management.dao.UserDao;
import com.etop.management.service.ParkGroupService;
import com.etop.management.util.EncoderHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ParkGroupServiceImpl implements ParkGroupService {

	@Resource
	private ParkGroupDao parkGroupDao;

	@Resource
	UserDao userDao;

	@Resource
	RoleDao roleDao;

	@Resource
	FuncDao funcDao;
	
	@Resource
	ParkDao parkDao;
	
	private static final String pfuncIds = "b10000,b10001,b10002,b20000,b20001,b20002,b20003,b20004,b30000,b30001,b30002,b40000,b40001,b50000,b50001,b60000,b60001,b50001,b70000,b70001,b80000,b80002,b90000,b90002";
	
	private static final String pfuncIds1 = "b20000,b20001,b20002,b20003,b20004";

	private static final String pfuncIds2 = "b90000,b90001,b90002,b10000,b10001,b10002,b30000,b30001,b30002,b100000,b100001,b100002,b100003";
	
	@Override
	public EtopPage<ParkGroup> getParkGroupList(ParkGroup group) throws Exception {

		List<ParkGroup> list = parkGroupDao.getParkGroupList(group);

		EtopPage<ParkGroup> page = new EtopPage<ParkGroup>();

		page.setRows(list);

		page.setTotal(list.size());

		return page;
	}

	@Override
	public ParkGroup getParkGroupInfo(String parkGroupId) throws Exception {

		ParkGroup info = parkGroupDao.getParkGroupInfo(parkGroupId);
		
		if(info.getParkGroupImg()!=null){
			info.setImgList(Arrays.asList(info.getParkGroupImg().split(",")));
		}else{
			info.setImgList(new ArrayList<String>());
		}

		return info;
	}

	@Override
	public ResultType addParkGroup(ParkGroup param,Settled settled) throws Exception {
		
		
		
		if(parkGroupDao.getParkGroupInfoByCode(param.getParkGroupCode())!=null){
			
			return ResultType.getFail("创建失败！编号重复");
		}
		

		if(userDao.getUserInfoByUserName(param.getApproval())!=null){
			
			return ResultType.getFail("无法通过，手机已注册！");
		}
		
		if(settled.getId()==null){
			
			settled.setId(UUID.randomUUID().toString());
			settled.setParkName(param.getParkGroupName());
			settled.setApplyTime(new Date());
			
			//创建入驻信息
			parkGroupDao.addSettled(settled);
		}
		
		param.setSettledId(settled.getId());
		
		param.setCreateAt(new Date());
		// 创建园区
		parkGroupDao.addParkGroup(param);
		
		// 创建帐号
		EtopUser user = new EtopUser();
		user.setUserName(param.getApproval());
		String passWordSalt = EncoderHandler.getRandomString(32);
		user.setPassWord(EncoderHandler.getSHA1("123456" + passWordSalt));
		user.setPassWordSalt(passWordSalt);
		user.setId(UUID.randomUUID().toString());
		user.setCreateAt(new Date());
		user.setParkId(param.getId());
		user.setActivated("1");
		user.setUserType(EtopUser.TYPE_GROUP);
		userDao.addUserInfo(user);
		
		// 创建观察帐号
		EtopUser user1 = new EtopUser();
		user1.setUserName(param.getParkGroupCode()+"ob");
		user1.setPassWord(EncoderHandler.getSHA1("123456" + passWordSalt));
		user1.setPassWordSalt(passWordSalt);
		user1.setId(UUID.randomUUID().toString());
		user1.setCreateAt(new Date());
		user1.setParkId(param.getId());
		user1.setActivated("1");
		user1.setUserType(EtopUser.TYPE_GROUP);
		userDao.addUserInfo(user1);
		
		// 创建操作员
		EtopUser user2 = new EtopUser();
		user2.setUserName(param.getParkGroupCode()+"op");
		user2.setPassWord(EncoderHandler.getSHA1("123456" + passWordSalt));
		user2.setPassWordSalt(passWordSalt);
		user2.setId(UUID.randomUUID().toString());
		user2.setCreateAt(new Date());
		user2.setParkId(param.getId());
		user2.setActivated("1");
		user2.setUserType(EtopUser.TYPE_GROUP);
		userDao.addUserInfo(user2);

		// 创建父角色
		Role parentRole = new Role();
		parentRole.setId(UUID.randomUUID().toString());
		parentRole.setCreateAt(new Date());
		parentRole.setRoleName(param.getParkGroupName());
		String t = String.valueOf(System.currentTimeMillis());
		parentRole.setRoleCode("gpmanager"+t.substring(t.length()-6,t.length()));
		parentRole.setRoleType("1");
		parentRole.setActivated("1");
		parentRole.setParkId(param.getId());
		parentRole.setRoleEscribe(param.getParkGroupName());
		roleDao.addRole(parentRole);

		// 创建角色
		Role role = new Role();
		role.setId(UUID.randomUUID().toString());
		role.setCreateAt(new Date());
		role.setRoleName("管理员");
		role.setRoleCode("groupadmin"+t.substring(t.length()-6,t.length()));
		role.setRoleType("1");
		role.setActivated("1");
		role.setParentId(parentRole.getId());
		role.setParkId(param.getId());
		role.setRoleEscribe(param.getParkGroupName());
		roleDao.addRole(role);
		
		// 创建观察角色
		Role role1 = new Role();
		role1.setId(UUID.randomUUID().toString());
		role1.setCreateAt(new Date());
		role1.setRoleName("观察员");
		role1.setRoleCode("groupob"+t.substring(t.length()-6,t.length()));
		role1.setRoleType("1");
		role1.setActivated("1");
		role1.setParentId(parentRole.getId());
		role1.setParkId(param.getId());
		role1.setRoleEscribe(param.getParkGroupName());
		roleDao.addRole(role1);
		
		// 创建操作者角色
		Role role2 = new Role();
		role2.setId(UUID.randomUUID().toString());
		role2.setCreateAt(new Date());
		role2.setRoleName("操作员");
		role2.setRoleCode("groupop"+t.substring(t.length()-6,t.length()));
		role2.setRoleType("1");
		role2.setActivated("1");
		role2.setParentId(parentRole.getId());
		role2.setParkId(param.getId());
		role2.setRoleEscribe(param.getParkGroupName());
		roleDao.addRole(role2);

		// 角色绑定用户
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", UUID.randomUUID().toString());
		map.put("userId", user.getId());
		map.put("roleId", role.getId());
		roleDao.addUserForRole(map);
		
		// 角色绑定用户
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("id", UUID.randomUUID().toString());
		map1.put("userId", user1.getId());
		map1.put("roleId", role1.getId());
		roleDao.addUserForRole(map1);
		
		// 角色绑定用户
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("id", UUID.randomUUID().toString());
		map2.put("userId", user2.getId());
		map2.put("roleId", role2.getId());
		roleDao.addUserForRole(map2);

		// 获取所有功能ID
		List<Func> funcIds = funcDao.getFuncParkGroupList();
		// 角色绑定功能
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> funcMap = null;
		for (Func f : funcIds) {
				funcMap = new HashMap<String, String>();
				funcMap.put("id", UUID.randomUUID().toString());
				funcMap.put("funcId", f.getId());
				funcMap.put("roleId", role.getId());
				funcMap.put("isRead", "0");
				list.add(funcMap);
		}
		
		// 角色绑定功能
		List<Map<String, String>> list1 = new ArrayList<Map<String, String>>();
		for (Func f : funcIds) {
			if (!pfuncIds1.contains(f.getId())){
				funcMap = new HashMap<String, String>();
				funcMap.put("id", UUID.randomUUID().toString());
				funcMap.put("funcId", f.getId());
				funcMap.put("roleId", role1.getId());
				funcMap.put("isRead", "0");
				list1.add(funcMap);
			}
		}
		
		// 角色绑定功能
		List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
		for (Func f : funcIds) {
			if (pfuncIds2.contains(f.getId())){
				funcMap = new HashMap<String, String>();
				funcMap.put("id", UUID.randomUUID().toString());
				funcMap.put("funcId", f.getId());
				funcMap.put("roleId", role2.getId());
				System.out.println(f.getId());
				if("b90000".equals(f.getId())||"b90001".equals(f.getId())||"b90002".equals(f.getId())||"b100000".equals(f.getId())||"b100001".equals(f.getId())||"b100002".equals(f.getId())||"b100003".equals(f.getId())){
						
					funcMap.put("isRead", "1");
				}else{
					funcMap.put("isRead", "0");
				}
				list2.add(funcMap);
			}
		}
		
		roleDao.addFuncForRole(list);
		
		roleDao.addFuncForRole(list1);
		
		roleDao.addFuncForRole(list2);

		
		return ResultType.getSuccess("审核成功");

	}

	@Override
	public void stopParkGroup(ParkGroup param) throws Exception {

		// 查询所有园区组下的管理账号
		EtopUser user = new EtopUser();
		user.setParkGroupId(param.getId());
		List<EtopUser> sysuser = userDao.getUserList(user);
		if (sysuser != null && sysuser.size() > 0) {
			List<String> sysUserId = new ArrayList<String>();
			for (EtopUser u : sysuser) {
				sysUserId.add(u.getId());
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("activated", param.getActivated());
			map.put("userList", sysUserId);
			// 关闭地下所有园区，客户，员工的权限
			parkGroupDao.stopGroupUser(map);

		}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("activated", param.getActivated());
			map.put("parkGroupId", param.getId());
			// 关闭地下所有园区，客户，员工的权限
			parkGroupDao.stopPark(map);


		// 修改园区组状态
		parkGroupDao.stopParkGroup(param);

	}

	@Override
	public List<ParkGroup> getParkGroupListForControl() {

		return parkGroupDao.getParkGroupListForControl();
	}
	@Override
	public List<ParkGroup> getParkGroupListForControl2() {
		
		return parkGroupDao.getParkGroupListForControl2();
	}

	@Override
	public EtopPage<Settled> getSettledList(Settled settled) {

		EtopPage<Settled> page = new EtopPage<Settled>();

		int BTablePageNum = (settled.getOffset() / settled.getLimit()) + 1;

		// 设置分页
		PageHelper.startPage(BTablePageNum, settled.getLimit());

		List<Settled> list = new ArrayList<Settled>();

		list = parkGroupDao.getSettledList(settled);

		PageInfo<Settled> table = new PageInfo<Settled>(list);

		page.setRows(table.getList());
		page.setTotal(table.getTotal());

		return page;
	}

	@Override
	public Settled getSettledInfo(String settledId) {

		return parkGroupDao.getSettledInfo(settledId);
	}

	@Override
	public ResultType updateParkGroup(ParkGroup param) {
		
		ParkGroup pg =  parkGroupDao.getParkGroupInfoByCode(param.getParkGroupCode());
			if(pg != null){
				if(!param.getId().equals(pg.getId())){
					return ResultType.getFail("更新失败！编号重复");
				}
				parkGroupDao.updateParkGroup(param);
			}else{
				parkGroupDao.updateParkGroup(param);
			}
		return ResultType.getSuccess("更新成功");
	}
	@Override
	public EtopPage<ParkGroupPresentation> getPresentationByParkGroupId(
			String parkGroupId, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit);
		EtopPage<ParkGroupPresentation> list = new EtopPage<ParkGroupPresentation>(parkGroupDao.getPresentationByParkGroupId(parkGroupId));
		return list;
	}

	@Override
	public int deletePresentation(String id) {
		
		return parkGroupDao.deletePresentation(id);
	}

	@Override
	public void updatePresentation(ParkGroupPresentation presentation) {


		parkGroupDao.updatePresentation(presentation);
		
	}

	@Override
	public ParkGroupPresentation getPresentationInfoById(String id) {
		
		return parkGroupDao.getPresentationInfoById(id);
	}

	@Override
	public int add(ParkGroupPresentation presentation) {

		presentation.setId(UUID.randomUUID().toString());
//		presentation.setPresentation(1);
		return parkGroupDao.add(presentation);
	}
}
