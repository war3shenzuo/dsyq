package com.etop.management.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ParkGroup;
import com.etop.management.bean.ParkGroupPresentation;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Settled;
import com.etop.management.service.ParkGroupService;
import com.etop.management.util.UploadUtil;
import com.etop.management.util.UserInfoUtil;

@Controller
@RequestMapping(value="/parkgroup")
public class ParkGroupController {
	
	@Resource
	ParkGroupService parkGroupService;
	
	private  final static Logger LOGGER =Logger.getLogger(ParkGroupController.class);

	@RequestMapping("/index.do")
	public String  index(){
		return "parkGroup/index";
	}
	
	@RequestMapping("/settledIndex.do")
	public String  settledIndex(){
		return "parkGroup/settledIndex";
	}
	
	@RequestMapping("/addPage.do")
	public String  openpackGroupListPage(Model model){
		
		model.addAttribute("id", UUID.randomUUID().toString());
		
		return "parkGroup/addParkGroup";
	}
	
	@ResponseBody
	@RequestMapping(value="/getParkGroupList.do")
	public EtopPage<ParkGroup> getParkGroupList( ParkGroup group){
		
		EtopPage<ParkGroup> page = null;
		
		try {
			page = parkGroupService.getParkGroupList(group);
		} catch (Exception e) {
			LOGGER.error("查询园区组报错");
			e.printStackTrace();
		}
		
		return page;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getSettledList.do")
	public EtopPage<Settled> getSettledList(Settled settled){
		
		EtopPage<Settled> page = null;
		
		try {
			page = parkGroupService.getSettledList(settled);
		} catch (Exception e) {
			LOGGER.error("查入驻报错");
			e.printStackTrace();
		}
		
		return page;
		
	}
	
	@RequestMapping(value="/getSettledInfo.do")
	public String getSettledInfo(String settledId,Model model){
		try{
			
			model.addAttribute("settledInfo",parkGroupService.getSettledInfo(settledId));
			
		} catch (Exception e) {
			
			LOGGER.error("查询入驻详细信息");
			
			e.printStackTrace();
			
		}
		
		return "parkGroup/SettledInfo"; 
		
		
	}
	
	@RequestMapping(value="/getParkGroupInfo.do")
	public String getParkGroupInfo(String parkGroupId,Model model,@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit){
		try{
			
			ParkGroup pg = parkGroupService.getParkGroupInfo(parkGroupId);
			
			model.addAttribute("PG",pg);
			
			model.addAttribute("SS",parkGroupService.getSettledInfo(pg.getSettledId()));

		} catch (Exception e) {
			
			LOGGER.error("查询园区组详细信息");
			
			e.printStackTrace();
			
		}
		
		return "parkGroup/parkGroupInfo"; 
		
		
	}
	
	@RequestMapping(value="/getParkGroupInfo2.do")
	public String getParkGroupInfo2(Model model){
		try{
			
			String parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			
			ParkGroup pg = parkGroupService.getParkGroupInfo(parkGroupId);
			
			model.addAttribute("read","1");
			
			model.addAttribute("PG",pg);
			
			model.addAttribute("SS",parkGroupService.getSettledInfo(pg.getSettledId()));
			
		} catch (Exception e) {
			
			LOGGER.error("查询园区组详细信息");
			
			e.printStackTrace();
			
		}
		
		return "parkGroup/parkGroupInfo"; 
		
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/addParkGroup.do")
	public ResultType addParkGroup(ParkGroup param,Settled settled){
		
		ResultType result = null;
		
		try {
			if(param.getId()!=null && param.getId().length()>0){
				param.setParkGroupType("1");//正式园区
				settled.setId(null);
				result = parkGroupService.addParkGroup(param,settled);
			}else{
				result = ResultType.getFail("添加园区组失败");
			}
		
		} catch (Exception e) {
			
			result = ResultType.getFail("添加园区组失败");
			
			LOGGER.error("添加园区组失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/formalParkGroup.do")
	public ResultType formalParkGroup(String id,String code,String approval){
		
		ResultType result = null;
		
		try {
			
				Settled settled = parkGroupService.getSettledInfo(id);
			
				ParkGroup param = new ParkGroup();
				param.setId(UUID.randomUUID().toString());
				param.setParkGroupName(settled.getParkName());
				param.setParkCount("1");
				param.setApproval(approval);
				param.setParkGroupType("0");
				param.setActivated("1");
				param.setParkGroupCode(code);
				
				result = parkGroupService.addParkGroup(param,settled);
				
//				if(result.status==10001){
//					
//					MailUtil mu = new MailUtil(false);
//					
//					String context = "您的E淘园园区组管理账号:"+settled.getMobile()+",密码:123456";
//					
//					result = mu.doSendHtmlEmail("E淘园园区组入驻审核通过", context, settled.getEmail());
//					
//				}
				
		} catch (Exception e) {
			
			result = ResultType.getFail("转正园区组失败");
			
			LOGGER.error("转正园区组失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/updateParkGroup.do")
	public ResultType updateParkGroup(ParkGroup param){
		
		ResultType result = null;
		
		try {
			
			result = parkGroupService.updateParkGroup(param);
		
		} catch (Exception e) {
			
			result = ResultType.getFail("更新园区组失败");
			
			LOGGER.error("更新园区组失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/stopParkGroup.do")
	public ResultType stopParkGroup(ParkGroup param){
		
		ResultType result = null;
		
		try {
			
			parkGroupService.stopParkGroup(param);
			
			result = ResultType.getSuccess("激活/关闭园区组成功");
		
		} catch (Exception e) {
			
			result = ResultType.getFail("激活/关闭园区组失败");
			
			LOGGER.error("激活/关闭园区组失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/getParkGroupListForControl.do")
	public List<ParkGroup> getParkGroupListForControl(){
		
		List<ParkGroup> list = null;
		
		try {
			
			list = parkGroupService.getParkGroupListForControl();
			
		} catch (Exception e) {
			
			LOGGER.error("查询园区列表失败");
			
			e.printStackTrace();
		}
		
		return list;
		
	}
	
	@ResponseBody
	@RequestMapping("/uploadImg.do")
	public ResultType uploadImg(HttpServletRequest request,String id) {
		
		return UploadUtil.upLoad(request, "", "",id);

	}
	
	@ResponseBody
	@RequestMapping("/getPresentationByParkGroupId.do")
	public EtopPage<ParkGroupPresentation> listbyCompanyId(
			String parkGroupId,@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="100")int limit, ParkGroup group) 
		 throws Exception {

		return parkGroupService.getPresentationByParkGroupId(parkGroupId, offset, limit);
		
	}
	
	
	@ResponseBody
	@RequestMapping("/deletePresentation.do")
	public ResultType deletePresentation(String id)  throws Exception{
		ResultType result= null;
		try{
			parkGroupService.deletePresentation(id);
			result =ResultType.getSuccess("删除成功！");
		}catch(Exception e){
			LOGGER.error("删除失败",e);
		result = ResultType.getFail("删除失败！");
		e.printStackTrace();		
	     }
		 return result;
		
	}
	
	@RequestMapping(value = "/getExperienceInfoById.do")
	public String getExperienceInfoById(String id, Model model) {

		ParkGroupPresentation presentation=parkGroupService.getPresentationInfoById(id);
		model.addAttribute("presentation",presentation);

		return "parkGroup/Infoalter";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/updatePresentation.do")
	public ResultType updatePresentation(ParkGroupPresentation presentation) throws Exception{
		ResultType result= null;
		try{
			parkGroupService.updatePresentation(presentation);
			result =ResultType.getSuccess("提交成功！");
		}catch(Exception e){
			LOGGER.error("提交失败",e);
		result = ResultType.getFail("提交失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
	@RequestMapping("/create.do")
	public String addPage(Model model, String parkGroupId) throws Exception {
		ParkGroup pg = parkGroupService.getParkGroupInfo(parkGroupId);		
		model.addAttribute("PG",pg);
		return "parkGroup/addPresentation";

	}
	
    @ResponseBody
	@RequestMapping(value = "/createmetho.do")
	public ResultType add(ParkGroupPresentation presentation) {
		ResultType result= null;
		try{
			
			parkGroupService.add(presentation);
			result =ResultType.getSuccess("添加成功！");
		}catch(Exception e){
			LOGGER.error("添加失败",e);
		result = ResultType.getFail("添加失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
}
