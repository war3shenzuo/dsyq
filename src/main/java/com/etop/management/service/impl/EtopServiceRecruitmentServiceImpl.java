package com.etop.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.website.bean.ServiceBusiness;
import com.etop.management.dao.BusinessServiceDao;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.EtopServiceDao;
import com.etop.management.dao.EtopServiceRecruitmentDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceRecruitment;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopServiceRecruitmentService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.dao.ParkserviceDao;
import com.etop.website.dao.ServiceQuotationDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceRecruitmentService<br>
 */
@Service("etopServiceRecruitmentService")
public class  EtopServiceRecruitmentServiceImpl  implements EtopServiceRecruitmentService {

	@Resource 
    private EtopServiceDao etopServiceDao;
	@Resource
	private EtopServiceRecruitmentDao etopServiceRecruitmentDao;
	
	@Resource
	ParkserviceDao parkserviceDao;

	@Resource
	ParkDao parkDao;
	
	@Resource
	ServiceQuotationDao serviceQuotationDao;
	
	@Resource 
	private EtopSequenceService etopSequenceSevice;
	
	@Resource
	BusinessServiceDao businessServiceDao;
	
	@Resource 
	private ContractDao contractDao; 
	
	
	@Override
	public EtopPage<EtopService> getServiceList(Map<String, Object> condition, int offset, int limit) {
		PageHelper.offsetPage(offset, limit,"apply_time desc");
//		EtopPage<EtopService> list = new EtopPage<EtopService>(etopServiceDao.selectRecruitmentService(condition));
//		return list;
		Page<EtopService> page =etopServiceDao.selectRecruitmentService(condition);
		for(EtopService etopService :page){
			if(etopService.getFinalPrice() != null){
				etopService.setTotalPrice(etopService.getFinalPrice());
			}else{
				etopService.setTotalPrice(etopService.getTotalPrice());
			}
		}
		EtopPage<EtopService> list = new EtopPage<EtopService>(page);
		return list;
	}
	
	@Override
	public EtopPage<EtopService> getServiceList(EtopService service) {
		
		EtopPage<EtopService> page = new EtopPage<EtopService>();
		

		int BTablePageNum = (service.getOffset() / service.getLimit())+ 1;

		// 设置分页
		PageHelper.startPage(BTablePageNum , service.getLimit());

		List<EtopService> list =etopServiceDao.selectRecruitmentService(service);


		PageInfo<EtopService> table = new PageInfo<EtopService>(list);

		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
	}

	@Override
	public EtopServiceRecruitment getServicePurchaseInfo(String serviceId) {
		return etopServiceRecruitmentDao.queryByServiceId(serviceId);
	}
	@Override
	public Etopservice querysByServiceId(String serviceId) {
		return etopServiceRecruitmentDao.querysByServiceId(serviceId);
	}

	@Override
	public EtopService getServiceInfo(String serviceId) {
		return etopServiceDao.queryById(serviceId);
	}

	@Override
	public void add(EtopServiceRecruitment param, EtopService service,ServiceBusiness serviceBusiness, EtopFloorRoom etopFloorRoom) {
	  //申请服务
	    EtopUser etopUser= UserInfoUtil.getUserInfo();
	    service.setServiceId(UUID.randomUUID().toString());	    
	    service.setApplyTime(new Date());
	    service.setClubId(etopUser.getParkId());
	    service.setCompanyId(etopUser.getCompanyId()); 
	    service.setCompanyName(parkserviceDao.getCompanyName(etopUser.getCompanyId()));
        service.setServiceStatus(101);

        service.setServiceNo(etopSequenceSevice.getFormatId(parkDao.getParkCode(etopUser.getParkId()), service.getServiceType()));
      //获取楼层区房间号
		  Contract map=contractDao.getRoom(etopUser.getCompanyId(),etopFloorRoom.getRoomNum());
		  service.setBuildingNo(map.getBuilding());
		  service.setStorey(map.getFloor());
		  service.setZoneNo(map.getBlock());
		  service.setRoomId(map.getRefCompanyId());
		  service.setRoomNo(map.getRoom());
		  service.setIsfree(1);
        //申请记录
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 
			StringBuffer changes =new  StringBuffer();
			changes.append("申请："+UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
			changes.append( date.format(new Date())+"\r");
			service.setChanges(changes.toString());
		  parkserviceDao.addServer(service);

		  
			param.setRecruitmentId(UUID.randomUUID().toString());
			param.setServiceId(service.getServiceId());
	        StringBuffer age =new  StringBuffer();
			  age.append(param.getAgeOne()+"-");
			  age.append(param.getAgeTwo());
//			  service_no.append(parkservice.getService_type()+"-");
//			  service_no.append( sdf.format(new Date()));
//			  parkservice.setService_no(service_no.toString());
			param.setAge(age.toString());
			etopServiceRecruitmentDao.add(param);
			
			serviceBusiness.setServiceId(service.getServiceId()); 
			serviceBusiness.setBusinessId(UUID.randomUUID().toString());	
			serviceQuotationDao.addServiceBusiness(serviceBusiness);
	}

	@Override
	public EtopPage<ServiceQuotation> makeRecruitment(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit);
		EtopPage<ServiceQuotation> list = new EtopPage<ServiceQuotation>(etopServiceRecruitmentDao.makeRecruitment(condition));
		return list;
	}
	
	@Override
	public EtopPage<ServiceQuotation> makeRecruitmentGroup(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit);
		EtopPage<ServiceQuotation> list = new EtopPage<ServiceQuotation>(etopServiceRecruitmentDao.makeRecruitmentGroup(condition));
		return list;
	}
  
	@Override
	public int addRecruitment(ServiceQuotation serviceQuotation) {
		
		serviceQuotation.setQuotationId(UUID.randomUUID().toString());
		serviceQuotation.setType("person");
		serviceQuotation.setCreateTime(new Date());
		serviceQuotation.setParkId(UserInfoUtil.getUserInfo().getParkId());
		serviceQuotation.setActivated("1");
		return businessServiceDao.addBusinessService(serviceQuotation);
	}
}
