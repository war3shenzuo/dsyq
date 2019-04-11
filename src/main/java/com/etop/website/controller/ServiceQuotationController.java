package com.etop.website.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.ServiceDispatch;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopFacility;
import com.etop.management.entity.EtopBill.AuditStatus;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopBill.BillStatus;
import com.etop.management.entity.EtopBill.BillType;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.service.CompanyServiceService;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopServiceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceBusiness;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.dao.ServiceQuotationDao;
import com.etop.website.service.ServiceQuotationService;
import com.etop.website.service.WebParkService;
import com.etop.management.controller.BaseAppController;
import com.etop.management.entity.EtopThreshold.ThresholdKey;

@Controller
@RequestMapping("/quotation")
public class ServiceQuotationController extends BaseAppController{

	Logger logger = LoggerFactory.getLogger(ServiceQuotationController.class);
	
	@Resource
	ServiceQuotationService serviceQuotationService;
	@Resource
	WebParkService webParkService;
	@Resource
	private EtopBillService etopBillService;
	@Resource
	private EtopSequenceService etopSequenceSevice;
	@Resource
	private ServiceQuotationDao serviceQuotationDao;
	@Resource
	private EtopThresholdService etopThresholdService;
	@Resource
	CompanyServiceService companyServiceService;
	@Resource
	private EtopServiceService etopServiceService;
	@Resource
	ParkDao parkDao;
	@Resource 
	private ContractDao contractDao; 
	
