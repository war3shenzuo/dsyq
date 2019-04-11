package com.etop.management.service;

import java.util.Map;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Etopservice;
import com.etop.website.bean.ServiceBusiness;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceRecruitment;
import com.etop.website.bean.ServiceQuotation;


/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceRecruitmentService<br>
 */
public interface EtopServiceRecruitmentService{

	EtopPage<EtopService> getServiceList(EtopService service);
	EtopPage<EtopService> getServiceList(Map<String, Object> condition, int offset, int limit);

	EtopServiceRecruitment getServicePurchaseInfo(String serviceId);
	public Etopservice querysByServiceId(String serviceId);

	EtopService getServiceInfo(String serviceId);

	void add(EtopServiceRecruitment param, EtopService service,ServiceBusiness serviceBusiness, EtopFloorRoom etopFloorRoom);
	
	EtopPage<ServiceQuotation> makeRecruitment(Map<String, Object> condition, Integer offset, Integer limit);
	
	EtopPage<ServiceQuotation> makeRecruitmentGroup(Map<String, Object> condition, Integer offset, Integer limit);
	
    int addRecruitment (ServiceQuotation serviceQuotation);

}
