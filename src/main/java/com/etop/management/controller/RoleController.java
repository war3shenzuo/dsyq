package com.etop.management.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.model.TreeModel;
import com.etop.management.service.ParkService;
import com.etop.management.service.RoleService;
import com.etop.management.util.TreeDataHandle;

/**
 * 
 * 角色管理
 * @author shixianjie
 * 下午3:19:18
 */
@RequestMapping("/role")
@Controller
public class RoleController {

private  final static Logger LOGGER =Logger.getLogger(RoleController.class);
	
	@Resource
	RoleService roleService;

	@Resource
	ParkService mangerParkService;
	
	@RequestMapping("/index.do")
	public String  index(Model model){
		model.addAttribute("parkLists", mangerParkService.getParkListForControl());
		return "role/index";
	}
	
	@RequestMapping("/addPage.do")
	public String  addPage(String parentId,String parkId,String roleType,Model model){
		model.addAttribute("parentId", parentId);
		model.addAttribute("parks", mangerParkService.getParkListForControl());
		model.addAttribute("parkId", parkId);
		model.addAttribute("roleType",roleType);
		return "role/addRole";
	}
	
	@RequestMapping("/openBindUserPage.do")
	public String  openBindUserPage(String parkId,Model model){
		model.addAttribute("parkId", parkId);
		return "role/selectUser";
	}
	
	@ResponseBody
	@RequestMapping(value="/getRoleList.do")
	public List<TreeModel> getRoleList(Role role){

		if("#".equals(role.getId())){
			role.setId(null);
		}
		
		List<TreeModel> tree = null;
		
		try {
			
			tree = TreeDataHandle.HandleRoleData(roleService.getRoleList(role));
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色列表失败");
			
			e.printStackTrace();
		}
		
		return tree;
		
	}


	@ResponseBody
	@RequestMapping(value="/getRoleListByParkId.do")
	public List<Role> getRoleListByParkId(Role role ){
		
		List<Role> list = null;
		
		try {
			
			list = roleService.getRoleList(role);
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色列表失败");
			
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getRoleInfo.do")
	public ResultType getRoleInfo(String roleId){
		
		ResultType result = null;
		
		try {
			
			Role role = roleService.getRoleInfo(roleId);
			
			result = ResultType.getSuccess("查询角色详细信息成功", role);
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色详细信息失败！");
			
			result = ResultType.getFail("查询角色详细信息失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addRole.do")
	public ResultType addRole(Role role){
		
		ResultType result = null;
		
		try {
			
			roleService.addRole(role);
			
			result = ResultType.getSuccess("添加角色成功");
			
		} catch (Exception e) {
			
			LOGGER.error("添加角色失败！");
			
			result = ResultType.getFail("添加角色失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateRole.do")
	public ResultType updateRole(Role role){
		
		ResultType result = null;
		
		try {
			
			roleService.updateRole(role);
			
			result = ResultType.getSuccess("修改角色成功");
			
		} catch (Exception e) {
			
			LOGGER.error("修改角色失败！");
			
			result = ResultType.getFail("修改角色失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/activeOrCloseRole.do")
	public ResultType activeOrCloseRole(Role role){
		
		ResultType result = null;
		
		try {
			
			roleService.activeOrCloseRole(role);
			
			result = ResultType.getSuccess("激活/关闭角色成功");
			
		} catch (Exception e) {
			
			LOGGER.error("激活/关闭角色失败");
			
			result = ResultType.getFail("激活/关闭角色失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@RequestMapping("/addFuncForRole.do")
	@ResponseBody
	public ResultType addFuncForRole(String roleId,String funcId){
		
		ResultType result = null ;
		
		try {
			
			roleService.addFuncForRole(funcId ,roleId);
			
			result = ResultType.getSuccess("角色添加功能成功");
			
		} catch (Exception e) {
			
			LOGGER.error("角色添加功能失败");
			
			e.printStackTrace();
			
			return ResultType.getFail("角色添加功能失败");
			
		}
		
		return result;
	}
	@RequestMapping("/addReadFuncForRole.do")
	@ResponseBody
	public ResultType addReadFuncForRole(String roleId,String funcId){
		
		ResultType result = null ;
		
		try {
			
			roleService.addReadFuncForRole(funcId ,roleId);
			
			result = ResultType.getSuccess("角色添加读写功能成功");
			
		} catch (Exception e) {
			
			LOGGER.error("角色添加读写功能失败");
			
			e.printStackTrace();
			
			return ResultType.getFail("角色添加读写功能失败");
			
		}
		
		return result;
	}
	
	
	@RequestMapping("/addUserForRole.do")
	@ResponseBody
	public ResultType addUserForRole(String userId,String roleId){
		
		ResultType result = null ;
		
		try {
			
			result = roleService.addUserForRole(userId,roleId);
			
		} catch (Exception e) {
			
			LOGGER.error("角色添加用户失败");
			
			e.printStackTrace();
			
			return ResultType.getFail("角色添加用户失败");
			
		}
		
		return result;
	}
	
	@RequestMapping("/delUserForRole.do")
	@ResponseBody
	public ResultType delUserForRole(String userId,String roleId){
		
		ResultType result = null ;
		
		try {
			
			roleService.delUserForRole(roleId,userId);
			
			result = ResultType.getSuccess("角色删除用户成功");
			
		} catch (Exception e) {
			
			LOGGER.error("角色删除用户成功");
			
			e.printStackTrace();
			
			return ResultType.getFail("角色删除用户成功");
			
		}
		
		return result;
	}
	
	@RequestMapping("/copyRoleFunc.do")
	@ResponseBody
	public ResultType copyRoleFunc(String oldRole,String parkId){
		
		ResultType result = null ;
		
		try {
			
			String parentId =  roleService.getParentRoleId(parkId);
			
			if(parentId!=null){
				
				roleService.copyRoleFunc(oldRole,parkId,parentId);
				
				result = ResultType.getSuccess("复制岗位权限成功");
				
			}else{
				
				result = ResultType.getFail("找不到父节点");
			}
			
			
		} catch (Exception e) {
			
			LOGGER.error("复制岗位权限成功");
			
			e.printStackTrace();
			
			return ResultType.getFail("复制岗位权限成功");
			
		}
		
		return result;
	}
	

	
}
