package com.etop.website.service;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.ServiceDispatch;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceBusiness;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.Page;



public interface ServiceQuotationService{

	void changStatus(Companyservice companyservice, String serviceId);
	
	int add(EtopBill etopBill,Companyservice companyservice, String serviceId);
	
	void addQuotation(ServiceQuotation serviceQuotation) throws Exception;
	
	public List<EtopFloorRoom> selectRoom(String companyId);

	public EtopFloorRoom getPlace(EtopFloorRoom etopFloorRoom);
	
	public void addServiceBusiness(ServiceBusiness serviceBusiness, Parkservice parkservice) throws Exception;
	
	public List<ServiceQuotation> getCategoryList(ServiceQuotation serviceQuotation);
	
	public List<ServiceQuotation> getQuotationList(Map<String,String> map);
	
	public List<ServiceQuotation> getQuotationServiceList(Map<String,String> map);
	
	public List<ServiceQuotation> getQuotationRecruitmentList(Map<String,String> map);
	
	public Map<String, String> getQuotationServiceListtwo(String service_type);
	
	public List<ServiceQuotation> getCategory(ServiceQuotation serviceQuotation);
	
	public Map<String, String> getQuotation(String quotationId);
	
	
	EtopPage<ServiceQuotation> getServiceQuotation(Map<String, Object> condition, Integer offset, Integer limit);
	
	EtopPage<ServiceQuotation> getServiceQuotationGroup(Map<String, Object> condition, Integer offset, Integer limit);
	
    ServiceQuotation getQuotationInfoById(String quotationId);
    
    void updateQuotation (ServiceQuotation serviceQuotation);
    
    int deleteQuotation(String quotationId);
    
    int addQuotationService (ServiceQuotation serviceQuotation);
    
    EtopPage<Etopservice> getQuotationBusiness(Map<String, Object> condition, Integer offset, Integer limit);
    
    Etopservice getQuotationBusinessInfoById(String service_id);
    
    ServiceDispatch getDispatchInfoById(String service_id);
    
    void updateFinalPrice(ServiceBusiness serviceBusiness,Companyservice companyservice, String serviceId);
    
//    void statusOfSend(String service_id,Companyservice companyservice);
    
    void overOfSend(String service_id,Companyservice companyservice,ServiceDispatch serviceDispatch, Parkservice parkservice);
    
    void statusOfOver(String service_id,Companyservice companyservice, Etopservice etopservice);
    
    void addDispatch(ServiceDispatch serviceDispatch,ServiceBusiness serviceBusiness, String serviceId,Companyservice companyservice);
    
    
    String proveQuotationName(ServiceQuotation serviceQuotation);
//	public Map<String,String> getQuotationServiceList(String service_type);
}
