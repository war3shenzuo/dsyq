package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopCompanyStaff;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.PersonalInfomation;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Workexperience;
import com.etop.management.bean.PersonalInfomation.employeesSexType;
import com.etop.management.constant.SysStatus;
import com.etop.management.dao.EtopCompEmployeesDao;
import com.etop.management.dao.PersonalUserDao;
import com.etop.management.model.ContractListModel;
import com.etop.management.service.EtopCompEmployeesService;
import com.etop.management.service.PersonalUserService;
import com.etop.management.service.UserService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by lvxiwei.
 *
 * @author lvxiwei
 * @DATE 2016/10/17
 */
@Service
public class PersonalUserServiceImpl implements PersonalUserService {

    @Autowired
    private PersonalUserDao personalUserDao;

    @Autowired
    private UserService userService;

 

	@Override
	public void updateUserInfo(PersonalInfomation infomation) {
		
		infomation.setUpdatedAt(new Date());
		infomation.setUpdatedBy(UserInfoUtil.getUserInfo().getUserName());
		infomation.setEmployeesId(UserInfoUtil.getUserInfo().getCompanyId());
		personalUserDao.updateUserInfo(infomation);
		
	}



	@Override
	public EtopPage<Workexperience> getExperienceByemployeesId(String employeesId, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit,"work_start ASC");
		EtopPage<Workexperience> list = new EtopPage<Workexperience>(personalUserDao.getExperienceByemployeesId(employeesId));
		return list;
	}



	@Override
	public int add(Workexperience workexperience) {
		
		workexperience.setId(UUID.randomUUID().toString());
		workexperience.setEmployeesId(UserInfoUtil.getUserInfo().getCompanyId());
		return personalUserDao.add(workexperience);

	}



	@Override
	public void updateUserExperience(Workexperience workexperience) {
		workexperience.setEmployeesId(UserInfoUtil.getUserInfo().getCompanyId());
		personalUserDao.updateUserExperience(workexperience);
	}



	@Override
	public Workexperience getExperienceInfoById(String id) {

		Workexperience list = personalUserDao.getExperienceInfoById(id);
		return list;
	}



	@Override
	public int deleteExperience(String id) {
		
		return personalUserDao.deleteExperience(id);
	}



}
