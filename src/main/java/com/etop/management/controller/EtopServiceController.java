package com.etop.management.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.Park;
import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.EtopServiceDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.etop.management.entity.EtopServiceType;
import com.etop.management.model.TreeModel;
import com.etop.management.service.EtopServiceService;
import com.etop.management.util.TreeDataHandle;
import com.etop.management.util.UploadUtil;
import com.etop.management.util.UserInfoUtil;
/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceController<br>
 *   <br>
 */ 
@Controller
@RequestMapping("/etopService")
public class EtopServiceController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopServiceController.class);
	@Autowired 
	private EtopServiceService etopServiceService; 
	
	@Resource 
	private ParkGroupDao parkGroupDao;
	
	@Resource
	private ParkDao parkDao;
	@Resource
	private EtopServiceDao etopServiceDao;
	//首页
	@RequestMapping("/index.do")
	public String  index(Model model){
		
		EtopUser  user=UserInfoUtil.getUserInfo();
		String parkGroupId="";
		String page="";
		if("4".equals(user.getUserType())){//园区组管理员
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			Park park =new Park();
			park.setParkGroupId(parkGroupId);
			List<Park> list = parkDao.getParkList(park);
			if(list.size()>0){
				model.addAttribute("defaultParkId", list.get(0).getId());
			}
			model.addAttribute("parks", list);
			page="service/index2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="service/index";
		}
		EtopServiceType type =new EtopServiceType();
		type.setParkgroupId(parkGroupId);
		model.addAttribute("types", etopServiceService.getServiceTypeListByPGId(type));
		
		return page;
		
	}
	
	//设置页
	@RequestMapping("/setIndex.do")
	public String  setIndex(Model model){
		
		return "service/setIndex";
		
	}
	
	//添加服务类型页
	@RequestMapping("/addServiceTypePage.do")
	public String  addServiceTypePage(Model model){
		
		return "service/addServiceType";
		
	}
	/**园区组列表*/
	@ResponseBody
	@RequestMapping(value = "/getPGServiceList.do")
	public List<TreeModel> getPGServiceList(EtopService service) {
		if("#".equals(service.getServiceId())){
			service.setServiceId(null);
		}
		List<TreeModel> tree = null;
		
		try {
			
			tree = TreeDataHandle.HandleParkGData(parkGroupDao.getParkGroupList(new ParkGroup()));
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色列表失败");
			
			e.printStackTrace();
		}
		
		return tree;

	}
	
	/**园区列表*/
	@ResponseBody
	@RequestMapping(value = "/getParkServiceList.do")
	public List<TreeModel> getParkServiceList(EtopService service) {
		if("#".equals(service.getServiceId())){
			service.setServiceId(null);
		}
		List<TreeModel> tree = null;
		
		try {
			String parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			Park park =new Park();
			park.setParkGroupId(parkGroupId);
			List<Park> list = parkDao.getParkList(park);
			tree = TreeDataHandle.HandleParkData(list);
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色列表失败");
			
			e.printStackTrace();
		}
		
		return tree;

	}
	@ResponseBody
	@RequestMapping(value = "/getServiceTypeList.do")
	public EtopPage<EtopServiceType> getServiceTypeList(EtopServiceType serviceType) {

		EtopPage<EtopServiceType> page = null;

		try {

			page = etopServiceService.getServiceTypeList(serviceType);

		} catch (Exception e) {

			LOGGER.error("查询服务列表失败",e);

			e.printStackTrace();
		}

		return page;

	}
	
	/**添加服务*/
	@ResponseBody
	@RequestMapping(value = "/addServiceType.do")
	public ResultType addServiceType(EtopServiceType serviceType){
		ResultType result = null;
		
		try {
			
			int res=etopServiceService.addServiceType(serviceType);
			
			if(res==-1){
				return ResultType.getError("服务编号已存在");
			}else{
			
				result = ResultType.getSuccess("添加服务类型成功");
			
			}
			
		} catch (Exception e) {
			
			LOGGER.error("添加服务类型失败！");
			
			result = ResultType.getFail("添加服务类型失败！");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	/**更新服务类型*/
	@ResponseBody
	@RequestMapping(value = "/updateServiceType.do")
	public ResultType updateServiceType(EtopServiceType serviceType){
		ResultType result = null;
		
		try {
			
			int res=etopServiceService.updateServiceType(serviceType);
			if(res==-1){
				return ResultType.getError("服务编号已存在");
			}else{
				result = ResultType.getSuccess("修改服务类型成功");
			}
			
		} catch (Exception e) {
			
			LOGGER.error("修改服务类型失败！",e);
			
			result = ResultType.getFail("修改服务类型失败！");
			
			e.printStackTrace();
		}
		
		return result;
	}

	@RequestMapping(value = "/getServiceTypeInfo.do")
	public String getParkInfo(EtopServiceType serviceType, Model model) {

		try {

			EtopServiceType type = etopServiceService.getServiceTypeInfo(serviceType);
			model.addAttribute("type",type);

		} catch (Exception e) {

			LOGGER.error("查询服务类型详细信息失败！");

			model.addAttribute("park", new Park());

			e.printStackTrace();
		}

		return "service/serviceTypeInfo";

	}
	@ResponseBody
	@RequestMapping("activeOrClosePark.do")
	public ResultType activeOrClosePark(EtopServiceType serviceType){
		ResultType result = null;
		
		try {
			
			etopServiceService.activeOrClosePark(serviceType);
			
			result = ResultType.getSuccess("添加楼成功");
			
		} catch (Exception e) {
			
			LOGGER.error("添加楼失败！");
			
			result = ResultType.getFail("添加楼失败！");
			
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**列表*/
	@ResponseBody
	@RequestMapping(value = "/getPGServiceTypeList.do")
	public List<EtopServiceType> getPGServiceTypeList(String parkGroupId) {
		
		try {
			
			EtopServiceType type =new EtopServiceType();
			
			type.setParkgroupId(parkGroupId);
			
			return etopServiceService.getServiceTypeListByPGId(type);
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色列表失败");
			
			e.printStackTrace();
			
			return new ArrayList<EtopServiceType>();
		}
		
	}
	
	
	/**绑定*/
	@ResponseBody
	@RequestMapping(value = "/bindServiceTypeList.do")
	public ResultType getBindServiceTypeList(String parkGroupId,String[] typeIds) {
		
		try {
			
			return  ResultType.getSuccess(etopServiceService.bindParkGroup(parkGroupId, Arrays.asList(typeIds)));
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色列表失败");
			
			e.printStackTrace();
			
			return  ResultType.getError("绑定出错");
		}
		
	}
	
	/**解绑*/
	@ResponseBody
	@RequestMapping(value = "/unbindServiceTypeList.do")
	public ResultType getUnbindServiceTypeList(String parkGroupId,String[] typeIds) {
		
		try {
			
			return  ResultType.getSuccess(etopServiceService.unbindParkGroup(parkGroupId, Arrays.asList(typeIds)));
			
		} catch (Exception e) {
			
			LOGGER.error("查询角色列表失败");
			
			e.printStackTrace();
			
			return  ResultType.getError("解绑出错");
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getServiceList.do")
	public EtopPage<EtopService> getServiceList(EtopService service) {

		EtopPage<EtopService> page = null;

		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if("3".equals(user.getUserType())){//园区组管理员
				service.setParkId(user.getParkId());
			}
			page = etopServiceService.getServiceList(service);

		} catch (Exception e) {

			LOGGER.error("查询服务列表失败",e);

			e.printStackTrace();
		}

		return page;

	}
	//获取楼信息
	@RequestMapping(value="/getserviceInfo.do")
	public String getserviceInfo(String serviceId,Model model){
		
		
		try {
			
			EtopService service = etopServiceService.getServiceInfo(serviceId);
			
			model.addAttribute("service", service);
			
			
		} catch (Exception e) {
			
			LOGGER.error("查询服务信息失败！",e);
			
			e.printStackTrace();
		}
		
		return "service/serviceInfo";
		
	}
	
	@ResponseBody
	@RequestMapping("/uploadImg.do")
	public ResultType uploadImg(HttpServletRequest request) {

		return UploadUtil.upLoad(request, null,null);

	}
	
	@ResponseBody
	@RequestMapping("/updateStatus.do")
	public ResultType updateStatus(String serviceId,int status, Companyservice companyservice, String message) {

		return etopServiceService.updateStatus( serviceId,status, companyservice, message);

	}
	
	@RequestMapping("/calculateRecruitment.do")
	@ResponseBody
	public EtopPage<Etopservice> calculateRecruitment(
			 String serviceNo,  String companyName, String applyTime, String completeTime, String category,
			 String serviceStatus,@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String subject) {

		Map<String, Object> condition = new HashMap<>();
		condition.put("clubId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("serviceStatus",serviceStatus);
		condition.put("type","person");
		condition.put("subject",subject);
		condition.put("serviceNo",serviceNo);
		condition.put("companyName",companyName);
		condition.put("applyTime",applyTime);
		condition.put("completeTime",completeTime);
		condition.put("categoryName",category);
		return etopServiceService.calculateQuotation(condition, offset, limit);
		
	}
	
	@RequestMapping("/calculateBusiness.do")
	@ResponseBody
	public EtopPage<Etopservice> calculateBusiness(
			 String serviceNo,  String companyName, String applyTime, String completeTime, String categoryName,
			String serviceStatus,@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String subject) {
		
		Map<String, Object> condition = new HashMap<>();
		condition.put("clubId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("serviceStatus",serviceStatus);
		condition.put("type","service");
 		condition.put("subject",subject);
		condition.put("serviceNo",serviceNo);
		condition.put("companyName",companyName);
		condition.put("applyTime",applyTime);
		condition.put("completeTime",completeTime);
		condition.put("categoryName",categoryName);
		return etopServiceService.calculateQuotation(condition, offset, limit);
		
	}
	
	@RequestMapping("/calculateQuotation.do")
	@ResponseBody
	public EtopPage<Etopservice> calculateQuotation(
			 String serviceNo,  String companyName, String applyTime, String completeTime, String categoryName,
			String serviceStatus,@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String subject) {
		
		Map<String, Object> condition = new HashMap<>();
		condition.put("clubId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("serviceStatus",serviceStatus);
		condition.put("type","repair");
		condition.put("subject",subject);
		condition.put("serviceNo",serviceNo);
		condition.put("companyName",companyName);
		condition.put("applyTime",applyTime);
		condition.put("completeTime",completeTime);
		condition.put("categoryName",categoryName);
		return etopServiceService.calculateQuotation(condition, offset, limit);
		
	}
	
	
//	状态自动变为已拒绝
	@ResponseBody
	@RequestMapping("/FacilityStatus.do")
	public void FacilityStatus()
	{

		List<EtopService> serviceList=etopServiceDao.selectFacilityServer(System.currentTimeMillis());
		
		for(EtopService etopService : serviceList)
		{
			if("1".equals(etopService.getExpirationTime())&101==etopService.getServiceStatus())
			{
				etopService.setServiceStatus(301);
				etopServiceDao.updateBySelective(etopService);
			}
		}
	
	
	}

	@ResponseBody
	@RequestMapping("/cronaddBill.do")	
	public void cronaddBill() {
		
		List<EtopService> serviceList=etopServiceDao.queryObject();
		
		
		for(EtopService etopService : serviceList)
		{
			
			if( etopService.getCompleteTime()!=null &204==etopService.getServiceStatus()){
				Date date = new Date();  
		        long times = date.getTime();
				long befores =etopService.getCompleteTime().getTime();
			if(times - befores>=259200000)
			{
				if("GGCG".equals(etopService.getServiceType())){
				etopServiceService.cronaddBill(etopService.getServiceId(), null, null);
				}
				else{
					etopServiceService.addBill(etopService.getServiceId(), null, null);
				}
			}
		}
	}
	}
	
	@RequestMapping("/getServiceBycompanyId.do")
	@ResponseBody
	public EtopPage<Companyservice> listbyCompanyId(
			String refCompanyId, String serviceNo,String serviceType,String applyTime, String parkId,
			 String employeesName, String serviceStatus, String completeTime, String searchValue, String category,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		EtopUser etopUser= UserInfoUtil.getUserInfo();
		Map<String, Object> condition = new HashMap<>();
		if("3".equals(etopUser.getUserType())){//园区组管理员
			condition.put("clubId", etopUser.getParkId());
		}else{
			condition.put("clubId", parkId);
		}
		condition.put("companyId", etopUser.getCompanyId());
		condition.put("serviceNo",serviceNo);
		condition.put("serviceType",serviceType);
		condition.put("serviceStatus",serviceStatus);
		condition.put("completeTime",completeTime);
		condition.put("applyTime",applyTime);
		condition.put("searchValue",searchValue);
		condition.put("category",category);
				return etopServiceService.getServiceBycompanyId(condition, offset, limit);
		
	}

}
