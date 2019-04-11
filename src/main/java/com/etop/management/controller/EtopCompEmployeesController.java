package com.etop.management.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopCompanyEmployees;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;
import com.etop.management.constant.Constants;
import com.etop.management.service.EtopCompEmployeesService;
import com.etop.management.service.UserService;
import com.etop.management.util.UserInfoUtil;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
@Controller
@RequestMapping("/etopCompEmployees")
public class EtopCompEmployeesController extends BaseAppController{

	private final static Logger LOGGER = Logger.getLogger(EtopCompEmployeesController.class);

	@Autowired
	private EtopCompEmployeesService etopCompEmployeesService;

	@Autowired
	private UserService userService;

	@RequestMapping("/addUserPage.do")
	public String  addUserPage(Model model, String companyId){
		model.addAttribute("readonly",isReadOnly("/etopCompEmployees/addUserPage.do"));
		model.addAttribute("companyId", companyId);
		return "etopCompany/addEtopCompEmployeesInfo";
	}

	@ResponseBody
	@RequestMapping("/getEtopCompEmployeesList.do")
	public Object getEtopCompEmployeesList(EtopCompanyEmployees etopCompanyEmployees,
			@RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
			@RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit) {
		EtopPage<EtopCompanyEmployees> etopCompanyEmployeesEtopPage = etopCompEmployeesService.search(etopCompanyEmployees, offset, limit);
		return etopCompanyEmployeesEtopPage;
	}

	@RequestMapping(value = "getEtopCompEmployeesInfoById.do")
	public Object getEtopCompEmployeesInfoById(EtopCompanyEmployees etopCompanyEmployees, Model model, String readonly){

		try{
			model.addAttribute("readonly",readonly==null?isReadOnly("/etopCompEmployees/getEtopCompEmployeesInfoById.do"):readonly);
			model.addAttribute("compEmployeesInfo",
					etopCompEmployeesService.getEtopCompEmployeesById(etopCompanyEmployees.getEmployeesId()));
			model.addAttribute("employeesId", etopCompanyEmployees.getEmployeesId());
		} catch (Exception e) {

			LOGGER.error("查询员工详细信息失败");

			e.printStackTrace();

		}
		return "etopCompany/updateEtopCompEmployeesInfoById";
	}

	/**
	 * 员工列表(暂时不用)
	 * @param etopCompanyEmployees
	 * @param offset
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getEmployeesList.do")
	public Object getEmployeesList(EtopCompanyEmployees etopCompanyEmployees,
		   @RequestParam(required = false, defaultValue = Constants.PAGE_START_STRING) Integer offset,
		   @RequestParam(required = false, defaultValue = Constants.PAGE_SIZE_DEFAULT_STRING) Integer limit){
		etopCompanyEmployees.setParkIds(getParkIds());
		EtopPage<EtopCompanyEmployees> pageInfo = etopCompEmployeesService.searchEmployees(etopCompanyEmployees, offset, limit);
		return pageInfo;
	}


	/**
	 * 绑定员工
	 * @param etopCompanyEmployees
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "bindEmployee.do")
	public Object bindEmployee(EtopCompanyEmployees etopCompanyEmployees){

		ResultType result = null;

		try {

			//判断用户是否存在用户表
			EtopUser user = userService.searchByAccount(etopCompanyEmployees.getAccount());

			if(user == null){
				return ResultType.getFail("请新增绑定账号! ");
			}

			if(!("2").equals(user.getUserType())){
				return ResultType.getFail("该账号不是个人用户");
			}

			//判断该账号是否绑定公司
			EtopCompanyEmployees companyEmployees = etopCompEmployeesService.getEmploeesById(etopCompanyEmployees);


			if(companyEmployees == null){
				//获取员工id
				String userId = user.getCompanyId();
				//绑定公司员工关系
				etopCompEmployeesService.addEmpStaff(etopCompanyEmployees.getCompanyId(), userId);
				//更新个人资料
				EtopCompanyEmployees etopEmployees = new EtopCompanyEmployees();
				etopEmployees.setEmployeesId(userId);
				etopEmployees.setUserName(etopCompanyEmployees.getAccount());
				etopCompEmployeesService.update(etopEmployees);
				return ResultType.getSuccess("员工绑定成功! ");
			}else if(companyEmployees != null){
				return ResultType.getFail("该员工已绑定公司! ");
			}
		} catch (Exception e) {

			result = ResultType.getFail("员工绑定失败! ");

			LOGGER.error("员工绑定失败! ");

			e.printStackTrace();
		}

		return result;

	}

	/**
	 * 新增员工
	 *
	 * @param
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addEmployees.do")
	public Object add(EtopCompanyEmployees etopCompanyEmployees){
		ResultType result = null;
		try {

			result = etopCompEmployeesService.addUser(etopCompanyEmployees);

		} catch (Exception e){

			result = ResultType.getFail("员工新增失败");

			LOGGER.error("员工新增失败");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 修改员工基本信息
	 *
	 * @param
	 * @param
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateEmployees.do")
	public Object update(EtopCompanyEmployees etopCompanyEmployees){
		ResultType result = null;
		try {

			etopCompEmployeesService.updateUserInfo(etopCompanyEmployees);
			result = ResultType.getSuccess("修改员工基本信息成功! ");

		} catch (Exception e){

			result = ResultType.getFail("修改员工基本信息失败! ");

			LOGGER.error("修改员工基本信息失败!");
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 解绑员工
	 * @param etopCompanyEmployees
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "unbundlingEtopCompEmployees.do")
	public Object delete(EtopCompanyEmployees etopCompanyEmployees){

		ResultType result = null;

		try {

			Integer size = etopCompanyEmployees.getEmployeesIds().size();
			etopCompEmployeesService.deleteById(etopCompanyEmployees);

			result = ResultType.getSuccess("解绑员工成功",size);

		} catch (Exception e) {

			result = ResultType.getFail("解绑员工失败");

			LOGGER.error("解绑员工失败");

			e.printStackTrace();
		}

		return result;

	}
	
	@RequestMapping("/userlist.do")
	public String list() {
		return "etopCompany/employeeslist";
	}

	@RequestMapping("/listbyCompanyId.do")
	@ResponseBody
	public EtopPage<EtopCompanyEmployees> listbyCompanyId(
			String refCompanyId,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String employeesName) {
		EtopUser etopUser= UserInfoUtil.getUserInfo();
		Map<String, Object> condition = new HashMap<>();
		condition.put("companyId", etopUser.getCompanyId());
		condition.put("employeesName",employeesName);
				return etopCompEmployeesService.ListbyCompanyId(condition, offset, limit);
		
	}

	@RequestMapping(value = "EmployeesInfoById.do")
	public Object EmployeesInfoById(EtopCompanyEmployees etopCompanyEmployees, Model model, String id){
		EtopCompanyEmployees Employees = etopCompEmployeesService.getEtopCompEmployeesById(id);

		model.addAttribute("compEmployeesInfo",Employees);

		return "etopCompany/updateEtopCompEmployeesInfoById";
	}

}
