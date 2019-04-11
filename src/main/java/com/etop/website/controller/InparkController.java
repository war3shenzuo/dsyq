package com.etop.website.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.ParkGroupPresentation;
import com.etop.management.controller.BaseAppController;
import com.etop.website.bean.Inpark;
import com.etop.website.bean.Webpark;
import com.etop.website.service.InparkService;
import com.etop.website.service.OlmerchantsService;



@Controller
@RequestMapping("/webinpark")
public class InparkController extends BaseAppController {


	
	Logger logger = LoggerFactory.getLogger(InparkController.class);
	@Resource
	InparkService inparkService;
	
	
	@Resource
	OlmerchantsService  olmerchantsService ;
	
	
	@RequestMapping(value = "/inparkList.do")
	public String about(Model model){
	
		return "inpark_site/parklist";
		
	}
	
	
	
	//获取园区列表
	@ResponseBody
	@RequestMapping("/getInpark.do")
	public List<Webpark> searchwebpark(Webpark webpark) {
		try{
			return olmerchantsService.searchWebpark(webpark);
		}catch (Exception e) {
			logger.error("显示异常", e);
			e.printStackTrace();
		}		
		return null;
	}
	
	@RequestMapping(value = "/inparkInfo.do")
	public String inparkPage(Model model, String parkGroupId) throws Exception {

			Inpark inpark = inparkService.searchInfo(parkGroupId);
			model.addAttribute("park_group_name", inpark.getParkGroupName());
			model.addAttribute("parkGroupDescribe",inpark.getParkGroupDescribe());
			model.addAttribute("city",inpark.getCity());
			model.addAttribute("img",inpark.getParkGroupImg());
			model.addAttribute("parkGroupId", parkGroupId);
			model.addAttribute("logo", inpark.getLogo());
			model.addAttribute("link", inpark.getLink());
		    return "inpark_site/index";
		
	}
	
	@RequestMapping(value = "/parkGroupInfo.do")
	public String parkPage(Model model, String parkGroupId) throws Exception {

			Inpark inpark = inparkService.searchInfo(parkGroupId);
			model.addAttribute("park_group_name", inpark.getParkGroupName());
			model.addAttribute("parkGroupDescribe",inpark.getParkGroupDescribe());
			model.addAttribute("city",inpark.getCity());
			model.addAttribute("img",inpark.getParkGroupImg());
			List<ParkGroupPresentation> parkGroupPresentation = inparkService.getCompanyByparkGroupId(parkGroupId);
			model.addAttribute("ParkGroupPresentation", parkGroupPresentation);
		    return "inpark_site/parkinfo";
		
	}
	
	
	@RequestMapping(value = "/parkGroupService.do")
	public String parkService(Model model, String parkGroupId) throws Exception {

			Inpark inpark = inparkService.searchInfo(parkGroupId);
			model.addAttribute("park_group_name", inpark.getParkGroupName());
			model.addAttribute("parkGroupDescribe",inpark.getParkGroupDescribe());
			model.addAttribute("city",inpark.getCity());
			model.addAttribute("img",inpark.getParkGroupImg());
			List<ParkGroupPresentation> parkGroupPresentation = inparkService.getServiceyByparkGroupId(parkGroupId);
			model.addAttribute("ParkGroupPresentation", parkGroupPresentation);
		    return "inpark_site/parkservice";
		
	}
	
	
	@RequestMapping(value = "/parkList.do")
	public String parkListg(Model model, String parkGroupId) throws Exception {

			Inpark inpark = inparkService.searchInfo(parkGroupId);
			model.addAttribute("park_group_name", inpark.getParkGroupName());
			model.addAttribute("parkGroupDescribe",inpark.getParkGroupDescribe());
			model.addAttribute("city",inpark.getCity());
			model.addAttribute("img",inpark.getParkGroupImg());
		    return "inpark_site/parklistg";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCity.do")
	public List<Inpark> getCity(Model model, String city) throws Exception {

		
		    return inparkService.getCity(city);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCity2.do")
	public Map<String,Object> getCity2(String parkGroupId){
		
			Map<String,String> m = new HashMap<String,String>();
			m.put("parkGroupId",parkGroupId);
		    return inparkService.getCity2(m);
		
	}
	
	
	//获取园区介绍列表
	@ResponseBody
	@RequestMapping(value="/getCompanyByparkGroupId.do")
	public List<ParkGroupPresentation> getCompanyByparkGroupId(String parkGroupId){
		
		List<ParkGroupPresentation> list = null;
		
		try {
			
			list = inparkService.getCompanyByparkGroupId(parkGroupId);
			
		} catch (Exception e) {
			
			logger.error("查询园区介绍失败");
			
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	//获取服务介绍列表
	@ResponseBody
	@RequestMapping(value="/getServiceByparkGroupId.do")
	public List<ParkGroupPresentation> getServiceByparkGroupId(String parkGroupId){
		
		List<ParkGroupPresentation> list = null;
		
		try {
			
			list = inparkService.getServiceyByparkGroupId(parkGroupId);
			
		} catch (Exception e) {
			
			logger.error("查询服务介绍失败");
			
			e.printStackTrace();
		}
		
		return list;
		
	}

	
}
