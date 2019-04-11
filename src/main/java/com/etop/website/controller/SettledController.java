package com.etop.website.controller;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.ResultType;
import com.etop.website.bean.Settled;
import com.etop.website.service.SettledService;


@Controller
@RequestMapping("/websettled")
public class SettledController  {

	Logger logger = LoggerFactory.getLogger(SettledController.class);
	
	@Resource
	SettledService settledService;
	
	@RequestMapping(value = "/index.do")
	public String about(Model model){
	
		return "settled_site/Settled";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/addApply.do")
	public ResultType addApply(Settled settled) throws Exception{
		ResultType result= null;
		try{
			settledService.addApply(settled);
			result =ResultType.getSuccess("提交成功！");
		}catch(Exception e){
			logger.error("提交失败",e);
		result = ResultType.getFail("提交失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
   

}
