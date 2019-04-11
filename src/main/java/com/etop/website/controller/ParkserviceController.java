package com.etop.website.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.ResultType;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceBusiness;
import com.etop.website.service.WebParkService;


@Controller
@RequestMapping("/webparkservice")
public class ParkserviceController  {

	Logger logger = LoggerFactory.getLogger(ParkserviceController.class);
	
	@Resource
	WebParkService webParkService;
	
	@RequestMapping(value = "/index.do")
	public String parkservice(Model model){
	
		return "parkservice_site/index";
		
	}
	
	@RequestMapping(value = "/serviceapl.do")
	public String parkserviceapl(Model model, String service_type){
	
		Map<String,String> parkservice=webParkService.searchName(service_type);
		model.addAttribute("parkservice",parkservice);

		
		return "parkservice_site/serviceapl";
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/addService.do")
	public ResultType addservice(Parkservice park,HttpServletRequest reuqest) throws Exception{
		ResultType result= null;
		try{
			webParkService.addservice(park, reuqest);
			result =ResultType.getSuccess("提交成功！");
		}catch(Exception e){
			logger.error("提交失败",e);
		result = ResultType.getFail("提交失败！");
		e.printStackTrace();		
	     }

		 return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addServer.do")
	public ResultType addService(Parkservice parkservice, HttpServletRequest request, EtopFloorRoom etopFloorRoom, ServiceBusiness serviceBusiness) throws Exception{
		ResultType result= null;
		try{
			webParkService.addService(parkservice, request, serviceBusiness, etopFloorRoom);
			result =ResultType.getSuccess("提交服务成功！");
		}catch(Exception e){
			logger.error("提交服务失败",e);
		result = ResultType.getFail("提交服务失败！");
		e.printStackTrace();		
	     }

		 return result;
	}

}
