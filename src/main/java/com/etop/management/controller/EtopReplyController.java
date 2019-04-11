package com.etop.management.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopReply;
import com.etop.management.bean.ResultType;
import com.etop.management.service.EtopReplyService;


@Controller
@RequestMapping("/reply")
public class EtopReplyController  {

	Logger logger = LoggerFactory.getLogger(EtopReplyController.class);
	
	@Resource
	EtopReplyService etopReplyService;

	

	@ResponseBody
	@RequestMapping("/addReply.do")
	public ResultType addReply(EtopReply etopReply)  throws Exception{
		ResultType result= null;
		try{
			etopReplyService.addReply(etopReply);
			result =ResultType.getSuccess("新建回复成功！");
		}catch(Exception e){
			logger.error("新建回复失败",e);
		result = ResultType.getFail("新建回复失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}

	
	@ResponseBody
	@RequestMapping("/getReply.do")
	public ResultType getReply(String noticeId)  throws Exception{
		ResultType result= null;
		try{
			etopReplyService.getReply(noticeId);
			result =ResultType.getSuccess("查询回复成功！");
		}catch(Exception e){
			logger.error("查询回复失败",e);
		result = ResultType.getFail("查询回复失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}
	
	
}
