package com.etop.management.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Func;
import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.ResultType;
import com.etop.management.model.TreeModel;
import com.etop.management.service.FuncService;
import com.etop.management.service.ParkGroupService;
import com.etop.management.util.TreeDataHandle;
import com.etop.management.util.UserInfoUtil;

/**
 * 
 * 功能管理
 * @author shixianjie
 * 下午3:19:18
 */
@RequestMapping("/func")
@Controller
public class FuncController {

private  final static Logger LOGGER =Logger.getLogger(FuncController.class);
	
	@Resource
	FuncService funcService;
	
	@Resource
	ParkGroupService parkGroupService;
	
	@RequestMapping("/index.do")
	public String  index(){
		return "func/index";
	}
	
	@RequestMapping("/addPage.do")
	public String  addPage(String parentId,Model model){
		model.addAttribute("parentId", parentId);
		return "func/addFunc";
	}
	
	@ResponseBody
	@RequestMapping(value="/getFuncList.do")
	public List<TreeModel> getFuncList(){
		
		List<TreeModel> tree = null;
		
		try {
			
			tree = TreeDataHandle.HandleFunData(funcService.getFuncList());
			
		} catch (Exception e) {
			
			LOGGER.error("查询功能列表失败");
			
			e.printStackTrace();
		}
		
		return tree;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getRoleFuncList.do")
	public List<TreeModel> getRoleFuncList(){
		
		List<TreeModel> tree = null;
		
		try {
			
			ParkGroup pg = parkGroupService.getParkGroupInfo(UserInfoUtil.getUserParkInfo().getParkGroupId());
			
			if(pg!=null){
				
				if("1".equals(pg.getParkGroupType())){
					
					tree = TreeDataHandle.HandleFunData(funcService.getFuncParkGroupList());
				}else{
					
					tree = TreeDataHandle.HandleFunData(funcService.getTemporaryFuncList());
				}
				
			}
			
		} catch (Exception e) {
			
			LOGGER.error("查询功能列表失败");
			
			e.printStackTrace();
		}
		
		return tree;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getFuncInfo.do")
	public ResultType getFuncInfo(String funcId){
		
		ResultType result = null;
		
		try {
			
			Func func = funcService.getFuncInfo(funcId);
			
			result = ResultType.getSuccess("查询功能详细信息成功", func);
			
		} catch (Exception e) {
			
			LOGGER.error("查询功能详细信息失败！");
			
			result = ResultType.getFail("查询功能详细信息失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addFunc.do")
	public ResultType addFunc(Func Func){
		
		ResultType result = null;
		
		try {
			
			funcService.addFunc(Func);
			
			result = ResultType.getSuccess("添加功能成功");
			
		} catch (Exception e) {
			
			LOGGER.error("添加功能失败！");
			
			result = ResultType.getFail("添加功能失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateFunc.do")
	public ResultType updateFunc(Func Func){
		
		ResultType result = null;
		
		try {
			
			funcService.updateFunc(Func);
			
			result = ResultType.getSuccess("修改功能成功");
			
		} catch (Exception e) {
			
			LOGGER.error("修改功能失败！");
			
			result = ResultType.getFail("修改功能失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/activeOrClosePark.do")
	public ResultType activeOrClosePark(Func func){
		
		ResultType result = null;
		
		try {
			
			funcService.activeOrClosePark(func);
			
			result = ResultType.getSuccess("激活/关闭功能成功");
			
		} catch (Exception e) {
			
			LOGGER.error("激活/关闭功能失败");
			
			result = ResultType.getFail("激活/关闭功能失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getFuncListByRoleId.do")
	public ResultType getFuncListByRoleId(String roleId){
		
		ResultType result = null;
		
		try {
			
			result = ResultType.getSuccess("查询角色下的功能成功",funcService.getFuncListByRoleIdNoParentId(roleId));
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色下的功能失败");
			
			result = ResultType.getFail("查询角色下的功能失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getFuncListBySelected.do")
	public List<TreeModel> getFuncListBySelected(String roleId){
		
		List<TreeModel> tree = null;
		
		try {
					
			tree = TreeDataHandle.HandleFunData(funcService.getFuncListByRoleId(roleId));
			
		} catch (Exception e) {
			
			LOGGER.error("查询功能列表失败");
			
			e.printStackTrace();
		}
		
		return tree;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getFuncListRead.do")
	public ResultType getFuncListRead(String roleId){
		
		ResultType result = null;
		
		try {
			
			result = ResultType.getSuccess("查询角色下的功能成功",funcService.getFuncListRead(roleId));
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色下的功能失败");
			
			result = ResultType.getFail("查询角色下的功能失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	

	
}
