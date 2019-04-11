package com.etop.website.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.Ecollege;
import com.etop.website.service.EcollegeService;



@Controller
@RequestMapping("/webecollege")
public class EcollegeController  {

	
	@Resource
	EcollegeService ecollegeService;
	
	@Resource
	ParkGroupDao parkGroupDao;
	
	@RequestMapping(value = "/index.do")
	public String ecollege(Model model,String offlineType){
		Map<String,String> m = new HashMap<String,String>();
		m.put("offlineType", offlineType);
		model.addAttribute("getCitys",ecollegeService.getCitys(m));
		return "ecollege_site/index";
		
	}
	
	
	@RequestMapping(value = "/collegeinfo.do")
	public String collegeinfo(Model model ,String id,String type){
		model.addAttribute("id",id);
		model.addAttribute("type",type);
		return "ecollege_site/collegeinfo";
		
	}
	@RequestMapping(value = "/offlinereg.do")
	public String offlinereg(Model model,String listType){
		model.addAttribute("type",listType);
		return "ecollege_site/offlinereg";
		
	}
	@RequestMapping(value = "/examination.do")
	public String examination(Model model){
		model.addAttribute("userInfo",UserInfoUtil.getUserInfo());
		return "ecollege_site/examination";
		
	}


	@ResponseBody
	@RequestMapping("/getecollege.do")
	public List<Ecollege> searchecollege(Ecollege ecollege) {
		try{
			return ecollegeService.searchList(ecollege);
		}catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	@RequestMapping(value = "/ecollegePage.do")
	public String ecollegePage(Model model, String id) throws Exception {

			Ecollege  ecollege= ecollegeService.searchInfo(id);
			model.addAttribute("course_code", ecollege.getCourse_code());
			model.addAttribute("course_name", ecollege.getCourse_name());
			model.addAttribute("platform", ecollege.getPlatform());
			model.addAttribute("post",ecollege.getPost());
			model.addAttribute("train_time",ecollege.getTrain_time());
			model.addAttribute("train_price",ecollege.getTrain_price());
			model.addAttribute("requirement", ecollege.getRequirement());
			model.addAttribute("people_number", ecollege.getPeople_number());
			model.addAttribute("total_price", ecollege.getTotal_price());
			model.addAttribute("post",ecollege.getPost());
			model.addAttribute("course_content",ecollege.getCourse_content());
			model.addAttribute("course_type",ecollege.getCourse_type());
			model.addAttribute("course_title",ecollege.getCourse_type());
			model.addAttribute("course_describe",ecollege.getCourse_describe());
			model.addAttribute("course_img",ecollege.getCourse_img());
			model.addAttribute("teacher",ecollege.getTeacher());
		    return "ecollege/registration";

		
	}
	
	
	@ResponseBody
	@RequestMapping("/selectTraining.do")
	public List<Ecollege> selectTraining(Ecollege ecollege, Integer trainType) {
		 List<Ecollege> list = null;
		 if(ecollege.getTargetName() != null){
			 ecollege.setTarget(ecollege.getTargetName());
		 }
		 if( UserInfoUtil.getUserInfo() != null){
			 if( UserInfoUtil.getUserInfo().getUserType() == "1"){
				 if(ecollege.getTarget() == null || ecollege.getTarget() == "" ){
					 String parkGroupId =UserInfoUtil.getUserInfo().getParkGroupId();
					 ParkGroup ParkGroup= parkGroupDao.getParkGroupInfo(parkGroupId);
					 ecollege.setTarget(ParkGroup.getParkGroupName());
				 }
				 if(ecollege.getParkGroupId() == null || ecollege.getParkGroupId() == "" ){
					 ecollege.setParkGroupId(UserInfoUtil.getUserInfo().getParkGroupId());
				 }
			 }
		 }
		 try{
			 if(trainType == null){//全部
				 list = ecollegeService.selectTraining(ecollege);
			 }else if(trainType == 1){//线上培训
				 list = ecollegeService.selectOnlineInfo(ecollege);
			 }else if(trainType == 0 || trainType == 3 || trainType == 2){//0：线下课程; 1：线下培训  ；2：园区活动
				 if(trainType == 0){
					 ecollege.setOfflineType(0);
					 list = ecollegeService.selectOffCourseInfo(ecollege);
				 }else if(trainType == 3){
					 ecollege.setOfflineType(1);
					 list = ecollegeService.selectOfflineInfo(ecollege);
				 }else{
					 ecollege.setOfflineType(2);
					 list = ecollegeService.selectOfflineInfo(ecollege);
				 }
			 }
		 }catch (Exception e) {
				e.printStackTrace();
			}	
	        return list;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCity.do")
	public Map<String,Object> getCity(String type){
		
			Map<String,String> m = new HashMap<String,String>();
			m.put("type", type);
		    return ecollegeService.getCity(m);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCitys.do")
	public List<Map<String, Object>> getCitys(String offlineType){
		Map<String,String> m = new HashMap<String,String>();
		m.put("offlineType", offlineType);
		return ecollegeService.getCitys(m);
		
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/getCitys.do")
//	public List<Ecollege> getCitys(String offlineType) {
//		
//		return ecollegeService.getCitys(offlineType);
//	}
}
