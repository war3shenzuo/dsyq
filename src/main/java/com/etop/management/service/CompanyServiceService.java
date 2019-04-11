package com.etop.management.service;

import java.util.Map;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopBill;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/30
 */
public interface  CompanyServiceService {

    
    EtopPage<Companyservice> getServiceBycompanyId(Map<String, Object> condition, Integer offset, Integer limit);
    
    Companyservice getServiceInfoById(String id);
    
    void cancel(String serviceId, Companyservice companyservice);
    
	void approve(String serviceId, Companyservice companyservice);
	
	int statusOfSuer(Companyservice companyservice, String serviceId, EtopBill etopBill);
	
	   String getServiceType(String id);

	   EtopPage<Companyservice> getServiceByBillId(Map<String, Object> condition, Integer offset, Integer limit);
}
