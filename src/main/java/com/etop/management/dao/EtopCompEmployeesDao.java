package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopCompanyStaff;
import com.etop.management.bean.EtopPage;
import com.etop.website.bean.Settled;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
public interface EtopCompEmployeesDao {

    Page<EtopCompanyEmployees> select(EtopCompanyEmployees etopCompanyEmployees);

    //筛选该园区下未绑定的员工
    Page<EtopCompanyEmployees> searchEmployees(EtopCompanyEmployees etopCompanyEmployees);

    //删除公司所选员工关系
    Integer deleteById(@Param("employeesIds") List<String> employeesIds, @Param("companyId") List<String> companyId);

    //删除公司所有员工
    Integer delete(@Param("companyId") List<String> companyId);

    EtopCompanyEmployees getEtopCompEmployeesById(String id);

    //批量绑定公司、员工关系
    Integer insert(List<Map<String, Object>> map);

    //绑定公司、员工关系
    Integer insertEmpStaff(EtopCompanyStaff etopCompanyStaff);

    //判断账号是否绑定公司
    EtopCompanyEmployees getEmploeesById(@Param("account") String account);
    
    //新增一个用户补充信息
    void inserEmployees(EtopCompanyEmployees etopCompanyEmployees);

    Integer update(EtopCompanyEmployees etopCompanyEmployees);
    
    //筛选该公司下所有员工
    Page<EtopCompanyEmployees> ListbyCompanyId(Map<String, Object> condition);
    
    public void updateUserInfo(EtopCompanyEmployees employees);

}