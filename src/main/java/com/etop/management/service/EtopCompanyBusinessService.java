package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopCompanyBusiness;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/30
 */
public interface EtopCompanyBusinessService {

    Integer add(String etopCompanyBusiness);

    List<EtopCompanyBusiness> getCompBusiness(String companyId);

    Integer deleteById(String companyId);

    Integer addInfo(String companyId);
}
