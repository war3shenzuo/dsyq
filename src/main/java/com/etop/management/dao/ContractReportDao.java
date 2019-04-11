package com.etop.management.dao;

import java.util.List;

import com.etop.management.bean.ContractReport;
import com.github.pagehelper.Page;

/**
 * Created by Alan.
 * 合同报表
 *
 *
 * @author 何利庭
 * @DATE 2016/10/12
 */
public interface ContractReportDao {

    ContractReport search(ContractReport contractReport);//园区

    List<ContractReport> searchAmount(ContractReport contractReport);//不同账单金额

    Page<ContractReport> searchFloor(ContractReport contractReport);//楼

    List<ContractReport> searchAmountFloor(ContractReport contractReport);//楼下不同账单金额

    Page<ContractReport> searchStorey(ContractReport contractReport);//层

    List<ContractReport> searchAmountStorey(ContractReport contractReport);//层下不同账单金额

    Page<ContractReport> searchArea(ContractReport contractReport);//区

    List<ContractReport> searchAmountArea(ContractReport contractReport);//区下不同账单金额
}
