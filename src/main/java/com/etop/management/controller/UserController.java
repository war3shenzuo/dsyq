package com.etop.management.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Park;
import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.SysStatus;
import com.etop.management.service.LoginService;
import com.etop.management.service.ParkGroupService;
import com.etop.management.service.ParkService;
import com.etop.management.service.UserService;
import com.etop.management.util.UserInfoUtil;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseAppController {
	
	private  final static Logger LOGGER =Logger.getLogger(UserController.class);
	
	@Resource
	UserService userService;
	
	@Resource
	ParkService mangerParkService;
	
	@Resource
	ParkGroupService parkGroupService;
	
	@Resource
	LoginService loginService;
	
	@Resource
	ParkService parkService1;
	
	@RequestMapping("/index.do")
	public String  index(Model model ){
		
		model.addAttribute("parks", parkService1.getParkName(getParkIds()));
		
		return "user/index";
	}
	
	@RequestMapping("/openBindUserPage.do")
	public String  openBindUserPage(String parkId,String type,Model model){
		model.addAttribute("parkId", parkId);
		model.addAttribute("type", type);
		return "user/selectUser";
	}
	
	@RequestMapping("/parkApproval.do")
	public String  parkApproval(Model model){
		
		model.addAttribute("parks", mangerParkService.getParkListForControl());
		
		model.addAttribute("parkGroupId", UserInfoUtil.getUserParkInfo().getParkGroupId());

		return "user/parkApproval";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllApproval.do")
	public Map<String,String> getAllApproval(String parkId,String parkGroupId ){
		
		Map<String,String> map = new HashMap<String, String>();
		
		
		
		try {
			Park park  = mangerParkService.getParkInfo(parkId);
			
			if(park !=null){
				
				map.put("parkApproval", mangerParkService.getParkInfo(parkId).getApproval());
				
				map.put("parkFinanceApproval", mangerParkService.getParkInfo(parkId).getFinanceApproval());
			}else{
				map.put("parkApproval", null);
				
				map.put("parkFinanceApproval", null);
				
			}
		
			map.put("parkGroupApproval", parkGroupService.getParkGroupInfo(parkGroupId).getApproval());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/setAllApproval.do")
	public ResultType setAllApproval(String parkId, String pa,String paf, String parkGroupId ,String pga){
		
		ResultType result = null;
		
		try {
			
			Park park = new Park();
			park.setId(parkId);
			park.setApproval(pa);
			park.setFinanceApproval(paf);
			mangerParkService.updatePark(park);
			
			ParkGroup parkGroup = new ParkGroup();
			parkGroup.setId(parkGroupId);
			parkGroup.setApproval(pga);
			parkGroupService.updateParkGroup(parkGroup);
			
			result = ResultType.getSuccess("更新审批用户成功");
			
		} catch (Exception e) {
			
			LOGGER.error("更新审批用户成功失败");
			
			result = ResultType.getFail("更新审批用户成功失败！");
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	@RequestMapping("/addUser.do")
	public String  addUser(Model model){
		model.addAttribute("parks", mangerParkService.getParkListForControl());
		return "user/addUser";
	}
	
	@ResponseBody
	@RequestMapping(value="/getUserList.do")
	public EtopPage<EtopUser> getUserList(EtopUser user ){
		
		EtopPage<EtopUser> page = null;
		
		try {
			
			page = userService.getUserList(user);
			
		} catch (Exception e) {
			
			LOGGER.error("查询用户列表失败");
			
			e.printStackTrace();
		}
		
		return page;
		
	}
	
	
	@RequestMapping(value="/getUserInfo.do")
	public String getUserInfo(String userId,Model model){
		
		
		try {
			
			EtopUser user = userService.getUserInfo(userId);
			
			model.addAttribute("parks", mangerParkService.getParkListForControl());
			
			model.addAttribute("user", user);
			
		} catch (Exception e) {
			
			LOGGER.error("查询用户详细信息失败！");
			
			model.addAttribute("user", new EtopUser());
			
			e.printStackTrace();
		}
		
		return "user/userInfo";
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addUserInfo.do")
	public ResultType addUserInfo(EtopUser user,String roleId){
		
		ResultType result = null;
		
		try {
			
			result = userService.addUserInfo(user,roleId);
			
		} catch (Exception e) {
			
			LOGGER.error("添加用户失败！");
			
			result = ResultType.getFail("添加用户失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateUserInfo.do")
	public ResultType updateUserInfo(EtopUser user){
		
		ResultType result = null;
		
		try {
			
			userService.updateUserInfo(user);
			
			result = ResultType.getSuccess("更新用户详细信息");
			
		} catch (Exception e) {
			
			LOGGER.error("更新用户详细信息失败");
			
			result = ResultType.getFail("更新用户详细信息失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/**
	 * 展示中心账户管理
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateUser.do")
	public ResultType updateUser(EtopUser user,EtopCompanyEmployees employees){
		
		ResultType result = null;
		
		try {
			
			userService.updateUser(user,employees);
			
			result = ResultType.getSuccess("更新用户详细信息");
			
		} catch (Exception e) {
			
			LOGGER.error("更新用户详细信息失败");
			
			result = ResultType.getFail("更新用户详细信息失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/activeOrCloseUser.do")
	public ResultType activeOrCloseUser(EtopUser user){
		
		ResultType result = null;
		
		try {
			
			userService.activeOrCloseUser(user);
			
			result = ResultType.getSuccess("激活/关闭用户");
			
		} catch (Exception e) {
			
			LOGGER.error("激活/关闭用户失败");
			
			result = ResultType.getFail("激活/关闭用户失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/changePassword.do")
	public ResultType changePassword  (EtopUser user){
		
		ResultType result = null;
		
		try {
			
			result = userService.changePassword(user,"change");
			
		} catch (Exception e) {
			
			LOGGER.error("修改密码失败");
			
			result = ResultType.getFail("修改密码失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/resetPassword.do")
	public ResultType resetPassword  (EtopUser user){
		
		ResultType result = null;
		
		try {
			
			result = userService.changePassword(user,"reset");
			
		} catch (Exception e) {
			
			LOGGER.error("修改密码失败");
			
			result = ResultType.getFail("修改密码失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	
	@ResponseBody
	@RequestMapping(value="/getUserListByRoleIds.do")
	public ResultType getUserListByRoleIds  (String roleId){
		
		ResultType result = null;
		
		try {
			
			List<String> roleIds = new ArrayList<String>();
			
			roleIds.add(roleId);
			
			List<EtopUser> list = userService.getUserListByRoleIds(roleIds);
			
			result = ResultType.getSuccess("根据角色ID查询用户", list);
			
		} catch (Exception e) {
			
			LOGGER.error("根据角色ID查询用户失败");
			
			result = ResultType.getFail("根据角色ID查询用户失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/**
	 * 
	 * @param newUser 用户对象
	 * @param acode 激活码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/register.do")
	public ResultType register  (EtopUser newUser,String acode,HttpServletRequest request){
		
		ResultType result = null;
		
		EtopUser olduser = new EtopUser();
		olduser.setUserName(newUser.getUserName());
		olduser.setPassWord(newUser.getPassWord());
		olduser.setUserType(newUser.getUserType());
		
		try {
			
			 result = userService.registerUser(newUser,acode);
			 
			 if(SysStatus.SUCCESS.getStatus()==result.getStatus()){
				 
				 loginService.loginCheck(olduser, request);
			 }
			
		} catch (Exception e) {
			
			LOGGER.error("注册用户失败");
			
			result = ResultType.getFail("注册用户失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
}
