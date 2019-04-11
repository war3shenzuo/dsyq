package com.etop.website.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.ServiceDispatch;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceBusiness;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.dao.ParkserviceDao;
import com.etop.website.dao.ServiceQuotationDao;
import com.etop.website.service.ServiceQuotationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;



@Service
public class ServiceQuotationServerImpl implements ServiceQuotationService{

	@Resource
	ServiceQuotationDao serviceQuotationDao;
	@Resource
	ParkserviceDao parkserviceDao;
	@Resource
	EtopBillDao etopBillDao;
	
	@Override
	public void changStatus(Companyservice companyservice, String serviceId) {
//		companyservice.setServiceId(service_id);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(serviceId).getChanges())+"XX");
        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
        changes.append( sdf.format(new Date())+"\r");
	  companyservice.setChanges(changes.toString());
	  serviceQuotationDao.changStatus(companyservice);
		
	}

	@Override
	public int add(EtopBill etopBill,Companyservice companyservice, String service_id) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(service_id).getChanges())+"出账：");
        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
        changes.append( sdf.format(new Date())+"\r");
	    companyservice.setChanges(changes.toString());
	    companyservice.setServiceId(service_id);
		serviceQuotationDao.changStatus(companyservice);
		return etopBillDao.add(etopBill);
	}
	
	@Override
	public void addQuotation(ServiceQuotation serviceQuotation)
			throws Exception {
		serviceQuotation.setQuotationId(UUID.randomUUID().toString());
		serviceQuotationDao.addQuotation(serviceQuotation);
	}




	@Override
	public List<EtopFloorRoom> selectRoom(String companyId) {

		return serviceQuotationDao.selectRoom(UserInfoUtil.getUserInfo().getCompanyId());
	}




	@Override
	public EtopFloorRoom getPlace(EtopFloorRoom etopFloorRoom) {

		return serviceQuotationDao.getPlace(etopFloorRoom);
	}




	@Override
	public void addServiceBusiness(ServiceBusiness serviceBusiness, Parkservice parkservice)
			throws Exception {
		
//		ServiceBusiness items=parkserviceDao.addService(parkservice);		
		
		serviceBusiness.setBusinessId(UUID.randomUUID().toString());
//		serviceBusiness.setServiceId(items.getServiceId());
		serviceQuotationDao.addServiceBusiness(serviceBusiness);
	}




	@Override
	public List<ServiceQuotation> getCategoryList(ServiceQuotation serviceQuotation) {
		EtopUser user = UserInfoUtil.getUserInfo();
		String parkId = user.getParkId();
		serviceQuotation.setParkId(parkId);
		return serviceQuotationDao.getCategoryList(serviceQuotation);
	}
	
	@Override
	public List<ServiceQuotation> getQuotationList(Map<String,String> map) {
		
		List<ServiceQuotation> list = serviceQuotationDao.getQuotationList(map);
		return list;
	}


	@Override
	public List<ServiceQuotation> getCategory(ServiceQuotation serviceQuotation) {
		
		List<ServiceQuotation> m =	serviceQuotationDao.getCategory(serviceQuotation);
		return m;
	}

	@Override
	public Map<String, String> getQuotation(String quotationId) {
		
		Map<String, String> m =	serviceQuotationDao.getQuotation(quotationId);

		return m;
	}




	@Override
	public EtopPage<ServiceQuotation> getServiceQuotation(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit);
		EtopPage<ServiceQuotation> list = new EtopPage<ServiceQuotation>(serviceQuotationDao.getServiceQuotation(condition));
		return list;
	}
	@Override
	public EtopPage<ServiceQuotation> getServiceQuotationGroup(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit);
		EtopPage<ServiceQuotation> list = new EtopPage<ServiceQuotation>(serviceQuotationDao.getServiceQuotationGroup(condition));
		return list;
	}




	@Override
	public ServiceQuotation getQuotationInfoById(String quotationId) {
		ServiceQuotation list = serviceQuotationDao.getQuotationInfoById(quotationId);
		return list;
	}




	@Override
	public void updateQuotation(ServiceQuotation serviceQuotation) {
		serviceQuotationDao.updateQuotation(serviceQuotation);
		
	}




	@Override
	public int deleteQuotation(String quotationId) {
	 return	serviceQuotationDao.deleteQuotation(quotationId);
	}




	@Override
	public int addQuotationService(ServiceQuotation serviceQuotation) {
	
		serviceQuotation.setQuotationId(UUID.randomUUID().toString());
		serviceQuotation.setType("repair");
		serviceQuotation.setCreateTime(new Date());
		serviceQuotation.setParkId(UserInfoUtil.getUserInfo().getParkId());
		serviceQuotation.setActivated("1");
		return serviceQuotationDao.addQuotationService(serviceQuotation);
	}




	@Override
	public EtopPage<Etopservice> getQuotationBusiness(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit,"apply_time DESC");
