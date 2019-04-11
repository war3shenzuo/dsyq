package com.etop.website.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.ServiceDispatch;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.website.bean.Floor;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceBusiness;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.Page;



@Repository
public interface ServiceQuotationDao {

	
	void changStatus(Companyservice companyservice);
	
	int add(EtopBill etopBill);
	
	public void addQuotation(ServiceQuotation serviceQuotation);
	
	public List<EtopFloorRoom> selectRoom(String companyId);
	
	public EtopFloorRoom getPlace(EtopFloorRoom etopFloorRoom);

	public void addServiceBusiness(ServiceBusiness serviceBusiness);
	
	public EtopFloorRoom getQuotation(EtopFloorRoom etopFloorRoom);
	
	public List<ServiceQuotation> getCategoryList(ServiceQuotation serviceQuotation);
	
	public List<ServiceQuotation> getQuotationList(Map<String,String> map);
	
	public List<ServiceQuotation> getQuotationServiceList(Map<String,String> map);
	
	public List<ServiceQuotation> getQuotationRecruitmentList(Map<String,String> map);
	
	public Map<String, String> getQuotationServiceListtwo(String service_type);
	
	public List<ServiceQuotation> getCategory(ServiceQuotation serviceQuotation);
	
	public Map<String, String> getQuotation(String quotationId);
	
	//获取楼层区房间名
	 String searchFloorId(String refFloorId);
	 
	 String searchStoreyId(String refFloorId);
	 
	 String searchAreaId(String refStoreyId);
	 
	 
	
    Page<ServiceQuotation> getServiceQuotation(Map<String, Object> condition);
    
    Page<ServiceQuotation> getServiceQuotationGroup(Map<String, Object> condition);
    
    ServiceQuotation getQuotationInfoById(String quotationId);
    
    void updateQuotation (ServiceQuotation serviceQuotation);
    
    int deleteQuotation(String quotationId);
    
    int addQuotationService (ServiceQuotation serviceQuotation);
    
    Page<Etopservice> getQuotationBusiness(Map<String, Object> condition);
    
    Etopservice getQuotationBusinessInfoById(String service_id);
    
    ServiceDispatch getDispatchInfoById(String service_id);
    
    void updateFinalPrice(ServiceBusiness serviceBusiness);
    
    void statusOfSend(String service_id);
    
    void overOfSend(Parkservice parkservice);
    
    void statusOfOver(String service_id);
    
    void addDispatch(ServiceDispatch serviceDispatch);
    
    String proveQuotationName(ServiceQuotation serviceQuotation);
//	public Map<String,String> getQuotationServiceList(String service_type);
 }
