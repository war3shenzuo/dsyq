package com.etop.management.service;

import java.util.Map;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.ServiceDispatch;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceQuotation;



public interface BusinessServiceService{

	EtopPage<ServiceQuotation> makeBusinessService(Map<String, Object> condition, Integer offset, Integer limit);
	
	EtopPage<ServiceQuotation> makeBusinessServiceGroup(Map<String, Object> condition, Integer offset, Integer limit);
	
    int addBusinessService (ServiceQuotation serviceQuotation);
    
    EtopPage<Etopservice> getBusinessService(Map<String, Object> condition, Integer offset, Integer limit);
    
    EtopPage<ServiceDispatch> dispatchList(Map<String, Object> condition, Integer offset, Integer limit);
	

}
