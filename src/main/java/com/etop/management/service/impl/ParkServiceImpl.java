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
import com.etop.management.bean.Park;
import com.etop.management.bean.Remind;
import com.etop.management.bean.Role;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.RoleDao;
import com.etop.management.service.ParkGroupService;
import com.etop.management.service.ParkService;
import com.etop.management.service.RemindService;
import com.etop.management.service.RoleService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ParkServiceImpl implements ParkService {
	
	@Resource
	ParkDao parkDao;
	
	@Resource
	ParkGroupService parkGroupService;
	
	@Resource
	RoleDao roleDao;
	
	@Resource
	RoleService roleService;
	
	@Resource
	RemindService remindService;

	@Override
	public EtopPage<Park> getParkList(Park park) throws Exception {

		EtopPage<Park> page = new EtopPage<Park>();
		
		park.setParkGroupId(UserInfoUtil.getUserParkInfo().getParkGroupId());

		int BTablePageNum = (park.getOffset() / park.getLimit())+ 1;

		// 设置分页
		PageHelper.startPage(BTablePageNum , park.getLimit());

		List<Park> list = new ArrayList<Park>();

		list = parkDao.getParkList(park);

		PageInfo<Park> table = new PageInfo<Park>(list);

		page.setRows(table.getList());
		page.setTotal(table.getTotal());

		return page;
	}
	
	@Override
	public List<String> getParkIdList() {

		List<String> list = new ArrayList<String>();

		list = parkDao.getParkIdList();
		
		return list;
	}

	@Override
	public List<Park> getParkNameList() {
		return parkDao.getParkNameList();
	}
	
	@Override
	public List<Park> getParkNameList2(String parkGroupId) {
		return parkDao.getParkNameList2(parkGroupId);
	}
	
	@Override
	public List<Park> getParkInfoList(String parkId) {
		return parkDao.getParkInfoList(parkId);
	}

	@Override
	public Park getParkInfo(String parkId) throws Exception {
		
		Park park = parkDao.getParkInfo(parkId);
		
		List<String> parkImgList = new ArrayList<String>();
		
		if(park!=null){
			if(park.getParkImg()!=null && !"".equals(park.getParkImg()) ){
				parkImgList = new ArrayList<String>(Arrays.asList(park.getParkImg().split(",")));
				park.setParkImgList(parkImgList);
			}else{
				park.setParkImgList(parkImgList);
			}
		}
		
		return park;
	}

	@Override
	public void addPark(Park park) throws Exception {
		if(park.getId()==null || "".equals(park.getId())){
			throw new RuntimeException("没有主键");
		}
		//创建园区
		park.setCreateAt(new Date());
		parkDao.addPark(park);
		
		//创建父角色
		Role parentRole = new Role();
		parentRole.setId(UUID.randomUUID().toString());
		parentRole.setCreateAt(new Date());
		parentRole.setRoleName(park.getParkName());
		parentRole.setRoleCode("PARK");
		parentRole.setRoleType("2");
		parentRole.setActivated("1");
		parentRole.setParkId(park.getId());
		parentRole.setRoleEscribe(park.getParkName());
		roleDao.addRole(parentRole);
		
		//创建子节点
		Role role = new Role();
		role.setParentId(parentRole.getId());
		role.setParkId(park.getId());
		role.setRoleCode(park.getParkCode());
		roleDao.addDefaultRole(role);
		
		//角色绑定功能
		List<Map<String,String>> list = null;
		
		Role roleParam = new Role();
		roleParam.setParentId(parentRole.getId());
		List<Role> roleList = roleDao.getRoleListByParentId(roleParam);
		
		for(Role r: roleList){
			list = new ArrayList<Map<String,String>>();
			String [] funcIds = r.getDefFunc().split(",");
			Map<String,String> funcMap = null;
			for(String f : funcIds){
				funcMap = new HashMap<String,String>();
				funcMap.put("id",UUID.randomUUID().toString());
				funcMap.put("funcId", f.substring(0,f.length()-1));
				funcMap.put("roleId", r.getId());
				funcMap.put("isRead", f.substring(f.length()-1,f.length()));
				list.add(funcMap);
			}
			roleDao.addFuncForRole(list);
			
		}
		
		//提醒
		EtopUser user = roleService.getSysJurisdiction();
		Remind remind = new Remind();
		remind.setRemindType(Role.QX_YQTX);
		remind.setContent("新园区建立");
		remind.setTarget(user.getId());
		
		String pgname = parkGroupService.getParkGroupInfo(park.getParkGroupId()).getParkGroupName();
		
		remind.setTitle(pgname+"创建了新园区。");
		remindService.addRemind(remind);
	}

	@Override
	public void activeOrClosePark(Park park) throws Exception {
		
		parkDao.activeOrClosePark(park);

	}

	@Override
	public void updatePark(Park park) throws Exception{
		
		parkDao.updatePark(park);

	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getParkListForControl() {
		
		String parkGroupId = UserInfoUtil.getUserParkInfo().getParkGroupId();
		
		return parkDao.getParkListForControl(parkGroupId);
	}
	
	
	@Override
	public List<String> getAllParkId(){
		
		String parkGroupId = UserInfoUtil.getUserParkInfo().getParkGroupId();
		
		List<Park> parks = parkDao.getAllParkForParkGroupId(parkGroupId);
		
		List<String> parkIds = new ArrayList<String>();
		for(Park park :parks){
			parkIds.add(park.getId());
		}
		
		return parkIds;
	}

	@Override
	public List<Park> getParkName(List<String> parkIds) {
		if(parkIds != null && parkIds.size() > 0){
			return parkDao.getParkName(parkIds);
		}
		return new ArrayList<Park>();
	}

	@Override
	public List<Park> getParkInfoByCode(String parkCode) throws Exception {
		
		return parkDao.getParkInfoByCode(parkCode);
	}


}
