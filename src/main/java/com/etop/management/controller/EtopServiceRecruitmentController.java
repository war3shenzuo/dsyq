package com.etop.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.ServiceDispatch;
import com.etop.website.bean.ServiceBusiness;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceRecruitment;
import com.etop.management.service.EtopServiceRecruitmentService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.service.ServiceQuotationService;
import com.etop.website.service.WebParkService;
/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceRecruitmentController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("recruitment")
public class EtopServiceRecruitmentController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopServiceRecruitmentController.class);
	@Autowired 
	private EtopServiceRecruitmentService etopServiceRecruitmentService; 
	
	@Resource 
	private ParkGroupDao parkGroupDao;
	
	@Resource
	private ParkDao parkDao;
	
	@Resource
	ServiceQuotationService serviceQuotationService;
	@Resource
	WebParkService webParkService;
	@Resource
	ContractDao contractDao;
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
			page="recruitment/index2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="recruitment/index";
			
			
		}
		
		return page;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getServiceList.do")
	public EtopPage<EtopService> getServiceList(EtopService service ,String quotationId, String quotationName,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String serviceStatus, String serviceNo
			, String category, String subject, String number, String totalPrice, String companyName,String applyTime, String completeTime, String parkId) {

		EtopPage<EtopService> page = null;

		try {
			Map<String, Object> condition = new HashMap<>();
			EtopUser  user=UserInfoUtil.getUserInfo();
			if("3".equals(user.getUserType())){//园区管理员
				service.setParkId(user.getParkId());
				condition.put("clubId",user.getParkId());
			}else{
				if(parkId==null|| parkId.length()==0){
					condition.put("clubId",111111);
				}else{
					condition.put("clubId",parkId);
				}
			}
			condition.put("serviceNo",serviceNo);
			condition.put("serviceStatus",serviceStatus);
			condition.put("category",category);
			condition.put("subject",subject);
			condition.put("number",number);
			condition.put("totalPrice",totalPrice);
			condition.put("companyName",companyName);
			condition.put("applyTime",applyTime);
			condition.put("completeTime",completeTime);
			page = etopServiceRecruitmentService.getServiceList(condition, offset, limit);
		} catch (Exception e) {

			LOGGER.error("查询服务列表失败",e);

			e.printStackTrace();
		}

		return page;

	}
