package com.etop.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(value="remind")
public class CenterController {

	@RequestMapping("index.do")
	public String getRemind(){
		
		return "remind/index";
	}
}
