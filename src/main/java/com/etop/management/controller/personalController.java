package com.etop.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.PersonalInfomation;
import com.etop.management.bean.EtopCompanyEmployees.employeesSex;
import com.etop.management.bean.PersonalInfomation.employeesSexType;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Workexperience;
import com.etop.management.entity.EtopBill.AuditStatus;
import com.etop.management.service.EtopCompEmployeesService;
import com.etop.management.service.PersonalUserService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.UserInfoUtil;

@Controller
@RequestMapping("/personal")

public class personalController {

	Logger logger = LoggerFactory.getLogger(personalController.class);
	
	@Resource
	PersonalUserService personalUserService;
	
	@Resource
	EtopCompEmployeesService etopCompEmployeesService;
	
	@RequestMapping("/createview.do")
	public String createView() {
		return "personal/create";
	}
	
	@RequestMapping("/getExperienceByemployeesId.do")
	@ResponseBody
	public EtopPage<Workexperience> listbyCompanyId(
			String employeesId,@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		
		EtopUser etopUser= UserInfoUtil.getUserInfo();
		employeesId=etopUser.getCompanyId();
		return personalUserService.getExperienceByemployeesId(employeesId, offset, limit);
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateUserInfo.do")
	public ResultType updateUserInfo(PersonalInfomation infomation) throws Exception{
		ResultType result= null;
		try{
			personalUserService.updateUserInfo(infomation);
			result =ResultType.getSuccess("提交成功！");
		}catch(Exception e){
			logger.error("提交失败",e);
		result = ResultType.getFail("提交失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
	@RequestMapping(value = "/userInfo.do")
	public String accountManaget(Model model) {

		EtopCompanyEmployees Employees = etopCompEmployeesService.getEtopCompEmployeesById(UserInfoUtil.getUserInfo().getCompanyId());

		model.addAttribute("EmployeesUserInfo",Employees);
//		model.addAttribute("birth",DateUtil.formatDate(Employees.getBirth()));
//		model.addAttribute("hiredate",DateUtil.formatDate(Employees.getHiredate()));
//		model.addAttribute("startTime",DateUtil.formatDate(Employees.getStartTime()));
//		model.addAttribute("endTime",DateUtil.formatDate(Employees.getEndTime()));
//		model.addAttribute("startDate",DateUtil.formatDate(Employees.getStartDate()));
//		model.addAttribute("overDate",DateUtil.formatDate(Employees.getOverDate()));		
//		model.addAttribute("workTime",DateUtil.formatDate(Employees.getWorkTime()));
//		model.addAttribute("overTime",DateUtil.formatDate(Employees.getOverTime()));

		return "personal/peopleInfo";
	}
    @ResponseBody
	@RequestMapping(value = "/add.do")
	public ResultType add(Workexperience workexperience) {
		ResultType result= null;
		try{
			personalUserService.add(workexperience);
			result =ResultType.getSuccess("添加成功！");
		}catch(Exception e){
			logger.error("添加失败",e);
		result = ResultType.getFail("添加失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
		
	@ResponseBody
	@RequestMapping(value = "/updateUserExperience.do")
	public ResultType updateUserExperience(Workexperience workexperience) throws Exception{
		ResultType result= null;
		try{
			personalUserService.updateUserExperience(workexperience);
			result =ResultType.getSuccess("提交成功！");
		}catch(Exception e){
			logger.error("提交失败",e);
		result = ResultType.getFail("提交失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
	

	@RequestMapping(value = "/getExperienceInfoById.do")
	public String getExperienceInfoById(String id, Model model) {

		Workexperience workexperience=	personalUserService.getExperienceInfoById(id);
		model.addAttribute("workexperience",workexperience);
		model.addAttribute("workStart",DateUtil.formatDate(workexperience.getWorkStart()));
		model.addAttribute("workEnd",DateUtil.formatDate(workexperience.getWorkEnd()));
		return "personal/Infodetail";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteExperience.do")
	public ResultType deleteExperience(String id)  throws Exception{
		ResultType result= null;
		try{
			personalUserService.deleteExperience(id);
			result =ResultType.getSuccess("删除成功！");
		}catch(Exception e){
			logger.error("删除失败",e);
		result = ResultType.getFail("删除失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}
}


