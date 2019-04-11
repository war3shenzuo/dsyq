package com.etop.website.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Remind;
import com.etop.management.bean.Role;
import com.etop.management.service.RemindService;
import com.etop.management.service.RoleService;
import com.etop.website.bean.Settled;
import com.etop.website.dao.SettledDao;
import com.etop.website.service.SettledService;




@Named("SettledService")
@Service
public class SettledServerImpl implements SettledService{

	@Resource
	SettledDao settledDao;
	
	@Resource
	RoleService roleService;
	
	@Resource
	RemindService remindService;
	
	public void addApply(Settled settled) throws Exception {
		settled.setId(UUID.randomUUID().toString());
		settled.setApply_time(new Date());
		settledDao.addApply(settled);
		
		EtopUser user = roleService.getSysJurisdiction();
		
		Remind remind = new Remind();
		
		remind.setRemindType(Role.QX_YQTX);
		remind.setContent("新园区组申请入驻");
		remind.setTarget(user.getId());
		remind.setTitle("有一家新园区组<"+ settled.getPark_name()+">申请入驻，请及时审核");
		remindService.addRemind(remind);

	}

	

}
