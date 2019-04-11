package com.etop.management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.ServiceDispatch;
import com.etop.management.dao.ParkDao;
import com.etop.management.service.BusinessServiceService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.service.ServiceQuotationService;


@Controller
@RequestMapping("/business")
public class BusinessServiceController  {

	Logger logger = LoggerFactory.getLogger(BusinessServiceController.class);
	
	@Resource
	BusinessServiceService businessServiceService;
	@Resource
	ServiceQuotationService serviceQuotationService;
	@Resource
	ParkDao parkDao;
	

	
//后台显示	——维修项目维护
	@RequestMapping("/businesslist.do")
	public String list(Model model) {
//		return "BusinessService/businesslist";
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
			page="BusinessService/businesslist2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="BusinessService/businesslist";
			
			
		}
		
		return page;
	}
	
	@RequestMapping("/examine.do")
	public String examine(Model model, String service_id) {
		Etopservice parkservice = serviceQuotationService.getQuotationBusinessInfoById(service_id);
		model.addAttribute("Parkservice", parkservice);
		ServiceDispatch	 serviceDispatch=serviceQuotationService.getDispatchInfoById(service_id);
	    model.addAttribute("ServiceDispatch", serviceDispatch);	
		return "BusinessService/examine";
	}
	
	@RequestMapping("/makeBusinessService.do")
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
		condition.put("quotationName",quotationName);
		condition.put("category",category);
		return businessServiceService.makeBusinessService(condition, offset, limit);
		
	}
	
	@RequestMapping("/makeBusinessServiceGroup.do")
	@ResponseBody
	public EtopPage<ServiceQuotation> getServiceQuotationGroup(
			String quotationId, String quotationName,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String category) {
		
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("quotationName",quotationName);
		condition.put("category",category);
		return businessServiceService.makeBusinessServiceGroup(condition, offset, limit);
		
	}

	
	@RequestMapping("/addPage.do")
	public String addPage(Model model, String parkGroupId) throws Exception {

		return "BusinessService/addQuotation";

	}
	
	@ResponseBody
	@RequestMapping("/addBusinessService.do")
	public ResultType addBusinessService(ServiceQuotation serviceQuotation)  throws Exception{
		ResultType result= null;
		try{
			businessServiceService.addBusinessService(serviceQuotation);
			result =ResultType.getSuccess("新建成功！");
		}catch(Exception e){
			logger.error("新建失败",e);
		result = ResultType.getFail("新建失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}
	
	@RequestMapping("/businessServicelist.do")
	public String quotationBusinesslist(Model model) {
//		return "BusinessService/businessServicelist";
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
			page="BusinessService/businessServicelist2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="BusinessService/businessServicelist";
			
			
		}
		
		return page;
	}
	
	@RequestMapping("/getBusinessService.do")
	@ResponseBody
	public EtopPage<Etopservice> getQuotationBusiness(
			 String serviceNo, String subject, String companyName, String applyTime, String completeTime,String serviceStatus,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String categoryName, String parkId) {
	
		Map<String, Object> condition = new HashMap<>();
		EtopUser  user=UserInfoUtil.getUserInfo();
		if("3".equals(user.getUserType())){//园区管理员
			condition.put("clubId",user.getParkId());
		}else{
			if(parkId==null|| parkId.length()==0){
				condition.put("clubId",111111);
			}else{
				condition.put("clubId",parkId);
			}
		}
//		condition.put("clubId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("serviceNo",serviceNo);
		condition.put("subject",subject);
		condition.put("companyName",companyName);
		condition.put("applyTime",applyTime);
		condition.put("completeTime",completeTime);
		condition.put("serviceStatus",serviceStatus);
		condition.put("categoryName",categoryName);
		return businessServiceService.getBusinessService(condition, offset, limit);
		
	}
	
	@RequestMapping("/dispatchList.do")
	@ResponseBody
	public EtopPage<ServiceDispatch> dispatchList(
			 String serviceId,@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String category) {
	
		Map<String, Object> condition = new HashMap<>();
		condition.put("serviceId",serviceId);

		return businessServiceService.dispatchList(condition, offset, limit);
		
	}
}
