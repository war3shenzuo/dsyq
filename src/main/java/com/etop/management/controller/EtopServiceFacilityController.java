package com.etop.management.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.EtopServiceFacilityDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.etop.management.entity.EtopBill.AuditStatus;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopBill.BillStatus;
import com.etop.management.entity.EtopBill.BillType;
import com.etop.management.entity.EtopThreshold.ThresholdKey;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopServiceFacilityService;
import com.etop.management.service.EtopServiceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.ServiceQuotation;
/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceFacilityController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/serviceFacility")
public class EtopServiceFacilityController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopServiceFacilityController.class);
	@Autowired 
	private EtopServiceFacilityService etopServiceFacilityService; 
	
	@Resource 
	private ParkGroupDao parkGroupDao;
	
	@Resource
	private ParkDao parkDao;
	
	@Resource 
	private EtopSequenceService etopSequenceSevice;
	
	@Resource
	private EtopThresholdService etopThresholdService;
	
	@Resource
	private EtopBillService etopBillService;
	
	@Resource
	private EtopServiceService etopServiceService;
	@Resource
	private EtopServiceFacilityDao etopServiceFacilityDao;
	//首页
	@RequestMapping("/index.do")
	public String  index(Model model){
		
		EtopUser  user=UserInfoUtil.getUserInfo();
		String parkGroupId="";
		String page="";
		if("4".equals(user.getUserType())){//园区组管理员
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			Park park =new Park();
			park.setParkGroupId(parkGroupId);
			List<Park> list = parkDao.getParkList(park);
			if(list.size()>0){
				model.addAttribute("defaultParkId", list.get(0).getId());
			}
			model.addAttribute("parks", list);
			page="facility/index2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="facility/index";
			
			
		}
		
		return page;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getServiceList.do")
	public EtopPage<EtopService> getServiceList(EtopService service) {

		EtopPage<EtopService> page = null;

		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType())){//园区组管理员
				service.setParkId(user.getParkId());
			}
			page = etopServiceFacilityService.getServiceList(service);

		} catch (Exception e) {

			LOGGER.error("查询服务列表失败",e);

			e.printStackTrace();
		}

		return page;

	}
	
	@ResponseBody
	@RequestMapping(value = "/getServiceList2.do")
	public ResultType getServiceList2(EtopService service) {

		EtopPage<EtopService> page = null;

		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType())){//园区组管理员
				service.setParkId(user.getParkId());
			}
			
			return ResultType.getSuccess(etopServiceFacilityService.getServiceList2(service));
			

		} catch (Exception e) {

			LOGGER.error("查询服务列表失败",e);

			e.printStackTrace();
			
			return ResultType.getError("查询失败");
		}

	}
	
	
	@RequestMapping(value="/getserviceInfo.do")
	public String getserviceInfo(String serviceId,Model model){
		
		
		try {
			
			EtopService service = etopServiceFacilityService.getServiceInfo(serviceId);
			
			Map<String,Object> facility=etopServiceFacilityService.getServiceFacilityInfo(serviceId);
			
			
			
			model.addAttribute("service", service);
			
			model.addAttribute("facility", facility);
			
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType()) && service.getServiceStatus()==202){
				model.addAttribute("bill", 1);
			}else{
				model.addAttribute("bill", 0);
			}
			
			
		} catch (Exception e) {
			
			LOGGER.error("查询服务信息失败！",e);
			
			e.printStackTrace();
		}
		
		return "facility/serviceInfo";
		
	}
	
	@RequestMapping("/addBill.do")
	@ResponseBody
	public ResultType addBill(String serviceId, ModelMap model, Companyservice companyservice, String message) {
		
		ResultType result = null;
		
		EtopService service = etopServiceFacilityService.getServiceInfo(serviceId);
		Map<String,Object> facility=etopServiceFacilityService.getServiceFacilityInfo(serviceId);
		String amout =facility.get("totalPrices").toString();
		if("0".equals(amout)){
			etopServiceService.updateStatus( serviceId,300, companyservice, message);
			result= ResultType.getSuccess();
		}else{
		EtopBill etopBill =new EtopBill();
		EtopUser  user=UserInfoUtil.getUserInfo();
		etopBill.setParkId(user.getParkId());
		etopBill.setBillId(etopSequenceSevice.getFormatId(getParkCode(), "zd"));
		etopBill.setBillType(BillType.INCOME.value);
		etopBill.setBillStatus(BillStatus.UNPAID.value);
		etopBill.setBillSource(BillSource.PARK_SERVICE.value);
		etopBill.setCompanyId(service.getCompanyId());
		etopBill.setCompanyName(service.getCompanyName());
		etopBill.setAmount((BigDecimal) facility.get("totalPrices"));
	    int day=Double.valueOf(etopThresholdService.getValue(getParkId(user.getParkId()), ThresholdKey.deadline.name)).intValue();//阀值
		etopBill.setDeadline( new LocalDateTime().plusDays(day).toDate(TimeZone.getDefault()));
		etopBill.setCreatedTime(new Date());
		etopBill.setAmountPaid(BigDecimal.ZERO);
		etopBill.setAccountRemission(BigDecimal.ZERO);
		etopBill.setOverdueFine(BigDecimal.ZERO);
		etopBill.setOverdueRemission(BigDecimal.ZERO);
		etopBill.setOverdueRate(BigDecimal.ZERO);
		etopBill.setAuditStatus(AuditStatus.UNCHECK.value);
		etopBill.setAuditLevel(2);	
		etopBill.setBillNoOut(serviceId);
		companyservice.setCompleteTime(new Date());
		if(etopBillService.add(etopBill) > 0){
			etopServiceService.updateStatus( serviceId,300, companyservice, message);
			result= ResultType.getSuccess();
		}else{
			result= ResultType.getFail();
		}
	  }
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/add.do")
	public ResultType add(EtopServiceFacility param,EtopService service, EtopFloorRoom etopFloorRoom){
		
		ResultType result = null;
		
		try {
			
			etopServiceFacilityService.add(param,service, etopFloorRoom);
			
			result = ResultType.getSuccess("提交服务成功");
		
		} catch (Exception e) {
			
			result = ResultType.getFail("提交服务失败");
			
			LOGGER.error("提交服务失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@RequestMapping("/getServiceQuotation.do")
	@ResponseBody
	public EtopPage<EtopServiceFacility> calculateFacility(
			String facilityName, String serviceNo, String companyName, String expirationTime, String facilityType, String beginTime, String endTime,
			 String serviceStatus,@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String applyTime, String parkId) {

		Map<String, Object> condition = new HashMap<>();
		EtopUser  user=UserInfoUtil.getUserInfo();
		if("3".equals(user.getUserType())){//园区管理员
			condition.put("clubId",user.getParkId());
		}else{
			condition.put("clubId",parkId);
		}
//		condition.put("clubId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("serviceStatus",serviceStatus);
		condition.put("facilityName",facilityName);
		condition.put("serviceNo",serviceNo);
		condition.put("companyName",companyName);
		condition.put("expirationTime",expirationTime);
		condition.put("facilityType",facilityType);
		condition.put("beginTime",beginTime);
		condition.put("endTime",endTime);
		condition.put("applyTime",applyTime);
		condition.put("currentTime",System.currentTimeMillis());
		return etopServiceFacilityService.calculateFacility(condition, offset, limit);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/checkAppointTime.do")
	public ResultType checkAppointTime(EtopServiceFacility param){
		
		ResultType result = null;
		int count=etopServiceFacilityDao.checkAppointTime(param);
		
		if(count>=1){
			result =ResultType.getSuccess("验证预约时间成功！");
		}
//		if("1".equals(etopServiceFacilityDao.checkAppointTime(param))){
//			result =ResultType.getSuccess("验证预约时间成功！");
//		}
		else{
			result = ResultType.getFail("验证预约时间失败！");
		}
		return result;
		
	}
/*	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	@ResponseBody
	@RequestMapping(value = "/fromToday.do")
	public static long fromToday(Date date, Date start, Date end) {
        long gap =0;
        
		long begintime = start.getTime() / 1000;
		long endtime = end.getTime() / 1000;
		long difference = endtime - begintime;
		if (difference <= ONE_HOUR){
			gap = 1;
		}else if(difference <= ONE_DAY & difference % ONE_HOUR / ONE_MINUTE != 0){
			 gap = difference / ONE_HOUR + 1;
		}else if(difference <= ONE_DAY & difference % ONE_HOUR / ONE_MINUTE == 0){
			 gap = difference / ONE_HOUR;
		}
		return gap;

	}*/

}
