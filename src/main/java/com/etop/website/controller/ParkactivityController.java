package com.etop.website.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.Ecollege;
import com.etop.website.service.EcollegeService;



@Controller
@RequestMapping("/Parkactivity")
public class ParkactivityController  {

	
	@Resource
	EcollegeService ecollegeService;
	

	
	@RequestMapping(value = "/activity.do")
	public String ecollege2(Model model){
		Map<String,String> m = new HashMap<String,String>();
		m.put("offlineType", "2");
		model.addAttribute("getCitys",ecollegeService.getCitys(m));
		return "ecollege_site/index-a";
		
	}


}
