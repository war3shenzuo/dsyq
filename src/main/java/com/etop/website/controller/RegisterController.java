package com.etop.website.controller;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/register")
public class RegisterController  {

	private  final static Logger LOGGER =Logger.getLogger(RegisterController.class);
	
	@RequestMapping(value = "/registerChoose.do")
	public String registerChoose(Model model){
	
		return "registerChoose";
		
	}
	
	@RequestMapping(value = "/register.do")
	public String register(Model model){
	
		return "register";
		
	}
	
}
