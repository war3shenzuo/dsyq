package com.etop.management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.ContractServiceItem;
import com.etop.management.bean.ResultType;
import com.etop.management.entity.EtopBillRule;
import com.etop.management.entity.EtopThreshold;
import com.etop.management.entity.EtopThreshold.ThresholdKey;
import com.etop.management.service.ContractServiceItemService;
import com.etop.management.service.EtopBillRuleService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.util.UserInfoUtil;
import com.etop.management.util.Util;

/**
 *  
 * @author lmc
 * @date 2016年8月23日
 */
@Controller
@RequestMapping("/threshold")
public class EtopThresholdController extends BaseAppController {
	
	@Inject
	private EtopThresholdService etopThresholdService;
	
	
	@Inject
	EtopBillRuleService etopBillRuleService;
	
	@Inject
	ContractServiceItemService contractServiceItemService;
	
	@RequestMapping("editview.do")
	public String editView(String parkId, ModelMap model) {
		model.put(ThresholdKey.OverdueRate.name, 
				etopThresholdService.getValue(getParkId(parkId), ThresholdKey.OverdueRate.name));
		model.put(ThresholdKey.BillAmount.name, 
				etopThresholdService.getValue(getParkId(parkId), ThresholdKey.BillAmount.name));
		model.put("deadline", 
				etopThresholdService.getValue(getParkId(parkId), ThresholdKey.deadline.name));
		
		return "/threshold/edit";
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public List<EtopThreshold> list(String parkId) {
		Map<String, Object> map = new HashMap<>();
		map.put("parkId", getParkId(parkId));
		return etopThresholdService.list(map);
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public ResultType update(@RequestParam String key, @RequestParam double value, String parkId) {
		Map<String, Object> map = new HashMap<>();
		map.put("parkId", getParkId(parkId));
		map.put("thresholdKey", key);
		List<EtopThreshold> list = etopThresholdService.list(map);
		EtopThreshold threshold;
		if(list.isEmpty()) {
			threshold = new EtopThreshold();
			threshold.setParkId(getParkId(parkId));
			threshold.setThresholdKey(key);
			threshold.setValue(value);
			threshold.setUpdateTime(new Date());
			threshold.setUpdateUser(getUserId());
			etopThresholdService.add(threshold);
		}
		else {
			threshold = list.get(0);
			threshold.setValue(value);
			threshold.setUpdateTime(new Date());
			threshold.setUpdateUser(getUserId());
			etopThresholdService.update(threshold);
		}
		
		return ResultType.getSuccess();
	}
	
	@RequestMapping("/saveRule.do")
	@ResponseBody
	public ResultType saveRule(EtopBillRule rule){
		
		ResultType result=null;
		
		try
		{
			
			rule.setRefParkId(UserInfoUtil.getUserParkInfo().getId());
			
			String rid=this.etopBillRuleService.saveRule(Util.getOpInfo(), rule);
					
			if(Util.isNullOrEmpty(rid))
			{
				result=ResultType.getFail("保存失败");
			}
			else
			{
				result=ResultType.getSuccess("保存成功");
			}
			
		}
		catch(Exception e)
		{
			result=ResultType.getError("系统错误");
		}
		
		
		return result;
	}
	
	
	@RequestMapping("/removeRule.do")
	@ResponseBody
	public ResultType removeRule(String id){
		
		ResultType result=null;
		
		try
		{
			
			int rid=this.etopBillRuleService.removeRule(Util.getOpInfo(),id);
					
			if(rid<1)
			{
				result=ResultType.getFail("删除失败");
			}
			else
			{
				result=ResultType.getSuccess("删除成功");
			}
			
		}
		catch(Exception e)
		{
			result=ResultType.getError("系统错误");
		}
		
		
		return result;
	}
	
	
	
	@RequestMapping("/saveServiceItem.do")
	@ResponseBody
	public ResultType saveServiceItem(ContractServiceItem serviceItem){
		
		ResultType result=null;
		
		try
		{
			if(Util.isNullOrEmpty(serviceItem.getId()))
			{
				serviceItem.setRefParkId(UserInfoUtil.getUserParkInfo().getId());
			}
			String rid=this.contractServiceItemService.saveServiceItem(Util.getOpInfo(), serviceItem);
					
			if(Util.isNullOrEmpty(rid))
			{
				result=ResultType.getFail("保存失败");
			}
			else
			{
				result=ResultType.getSuccess("保存成功");
			}
			
		}
		catch(Exception e)
		{
			result=ResultType.getError("系统错误");
		}
		
		
		return result;
	}
	
	
	@RequestMapping("/removeServiceItem.do")
	@ResponseBody
	public ResultType removeServiceItem(String id){
		
		ResultType result=null;
		
		try
		{
			
			int rid=this.contractServiceItemService.removeServiceItem(Util.getOpInfo(),id);
					
			if(rid<1)
			{
				result=ResultType.getFail("删除失败");
			}
			else
			{
				result=ResultType.getSuccess("删除成功");
			}
			
		}
		catch(Exception e)
		{
			result=ResultType.getError("系统错误");
		}
		
		
		return result;
	}
	
	
	
	
	
}