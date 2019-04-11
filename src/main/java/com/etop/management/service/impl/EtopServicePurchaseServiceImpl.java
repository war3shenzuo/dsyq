package com.etop.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopAllGoods;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.Remind;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.EtopServiceDao;
import com.etop.management.dao.EtopServicePurchaseDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.etop.management.entity.EtopServicePurchase;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopServicePurchaseService;
import com.etop.management.service.RemindService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.dao.ParkserviceDao;
import com.etop.website.dao.ServiceQuotationDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServicePurchaseService<br>
 */
@Service("etopServicePurchaseService")
public class  EtopServicePurchaseServiceImpl  implements EtopServicePurchaseService {
	
	@Resource 
    private EtopServiceDao etopServiceDao;
	
	@Resource
	private EtopServicePurchaseDao etopServicePurchaseDao;
	@Resource
	ParkserviceDao parkserviceDao;

	@Resource
	ParkDao parkDao;
	
	@Resource
	RemindService remindService;
	
	@Resource
	ServiceQuotationDao serviceQuotationDao;
	@Resource 
	private EtopSequenceService etopSequenceSevice;
	@Resource 
	private ContractDao contractDao; 
	
	@Override
	public EtopPage<EtopService> getServiceList(Map<String, Object> condition, int offset, int limit) {
		PageHelper.offsetPage(offset, limit,"apply_time desc");
		EtopPage<EtopService> list = new EtopPage<EtopService>(etopServiceDao.selectPurchaseService(condition));
		return list;
	}
	
	@Override
	public EtopPage<EtopService> getServiceList(EtopService service) {
		
		
		EtopPage<EtopService> page = new EtopPage<EtopService>();
		

		int BTablePageNum = (service.getOffset() / service.getLimit())+ 1;

		// 设置分页
		PageHelper.startPage(BTablePageNum , service.getLimit());

		List<EtopService> list =etopServiceDao.selectPurchaseService(service);


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
	public EtopServicePurchase getServicePurchaseInfo(String serviceId) {
		return etopServicePurchaseDao.queryByServiceId(serviceId);
	}

	@Override
	public EtopPage<EtopAllGoods> calculateGoods(EtopAllGoods goods) {
		EtopPage<EtopAllGoods> page = new EtopPage<EtopAllGoods>();
		

		int BTablePageNum = (goods.getOffset() / goods.getLimit())+ 1;

		// 设置分页
		PageHelper.startPage(BTablePageNum , goods.getLimit());

		List<EtopAllGoods> list =etopServiceDao.calculateGoods(goods);


		PageInfo<EtopAllGoods> table = new PageInfo<EtopAllGoods>(list);

		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
	}

	@Override
	public void add(EtopServicePurchase param, EtopService service, EtopFloorRoom etopFloorRoom) {
		
		  //申请服务
	    EtopUser etopUser= UserInfoUtil.getUserInfo();
	    service.setServiceId(UUID.randomUUID().toString());	    
	    service.setApplyTime(new Date());
	    service.setClubId(etopUser.getParkId());
	    service.setCompanyId(etopUser.getCompanyId()); 
	    service.setCompanyName(parkserviceDao.getCompanyName(etopUser.getCompanyId()));
        service.setServiceStatus(101);
	  //拼接服务编号
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//        StringBuffer service_no =new  StringBuffer();
//		  service_no.append(parkDao.getParkCode(etopUser.getParkId())+"-");
//		  service_no.append(service.getServiceType()+"-");
//		  service_no.append( sdf.format(new Date()));
//		  service.setServiceNo(service_no.toString());
        service.setServiceNo(etopSequenceSevice.getFormatId(parkDao.getParkCode(etopUser.getParkId()), service.getServiceType()));
//获取楼层区房间号
		  etopFloorRoom.setCompanyId(etopUser.getCompanyId());
//		  EtopFloorRoom map=serviceQuotationDao.getPlace(etopFloorRoom);
		  Contract map=contractDao.getRoom(etopUser.getCompanyId(),service.getRoomNo());
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
		
//		service.setServiceId(UUID.randomUUID().toString());
//		etopServiceDao.add(service);
		param.setPurchaseId(UUID.randomUUID().toString());
		param.setServiceId(service.getServiceId());
		etopServicePurchaseDao.add(param);
		
		
/*        Remind remind = new Remind();
        List<EtopUser> list = remindService.getJurisdiction(etopUser.getParkId(), Role.QX_QSTX);
        if(list != null && list.size() > 0){
            for(int i=0;i<list.size();i++){
        remind.setRemindType(Role.QX_QSTX);	
        remind.setTitle("服务申请提醒");
        remind.setContent(" 收到客户发起的服务申请，请及时审核！");
        remind.setTarget(list.get(i).getId());
        remindService.addRemind(remind);
            }
        }*/
	}

	@Override
	public EtopPage<EtopAllGoods> calculateGoods(Map<String, Object> condition,
			Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit);

		EtopPage<EtopAllGoods> list = new EtopPage<EtopAllGoods>(etopServiceDao.calculateGoods(condition));
		return list;
	}


  
}
