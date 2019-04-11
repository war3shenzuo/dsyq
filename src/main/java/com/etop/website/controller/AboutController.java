package com.etop.website.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etop.management.service.WebIndexService;
import com.etop.management.util.ProUtil;
import com.github.pagehelper.StringUtil;
@Controller
@RequestMapping("/about")
public class AboutController {

	@Resource
	WebIndexService webIndexService;
	
	@RequestMapping(value = "/about.do")
	public String about(Model model, HttpServletRequest request, String url) {
	
		String value = webIndexService.getWebIndex("about");
		
		ProUtil pro =  new ProUtil(ProUtil.PROPERTIESPATH, "path.properties");
		
		System.out.println(pro.getPropertiesValue("path"));
		
		value = value.replaceAll("%path%", pro.getPropertiesValue("path"));
		
		model.addAttribute("value", value);
		
		return "about";
		
	}
}