//		EtopPage<Parkservice> list = new EtopPage<Parkservice>(serviceQuotationDao.getQuotationBusiness(condition));
		Page<Etopservice> page = serviceQuotationDao.getQuotationBusiness(condition);
		for(Etopservice parkservice : page){
			if(parkservice.getFinalPrice() !=null){
				parkservice.setTotalPrice(parkservice.getFinalPrice());
			}else{
				parkservice.setTotalPrice(parkservice.getTotalPrice());
			}
		}
		EtopPage<Etopservice> list = new EtopPage<Etopservice>(page);
		return list;
	}




	@Override
	public Etopservice getQuotationBusinessInfoById(String serviceId) {
	
		return serviceQuotationDao.getQuotationBusinessInfoById(serviceId);
	}




	@Override
	public void updateFinalPrice(ServiceBusiness serviceBusiness,Companyservice companyservice, String serviceId) {

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
	        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(serviceId).getChanges())+"发布报价：");
	        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
	        changes.append( sdf.format(new Date())+"\r");
		  companyservice.setChanges(changes.toString());
		  serviceQuotationDao.changStatus(companyservice);
		  int c   = serviceBusiness.getTotalPrice().compareTo(serviceBusiness.getFinalPrice());
		  if(c==0){
			  serviceBusiness.setServiceStatus("202");
		  }else{
			  serviceBusiness.setServiceStatus("102");
		  }

		serviceQuotationDao.updateFinalPrice(serviceBusiness);
		
	}




//	@Override
//	public void statusOfSend(String service_id,Companyservice companyservice) {
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//        StringBuffer changes =new  StringBuffer();
//	        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(service_id).getChanges()));
//	        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
//	        changes.append( sdf.format(new Date())+"\r");
//		  companyservice.setChanges(changes.toString());
//		  serviceQuotationDao.changStatus(companyservice);
//		serviceQuotationDao.statusOfSend(service_id);
//		
//	}

	@Override
	public void overOfSend(String service_id,Companyservice companyservice,ServiceDispatch serviceDispatch, Parkservice parkservice) {
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
	        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(service_id).getChanges())+"完工：");
	        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
	        changes.append( sdf.format(new Date())+"\r");
		  companyservice.setChanges(changes.toString());
		  companyservice.setServiceId(parkservice.getService_id());
		  serviceQuotationDao.changStatus(companyservice);
		  parkservice.setCompleteTime(new Date());
		  parkservice.setComplete_time(new Date());
		  serviceDispatch.setServiceId(parkservice.getService_id());
		serviceQuotationDao.overOfSend(parkservice);
		
	}




	@Override
	public void addDispatch(ServiceDispatch serviceDispatch,ServiceBusiness serviceBusiness, String serviceId,Companyservice companyservice) {
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
	        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(serviceId).getChanges())+"派工：");
	        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
	        changes.append( sdf.format(new Date())+"\r");
		  companyservice.setChanges(changes.toString());
		  serviceQuotationDao.changStatus(companyservice);
		  
		serviceDispatch.setDispatchId(UUID.randomUUID().toString());
		serviceDispatch.setStatus(101);
//		serviceDispatch.setDespatchTime(new Date());
		serviceDispatch.setDispatcher(UserInfoUtil.getUserInfo().getUserName());
		serviceQuotationDao.addDispatch(serviceDispatch);
		serviceQuotationDao.statusOfSend(serviceId);
		
	}


	@Override
	public void statusOfOver(String service_id,Companyservice companyservice, Etopservice etopservice) {
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
	        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(service_id).getChanges())+"出账：");
	        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
	        changes.append( sdf.format(new Date())+"\r");
		  companyservice.setChanges(changes.toString());
		  companyservice.setServiceId(etopservice.getServiceId());
		  serviceQuotationDao.changStatus(companyservice);

		serviceQuotationDao.statusOfOver(service_id);
		
	}

	@Override
	public List<ServiceQuotation> getQuotationServiceList(Map<String,String> map) {

		return serviceQuotationDao.getQuotationServiceList(map);
	}
	@Override
	public List<ServiceQuotation> getQuotationRecruitmentList(Map<String,String> map) {
		
		return serviceQuotationDao.getQuotationRecruitmentList(map);
	}

	@Override
	public Map<String, String> getQuotationServiceListtwo(String service_type) {

		return serviceQuotationDao.getQuotationServiceListtwo(service_type);
	}
	
	@Override
	public ServiceDispatch getDispatchInfoById(String service_id) {
		
		return serviceQuotationDao.getDispatchInfoById(service_id);
	}

	@Override
	public String proveQuotationName(ServiceQuotation serviceQuotation) {
	return	serviceQuotationDao.proveQuotationName(serviceQuotation);
		
	}








//	@Override
//	public Map<String, String> getQuotationServiceList(String service_type) {
//		return serviceQuotationDao.getQuotationServiceList(service_type);
//	}
}