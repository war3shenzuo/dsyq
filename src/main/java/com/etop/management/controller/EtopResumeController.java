package com.etop.management.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopResume;
import com.etop.management.service.EtopResumeService;
import com.etop.management.util.UploadUtil;
import com.etop.management.util.UserInfoUtil;
/**
 * 
 * <br>
 * <b>功能：</b>EtopResumeController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("resume")
public class EtopResumeController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopResumeController.class);
	@Autowired 
	private EtopResumeService etopResumeService; 
	
	@RequestMapping("/index.do")
	public String  index(Model model,String serviceId,String status){
	    model.addAttribute("serviceId", serviceId);
	    model.addAttribute("status", status);
		return "resume/index";
		
	}
	
	@RequestMapping("/index2.do")
	public String  index2(Model model,String serviceId,String status){
	    model.addAttribute("serviceId", serviceId);
	    model.addAttribute("status", status);
		return "resume/index2";
		
	}

	
	@ResponseBody
	@RequestMapping(value="/getEtopResumeList.do")
	public EtopPage<EtopResume> getEtopResumeList(EtopResume etopResume){
		
		EtopPage<EtopResume> page = null;
		
		try {
			
			page = etopResumeService.getEtopResumeList(etopResume);
		} catch (Exception e) {
			LOGGER.error("查询简历报错");
			e.printStackTrace();
		}
		
		return page;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getEtopResumeInfo.do")
	public ResultType getEtopResumeInfo(String etopResumeId,Model model,String readonly){
		try{
			
			return ResultType.getSuccess(etopResumeService.getEtopResumeInfo(etopResumeId));
		} catch (Exception e) {
			
			LOGGER.error("查询简历详细信息");
			
			e.printStackTrace();
			return ResultType.getError("获取失败");
			
		}
		
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addEtopResume.do")
	public ResultType addEtopResume(EtopResume param){
		
		ResultType result = null;
		
		try {
			etopResumeService.addEtopResume(param);
			
			result = ResultType.getSuccess("添加简历成功");
		
		} catch (Exception e) {
			
			result = ResultType.getFail("添加简历失败");
			
			LOGGER.error("添加简历失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateEtopResume.do")
	public ResultType updateEtopResume(EtopResume etopResume) {

		ResultType result = null;

		try {

			etopResumeService.updateEtopResume(etopResume);

			result = ResultType.getSuccess("更新简历信息");

		} catch (Exception e) {

			LOGGER.error("更新简历失败");

			result = ResultType.getFail("更新简历失败！");

			e.printStackTrace();
		}

		return result;

	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteEtopResumeInfo.do")
	public ResultType deleteEtopResumeInfo(EtopResume etopResume) {
	
		ResultType result = null;
	
		try {
	
			etopResumeService.deleteEtopResumeInfo(etopResume);
	
			result = ResultType.getSuccess("更新简历信息");
	
		} catch (Exception e) {
	
			LOGGER.error("更新简历失败");
	
			result = ResultType.getFail("更新简历失败！");
	
			e.printStackTrace();
		}
	
		return result;
	
	}
	
	
	@ResponseBody
	@RequestMapping("/upload.do")
	public ResultType upload(HttpServletRequest request){
		EtopUser  user=UserInfoUtil.getUserInfo();
    	return UploadUtil.upLoad(request, "", user.getParkId());
    	
	}
	

}
