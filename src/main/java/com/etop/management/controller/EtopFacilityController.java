package com.etop.management.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.EtopFacilityDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopFacility;
import com.etop.management.service.EtopFacilityService;
import com.etop.management.util.UserInfoUtil;
/**
 * 
 * <br>
 * <b>功能：</b>EtopFacilityController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("facility")
public class EtopFacilityController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopFacilityController.class);
	@Autowired 
	private EtopFacilityService etopFacilityService; 
	@Resource
	private ParkDao parkDao;
	@Resource
	private EtopFacilityDao etopFacilityDao;
	
	@RequestMapping("/index.do")
	public String  index(Model model){
		//model.addAttribute("readonly",isReadOnly("/Facility/index.do"));
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
			page="etopFacility/index2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="etopFacility/index";
		}
		return page;
	}

	@RequestMapping("/addPage.do")
	public String  addPage(){
		return "etopFacility/addEtopFacility";
	}
	
	@ResponseBody
	@RequestMapping(value="/getEtopFacilityList.do")
	public EtopPage<EtopFacility> getEtopFacilityList(EtopFacility EtopFacility,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit){
		
		EtopPage<EtopFacility> page = null;
		
		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType())){
				EtopFacility.setParkId(user.getParkId());
			}else{
				if(EtopFacility.getParkId()==null|| EtopFacility.getParkId().length()==0){
					EtopFacility.setParkId("111111");
				}
			}
			page = etopFacilityService.getEtopFacilityList(EtopFacility,offset,limit);
		} catch (Exception e) {
			LOGGER.error("查询设施报错");
			e.printStackTrace();
		}
		
		return page;
		
	}
	@ResponseBody
	@RequestMapping(value="/getEtopFacilityListGroup.do")
	public EtopPage<EtopFacility> getEtopFacilityListGroup(EtopFacility EtopFacility,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit){
		
		EtopPage<EtopFacility> page = null;
		
		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType())){
				EtopFacility.setParkId(user.getParkId());
			}else{
				if(EtopFacility.getParkId()==null|| EtopFacility.getParkId().length()==0){
					EtopFacility.setParkId("111111");
				}
			}
			page = etopFacilityService.getEtopFacilityListGroup(EtopFacility,offset,limit);
		} catch (Exception e) {
			LOGGER.error("查询设施报错");
			e.printStackTrace();
		}
		
		return page;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getEtopFacilityList2.do")
	public ResultType getEtopFacilityList2(EtopFacility EtopFacility){
		
		
		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType())){
				EtopFacility.setParkId(user.getParkId());
			}else{
				if(EtopFacility.getParkId()==null|| EtopFacility.getParkId().length()==0){
					EtopFacility.setParkId("111111");
				}
			}
			return ResultType.getSuccess(etopFacilityDao.queryByList(EtopFacility.getCriteria()));
		} catch (Exception e) {
			LOGGER.error("查询设施报错");
			e.printStackTrace();
			return ResultType.getException();
		}
		
		
	}
	
	@RequestMapping(value="/getEtopFacilityInfo.do")
	public String getEtopFacilityInfo(String EtopFacilityId,Model model,String readonly){
		try{
			EtopUser  user=UserInfoUtil.getUserInfo();
			if("4".equals(user.getUserType())){//园区组管理员
				model.addAttribute("read", 1);
			}else{
				model.addAttribute("read", 0);
			}
			model.addAttribute("readonly", readonly);
			model.addAttribute("facility",etopFacilityService.getEtopFacilityInfo(EtopFacilityId));
		} catch (Exception e) {
			
			LOGGER.error("查询设施详细信息");
			
			e.printStackTrace();
			
		}
		
		return "etopFacility/EtopFacilityInfo"; 
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addEtopFacility.do")
	public ResultType addEtopFacility(EtopFacility param){
		
		ResultType result = null;
		
		try {
			
			EtopUser  user=UserInfoUtil.getUserInfo();
			param.setParkId(user.getParkId());
			etopFacilityService.addEtopFacility(param);
			
			result = ResultType.getSuccess("添加设施成功");
		
		} catch (Exception e) {
			
			result = ResultType.getFail("添加设施失败");
			
			LOGGER.error("添加设施失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateEtopFacility.do")
	public ResultType updateEtopFacility(EtopFacility EtopFacility) {

		ResultType result = null;

		try {

			etopFacilityService.updateEtopFacility(EtopFacility);

			result = ResultType.getSuccess("更新设施信息");

		} catch (Exception e) {

			LOGGER.error("更新设施失败");

			result = ResultType.getFail("更新设施失败！");

			e.printStackTrace();
		}

		return result;

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/activeOrClose.do")
	public ResultType activeOrClosePark(EtopFacility EtopFacility) {

		ResultType result = null;

		try {

			etopFacilityService.activeOrClosePark(EtopFacility);

			result = ResultType.getSuccess("激活/关闭设施");

		} catch (Exception e) {

			LOGGER.error("激活/关闭设施失败");

			result = ResultType.getFail("激活/关闭设施失败！");

			e.printStackTrace();
		}

		return result;

	}
	@ResponseBody
	@RequestMapping("/provefacilityName.do")
	public ResultType provefacilityName(String facilityName,EtopFacility etopFacility){
		ResultType result= null;
		etopFacility.setFacilityName(facilityName);
		etopFacility.setParkId(UserInfoUtil.getUserInfo().getParkId());
		if("1".equals(etopFacilityService.provefacilityName(etopFacility))){
			result =ResultType.getSuccess("验证成功！");
		}
		else{
			result = ResultType.getFail("验证失败！");
		}
		return result;
		
	}

}