	@ResponseBody
	@RequestMapping(value = "/changStatus.do")
	public ResultType changStatus(Companyservice companyservice,String serviceId) throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.changStatus(companyservice, serviceId);
			result =ResultType.getSuccess("操作信息修改成功！");
		}catch(Exception e){
			logger.error("操作信息修改失败",e);
		result = ResultType.getFail("操作信息修改失败！");
		e.printStackTrace();		
	     }

		 return result;
	}

	
	@ResponseBody
	@RequestMapping(value = "/addQuotation.do")
	public ResultType addQuotation(ServiceQuotation serviceQuotation) throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.addQuotation(serviceQuotation);
			result =ResultType.getSuccess("提交成功！");
		}catch(Exception e){
			logger.error("提交失败",e);
		result = ResultType.getFail("提交失败！");
		e.printStackTrace();		
	     }

		 return result;
	}
	
	

	@RequestMapping(value = "/selectRoom.do")
	public String selectRoom(String companyId, Model model, String service_type, ServiceQuotation serviceQuotation, String category) throws Exception{
		serviceQuotation.setType("repair");
		List<ServiceQuotation> categorys =  serviceQuotationService.getCategoryList(serviceQuotation);
		model.addAttribute("category",categorys);
//		List<EtopFloorRoom> room =  serviceQuotationService.selectRoom(UserInfoUtil.getUserInfo().getCompanyId());
		List<Contract> room =  contractDao.getRoomList(UserInfoUtil.getUserInfo().getCompanyId());
		model.addAttribute("room",room);
		Map<String,String> maps =new HashMap<String,String>();
		maps.put("category", category);
		List<ServiceQuotation> item =  serviceQuotationService.getQuotationList(maps);
		model.addAttribute("item",item);
		Map<String,String> parkservice=webParkService.searchName(service_type);
		model.addAttribute("parkservice",parkservice);
		return "parkservice_site/Servicequotation";
	}
	
	@RequestMapping(value = "/BusinessService.do")
	public String BusinessService(String companyId, Model model, String service_type, ServiceQuotation serviceQuotation, String category) throws Exception{
		serviceQuotation.setType("service");
		List<ServiceQuotation> categorys =  serviceQuotationService.getCategoryList(serviceQuotation);
		model.addAttribute("category",categorys);
//		List<EtopFloorRoom> room =  serviceQuotationService.selectRoom(UserInfoUtil.getUserInfo().getCompanyId());
		List<Contract> room =  contractDao.getRoomList(UserInfoUtil.getUserInfo().getCompanyId());
		model.addAttribute("room",room);
		Map<String,String> maps =new HashMap<String,String>();
		maps.put("category", category);
		List<ServiceQuotation> item =  serviceQuotationService.getQuotationServiceList(maps);
		model.addAttribute("item",item);
		Map<String,String> map =  serviceQuotationService.getQuotationServiceListtwo( service_type);
		model.addAttribute("map",map);
		Map<String,String> parkservice=webParkService.searchName(service_type);
		model.addAttribute("parkservice",parkservice);
		return "parkservice_site/BusinessServicequotation";
	}
	

	@ResponseBody
	@RequestMapping(value = "/addPosition.do")
	public EtopFloorRoom addPosition(EtopFloorRoom etopFloorRoom) throws Exception{
		EtopFloorRoom result= null;
		try{
			result= serviceQuotationService.getPlace(etopFloorRoom);
		}catch(Exception e){
			
			logger.error("查询房间失败",e);

		    e.printStackTrace();		
	     }

		 return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addServiceBusiness.do")
	public ResultType addServiceBusiness(ServiceBusiness serviceBusiness, Parkservice parkservice) throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.addServiceBusiness(serviceBusiness, parkservice);
			result =ResultType.getSuccess("提交成功！");
		}catch(Exception e){
			logger.error("提交失败",e);
		result = ResultType.getFail("提交失败！");
		e.printStackTrace();		
	     }

		 return result;
	}
	

	@ResponseBody
	@RequestMapping(value = "/getCategory.do")
	public List<ServiceQuotation> getQuotationList(ServiceQuotation serviceQuotation) throws Exception{
		
//		Map<String, String> info =  serviceQuotationService.getQuotation(quotationName);
//		model.addAttribute("info",info);
		serviceQuotation.setParkId(UserInfoUtil.getUserInfo().getParkId());
		return serviceQuotationService.getCategory(serviceQuotation);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getPrice.do")
	public Map<String, String> getQuotation( String quotationId) throws Exception{

//		Map<String, String> info =  serviceQuotationService.getQuotation(quotationName);
//		model.addAttribute("info",info);
		
		return serviceQuotationService.getQuotation(quotationId);

	}
	
	
	
//后台显示	——维修项目维护
	@RequestMapping("/quotationlist.do")
	public String list(Model model) {
//		return "ServiceQuotation/quotationlist";
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
			page="ServiceQuotation/quotationlist2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="ServiceQuotation/quotationlist";
			
			
		}
		
		return page;
	}
	
	@RequestMapping("/getServiceQuotation.do")
	@ResponseBody
	public EtopPage<ServiceQuotation> getServiceQuotation(
			 String quotationId, String quotationName,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String category,String parkId) {


		Map<String, Object> condition = new HashMap<>();
//		condition.put("parkId",UserInfoUtil.getUserInfo().getParkId());
		EtopUser  user=UserInfoUtil.getUserInfo();
		if("3".equals(user.getUserType())){//园区管理员
			condition.put("parkId",user.getParkId());
		}else{
			if(parkId==null|| parkId.length()==0){
				condition.put("parkId",111111);
			}else{
				condition.put("parkId",parkId);
			}
		}
		condition.put("category",category);
		condition.put("quotationId",quotationId);
		condition.put("quotationName",quotationName);

		return serviceQuotationService.getServiceQuotation(condition, offset, limit);
		
	}
	@RequestMapping("/getServiceQuotationGroup.do")
	@ResponseBody
	public EtopPage<ServiceQuotation> getServiceQuotationGroup(
			String quotationId, String quotationName,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String category) {
		
		
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("category",category);
		condition.put("quotationId",quotationId);
		condition.put("quotationName",quotationName);
		
		return serviceQuotationService.getServiceQuotationGroup(condition, offset, limit);
		
	}

	@RequestMapping("/getQuotationInfoById.do")
	public String getQuotationInfoById(String quotationId,Model model) {
		ServiceQuotation serviceQuotation = serviceQuotationService.getQuotationInfoById(quotationId);
		model.addAttribute("ServiceQuotation", serviceQuotation);
		return "ServiceQuotation/quotationInfoById";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateQuotation.do")
	public ResultType updateQuotation(ServiceQuotation serviceQuotation) throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.updateQuotation(serviceQuotation);
			result =ResultType.getSuccess("提交成功！");
		}catch(Exception e){
			logger.error("提交失败",e);
		result = ResultType.getFail("提交失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/activeOrClose.do")
	public ResultType activeOrClose(ServiceQuotation serviceQuotation) {

		ResultType result = null;

		try {

			serviceQuotationService.updateQuotation(serviceQuotation);

			result = ResultType.getSuccess("激活/关闭");

		} catch (Exception e) {

			logger.error("激活/关闭失败");

			result = ResultType.getFail("激活/关闭失败！");

			e.printStackTrace();
		}

		return result;

	}
	
	@ResponseBody
	@RequestMapping("/deleteQuotation.do")
	public ResultType deleteQuotation(String quotationId)  throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.deleteQuotation(quotationId);
			result =ResultType.getSuccess("删除成功！");
		}catch(Exception e){
			logger.error("删除失败",e);
		result = ResultType.getFail("删除失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}
	
	
	@RequestMapping("/addPage.do")
	public String addPage(Model model, String parkGroupId) throws Exception {

		return "ServiceQuotation/addQuotation";

	}
	
	@ResponseBody
	@RequestMapping("/addQuotationService.do")
	public ResultType addQuotationService(ServiceQuotation serviceQuotation)  throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.addQuotationService(serviceQuotation);
			result =ResultType.getSuccess("新建成功！");
		}catch(Exception e){
			logger.error("新建失败",e);
		result = ResultType.getFail("新建失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}
	
	
	//维修服务列表
	@RequestMapping("/quotationBusinesslist.do")
	public String quotationBusinesslist(Model model) {
//		return "ServiceQuotation/quotationBusinesslist";
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
			page="ServiceQuotation/quotationBusinesslist2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="ServiceQuotation/quotationBusinesslist";
			
			
		}
		
		return page;
	}
	
	
	@RequestMapping("/getQuotationBusiness.do")
	@ResponseBody
	public EtopPage<Etopservice> getQuotationBusiness(
			 String serviceNo, String subject, String companyName, String applyTime, String completeTime,String serviceStatus,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String categoryName, String parkId) {
	
		Map<String, Object> condition = new HashMap<>();
		EtopUser  user=UserInfoUtil.getUserInfo();
		if("3".equals(user.getUserType())){//园区管理员
			condition.put("clubId",user.getParkId());
		}else{
			condition.put("clubId",parkId);
		}
//		condition.put("clubId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("serviceNo",serviceNo);
		condition.put("categoryName",categoryName);
		condition.put("subject",subject);
		condition.put("companyName",companyName);
		condition.put("applyTime",applyTime);
		condition.put("completeTime",completeTime);
		condition.put("serviceStatus",serviceStatus);
		return serviceQuotationService.getQuotationBusiness(condition, offset, limit);
		
	}
	
	@RequestMapping("/getQuotationBusinessInfoById.do")
	public String getQuotationBusinessInfoById(Model model, String service_id) {
		Etopservice parkservice = serviceQuotationService.getQuotationBusinessInfoById(service_id);
		model.addAttribute("Parkservice", parkservice);
		ServiceDispatch	 serviceDispatch=serviceQuotationService.getDispatchInfoById(service_id);
	    model.addAttribute("ServiceDispatch", serviceDispatch);	
		return "ServiceQuotation/quotationBusinessInfoById";
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/updateFinalPrice.do")
	public ResultType updateFinalPrice(ServiceBusiness serviceBusiness,Companyservice companyservice, String serviceId) throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.updateFinalPrice(serviceBusiness,companyservice,serviceId);
			result =ResultType.getSuccess("更新成功！");
		}catch(Exception e){
			logger.error("更新失败",e);
		result = ResultType.getFail("更新失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/statusOfSend.do")
//	public ResultType statusOfSend(String service_id,Companyservice companyservice) throws Exception{
//		ResultType result= null;
//		try{
//			serviceQuotationService.statusOfSend(service_id, companyservice);
//			result =ResultType.getSuccess("成功修改为已派工！");
//		}catch(Exception e){
//			logger.error("修改派工失败",e);
//		result = ResultType.getFail("修改派工失败！");
//		e.printStackTrace();		
//	     }
//		 return result;
//	}
	
	@ResponseBody
	@RequestMapping(value = "/overOfSend.do")
	public ResultType overOfSend(String service_id,Companyservice companyservice,ServiceDispatch serviceDispatch, Parkservice parkservice) throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.overOfSend(service_id,companyservice,serviceDispatch,parkservice);
			result =ResultType.getSuccess("成功修改为完工！");
		}catch(Exception e){
			logger.error("修改为完工失败",e);
		result = ResultType.getFail("修改为完工失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
	
	//派工
	
	@RequestMapping("/dispatch.do")
	public String dispatch(Model model, String service_id) {
		Etopservice parkservice = serviceQuotationService.getQuotationBusinessInfoById(service_id);
		model.addAttribute("Parkservice", parkservice);
		return "ServiceQuotation/dispatch";
	}
	
	@ResponseBody
	@RequestMapping(value = "/addDispatch.do")
	public ResultType addDispatch(ServiceDispatch serviceDispatch, ServiceBusiness serviceBusiness, String serviceId,Companyservice companyservice) throws Exception{
		ResultType result= null;
		try{
			serviceQuotationService.addDispatch(serviceDispatch, serviceBusiness,serviceId,companyservice);
			result =ResultType.getSuccess("派工成功！");
		}catch(Exception e){
			logger.error("派工失败",e);
		result = ResultType.getFail("派工失败！");
		e.printStackTrace();		
	     }

		 return result;
	}
	
	
	@RequestMapping("/addService.do")
	@ResponseBody
	public ResultType addService( String service_id, ModelMap model, String parkId, Companyservice companyservice, String serviceId, BigDecimal finalPrice, String message) {
		Etopservice parkservice = serviceQuotationService.getQuotationBusinessInfoById(serviceId);
		String amout =parkservice.getTotalPrice().toString();
		if("0.00".equals(amout)){
			etopServiceService.updateStatus( serviceId,300, companyservice, message);
			return ResultType.getSuccess();
		}else{
		EtopBill etopBill =new EtopBill();
		EtopUser  user=UserInfoUtil.getUserInfo();
		Park park = UserInfoUtil.getUserParkInfo();
		etopBill.setParkId(user.getParkId());
		etopBill.setBillId(etopSequenceSevice.getFormatId(park.getParkCode(), "zd"));
		etopBill.setBillType(BillType.INCOME.value);
		etopBill.setBillStatus(BillStatus.UNPAID.value);
		etopBill.setBillSource(BillSource.PARK_SERVICE.value);
		etopBill.setCompanyId(parkservice.getCompanyId());
		etopBill.setCompanyName(parkservice.getCompanyName());
		if(parkservice.getFinalPrice() != null){
		etopBill.setAmount(parkservice.getFinalPrice());
		}
		else{
		etopBill.setAmount(parkservice.getTotalPrice());
		}
		etopBill.setCreatedTime(new Date());
		
		Date d = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
	        calendar.setTime(new Date());
	        calendar.add(Calendar.DAY_OF_MONTH, (int) etopThresholdService.getValue(getParkId(parkId), ThresholdKey.deadline.name));
	        Date date =calendar.getTime();
		
		etopBill.setDeadline(date); 
		etopBill.setAmountPaid(BigDecimal.ZERO);
		etopBill.setAccountRemission(BigDecimal.ZERO);
		etopBill.setOverdueFine(BigDecimal.ZERO);
		etopBill.setOverdueRemission(BigDecimal.ZERO);
		etopBill.setOverdueRate(BigDecimal.ZERO);
		etopBill.setAuditStatus(AuditStatus.UNCHECK.value);
		etopBill.setAuditLevel(2);		
		etopBill.setBillNoOut(serviceId);
/*		serviceQuotationDao.statusOfOver(serviceId);
		if(companyServiceService.statusOfSuer(companyservice, serviceId, etopBill) > 0)
			return ResultType.getSuccess();
		else
			return ResultType.getFail();*/
		if(etopBillService.add(etopBill) > 0){
			etopServiceService.updateStatus( serviceId,300, companyservice, message);
			return ResultType.getSuccess();
		}else{
			return ResultType.getFail();
		}
		}
	}

	@ResponseBody
	@RequestMapping("/proveQuotationName.do")
	public ResultType proveQuotationName(ServiceQuotation serviceQuotation, String quotationName, String type){
		ResultType result= null;
//		try{
//			"1".equals(serviceQuotationService.proveQuotationName(quotationName));
//			serviceQuotationService.proveQuotationName(quotationName);
//			result =ResultType.getSuccess("验证成功！");
//		}catch(Exception e){
//			logger.error("验证失败",e);
//		result = ResultType.getFail("验证失败！");
//		e.printStackTrace();		
//	     }
//		 return result;
		serviceQuotation.setQuotationName(quotationName);
		serviceQuotation.setParkId(UserInfoUtil.getUserInfo().getParkId());
		serviceQuotation.setType(type);
		if("1".equals(serviceQuotationService.proveQuotationName(serviceQuotation))){
			result =ResultType.getSuccess("验证成功！");
		}
		else{
			result = ResultType.getFail("验证失败！");
		}
		return result;
		
	}
	@ResponseBody
	@RequestMapping(value = "/getQuotationList.do")
	public List<ServiceQuotation> getQuotationList1(String category, String categories) throws Exception{
		Map<String,String> maps =new HashMap<String,String>();
		maps.put("category", category);
		maps.put("categories", categories);
		maps.put("parkId", UserInfoUtil.getUserInfo().getParkId());
		return serviceQuotationService.getQuotationList(maps);
	}
	
	@ResponseBody
	@RequestMapping(value = "/QuotationServiceList.do")
	public List<ServiceQuotation> QuotationServiceList(String category, String categories) throws Exception{
		Map<String,String> maps =new HashMap<String,String>();
		maps.put("category", category);
		maps.put("categories", categories);
		maps.put("parkId", UserInfoUtil.getUserInfo().getParkId());
		return serviceQuotationService.getQuotationServiceList(maps);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getRecruitmentList.do")
	public List<ServiceQuotation> getRecruitmentList(String category, String categories) throws Exception{
		Map<String,String> maps =new HashMap<String,String>();
		maps.put("category", category);
		maps.put("categories", categories);
		maps.put("parkId", UserInfoUtil.getUserInfo().getParkId());
		return serviceQuotationService.getQuotationRecruitmentList(maps);
	}
}
