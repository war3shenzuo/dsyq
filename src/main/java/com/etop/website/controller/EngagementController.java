package com.etop.website.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Contract;
import com.etop.management.bean.Park;
import com.etop.management.dao.ContractDao;
import com.etop.management.entity.EtopFacility;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.service.EtopFacilityService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.dao.ServiceQuotationDao;
import com.etop.website.service.ServiceQuotationService;

/** 
 * @author lvxiwei 

 * @time   2016年9月14日 下午3:45:14 
 */

@Controller
@RequestMapping("/engagement")
public class EngagementController {

	@Resource
	EtopFacilityService etopFacilityService;
	@Resource
	ServiceQuotationService serviceQuotationService;
	@Resource
	ContractDao contractDao;
	
	@RequestMapping(value = "/meetRoom.do")
	public String meetRoom(){
	
		return "meetRoom";
		
	}
	
	@RequestMapping(value = "/meetCal.do")
	public String meetRoomtime(Model model, String companyId){
	Park park=UserInfoUtil.getUserParkInfo();
	model.addAttribute("park", park);
	List<EtopFacility> facilityType =  etopFacilityService.getfacilityTypeList(park.getId());
	model.addAttribute("facilityType",facilityType);
//	List<EtopFloorRoom> room =  serviceQuotationService.selectRoom(UserInfoUtil.getUserInfo().getCompanyId());
	List<Contract> room =  contractDao.getRoomList(UserInfoUtil.getUserInfo().getCompanyId());
	model.addAttribute("room",room);
		return "meetCal";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getfacilityName.do")
	public List<EtopFacility> getfacilityName(EtopFacility etopFacility) throws Exception{

		return etopFacilityService.getfacilityName(etopFacility);
		
	}
	
}
