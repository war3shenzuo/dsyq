package com.etop.management.service;

import java.util.Map;

import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopCompanyStaff;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/30
 */
public interface  EtopCompEmployeesService {

    EtopPage<EtopCompanyEmployees> search(EtopCompanyEmployees etopCompanyEmployees, Integer offset, Integer limit);

    EtopPage<EtopCompanyEmployees> searchEmployees(EtopCompanyEmployees etopCompanyEmployees, Integer offset, Integer limit);

    EtopCompanyEmployees getEtopCompEmployeesById(String id);

    void deleteById(EtopCompanyEmployees etopCompanyEmployees);

    //批量绑定公司、员工关系
    void add(EtopCompanyStaff etopCompanyStaff);

    //绑定公司、员工关系
    void addEmpStaff(String companyId, String userId);

    //判断该账号是否绑定公司
    EtopCompanyEmployees getEmploeesById(EtopCompanyEmployees etopCompanyEmployees);

    ResultType addUser(EtopCompanyEmployees etopCompanyEmployees);

    void update(EtopCompanyEmployees etopCompanyEmployees);
    
    EtopPage<EtopCompanyEmployees> ListbyCompanyId(Map<String, Object> condition, Integer offset, Integer limit);
    
    void updateUserInfo(EtopCompanyEmployees employees);
}