//	@ResponseBody
//	@RequestMapping(value = "/getServiceList.do")
//	public EtopPage<EtopService> getServiceList(EtopService service) {
//
//		EtopPage<EtopService> page = null;
//
//		try {
//			EtopUser  user=UserInfoUtil.getUserInfo();
//			if("3".equals(user.getUserType())){//园区组管理员
//				service.setParkId(user.getParkId());
//			}
//			page = etopServiceRecruitmentService.getServiceList(service);
//
//		} catch (Exception e) {
//
//			LOGGER.error("查询服务列表失败",e);
//
//			e.printStackTrace();
//		}
//
//		return page;
//
//	}
	
	@RequestMapping(value="/getserviceInfo.do")
	public String getserviceInfo(String serviceId,Model model){
		
		
		try {
			
			EtopService service = etopServiceRecruitmentService.getServiceInfo(serviceId);
			
			EtopServiceRecruitment recruitment=etopServiceRecruitmentService.getServicePurchaseInfo(serviceId);
			
			Etopservice etopservice = etopServiceRecruitmentService.querysByServiceId(serviceId);
			
			ServiceDispatch serviceDispatch=serviceQuotationService.getDispatchInfoById(serviceId);
			
			if(recruitment.getSexual()==1){
				recruitment.setSexualStr("男");
			}else if(recruitment.getSexual()==2){
				recruitment.setSexualStr("女");
			}else if(recruitment.getSexual()==3){
				recruitment.setSexualStr("无要求");
			}
			
			model.addAttribute("service", service);
			
			model.addAttribute("recruitment", recruitment);
			
			model.addAttribute("etopservice", etopservice);
			
			model.addAttribute("ServiceDispatch", serviceDispatch);	
			
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
		
		return "recruitment/serviceInfo";
		
	}
	
	@RequestMapping(value="/examine.do")
	public String examine(String serviceId,Model model){
		
		
		try {
			
			EtopService service = etopServiceRecruitmentService.getServiceInfo(serviceId);
			
			EtopServiceRecruitment recruitment=etopServiceRecruitmentService.getServicePurchaseInfo(serviceId);
			
			Etopservice etopservice = etopServiceRecruitmentService.querysByServiceId(serviceId);
			
			ServiceDispatch serviceDispatch=serviceQuotationService.getDispatchInfoById(serviceId);
			
			
			model.addAttribute("service", service);
			
			model.addAttribute("recruitment", recruitment);
			
			model.addAttribute("etopservice", etopservice);
			
			model.addAttribute("ServiceDispatch", serviceDispatch);	
			
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
		
		return "recruitment/examine";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/add.do")
	public ResultType add(EtopServiceRecruitment param,EtopService service, ServiceBusiness serviceBusiness, EtopFloorRoom etopFloorRoom){
		
		ResultType result = null;
		
		try {
			
			etopServiceRecruitmentService.add(param,service, serviceBusiness,etopFloorRoom);
			
			result = ResultType.getSuccess("提交服务成功");
		
		} catch (Exception e) {
			
			result = ResultType.getFail("提交服务失败");
			
			LOGGER.error("提交服务失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@RequestMapping(value = "/recruitmentApply.do")
	public String recruitmentApply(String companyId, Model model, String service_type,ServiceQuotation serviceQuotation, String category) throws Exception{

//		List<EtopFloorRoom> room =  serviceQuotationService.selectRoom(UserInfoUtil.getUserInfo().getCompanyId());
		List<Contract> room =  contractDao.getRoomList(UserInfoUtil.getUserInfo().getCompanyId());
		model.addAttribute("room",room);
		Map<String,String> maps =new HashMap<String,String>();
		maps.put("category", category);
		List<ServiceQuotation> item =  serviceQuotationService.getQuotationRecruitmentList(maps);
		model.addAttribute("item",item);
		Map<String,String> parkservice=webParkService.searchName(service_type);
		model.addAttribute("parkservice",parkservice);
		serviceQuotation.setType("person");
		serviceQuotation.setParkId(UserInfoUtil.getUserInfo().getParkId());
		List<ServiceQuotation> categorys =  serviceQuotationService.getCategoryList(serviceQuotation);
		model.addAttribute("category",categorys);
		return "recruitment/recruitmentApply";
	}

	
	
	@RequestMapping("/recruitmentlist.do")
	public String list(Model model) {
//		return "recruitment/recruitmentlist";

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
			page="recruitment/recruitmentlist2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="recruitment/recruitmentlist";
			
			
		}
		
		return page;
	}
	
	@RequestMapping("/makeRecruitment.do")
	@ResponseBody
	public EtopPage<ServiceQuotation> makeRecruitment(
			 String quotationId, String quotationName,
			 @RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String category,String parkId) {
		
				Map<String, Object> condition = new HashMap<>();
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
//				condition.put("parkId",UserInfoUtil.getUserInfo().getParkId());
				condition.put("quotationName",quotationName);
				condition.put("category",category);
				return etopServiceRecruitmentService.makeRecruitment(condition, offset, limit);
				
			}
	@RequestMapping("/makeRecruitmentGroup.do")
	@ResponseBody
	public EtopPage<ServiceQuotation> makeRecruitmentGroup(
			String quotationId, String quotationName,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String category,String parkId) {
		
		Map<String, Object> condition = new HashMap<>();
//		EtopUser  user=UserInfoUtil.getUserInfo();
//		if("3".equals(user.getUserType())){//园区管理员
//			condition.put("parkId",user.getParkId());
//		}else{
//			if(parkId==null|| parkId.length()==0){
//				condition.put("parkId",111111);
//			}else{
//				condition.put("parkId",parkId);
//			}
//		}
		condition.put("parkId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("quotationName",quotationName);
		condition.put("category",category);
		return etopServiceRecruitmentService.makeRecruitmentGroup(condition, offset, limit);
		
	}
	
	@RequestMapping("/addPage.do")
	public String addPage(Model model, String parkGroupId) throws Exception {

		return "recruitment/addQuotation";

	}
	
	@ResponseBody
	@RequestMapping("/addRecruitment.do")
	public ResultType addRecruitment(ServiceQuotation serviceQuotation)  throws Exception{
		ResultType result= null;
		try{
			etopServiceRecruitmentService.addRecruitment(serviceQuotation);
			result =ResultType.getSuccess("新建成功！");
		}catch(Exception e){
			LOGGER.error("新建失败",e);
		result = ResultType.getFail("新建失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}
}
