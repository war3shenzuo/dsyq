package com.etop.management.service;

import com.etop.management.bean.ContractReport;
import com.etop.management.bean.EtopPage;

/**
 * Created by Alan.
 * 费金回收
 *
 * @author 何利庭
 * @DATE 2016/10/12
 */
public interface FaiginService {

    EtopPage<ContractReport> search(ContractReport contractReport, String type);

}
