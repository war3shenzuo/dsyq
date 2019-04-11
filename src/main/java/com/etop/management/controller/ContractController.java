package com.etop.management.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Contract;
import com.etop.management.bean.ContractEnergy;
import com.etop.management.bean.ContractExpress;
import com.etop.management.bean.ContractExpressItem;
import com.etop.management.bean.ContractItem;
import com.etop.management.bean.ContractServiceItem;
import com.etop.management.bean.Criteria;
import com.etop.management.bean.EnergyCost;
import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.PageContract;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.constant.SysStatus;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopBillRule;
import com.etop.management.model.BuildingEnergyModel;
import com.etop.management.model.CalendarDifferenceModel;
import com.etop.management.model.ContractListModel;
import com.etop.management.model.ContractModel;
import com.etop.management.service.ContractEnergyService;
import com.etop.management.service.ContractExpressService;
import com.etop.management.service.ContractItemService;
import com.etop.management.service.ContractService;
import com.etop.management.service.ContractServiceItemService;
import com.etop.management.service.EnergyCostService;
import com.etop.management.service.EtopBillRuleService;
import com.etop.management.service.EtopCompanyIntentionService;
import com.etop.management.service.EtopCompanyService;
import com.etop.management.service.RemindService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.UserInfoUtil;
import com.etop.management.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/contract")
public class ContractController extends BaseAppController {
	
//	private  final static Logger LOGGER =Logger.getLogger(ContractController.class);
	
	@Resource
	ContractService contractService;
	
	@Resource
	ContractItemService contractItemService;
	
	@Resource
	EtopBillRuleService etopBillRuleService;
	
	@Resource
	ContractEnergyService contractEnergyService;
	
	@Resource
	ContractExpressService contractExpressService;
	

	@Resource
	EnergyCostService energyCostService;
	
	@Resource
	EtopCompanyIntentionService etopCompanyIntentionService;
	
	@Resource
	EtopCompanyService etopCompanyService;
	
	@Resource
	ContractServiceItemService contractServiceItemService;
	
	@Resource
	RemindService remindService;
	
	@Resource
	com.etop.management.service.ParkService parkService1;
	
	@RequestMapping("/index.do")
	public String index(Model model) {
		
		try{
		
			model.addAttribute("parks", this.parkService1.getParkName(getParkIds()));
			
			model.addAttribute("readonly", isReadOnly("/contract/index.do"));//isReadOnly("/contract/index.do")
			
			List<Role> roles=UserInfoUtil.getUserRoleInfo();
			
			int parkerAudit=0;
			
			int financeAudit=0;
			
			for(Role r :roles)
			{			
				
				if(Util.isNullOrEmpty(r.getRoleApproval()))
				{
					continue;
				}
				
				String[] strs=r.getRoleApproval().split(",");
				
				List<String> list = new ArrayList<String>(Arrays.asList(strs));
				
				if(list.indexOf(Role.QX_YZSP)>-1)
				{
					parkerAudit=1;
				}
				
				if(list.indexOf(Role.QX_CWSP)>-1)
				{
					financeAudit=1;
					
				}
				
				
			}
			
			model.addAttribute("parkerAudit",parkerAudit);
			
			model.addAttribute("financeAudit",financeAudit);
			
			return "contract/index";
		}
		catch(Exception e)
		{	
			LoggerUtil.error(e);
			
			//e.printStackTrace();
			
			model.addAttribute("error", "读取信息出错，请联系管理员。");
			
			return "/other/error";
		}
	}
	
