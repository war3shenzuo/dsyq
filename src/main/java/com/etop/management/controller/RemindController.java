package com.etop.management.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Remind;
import com.etop.management.bean.ResultType;
import com.etop.management.service.RemindService;

/** 
 * @author lvxiwei 

 * @time   2016年9月19日 下午5:33:37 
 */
@Controller
@RequestMapping("/remind")
public class RemindController {
	private  final static Logger LOGGER =Logger.getLogger(RoleController.class);
	@Resource
	RemindService remindService;
	

	//显示查询列表
	@ResponseBody
	@RequestMapping("/searchbytarget.do")	
	public List<Remind> searchbytarget(String target, HttpServletRequest request, String beforeTime) throws ParseException{
		
		return remindService.searchbytarget(target, request, beforeTime);
		
	}
	
	//新增提醒
	@ResponseBody
	@RequestMapping("/addRemind.do")
	public void addRemind(Remind remind) {
		
		 remindService.addRemind(remind);
	}
	
	@ResponseBody
	@RequestMapping("/searchbyId.do")
    public Remind searchbyId(String id, Model model) {
//		Remind remind=remindService.searchbyId(id);	
//		model.addAttribute("title", remind.getTitle());
//		model.addAttribute("remindType", remind.getRemindType());
//		model.addAttribute("content", remind.getContent());
//		model.addAttribute("createTime", remind.getCreateTime());
		return remindService.searchbyId(id);
		
	}
	
	@ResponseBody
	@RequestMapping("/upadteStatus.do")
	public ResultType upadteStatus(String id) {
           ResultType result = null;
		
		try {
			
			remindService.upadteStatus(id);
			
			result = ResultType.getSuccess("已读");
			
		} catch (Exception e) {
			
			LOGGER.error("标记失败");
			
			result = ResultType.getFail("标记失败！");
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/upadteTop.do")
	public ResultType upadteTop (Remind remind, String id) {
		ResultType result = null;
		
		try {
			
			remindService.upadteTop(remind,id);
			
			result = ResultType.getSuccess("已置顶");
			
		} catch (Exception e) {
			
			LOGGER.error("置顶失败");
			
			result = ResultType.getFail("置顶失败！");
			
			e.printStackTrace();
		}
		
		return result;
	}

	
	@ResponseBody
	@RequestMapping("/finalTop.do")
	public ResultType finaltop (Remind remind, String id) {
		ResultType result = null;
		
		try {
			
			remindService.finalTop(remind, id);
			
			result = ResultType.getSuccess("绝对置顶");
			
		} catch (Exception e) {
			
			LOGGER.error("置顶失败");
			
			result = ResultType.getFail("置顶失败！");
			
			e.printStackTrace();
		}
		
		return result;
	}
}
