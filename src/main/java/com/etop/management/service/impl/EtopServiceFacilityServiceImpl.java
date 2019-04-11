package com.etop.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.EtopServiceDao;
import com.etop.management.dao.EtopServiceFacilityDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopServiceFacilityService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.dao.ParkserviceDao;
import com.etop.website.dao.ServiceQuotationDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceFacilityService<br>
 */
@Service("etopServiceFacilityService")
public class  EtopServiceFacilityServiceImpl  implements EtopServiceFacilityService {
	@Resource 
    private EtopServiceDao etopServiceDao;
	@Resource
	private EtopServiceFacilityDao etopServiceFacilityDao;
	@Resource
	ServiceQuotationDao serviceQuotationDao;
	@Resource
	ParkserviceDao parkserviceDao;
	@Resource
	ParkDao parkDao;
	@Resource 
	private EtopSequenceService etopSequenceSevice;
	@Resource 
	private ContractDao contractDao;
	@Override
	public EtopPage<EtopService> getServiceList(EtopService service) {
		
		EtopPage<EtopService> page = new EtopPage<EtopService>();
		

		int BTablePageNum = (service.getOffset() / service.getLimit())+ 1;

		// 设置分页
		PageHelper.startPage(BTablePageNum , service.getLimit());

		service.setCurrentTime(System.currentTimeMillis());
		
		List<EtopService> list =etopServiceDao.selectFacilityService(service);


		PageInfo<EtopService> table = new PageInfo<EtopService>(list);

		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
	}

	@Override
	public EtopService getServiceInfo(String serviceId) {
		return etopServiceDao.queryById(serviceId);
	}

	@Override
	public Map<String,Object> getServiceFacilityInfo(String serviceId) {
		
		Map<String,Object> map=etopServiceFacilityDao.queryByServiceId(serviceId);
		
		if(map.get("beginTime")!=null){
			map.put("beginTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date((long)map.get("beginTime"))));
		}
		
		if(map.get("endTime")!=null){
			map.put("endTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date((long)map.get("endTime"))));
		}
		
		if((long)map.get("expirationTime") ==1){
			map.put("isExpiration", "是");
		}else{
			map.put("isExpiration", "否");
		}
		
		return map;
	}

	@Override
	public void add(EtopServiceFacility param, EtopService service, EtopFloorRoom etopFloorRoom) {
		//验证预约时间
		
/*		int count=etopServiceFacilityDao.checkAppointTime(param);
		
		if(count>=1){
			throw new RuntimeException("预约时间重复");
		}*/


		EtopUser etopUser= UserInfoUtil.getUserInfo();
	    service.setServiceId(UUID.randomUUID().toString());	    
	    service.setApplyTime(new Date());
	    service.setClubId(etopUser.getParkId());
	    service.setCompanyId(etopUser.getCompanyId()); 
	    service.setServiceType("YYFW");
	    service.setCompanyName(parkserviceDao.getCompanyName(etopUser.getCompanyId()));
        service.setServiceStatus(101);
      //获取楼层区房间号
//		  etopFloorRoom.setCompanyId(etopUser.getCompanyId());
//		  EtopFloorRoom map=serviceQuotationDao.getPlace(etopFloorRoom);
//		  service.setBuildingNo(serviceQuotationDao.searchFloorId(map.getRefFloorId()));
//		  service.setStorey(serviceQuotationDao.searchStoreyId(map.getRefStoreyId()));
//		  service.setZoneNo(serviceQuotationDao.searchAreaId(map.getRefAreaId()));
//		  service.setRoomId(map.getId());
//		  service.setRoomNo(map.getRoomNum());
          Contract map=contractDao.getRoom(etopUser.getCompanyId(),etopFloorRoom.getRoomNum());
		  service.setBuildingNo(map.getBuilding());
		  service.setStorey(map.getFloor());
		  service.setZoneNo(map.getBlock());
		  service.setRoomId(map.getRefCompanyId());
		  service.setRoomNo(map.getRoom());
        service.setServiceNo(etopSequenceSevice.getFormatId(parkDao.getParkCode(etopUser.getParkId()), service.getServiceType()));
		  service.setIsfree(1);
        //申请记录
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd"); 
		StringBuffer changes =new  StringBuffer();
		changes.append("申请："+UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
		changes.append( date.format(new Date())+"\r");
		service.setChanges(changes.toString());
		parkserviceDao.addServer(service);
		
		param.setServiceId(service.getServiceId());  

		param.setId(UUID.randomUUID().toString());
		etopServiceFacilityDao.add(param);

	}
	@Override
	public List<EtopService> getServiceList2(EtopService service) {
		
		/*service.setCurrentTime(System.currentTimeMillis());
		
		LocalDate localData=LocalDate.now();
		
		String begin=localData.format(DateTimeFormatter.ofPattern("yyyy-MM"))+"-01";
		
		long beginTimestamp=new SimpleDateFormat("yyyy-MM-dd").parse(begin).getTime();
		
		LocalDate nextLocalData=localData.plusMonths(1);
		
		String end=localData.format(DateTimeFormatter.ofPattern("yyyy-MM"))+"-01";
		
		long endTimestamp =new SimpleDateFormat("yyyy-MM-dd").parse(end).getTime();
		
		service.setBeginTime(beginTimestamp);*/

		List<EtopService> list =etopServiceDao.selectFacilityServiceApply(service);
		
		return list;
		
		
	}

	@Override
	public EtopPage<EtopServiceFacility> calculateFacility(
			Map<String, Object> condition, int offset, int limit) {
		
		EtopPage<EtopServiceFacility> list =new EtopPage<EtopServiceFacility>(etopServiceFacilityDao.calculateFacility(condition));
		return list;
	}


	
}