	@ResponseBody
	@RequestMapping("/getContractList.do")
	public Map<String,Object> getContractList(PageContract page,HttpServletRequest request)
	{
		//ResultType result=null;
		
		Map<String,Object> result=new HashMap<String, Object>();
		
		try {
			
			
			
			List<ContractListModel> list=new ArrayList<ContractListModel>();
			
			int count=0;
			
			if(!Util.isNullOrEmpty(page.getRefParkId()))
			{
			
				List<String> refParkIds=Arrays.asList(page.getRefParkId().split(","));
				
				page.setRefParkIds(refParkIds);
				
				String orderColumn=request.getParameter("columns[" + request.getParameter("order[0][column]").toString() + "][data]").toString();
				
				String orderDir=request.getParameter("order[0][dir]").toString();
							
				page.setOrderColumn(Util.humpToLine(orderColumn));
				
				page.setOrderDir(orderDir);
				
				if(!Util.isNullOrEmpty(page.getEndDate()))
				{
					page.setEndDate(Util.increaseDate(page.getEndDate(), 1));
				}
				
				list=this.contractService.getContractList(page);
				
				
				count=this.contractService.getContractListCount(page);
			
			}
			
			result.put("msg", "");
			
			result.put("data", list);
			
			result.put("draw", page.getDraw());
			
			result.put("recordsTotal", count);
			
			result.put("recordsFiltered", count); 
			
			//throw new Exception("xxx");

			//result = ResultType.getSuccess("success!", list);
			
		} catch (Exception e) {
			
	
			LoggerUtil.error(e);
			
			//result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/checkOtherContractByLease.do")
	public ResultType checkOtherContractByLease(String leaseIds,HttpServletRequest request)
	{
		ResultType result=null;
		
		Map<String,String> m=null;
		
		List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
		
		try {
			
			Gson gson=new Gson();
			
			List<String> idList=gson.fromJson(leaseIds,  new TypeToken<List<String>>(){}.getType());
									
			List<Contract> energyContractList=this.contractService.getOtherContractByLease(idList, BillSource.ENERGY_CONTRACT.value);
			
			List<Contract> propertyContractList=this.contractService.getOtherContractByLease(idList, BillSource.PROPERTY_CONTRACT.value);
			
			List<String> energyList=new ArrayList<String>();
			
			List<String> propertyList=new ArrayList<String>();
			
			for(Contract c:energyContractList)
			{
				energyList.add(c.getRefContractLeaseId());
			}
			
			for(Contract c:propertyContractList)
			{
				propertyList.add(c.getRefContractLeaseId());
			}
			
			for(String id:idList)
			{
				int r=0;
				
				if(energyList.indexOf(id)>-1)
				{
					r+=1;
				}
				
				if(propertyList.indexOf(id)>-1)
				{
					r+=2;
				}
				
				m=new HashMap<String, String>();
				
				m.put("id", id);
				
				m.put("result", r+"");
				
				resultList.add(m);
				
			}
			
			result = ResultType.getSuccess("success!", resultList);
			
		} catch (Exception e) {
			
	
			LoggerUtil.error(e);
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	
	@RequestMapping("/edit.do")
	public String edit(String id,String leaseId,String refCompanyId,int category,String ro,Model model) throws Exception {
		
		try{
		
			ContractModel cModel=new ContractModel();
			
			//create
			if(Util.isNullOrEmpty(id))
			{		
				
				cModel.setContractCategory(category);
				
				cModel.setContractType(0);
				
				cModel.setContractStatus(Contract.ContractStatus.INIT.value);
				
				//cModel.setAuditStatus(Contract.ContractAuditStatus.INIT.value);
				
				cModel.setRefContractLeaseId(leaseId);
				
				cModel.setRefCompanyId(refCompanyId);
				
				cModel.setDeposit(new BigDecimal(0));
				
				String parkId=null;
				
				//set company info from intention, for lease  
				//从意向公司创建租赁合同
				if(!Util.isNullOrEmpty(refCompanyId) && category==BillSource.LEASE_CONTRACT.value)
				{
					EtopCompanyIntention ci=this.etopCompanyIntentionService.getCompIntentionInfoById(refCompanyId);
					
					if(ci==null)
					{
						model.addAttribute("error", "无意向公司");
						
						return "/other/error";
					}
					
					//从意向公司取parkId
					parkId=ci.getParkId();
									
					//cModel.setRefParkId(ci.getParkId());
								
					
					
					cModel.setCompanyContactsName(ci.getContact());
					
					cModel.setCompanyContactsPhone(ci.getMobile());
					
					cModel.setCompanyName(ci.getCompanyName());
					
					
				}
				
				//if category!=0, get contract lease info, and set
				//根据租赁合同创建其他合同,不包括服务合同
				if(category!=BillSource.LEASE_CONTRACT.value && category!=BillSource.SERVICE_CONTRACT.value && !Util.isNullOrEmpty(leaseId))
				{
					Contract lease=this.contractService.getContractById(leaseId);
					
					if(lease==null)
					{
						model.addAttribute("error", "无租赁合同信息");
						
						return "/other/error";
					}
					
					//从租赁合同取parkId
					parkId=lease.getRefParkId();
					
					cModel.setRefContractLeaseId(lease.getId());
					
					cModel.setRefCompanyId(lease.getRefCompanyId());
					
					cModel.setRefRoomId(lease.getRefRoomId());
					
					cModel.setCompanyName(lease.getCompanyName());
					
					cModel.setCompanyRegistrationDate(lease.getCompanyRegistrationDate());
					
					cModel.setPark(lease.getPark());
					
					cModel.setBuilding(lease.getBuilding());
					
					cModel.setFloor(lease.getFloor());
					
					cModel.setBlock(lease.getBlock());
					
					cModel.setRoom(lease.getRoom());
					
					cModel.setContractStartDate(lease.getContractStartDate());
					
					cModel.setContractEndDate(lease.getContractEndDate());
									
					//set company info from company
					EtopCompany c=this.etopCompanyService.getCompInfoById(cModel.getRefCompanyId());
					
					if(c==null)
					{
						model.addAttribute("error", "无公司信息");
						
						return "/other/error";
					}
					
					cModel.setCompanyContactsName(c.getContact());
					
					cModel.setCompanyContactsPhone(c.getMobile());
					
					cModel.setCompanyName(c.getCompanyName());
					
					cModel.setRefParkId(c.getParkId());
					
					cModel.setParkName(c.getParkName());				
					
				}
				
				//新建租凭合同
				if(category==BillSource.LEASE_CONTRACT.value && Util.isNullOrEmpty(refCompanyId))
				{
					//从session中取parkId
					parkId=UserInfoUtil.getUserInfo().getParkId();
					
	//				if(Util.isNullOrEmpty(parkId))
	//				{
	//					model.addAttribute("error", "无园区");
	//					
	//					return "/other/error";
	//				}
	//				
	//				cModel.setRefParkId(parkId);
				}
				
				if(category==BillSource.SUBCONTRACT_CONTRACT.value || category==BillSource.SERVICE_CONTRACT.value)
				{
					parkId=UserInfoUtil.getUserParkInfo().getId();
				}
				
				//统一设置park
				Park park=this.parkService1.getParkInfo(parkId);
				
				if(park!=null)
				{
					cModel.setPark(park.getParkName());
					
					cModel.setRefParkId(park.getId());
				}
				else
				{
					model.addAttribute("error", "无园区信息");
					
					return "/other/error";
				}
				
				
			
				
			}else			
			{
				Contract entity=this.contractService.getContractById(id);
				
				//id取不到时，应为no
				if(entity==null)
				{
					cModel=this.contractService.getContractByNo(id);
				}
				else
				{
					Util.translate(entity, cModel);
				}		
				
				
				
				
				
				//set company info
	//			EtopCompany c=this.etopCompanyService.getCompInfoById(cModel.getRefCompanyId());
	//			
	//			if(c==null)
	//			{
	//				model.addAttribute("error", "无公司信息");
	//				
	//				return "/other/error";
	//			}
				
	//			cModel.setCompanyContactsName(c.getContact());
	//			
	//			cModel.setCompanyContactsPhone(c.getMobile());
	//			
	//			cModel.setCompanyName(c.getCompanyName());
				
	//			cModel.setRefParkId(c.getParkId());
	//			
	//			cModel.setParkName(c.getParkName());
							
				
			}
			
			
			//12.13
			//当合同为物业，能源时，需要取出对应租赁合同的起止日期
			if(cModel.getContractCategory()==BillSource.PROPERTY_CONTRACT.value || cModel.getContractCategory()==BillSource.ENERGY_CONTRACT.value)
			{
				Criteria c=new Criteria(); 
				
				c.put("id", cModel.getRefContractLeaseId());
				
				Contract lease=this.contractService.query(c);
				
				if(lease==null)
				{
					model.addAttribute("error", "无相应租赁合同");
					
					return "/other/error";
				}
				
				cModel.setLeaseContractStartDate(lease.getContractStartDate());
				
				cModel.setLeaseContractEndDate(lease.getContractEndDate());
				
				
			}
			
			
			model.addAttribute("contract", cModel);
			
			model.addAttribute("readonly", Util.isNullOrEmpty(ro)?"true":ro);
			
			return "contract/edit";
		
		}
		catch(Exception e)
		{
			LoggerUtil.error(e);
			
			//e.printStackTrace();
			
			model.addAttribute("error", "读取信息出错，请联系管理员。");
			
			return "/other/error";
		}
		
		
	}
	
	@ResponseBody
	@RequestMapping("/save.do")
	public ResultType save(ContractModel model,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			boolean isCreated=false;
			
			if(Util.isNullOrEmpty(model.getId()))
			{
				isCreated=true;
			}
			
			/*
			if(Util.isNullOrEmpty(model.getContractNo()))
			{
				isCreated=true;
				
				//check contract first,外包与服务无所谓
				if(model.getContractCategory()!=BillSource.SUBCONTRACT_CONTRACT.value
						
						&&
						
						model.getContractCategory()!=BillSource.SERVICE_CONTRACT.value
						
						)
				{
					List<Contract> temp=this.contractService.getContractByCompanyAndCategoryAndRoom(model.getRefCompanyId(),model.getRefRoomId(),	model.getContractCategory(),Util.formatDate(new Date()));
					
					if(temp!=null && temp.size()>0)
					{
						return ResultType.getFail("此公司已创建此合同");
					}
				}
				
				
			}*/
			
			//for sequence
			model.setParkCode(this.getParkCode());
			
			String id=this.contractService.saveContract(Util.getOpInfo(), model);
			
			result = ResultType.getSuccess("保存成功", id);
			
			if(isCreated)
			{
				if(Util.isNullOrEmpty(id))
				{
					result=ResultType.getFail("生成合同失败");
					
				}
				
			}
			
			
			
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);		
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}	
	
	@ResponseBody
	@RequestMapping("/remove.do")
	public ResultType remove(String id,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			int c=this.contractService.removeContract(Util.getOpInfo(), id);
			
			if(c==-2)
			{
				result=ResultType.getFail("合同不存在");
			}

			if(c==-1)
			{
				result=ResultType.getFail("此租赁合同已有其他合同存在。");
			}
			
			if(c==0)
			{
				result=ResultType.getFail("无删除");
			}
			if(c>0)
			{
				result = ResultType.getSuccess("删除成功", c);
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/getCalendarDiff.do")
	public ResultType getCalendarDiff(String dateStart,String dateEnd,HttpServletRequest request)
	{
		ResultType result=null;
		try
		{
			
			CalendarDifferenceModel model=Util.getCalendarDifference(dateStart, dateEnd);
			
			result=ResultType.getSuccess(model);
						
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/saveitem.do")
	public ResultType saveitem(ContractItem model,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
//			if(Util.isNullOrEmpty(model.getLastFeeDate()))
//			{
//				model
//			}
			
			String id=this.contractItemService.save(Util.getOpInfo(), model);
			
			result = ResultType.getSuccess("保存成功", id);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/deleteitem.do")
	public ResultType deleteitem(String id,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			int r=this.contractItemService.remove(Util.getOpInfo(), id);
								
			result = ResultType.getSuccess("删除成功", r);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);		
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/getActiveContractsByCompanyAndCategory.do")
	public ResultType getActiveContractsByCompanyAndCategory(String refCompanyId, int contractCategory)
	{
		ResultType result=null;
		
		try {
			
			String todayStr=Util.formatDate(new Date());
			
			List<Contract> list=this.contractService.getActiveContractsByCompanyAndCategory(refCompanyId, contractCategory, todayStr);
			
			result = ResultType.getSuccess("success!", list);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/getContractItemList.do")
	public ResultType getContractItemList(String contractId,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			List<ContractItem> list=this.contractItemService.getContractItemList(contractId);
			
			result = ResultType.getSuccess("success!", list);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	@ResponseBody
	@RequestMapping("/getContractItem.do")
	public ResultType getContractItem(String id,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			ContractItem entity=this.contractItemService.getValueById(id);
								
			if(entity==null)
			{
				result=ResultType.getFail();
			}
			else
			{
				result = ResultType.getSuccess("success", entity);
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/getItemBillRule.do")
	public ResultType getItemBillRule(String refContractId,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			ContractItem entity=this.contractItemService.getOneOfContractItem(refContractId);
								
			if(entity==null)
			{
				result=ResultType.getFail();
			}
			else
			{
				result = ResultType.getSuccess("success", entity);
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/getContractEnergy.do")
	public ResultType getContractEnergy(String refContractId,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			ContractEnergy entity=this.contractEnergyService.getValueByRefContractId(refContractId);
								
			if(entity==null)
			{
				result=ResultType.getNoData();
			}
			else
			{
				result = ResultType.getSuccess("success", entity);
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/getContractEnergyByRoom.do")
	public ResultType getContractEnergyByRoom(String refRoomId,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			Contract entity=this.contractService.getLastActiveContractByRefRoomId(refRoomId,BillSource.ENERGY_CONTRACT.value);
											
			if(entity==null)
			{
				result=ResultType.getNoData();
			}
			else
			{
				result = ResultType.getSuccess("success", entity);
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/saveEnergy.do")
	public ResultType saveEnergy(ContractEnergy model,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
						
			List<String> cr=this.contractEnergyService.checkEnergyRecord(model);
			
			if(cr!=null && cr.size()>0)
			{
				//throw new IllegalArgumentException(Util.List2Str(cr, "<br />"));
				return ResultType.getFail(Util.List2Str(cr, "<br />"));
			}			
			
			String id=this.contractEnergyService.save(Util.getOpInfo(), model);
			
			result = ResultType.getSuccess("保存成功", id);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException(e.getMessage());
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	@ResponseBody
	@RequestMapping("/getContractExpress.do")
	public ResultType getContractExpress(String refContractId,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			ContractExpress entity=this.contractExpressService.getValueByRefContractId(refContractId);
								
			if(entity==null)
			{
				result=ResultType.getFail();
			}
			else
			{
				result = ResultType.getSuccess("success", entity);
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/saveExpress.do")
	public ResultType saveExpress(ContractExpress model,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			String id=this.contractExpressService.save(Util.getOpInfo(), model);
			
			result = ResultType.getSuccess("保存成功", id);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	
	
	
	@ResponseBody
	@RequestMapping("/getContractExpressItem.do")
	public ResultType getContractExpressItem(String id,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			ContractExpressItem entity=this.contractExpressService.getValueById(id);
								
			if(entity==null)
			{
				result=ResultType.getFail();
			}
			else
			{
				result = ResultType.getSuccess("success", entity);
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/saveExpressItem.do")
	public ResultType saveExpressItem(ContractExpressItem model,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			String id=this.contractExpressService.saveExpressItem(Util.getOpInfo(), model);
			
			result = ResultType.getSuccess("保存成功", id);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	@ResponseBody
	@RequestMapping("/removeExpressItem.do")
	public ResultType removeExpressItem(String  id,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			int d=this.contractExpressService.removeExpressItem(Util.getOpInfo(), id);
			
			result = ResultType.getSuccess("删除成功", d);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/terminateContract.do")
	public ResultType terminateContract(String  id,Date tDate,String reason,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			//设置状态为2终止审核中，并向园长发送消息
			
			Contract contract=this.contractService.getContractById(id);
			
			if(contract==null) return ResultType.getFail("合同未找到。");
			
			//终止审批中
			contract.setContractStatus(Contract.ContractStatus.TERMINATE_AUDITING.value);
			
			contract.setTerminateDate(tDate);
			
			contract.setTerminateReason(reason);
			
			this.contractService.updateContract(Util.getOpInfo(), contract);
			
//			String content=String.format("公司（%s）合同终止，请审批。", contract.getCompanyName(),BillSource.valueOf(contract.getContractCategory()).desc);
			String content=String.format("收到新的(%s)合同终止申请，请及时审核！ 编号：%s，客户：%s，房间：%s，开始日期：%s，结束日期：%s，终止日期：%s",
					BillSource.valueOf(contract.getContractCategory()).desc,
					contract.getContractNo(),contract.getCompanyName(),contract.getRoom(),
					DateUtil.formatDate(contract.getContractStartDate()),DateUtil.formatDate(contract.getContractEndDate()),DateUtil.formatDate(contract.getTerminateDate()));
			
			this.remindService.remind(UserInfoUtil.getUserParkInfo().getId(), Role.QX_YZSP, "合同终止", content);
			
			remindEnergy(contract);
			
			//int d=this.contractService.terminateContract(Util.getOpInfo(), id,tDate,reason);
			
			LoggerUtil.info(String.format("[terminateContract.do]%s",contract.toString() ));
			
			result = ResultType.getSuccess("已提交审批。", null);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	@ResponseBody
	@RequestMapping("/delContract.do")
	public ResultType delContract(String  id,Date tDate,String reason,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			//设置状态为2删除审批中中，并向园长发送消息
			
			Contract contract=this.contractService.getContractById(id);
			
			if(contract==null) return ResultType.getFail("合同未找到。");
			
			//删除审批中
			contract.setContractStatus(Contract.ContractStatus.DELETE_AUDITING.value);
			
			contract.setTerminateDate(tDate);
			
			contract.setTerminateReason(reason);
			
			this.contractService.updateContract(Util.getOpInfo(), contract);
			
			String content=String.format("公司（%s）合同终止，请审批。", contract.getCompanyName(),BillSource.valueOf(contract.getContractCategory()).desc);
			
			this.remindService.remind(UserInfoUtil.getUserParkInfo().getId(), Role.QX_YZSP, "合同删除", content);
			
			//remindEnergy(contract);
			
			//int d=this.contractService.terminateContract(Util.getOpInfo(), id,tDate,reason);
			
			LoggerUtil.info(String.format("[delContract.do]%s",contract.toString() ));
			
			result = ResultType.getSuccess("已提交审批。", null);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/terminateContractAudit.do")
	public ResultType terminateContractAudit(String  id,int terminateType,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			
			Contract contract=this.contractService.getContractById(id);
			
			if(contract==null) return ResultType.getFail("合同未找到。");
			
			if(contract.getContractStatus()!=Contract.ContractStatus.TERMINATE_AUDITING.value)
			{
				return ResultType.getFail("合同未提交终止申请");
			}
			
			remindEnergy(contract);
			
			
			List<Integer> tResult=this.contractService.terminateContract(Util.getOpInfo(), contract,terminateType);
			
			if(tResult.size()==2)
			{
				if(terminateType==0)
				{
					result = ResultType.getSuccess("已拒绝终止合同。", tResult);
				}
				else{
					result = ResultType.getSuccess(String.format("已同意终止合同。终止合同数量(%d),生成帐单数量(%d)", tResult.get(0),tResult.get(1)));
				}
			}
			else
			{
				result= ResultType.getFail("审批终止合同失败");
			}
			
//			String content=String.format("公司（%s）合同终止，请审批。", contract.getCompanyName(),BillSource.valueOf(contract.getContractCategory()).desc);
//			
//			this.remindService.remind(UserInfoUtil.getUserParkInfo().getId(), Role.QX_YZSP, "合同终止", content);
//			
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	/**
	 * 删除合同审核
	 * @param id
	 * @param delType
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteContractAudit.do")
	public ResultType deleteContractAudit(String  id,int delType,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			
			Contract contract=this.contractService.getContractById(id);
			
			if(contract==null) return ResultType.getFail("合同未找到。");
			
			if(contract.getContractStatus()!=Contract.ContractStatus.DELETE_AUDITING.value)
			{
				return ResultType.getFail("合同未提交终止申请");
			}
			
			//remindEnergy(contract);
			
			
			List<Integer> tResult=this.contractService.delContract(Util.getOpInfo(), contract,delType);
			
			if(tResult.size()==1)
			{
				if(delType==0)
				{
					result = ResultType.getSuccess("已拒绝删除合同。", tResult);
				}
				else{
					result = ResultType.getSuccess(String.format("已同意删除合同。"));
				}
			}
			else
			{
				result= ResultType.getFail("审批删除合同失败");
			}
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	/**
	 * 终止合同申请，审批后都要提醒能源抄表。
	 * @param roomId
	 * @param terminateDate
	 */
	private void remindEnergy(Contract contract)
	{
		//只能租赁合同与能源合同
		if(!(contract.contractCategory==BillSource.LEASE_CONTRACT.value || contract.contractCategory==BillSource.ENERGY_CONTRACT.value))
		{
			return;
		}
		
		//如果是租赁合同，先判断是否有能源合同，没有，则不用发通知。
		if(contract.contractCategory==BillSource.LEASE_CONTRACT.value)
		{
			Criteria criteria=new Criteria();
			
			criteria.put("refContractLeaseId", contract.getId());
			
			criteria.put("contractCategory", BillSource.ENERGY_CONTRACT.value);			
			
			Contract energyContrat=this.contractService.query(criteria);
			
			if(energyContrat==null) return;
		}
		
		
		String failmsg="%s未抄表，";
		
		String energyReminder="";
		
		for(int i=0;i<4;i++)
		{
			EnergyCost cost=this.energyCostService.getFirstRecordOfRoomAfterStartDate(contract.getRefRoomId(),i,Util.formatDate(contract.getTerminateDate()));
			
			if(cost==null) 
			{
				energyReminder+=String.format(failmsg, EnergyCost.EnergyType.valueOf(i).desc)+"\r\n";
				
			}
		
		}
		
		
		if(!Util.isNullOrEmpty(energyReminder))
		{	
			
			energyReminder=String.format("公司（%s）终止合同，楼：%s，层：%s，区：%s，房：%s。能源抄表提醒：\r\n%s"			
						
						, contract.getCompanyName(),contract.building,contract.floor,
						
						contract.block,contract.room,energyReminder);
			
			LoggerUtil.info(energyReminder);
			
			this.remindService.remind(contract.getRefParkId(), Role.QX_RWTX, "能源抄表", energyReminder);				
	
		}		
		
	}
	
	@ResponseBody
	@RequestMapping("/getExpressItemList.do")
	public ResultType getExpressItemList(String contractId,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			List<ContractExpressItem> list=this.contractExpressService.getExpressItemList(contractId);
			
			result = ResultType.getSuccess("查询成功", list);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	@ResponseBody
	@RequestMapping("/getBillRules.do")
	public ResultType getBillRules(HttpServletRequest request)
	{
		ResultType result=null;
		
		List<EtopBillRule> list=new ArrayList<EtopBillRule>();
		
		try {
			
			list=this.etopBillRuleService.getBillRuleList(UserInfoUtil.getUserParkInfo().getId());
			
			result = ResultType.getSuccess("success", list);			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/getContractServiceItems.do")
	public ResultType getContractServiceItems(Boolean active,HttpServletRequest request)
	{
		ResultType result=null;
		
		List<ContractServiceItem> list=new ArrayList<ContractServiceItem>();
		
		try {
			
			String refParkId=UserInfoUtil.getUserParkInfo().getId();
			
			list=this.contractServiceItemService.getContractServiceItemList(refParkId,active);
			
			result = ResultType.getSuccess("success", list);			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}	
	
	@ResponseBody
	@RequestMapping("/generateBillForOwnPark.do")
	public ResultType generateBillForOwnPark(HttpServletRequest request)
	{
		ResultType result=null;
		
		
		
		try {
			
			String pid=UserInfoUtil.getUserParkInfo().getId();
			
			if(!Util.isNullOrEmpty(pid))
			{
			
				ResultType rt=this.contractService.generateBillByPark(pid,Util.formatDate(new Date()));
				
				if(rt.getStatus()==SysStatus.SUCCESS.getStatus())
				{
					@SuppressWarnings("unchecked")
					Map<String,Integer> map=(Map<String,Integer>)rt.getData();
					
					result = ResultType.getSuccess(String.format("成功帐单数量：%d，失败帐单数量:%d",map.get("success"),map.get("failed")));
				}
				else
				{
					result=ResultType.getFail("生成失败");
				}
			}
			else
			{
				result=ResultType.getFail("无园区信息");
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("服务器错误！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	/**
	 * 根据房间取楼能源设置
	 * @param refRoomId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBuildingEnergyByRoom.do")
	public ResultType getBuildingEnergyByRoom(String refRoomId,HttpServletRequest request)
	{
		ResultType result=null;	
		
		try {
			
			if(!Util.isNullOrEmpty(refRoomId))
			{
			
				List<BuildingEnergyModel> list=this.energyCostService.getBuildingEnergyByRoom(refRoomId);
				
				if(list!=null)
				{
					result = ResultType.getSuccess(list);
				}
				else
				{
					result=ResultType.getNoData();
				}
			}
			else
			{
				result=ResultType.getParamIllegal();
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("服务器错误！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	
//	@ResponseBody
//	@RequestMapping("/generateBillForContract.do")
//	public ResultType generateBillForContract(String contractId,HttpServletRequest request)
//	{
//		ResultType result=null;
//		
//		try {
//			
//			String pid=UserInfoUtil.getUserParkInfo().getId();
//			
//			if(!Util.isNullOrEmpty(pid))
//			{
//				//get contract
//				
//				Contract contract=this.contractService.getContractById(contractId);
//			
//				//能源合同另外规则
//				if(contract.getContractCategory()==BillSource.ENERGY_CONTRACT.value)
//				{
//					
//				}else
//				{	
//					this.contractService.generateBillByContract(contract);
//				}							
//				result = ResultType.getSuccess("生成成功");
//			}
//			else
//			{
//				result=ResultType.getFail("无园区信息");
//			}
//			
//		} catch (Exception e) {
//			
//			LOGGER.error(e);			
//			
//			result = ResultType.getException("服务器错误！");
//			
//			//e.printStackTrace();
//		}
//		
//		return result;
//			
//	}
	@ResponseBody
	@RequestMapping("/submitAudit.do")
	public ResultType submitAudit(String  id,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			
			Contract contract=this.contractService.getContractById(id);
			
			if (contract==null)
			{
				return ResultType.getFail("合同不存在。");
			}
			
			boolean conflict=false;
			
			//保存合同时，需要取出对应公司此房间的所有同类合同，然后判断日期是否有交集
//			if(model.getContractCategory()==BillSource.LEASE_CONTRACT.value)
//			{
				List<Contract> leases=this.contractService.queryLeaseListByCompanyAndRoom(contract.getRefCompanyId(), contract.getRefRoomId(), contract.getContractCategory(), Util.formatDate(contract.getContractStartDate()), Util.formatDate(contract.getContractEndDate()));
				
				if(leases!=null && leases.size()>0)
				{
					//return ResultType.getFail("与现有合同日期冲突，请检查。");
				}
				
				StringBuilder conflictContracts = new StringBuilder();
				
				conflictContracts.append("与下列合同有日期冲突，请检查：").append("<br />");
				
				boolean needAppend=false;
				
				
				for(Contract c:leases)
				{
					needAppend=false;
					
					//排除自身
					if(c.getId().equals(contract.getId()))
					{
						continue;
					}
					//排除外包合同
					if(c.getContractCategory()==BillSource.SUBCONTRACT_CONTRACT.value)
					{
						continue;
					}
					
					//排除被拒绝的合同，初始状态的合同
					if(c.getContractStatus()==Contract.ContractStatus.FINANCE_REFUSED.value ||
							c.getContractStatus()==Contract.ContractStatus.PARKER_REFUSED.value ||
							c.getContractStatus()==Contract.ContractStatus.INIT.value)
					{
						continue;
					}
					
					
					
					//若是终止合同，与终止日期判断
					if(c.getContractStatus()==Contract.ContractStatus.TERMINATED.value)
					{
						if(c.getContractStartDate().getTime()<=contract.getContractEndDate().getTime() &&
								c.getTerminateDate().getTime()>=contract.getContractStartDate().getTime()
								)
						{
							
							conflict=true;
							
							needAppend=true;
							
							//break;
						}
									
						
					}else
					{
						conflict=true;
						
						needAppend=true;
						
					}
					
					if(needAppend)
					{
						conflictContracts.append(String.format("合同编号：%s",c.getContractNo())).append("<br />");
					}
					
					
				}
				
				
				
//			}
			
			
			if(conflict)
			{
				return ResultType.getError(conflictContracts.toString());
			}
			
			
			if(contract.getContractCategory()==BillSource.ENERGY_CONTRACT.value)
			{
			
				ContractEnergy ce=this.contractEnergyService.getValueByRefContractId(id);
			
				if(ce==null)
				{
					return ResultType.getFail("能源合同不存在");
				}
				
				List<String> cr=this.contractEnergyService.checkEnergyRecord(ce);
				
				if(cr!=null && cr.size()>0)
				{										
					return ResultType.getFail(Util.List2Str(cr, "<br />"));					
				}			
			
			}
			
			
			//财务审批中 
			contract.setContractStatus(Contract.ContractStatus.FINANCE_AUDITING.value);
			//contract.setAuditStatus(Contract.ContractAuditStatus.UNCHECK.value);
			
			this.contractService.updateContract(Util.getOpInfo(), contract);
			
			//send notify
			//给财务审批提醒
/*			String content=String.format("园区(%s)与公司(%s)签订%s，请审批。",
					contract.getPark(), 
					contract.getCompanyName(),
					BillSource.valueOf(contract.getContractCategory()).desc
					);*/
			String content=String.format("收到新的(%s)合同审批申请，请及时审核！ 编号：%s，客户：%s，房间：%s，开始日期：%s，结束日期：%s",
					BillSource.valueOf(contract.getContractCategory()).desc,
					contract.getContractNo(),contract.getCompanyName(),contract.getRoom(),
					DateUtil.formatDate(contract.getContractStartDate()),DateUtil.formatDate(contract.getContractEndDate()));
			
			this.remindService.remind(contract.getRefParkId(), Role.QX_CWSP, "合同审批", content);					
	
			
			result = ResultType.getSuccess("已提交财务审批。", null);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/parkerAudit.do")
	public ResultType parkerAudit(String contractId,int type,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			Contract contract=this.contractService.getContractById(contractId);
			
			if(contract==null)
			{
				return ResultType.getFail("合同不存在");
			}
			
			if(contract.getContractCategory()==BillSource.ENERGY_CONTRACT.value)
			{
			
				ContractEnergy ce=this.contractEnergyService.getValueByRefContractId(contractId);
			
				if(ce==null)
				{
					return ResultType.getFail("能源合同不存在");
				}
				
				List<String> cr=this.contractEnergyService.checkEnergyRecord(ce);
				
				if(cr!=null && cr.size()>0)
				{
					//throw new IllegalArgumentException(Util.List2Str(cr, "<br />"));
					
					//能源数据不合法，直接拒绝					
					int r0=this.contractService.auditContractByParker(Util.getOpInfo(), contractId, 0);
										
					return ResultType.getFail(Util.List2Str(cr, "<br />")+"<br />合同已拒绝。");
					
				}			
			
			}
			
			//园长审核
			int r=this.contractService.auditContractByParker(Util.getOpInfo(), contractId, type);
			
			
			contract=this.contractService.getContractById(contractId);
			
			if(contract!=null && contract.getContractStatus()==Contract.ContractStatus.PARKER_ACCEPTED.value)
			{
				result=ResultType.getSuccess("园长审核能过成功");
				
				String content=String.format("欢迎(%s)入驻园区(%s)。", contract.getCompanyName(),contract.getPark());
				
				//客户提醒
				this.remindService.remind(UserInfoUtil.getUserParkInfo().getId(), Role.QX_KHTX,"新公司加入",content);
				
				
				content=String.format("园区(%s)已与公司(%s)签订%s。", contract.getPark(),contract.getCompanyName(),BillSource.valueOf(contract.getContractCategory()).desc);
				
				
				//合同提醒 
				this.remindService.remind(UserInfoUtil.getUserParkInfo().getId(), Role.QX_HTTX,"新合同",content);
				
			}
			if(contract!=null && contract.getContractStatus()==Contract.ContractStatus.PARKER_REFUSED.value)
			{
				result=ResultType.getSuccess("园长审核拒绝成功");
			}
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException(e.getMessage());
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/financeAudit.do")
	public ResultType financeAudit(String contractId,int type,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
						
			Contract contract=this.contractService.getContractById(contractId);
			
			if(contract==null)
			{
				return ResultType.getFail("合同不存在");
			}
			
			if(contract.getContractCategory()==BillSource.ENERGY_CONTRACT.value)
			{
			
				ContractEnergy ce=this.contractEnergyService.getValueByRefContractId(contractId);
			
				if(ce==null)
				{
					return ResultType.getFail("能源合同不存在");
				}
				
				List<String> cr=this.contractEnergyService.checkEnergyRecord(ce);
				
				if(cr!=null && cr.size()>0)
				{
					//throw new IllegalArgumentException(Util.List2Str(cr, "<br />"));
					
					//能源数据不合法，直接拒绝					
					int r0=this.contractService.auditContractByFinance(Util.getOpInfo(), contractId, 0);
										
					return ResultType.getFail(Util.List2Str(cr, "<br />")+"<br />合同已被拒绝。");
					
				}			
			
			}	
			
			
			//财务审核
			
			int r=this.contractService.auditContractByFinance(Util.getOpInfo(), contractId, type);
			
			contract=this.contractService.getContractById(contractId);
			
			if(contract!=null && contract.getContractStatus()==Contract.ContractStatus.PARKER_AUDITING.value)
			{
				result=ResultType.getSuccess("财务审核通过合同",r);
				
				//成功后给园长发消息
				
				//send notify
//				String content=String.format("园区(%s)与公司(%s)的%s，财务已审批通过，请园长审批。", contract.getPark(),contract.getCompanyName(),
//						BillSource.valueOf(contract.getContractCategory()).desc);
				
				String content=String.format("收到新的(%s)合同审批申请，请及时审核！ 编号：%s，客户：%s，房间：%s，开始日期：%s，结束日期：%s",
						BillSource.valueOf(contract.getContractCategory()).desc,
						contract.getContractNo(),contract.getCompanyName(),contract.getRoom(),
						DateUtil.formatDate(contract.getContractStartDate()),DateUtil.formatDate(contract.getContractEndDate()));
				
				this.remindService.remind(UserInfoUtil.getUserParkInfo().getId(), Role.QX_YZSP,"合同审批",content);
				
				
				
				
			}
			else
			{
				result=ResultType.getFail("财务拒绝通过合同。");
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("服务器错误！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}	
	
	@RequestMapping("/userlist.do")
	public String list() {
		return "contract/companylist";
	}
	
	@RequestMapping("/listbyCompanyId.do")
	@ResponseBody
	public EtopPage<ContractListModel> listbyCompanyId(
			String contractNo,String refCompanyId,Integer contractCategory,String terminateReason,Date contractStartDate,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, Date contractEndDate) {
		EtopUser etopUser= UserInfoUtil.getUserInfo();
		Map<String, Object> condition = new HashMap<>();
		condition.put("refCompanyId", etopUser.getCompanyId());
		condition.put("contractNo", contractNo);
		condition.put("contractCategory", contractCategory);
//		condition.put("terminateReason", terminateReason);
		condition.put("contractStartDate", contractStartDate);
		condition.put("contractEndDate", contractEndDate);
				return contractService.ListbyCompanyId(condition, offset, limit);
		
	}
	
	
	@RequestMapping("/userlistInfo.do")
	public String detailView( ModelMap model, String contractNo) {
		ContractModel contractModel = contractService.getContractByNo(contractNo);
		model.addAttribute("contractNo",contractModel.getContractNo());
		model.addAttribute("companyName",contractModel.getCompanyName());
		model.addAttribute("companyRegistrationDate",contractModel.getCompanyRegistrationDate());
		model.addAttribute("contractStartDate",DateUtil.formatDate(contractModel.getContractStartDate()));
		model.addAttribute("contractEndDate",DateUtil.formatDate(contractModel.getContractEndDate()));
		model.addAttribute("contractSignDate",DateUtil.formatDate(contractModel.getContractSignDate()));
		model.addAttribute("paperNo",contractModel.getPaperNo());
		model.addAttribute("terminateReason",contractModel.getTerminateReason());
		model.addAttribute("contractStatusStr",contractModel.getContractStatusStr());
		return "contract/companylistInfo";
		
	}
}
