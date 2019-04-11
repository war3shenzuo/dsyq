package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.model.CompanyModel;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/30
 */
public interface EtopCompanyService {

    EtopPage<EtopCompany> search(EtopCompany etopCompany, Integer offset, Integer limit, String genre);
    
    EtopPage<EtopCompany> search2(EtopCompany etopCompany, Integer offset, Integer limit, String genre);

    /**
     * 查找园区公司
     * @param parkId
     * @param searchValue
     * @return
     */
    List<CompanyModel> searchCompany(String parkId,String searchValue);

    ResultType add(EtopCompany newEtopCompany);

    void  addInfo(EtopCompanyIntention etopCompanyIntention);

    void deleteById(EtopCompany etopCompany, List<String> additionalCompanyIds);

    ResultType updateById(EtopCompany etopCompany, String genre);

    //退园（将公司状态改为4）
    void updateStatus(EtopCompany etopCompany);

    EtopCompany getCompInfoById(String id);

    //获取当前园区下所有公司列表
    EtopPage<EtopCompany> getCompanyByParkId(EtopCompany etopCompany, Integer offset, Integer limit);

    ResultType generatedAccount(String companyId);

    Integer selectEmployeesNums(String companyId);
}
