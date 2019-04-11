package com.etop.website.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopUser;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceBusiness;
import com.etop.website.dao.ParkserviceDao;
import com.etop.website.dao.ServiceQuotationDao;
import com.etop.website.service.WebParkService;


@Named("parkService")
@Service
public class WebParkServerImpl implements WebParkService{

	@Resource
	ParkserviceDao parkserviceDao;

	@Resource
	ParkDao parkDao;
	
	@Resource 
	private ContractDao contractDao; 
	
	@Resource
	ServiceQuotationDao serviceQuotationDao;
	@Resource 
	private EtopSequenceService etopSequenceSevice;
	public void addservice(Parkservice parkservice,HttpServletRequest request) throws Exception {

	    EtopUser etopUser= UserInfoUtil.getUserInfo();

	    
	    parkservice.setService_id(UUID.randomUUID().toString());
	    
	    parkservice.setApply_time(new Date());

	    parkservice.setClub_id(etopUser.getParkId());
	    parkservice.setCompany_id(etopUser.getCompanyId());
	    parkservice.setApplicant(etopUser.getUserName());
	    parkservice.setApplicant_phone(etopUser.getMobile());
	    parkservice.setCompany_name(parkserviceDao.getCompanyName(etopUser.getCompanyId()));
        parkservice.setPark_code(parkDao.getParkCode(etopUser.getParkId()));

	
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer service_no =new  StringBuffer();
		  service_no.append(parkservice.getPark_code()+"-");
		  service_no.append(parkservice.getService_type()+"-");
		  service_no.append( sdf.format(new Date()));
		  parkservice.setService_no(service_no.toString());
		

		  parkservice.setService_status("101");

		  
		parkserviceDao.addservice(parkservice);
	
	    
	}
	@Override
	public Map<String,String> searchName(String service_type) {

		Map<String,String> parkservice = parkserviceDao.searchName(service_type);
	 
	  return parkservice;

	}
	

	public void addService(Parkservice parkservice,HttpServletRequest request, ServiceBusiness serviceBusiness, EtopFloorRoom etopFloorRoom) throws Exception {

	    EtopUser etopUser= UserInfoUtil.getUserInfo();
	  //申请服务
	    parkservice.setService_id(UUID.randomUUID().toString());	    
	    parkservice.setApply_time(new Date());
	    parkservice.setClub_id(etopUser.getParkId());
	    parkservice.setCompany_id(etopUser.getCompanyId()); 
	    parkservice.setCompany_name(parkserviceDao.getCompanyName(etopUser.getCompanyId()));
        parkservice.setPark_code(parkDao.getParkCode(etopUser.getParkId()));
        parkservice.setService_status("101");
//		  etopBill.setBillId(etopSequenceSevice.getFormatId(getParkCode(), "zd"));
		  parkservice.setService_no(etopSequenceSevice.getFormatId(parkservice.getPark_code(), parkservice.getService_type()));
       //获取楼层区房间号
		  etopFloorRoom.setCompanyId(etopUser.getCompanyId());
		 /* EtopFloorRoom map=serviceQuotationDao.getPlace(etopFloorRoom);
		  parkservice.setBuilding_no(serviceQuotationDao.searchFloorId(map.getRefFloorId()));
		  parkservice.setStorey(serviceQuotationDao.searchStoreyId(map.getRefStoreyId()));
		  parkservice.setZone_no(serviceQuotationDao.searchAreaId(map.getRefAreaId()));
		  parkservice.setRoom_no(map.getRoomNum());
		  parkservice.setRoom_id(map.getId());*/
		  Contract map=contractDao.getRoom(etopUser.getCompanyId(),etopFloorRoom.getRoomNum());
		  parkservice.setBuilding_no(map.getBuilding());
		  parkservice.setStorey(map.getFloor());
		  parkservice.setZone_no(map.getBlock());
		  parkservice.setRoom_id(map.getRefCompanyId());
		  parkservice.setRoom_no(map.getRoom());
		  parkservice.setIsfree("1");
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 
			StringBuffer changes =new  StringBuffer();
			changes.append("申请："+UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
			changes.append( date.format(new Date())+"\r");
			parkservice.setChanges(changes.toString());
			parkserviceDao.addService(parkservice);
		  serviceBusiness.setServiceId(parkservice.getService_id()); 
		  serviceBusiness.setBusinessId(UUID.randomUUID().toString());	
			serviceQuotationDao.addServiceBusiness(serviceBusiness);
	}






}
