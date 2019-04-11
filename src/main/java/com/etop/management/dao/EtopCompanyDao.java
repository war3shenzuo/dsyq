package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopCompany;
import com.etop.management.model.CompanyModel;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
public interface EtopCompanyDao {

    Page<EtopCompany> select(EtopCompany etopCompany);

    Integer insert(EtopCompany etopCompany);

    Integer insertInfo(EtopCompany etopCompany);

    Integer deleteById(@Param("companyIds") List<String> companyIds);

    Integer updateById(EtopCompany etopCompany);

    EtopCompany getCompInfoById(String id);

    Page<EtopCompany> getCompanyByParkId(@Param("parkIds") List<String> parkIds);
    
    List<CompanyModel> searchCompany(@Param("parkId")String parkId, @Param("searchValue")String searchValue);

    EtopCompany getCompanyNum(@Param("parkId") String parkId);

    Integer selectEmployeesNums(@Param("companyId") String companyId);

    EtopCompany selectCompany(@Param("companyName") String companyName, @Param("companyId") String companyId);
    
    List<EtopCompany> selectCompanyList();
    
    Integer updateBycompanyId(String companyId);
    
    String getCompanyName(String id);
    
    Integer updateOfTurnover(EtopCompany etopCompany);
}