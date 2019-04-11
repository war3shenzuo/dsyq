package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopCompanyStaff;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.SysStatus;
import com.etop.management.dao.EtopCompEmployeesDao;
import com.etop.management.service.EtopCompEmployeesService;
import com.etop.management.service.UserService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
@Service
public class EtopCompEmployeesServiceImpl implements EtopCompEmployeesService {

    @Autowired
    private EtopCompEmployeesDao etopCompEmployeesDao;

    @Autowired
    private UserService userService;

    @Override
    public EtopPage<EtopCompanyEmployees> search(EtopCompanyEmployees etopCompanyEmployees, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "updated_at DESC");
        etopCompanyEmployees.setParkId(UserInfoUtil.getUserInfo().getParkId());
        Page<EtopCompanyEmployees> list = etopCompEmployeesDao.select(etopCompanyEmployees);

        return new EtopPage<EtopCompanyEmployees>(list);
    }

    @Override
    public EtopPage<EtopCompanyEmployees> searchEmployees(EtopCompanyEmployees etopCompanyEmployees, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "updated_at DESC");

        Page<EtopCompanyEmployees> list = etopCompEmployeesDao.searchEmployees(etopCompanyEmployees);

        return new EtopPage<EtopCompanyEmployees>(list);
    }

    @Override
    public EtopCompanyEmployees getEtopCompEmployeesById(String id) {
    	EtopCompanyEmployees  list =etopCompEmployeesDao.getEtopCompEmployeesById(id);
        return list;
    }

    @Override
    public void deleteById(EtopCompanyEmployees etopCompanyEmployees) {

        List<String> employeesIds = etopCompanyEmployees.getEmployeesIds();
        etopCompEmployeesDao.deleteById(employeesIds, etopCompanyEmployees.getCompany_id());

    }

    /**
     * 批量绑定一家公司下多名员工
     *
     * @param etopCompanyStaff
     */
    @Override
    public void add(EtopCompanyStaff etopCompanyStaff) {

        List<Map<String,Object>> newRoles = new ArrayList<Map<String,Object>>();
        List<String> userIds = etopCompanyStaff.getUserIds();
        String companyId = etopCompanyStaff.getCompanyId();
        for (int i=0; i<userIds.size(); i++){
            Map<String,Object> map = new HashMap<>();
            Date now = new Date();
            map.put("id", UUID.randomUUID().toString());
            map.put("companyId",companyId);
            map.put("userId", userIds.get(i));
            map.put("createdAt",now);
            map.put("createdBy", UserInfoUtil.getUserInfo().getId());
            newRoles.add(map);
        }
        etopCompEmployeesDao.insert(newRoles);
    }

    @Override
    public void addEmpStaff(String companyId, String userId) {
        EtopCompanyStaff etopCompanyStaff = new EtopCompanyStaff();
        Date now = new Date();
        etopCompanyStaff.setCompanyId(companyId);
        etopCompanyStaff.setUserId(userId);
        etopCompanyStaff.setId(UUID.randomUUID().toString());
        etopCompanyStaff.setCreatedAt(now);
        etopCompanyStaff.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompanyStaff.setUpdatedAt(now);
        etopCompEmployeesDao.insertEmpStaff(etopCompanyStaff);
    }

    @Override
    public EtopCompanyEmployees getEmploeesById(EtopCompanyEmployees etopCompanyEmployees) {
        return etopCompEmployeesDao.getEmploeesById(etopCompanyEmployees.getAccount());
    }

    @Override
    public ResultType addUser(EtopCompanyEmployees etopCompanyEmployees) {

        try {
            EtopUser newUser = new EtopUser();
            newUser.setUserType(EtopUser.PERSONAL);
            newUser.setPassWord(EtopUser.INITIAL_PASSWORD);
            newUser.setUserName(etopCompanyEmployees.getMobile());
            newUser.setMobile(etopCompanyEmployees.getMobile());
            newUser.setParkId(UserInfoUtil.getUserInfo().getParkId());
            ResultType resultType =  userService.registerUser(newUser, "tongguo");//新增User表
            if(resultType.getStatus() == SysStatus.FAIL.getStatus()){
                return resultType;
            }

            EtopCompanyStaff etopCompanyStaff = new EtopCompanyStaff();
            Date now = new Date();
            etopCompanyStaff.setCompanyId(etopCompanyEmployees.getCompanyId());
            etopCompanyStaff.setUserId(newUser.getCompanyId());
            etopCompanyStaff.setId(UUID.randomUUID().toString());
            etopCompanyStaff.setCreatedAt(now);
            etopCompanyStaff.setCreatedBy(UserInfoUtil.getUserInfo().getId());
            etopCompanyStaff.setUpdatedAt(now);
            etopCompEmployeesDao.insertEmpStaff(etopCompanyStaff);//新增关系表

            etopCompanyEmployees.setEmployeesId(newUser.getCompanyId());
            etopCompanyEmployees.setEmployeesStatus(1);
            etopCompanyEmployees.setCreatedAt(now);
            etopCompanyEmployees.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
            etopCompanyEmployees.setUpdatedAt(now);
            etopCompanyEmployees.setHiredate(now);
            etopCompEmployeesDao.update(etopCompanyEmployees);//更新用户信息

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultType.getSuccess("新增员工成功! ");
    }

    @Override
    public void update(EtopCompanyEmployees etopCompanyEmployees) {

        etopCompanyEmployees.setId(UUID.randomUUID().toString());
        etopCompanyEmployees.setEmployeesStatus(1);
        etopCompanyEmployees.setCreatedAt(new Date());
        etopCompanyEmployees.setUpdatedAt(new Date());
        etopCompanyEmployees.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompEmployeesDao.update(etopCompanyEmployees);

    }


	@Override
	public EtopPage<EtopCompanyEmployees> ListbyCompanyId(
			Map<String, Object> condition, Integer offset,
			Integer limit) {
		PageHelper.offsetPage(offset, limit,"hiredate DESC");
	
				EtopPage<EtopCompanyEmployees> list = new EtopPage<EtopCompanyEmployees>(etopCompEmployeesDao.ListbyCompanyId(condition));
				
				return list;
	}

	@Override
	public void updateUserInfo(EtopCompanyEmployees employees) {
		
		employees.setUpdatedAt(new Date());
		employees.setUpdatedBy(UserInfoUtil.getUserInfo().getUserName());
		etopCompEmployeesDao.update(employees);
		
	}


}
