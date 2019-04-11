package com.etop.management.service;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.ResultType;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.etop.management.entity.EtopServiceType;
import com.etop.website.bean.ServiceQuotation;


/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceService<br>
 */
public interface EtopServiceService{
	
	
	EtopPage<EtopService> getServiceList(EtopService service);
	
	EtopService getServiceInfo(String serviceId);
	
	List<EtopServiceType> getServiceTypeList();
	
	
	
	EtopPage<EtopServiceType> getServiceTypeList(EtopServiceType type);
	
	int bindParkGroup(String parkGroupId,List<String> typeCodes);
	
	int  unbindParkGroup(String parkGroupId,List<String> typpeCodes);
	
	List<EtopServiceType> getServiceTypeListByPGId(EtopServiceType type);

	int addServiceType(EtopServiceType serviceType);

	int updateServiceType(EtopServiceType serviceType);

	EtopServiceType getServiceTypeInfo(EtopServiceType serviceType);

	int activeOrClosePark(EtopServiceType serviceType);

	ResultType updateStatus(String serviceId, int status, Companyservice companyservice, String message);

	void getFinishList(long difference);
	
	public void cronaddBill(String serviceId, Companyservice companyservice, String message) ;
	
	public void addBill(String serviceId, Companyservice companyservice, String message);
	
	EtopPage<Etopservice> calculateQuotation(Map<String, Object> condition, Integer offset, Integer limit);
	
    EtopPage<Companyservice> getServiceBycompanyId(Map<String, Object> condition, Integer offset, Integer limit);
}
