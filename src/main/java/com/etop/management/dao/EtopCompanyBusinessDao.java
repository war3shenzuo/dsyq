package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.EtopCompanyBusiness;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
public interface EtopCompanyBusinessDao {

    Integer insert(@Param("etopCompanyBusiness") List<EtopCompanyBusiness> etopCompanyBusiness);

    Integer deleteByIds(@Param("companyIds") List<String> companyIds);

    List<EtopCompanyBusiness> getCompBusiness(@Param("companyId") String companyId);

    Integer addInfo(EtopCompanyBusiness etopCompanyBusiness);

}