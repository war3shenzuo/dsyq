package com.etop.management.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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

import com.etop.management.bean.Contract;
import com.etop.management.bean.Contract.ContractStatus;
import com.etop.management.bean.EnergyCost;
import com.etop.management.bean.PageEnergyBill;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.entity.EtopFloor;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.model.BuildingEnergyModel;
import com.etop.management.model.EnergyBillModel;
import com.etop.management.model.EnergyModel;
import com.etop.management.model.EnergyRecordModel;
import com.etop.management.model.EnergySupplyModel;
import com.etop.management.service.ContractEnergyService;
import com.etop.management.service.EnergyCostService;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopFloorService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.UserInfoUtil;
import com.etop.management.util.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/energy")
public class EnergyCostController extends BaseAppController {
	
	//private  final static Logger LOGGER =Logger.getLogger(FuncController.class);
	
	@Resource
	EnergyCostService energyCostService;
	
	@Resource
	EtopBillService etopBillService;
	
	@Resource
	EtopFloorService etopFloorService;
	
	@Resource
	ContractEnergyService contractEnergyService;
	
	@Resource
	com.etop.management.service.ParkService parkService1;
	
	@RequestMapping("/edit.do")
	public String edit(Model model) {
		
		model.addAttribute("readonly", false);//isReadOnly("/energy/edit.do")
		
		return "energy/edit";
	}
	
	@RequestMapping("/show.do")
	public String show(Model model)
	{
		
		try {
			
		
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
			model.addAttribute("parks", this.parkService1.getParkName(getParkIds()));
			
			model.addAttribute("parkerAudit",parkerAudit);
						
			return "energy/show";
		
		}
		catch(Exception e)
		{
			LoggerUtil.error(e);			
			
			//e.printStackTrace();
			
			model.addAttribute("error", "读取数据出错，请联系管理员。");
			
			return "/other/error";			
			
		}
	}
	
	/**
	 * 取分区中所有房间
	 * @param refBlockId
	 * @param energyType
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getRoomEditList.do")
	public ResultType getRoomEditList(String refBlockId,int energyType,String lastRecordDate,String currentRecordDate,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			if(Util.isNullOrEmpty(lastRecordDate) || Util.isNullOrEmpty(currentRecordDate) || Util.isNullOrEmpty(refBlockId))
			{
				return ResultType.getFail("参数不合法");
			}
			
			List<EnergyModel> list=this.energyCostService.getRoomEditListByRecordDate("","",refBlockId, energyType, lastRecordDate, currentRecordDate);
			
			if(list==null) {
				list=new ArrayList<EnergyModel>(); 
			}
			
			result = ResultType.getSuccess("success!", list);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/getBuildingEditList.do")
	public ResultType getBuildingEditList(int energyType,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			List<EnergyModel> list=this.energyCostService.getBuildingEditList(UserInfoUtil.getUserParkInfo().getId(),energyType);
			
			result = ResultType.getSuccess("success!", list);
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	@ResponseBody
	@RequestMapping("/getLastRecord.do")
	public ResultType getLastRecord(String id,int energyType,int energyCategory,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			EnergyCost entity=this.energyCostService.getLastEnergyCost(id, energyCategory, energyType);
			
			if(entity!=null)
			{
				result = ResultType.getSuccess("success!", entity);
			}
			else
			{
				result=ResultType.getFail("无数据");
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/loadBuildingEnergyModel.do")
	public ResultType loadBuildingEnergyModel(String id,int energyType,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			EtopFloor building=this.etopFloorService.getFloorInfo(id);
			
			if(building==null)
			{
				return ResultType.getFail("查询失败");
			}
			//楼能源信息
			BuildingEnergyModel bm=this.energyCostService.getBuildingEnergy(id, energyType);
			
			if(bm==null)
			{
				return ResultType.getFail("查询楼信息失败");
			}
			
			
			EnergyModel model=new EnergyModel();
			
			model.setShareType(bm.getShareType());
			
			model.setRoomAmountUsed(bm.getRoomAmountUsed());
						
			
			model.setEnergyEnterType(building.getEnergyEnterType());
			
			model.setEnergyEnterDay(building.getEnergyEnterDay());
			
			Date today=new Date();
			
			String recordDate=this.generateEnterDate(model.getEnergyEnterDay(), model.getEnergyEnterType());
			
			LoggerUtil.trace("enter type:"+model.getEnergyEnterType()+
					",enter day:"+model.getEnergyEnterDay()+
					"recordDate:"+recordDate+
					",today:"+Util.formatDate(today)+
					",dateDiff:"+Util.dateDiff(recordDate, Util.formatDate(today)));
			
			//12.14
			//先进行日期判断，如果未到此次录入日，则使用上次录入日
			if(Util.dateDiff(recordDate, Util.formatDate(today))>0)
			{
				recordDate=this.generateLastEnterDate(model.getEnergyEnterType(), recordDate);
				
				LoggerUtil.trace("lastRecordDate:"+recordDate);
			}
			
			
			
			
			//building，设置记录日期，按月
			/*if(model.getEnergyEnterType()==0)
			{
								
				String y=DateUtil.getYear();
				
				String m=DateUtil.getMonth();
				
				recordDate=y+"-"+String.format("%02d", Integer.valueOf(m))+"-"+String.format("%02d", model.getEnergyEnterDay());
				
				
			}
			else//按周
			{
				
				Calendar c=Calendar.getInstance();
				
				c.setTimeInMillis((building.getEnergyEnterDay() - Util.getWeekDay(today))*(3600*24*1000)+today.getTime());
								
				recordDate=c.get(Calendar.YEAR)+"-"+String.format("%02d", c.get(Calendar.MONTH)+1)+"-"+String.format("%02d", c.get(Calendar.DAY_OF_MONTH));
				
				
			}*/
			
			model.setCurrRecordDate(recordDate);
			
			Date lastFeeDate=null;
			
//			BuildingEnergyModel buildingEnergy=this.energyCostService.getBuildingEnergy(id, energyType);
//			
//			if(buildingEnergy==null) 
//			{
//				return ResultType.getFail("查询失败");
//			}
			
			//从楼中取
			lastFeeDate=building.getEnergyLastFeeDate();
					//bm.getFeeDate();
			
			
			
			//若楼最后结算日不为空，则去取上次结算楼数据，
			if(lastFeeDate!=null)
			{				
				
				EnergyCost entity=this.energyCostService.getBuildingEnergyCost(id,Util.formatDate(lastFeeDate),energyType);
				
				if(entity==null)
				{
					//return ResultType.getFail("查询失败");
										
					model.setLastRecordDate(lastFeeDate);
				}
				else
				{
					model.setLastRecord(entity.getRecord());
					
					model.setLastRecordDate(entity.getRecordDate());
					
					model.setLastFeeDate(lastFeeDate);
					
				}
			
				
				//最后结算日肯定是isBilled
				//model.setIsBilled(entity.getIsBilled());
			
			}
			else
			{
				//最后结算日为空，则需要根据规则计算出上次结算日
				
				String lastFeeDateStr="";
				
				if(model.getEnergyEnterType()==0)
				{
					String[] dd=recordDate.split("-");
					
					int y=Integer.valueOf(dd[0]);
					
					int m=Integer.valueOf(dd[1]);
					
					int d=Integer.valueOf(dd[2]);
					
					if(m==1)
					{
						m=12;
						
						y-=1;
					}else
					{
						m-=1;
					}
					
					lastFeeDateStr=y+"-"+String.format("%02d", m)+"-"+String.format("%02d", d);
					
				}
				else
				{
					Calendar cc=Calendar.getInstance();
					
					cc.setTimeInMillis(Util.str2Date(recordDate).getTime()-7*(3600*24*1000));
									
					lastFeeDateStr=cc.get(Calendar.YEAR)+"-"+String.format("%02d", cc.get(Calendar.MONTH)+1)+"-"+String.format("%02d", cc.get(Calendar.DAY_OF_MONTH));
										
				}
				
				model.setLastRecordDate(Util.str2Date(lastFeeDateStr));
				
				
			}
			

			

			
			//取当前录入日期记录，若有，设置model
			EnergyCost curEntity=this.energyCostService.getBuildingEnergyCost(id,recordDate,energyType);
			
			if(curEntity!=null)
			{
				model.setRecord(curEntity.getRecord());
				
				model.setRecordDate(curEntity.getRecordDate());
				
				//当前记录可能已被出帐，前端不能修改
				model.setIsBilled(curEntity.getIsBilled());
			}
			
			//先取出合同列表
			List<EnergySupplyModel> contractList=this.contractEnergyService.getSupplyContractEnergyList(id, recordDate, Util.formatDate(model.getLastRecordDate()));
			//每个进行判断，没有记录，就扔进model.list中
			List<EnergySupplyModel> supplyList=new ArrayList<EnergySupplyModel>();
			//已有读数，需要update isbilled
			List<EnergyCost> records=new ArrayList<EnergyCost>();
			for(EnergySupplyModel s:contractList)
			{
				String rd="";
				if(s.getContractStatus()==Contract.ContractStatus.TERMINATED.value)
				{
					rd=Util.increaseDate(Util.formatDate(s.getTerminateDate()),1);
				}
				else
				{
					rd=Util.increaseDate(Util.formatDate(s.getContractEndDate()),1);
				}
				
				//无论isbilled，都取出
				EnergyCost ec=this.energyCostService.getValue(s.getRefRoomId(), rd, energyType, EnergyCost.EnergyCategory.ROOM.value,-1);
				
				//为空，则要填
				if(ec==null)
				{
					s.setRecordDate(Util.str2Date(rd));
					//终止的，结束日期显示为终止日期
					if (s.getContractStatus()==ContractStatus.TERMINATED.value)
					{
						s.setContractEndDate(s.getTerminateDate());
					}
					
					supplyList.add(s);
				}
				//不为空，则直接修改isBilled=0
				else
				{
					ec.setIsBilled(0);
					
					records.add(ec);					
				}
				
			}
			
			if(records.size()>0)
			{
				LoggerUtil.info("update energy cost, when set supplylist");
				
				this.energyCostService.updateEnergyCostList(Util.getOpInfo(), records);
			}
			
			model.setSupplyList(supplyList);
						
			result = ResultType.getSuccess("success!", model);
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	/**
	 * 根据规则，生成此次录入日期
	 * @param enterDay
	 * @param enterType
	 * @return
	 */
	private String generateEnterDate(int enterDay,int enterType)
	{
		String recordDate="";
		
		Date today=new Date();
		
		Calendar c=Calendar.getInstance();
		//building，设置记录日期，按月
		if(enterType==0)
		{
							
			String y=c.get(Calendar.YEAR)+"";
					//DateUtil.getYear();
			
			String m=c.get(Calendar.MONTH)+1+"";
					//DateUtil.getMonth();
			
			recordDate=y+"-"+String.format("%02d", Integer.valueOf(m))+"-"+String.format("%02d", enterDay);
			
			
		}
		else//按周
		{
			
			
			
			c.setTimeInMillis((enterDay - Util.getWeekDay(today))*(3600*24*1000)+today.getTime());
							
			recordDate=c.get(Calendar.YEAR)+"-"+String.format("%02d", c.get(Calendar.MONTH)+1)+"-"+String.format("%02d", c.get(Calendar.DAY_OF_MONTH));
			
			
		}	
		
		
		return recordDate;
	}	
	
	/**
	 * 生成上次录入日，减一个月，或减七天
	 * @param enterType
	 * @param currEnterDate
	 * @return
	 */
	private String generateLastEnterDate(int enterType,String currEnterDate)
	{
		//String lastRecordDate="";
		
		Calendar cal = Calendar.getInstance();  
		
	    cal.setTime(Util.str2Date(currEnterDate));
		
		//building，设置记录日期，按月
		if(enterType==0)
		{
			
	      
	        cal.add(Calendar.MONTH, -1);
	       
	        
		}
		else
		{
			cal.add(Calendar.DATE, -7);
		}
		
		
		
		return Util.formatDate(cal.getTime());
	}
	
	
	/**
	 * 保存能源记录。当保存楼能源时，判断是否是ISBILLED，是，需要更新楼的LASTFEEDATE
	 * @param energyCategory
	 * @param energyType
	 * @param records
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveEnergyRecord.do")
	public ResultType saveEnergyRecord(int energyCategory,int energyType,String records,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			Gson gson=new Gson();
			
			List<EnergyCost> energyCosts=gson.fromJson(records,  new TypeToken<List<EnergyCost>>(){}.getType());
			
			//12.12判断记录值，大于前一次录入，且小于后一次录入
			
			List<String> checkResult=this.energyCostService.checkEnergyCost(energyCosts);
			
			if(checkResult.size()>0)
			{
				return ResultType.getFail(Util.List2Str(checkResult,"<br />"));
			}
			
			
			int count=this.energyCostService.createEnergyCostList(Util.getOpInfo(), energyCosts, energyCategory, energyType);
			
			if(count>0)
			{
				result = ResultType.getSuccess("保存成功",count);
			}
			else
			{
				result = ResultType.getFail();
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	/**
	 * 修改能源，园长专用
	 * @param energyCategory
	 * @param energyType
	 * @param records
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateEnergyRecord.do")
	public ResultType updateEnergyRecord(String records,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			Gson gson=new Gson();
			
			List<EnergyCost> energyCosts=gson.fromJson(records,  new TypeToken<List<EnergyCost>>(){}.getType());
			
			List<EnergyCost> newEnergyCosts=new ArrayList<EnergyCost>();
			
			for(EnergyCost ec:energyCosts)
			{
				EnergyCost e=this.energyCostService.getValueById(ec.getId());
				
				if(e!=null)
				{
					if(e.getRecord()!=ec.getRecord())
					{
						e.setRecord(ec.getRecord());
					
						newEnergyCosts.add(e);
					}
				}
				
			}
			
			//12.12判断记录值，大于前一次录入，且小于后一次录入
			
			List<String> checkResult=this.energyCostService.checkEnergyCost(newEnergyCosts);
			
			if(checkResult.size()>0)
			{
				return ResultType.getFail(Util.List2Str(checkResult,"<br />"));
			}
			
			
			int count=this.energyCostService.updateEnergyCostList(Util.getOpInfo(), newEnergyCosts);
			
			
			if(count>0)
			{
				result = ResultType.getSuccess("保存成功",count);
			}
			else
			{
				result = ResultType.getSuccess("并无记录被修改",count);
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	/**
	 * 房间批量填0
	 * @param refBuildingId
	 * @param energyType
	 * @param curRecordDate
	 * @param lastRecordDate
	 * @param hasLastRecord
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/roomBatchFillZero.do")
	public ResultType roomBatchFillZero(String refBuildingId,int energyType,String curRecordDate,String lastRecordDate,int hasLastRecord,HttpServletRequest request)
	{
		ResultType result=null;
		
		try {
			
			//取回本楼所有房间
			List<EnergyModel> energyModels=this.energyCostService.getRoomEditListByRecordDate(refBuildingId,"","", energyType, lastRecordDate, curRecordDate);
			
			List<EnergyCost> energyCosts=new ArrayList<EnergyCost>();
			
			EnergyCost ec;
			
			for(EnergyModel em:energyModels)
			{		
				//本次
				if(em.getRecordDate()==null)
				{
					ec=new EnergyCost();
					
					ec.setRecord(0);
					
					ec.setRecordDate(Util.str2Date(curRecordDate));
					
					ec.setRefItemId(em.getRefItemId());
					
					ec.setRefBuildingId(refBuildingId);
					
					ec.setEnergyCategory(EnergyCost.EnergyCategory.ROOM.value);
					
					ec.setEnergyType(energyType);
					
					ec.setIsBilled(em.getIsBilled());
					
					energyCosts.add(ec);
				}
				
				//上次
				if(em.getLastRecordDate()==null)
				{
					ec=new EnergyCost();
					
					ec.setRecord(0);
					
					ec.setRecordDate(Util.str2Date(lastRecordDate));
					
					ec.setRefItemId(em.getRefItemId());
					
					ec.setRefBuildingId(refBuildingId);
					
					ec.setEnergyCategory(EnergyCost.EnergyCategory.ROOM.value);
					
					ec.setEnergyType(energyType);
					
					ec.setIsBilled(1);
					
					energyCosts.add(ec);
				}
				
			}
			
			
			
			//List<EtopFloorRoom> rooms=this.etopFloorService.getRooms(refBuildingId, null, null, "1");
			
			//this.generateEnergyCostsByRooms(energyCosts, rooms, energyType, curRecordDate, 0);
			
//			if(hasLastRecord==0)
//			{
			//全部添加或更新
			//this.generateEnergyCostsByRooms(energyCosts, rooms, energyType, lastRecordDate, 1);
//			}
			
			//批量填0不需要检查	
//			List<String> checkResult=this.energyCostService.checkEnergyCost(energyCosts);
//			
//			if(checkResult.size()>0)
//			{
//				return ResultType.getFail(Util.List2Str(checkResult,"<br />"));
//			}	
				
			int count=this.energyCostService.createEnergyCostList(Util.getOpInfo(), energyCosts, EnergyCost.EnergyCategory.ROOM.value, energyType);
			
			if(count>0)
			{
				result = ResultType.getSuccess("保存成功",count);
			}
			else
			{
				result = ResultType.getFail();
			}
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	/**
	 * 根据房间生成 能源列表，读数为0
	 * @param energyCosts
	 * @param rooms
	 * @param energyType
	 * @param recordDate
	 * @param isBilled
	 */
	private void generateEnergyCostsByRooms(List<EnergyCost> energyCosts,List<EtopFloorRoom> rooms,int energyType,String recordDate,int isBilled)
	{		
		
		for(EtopFloorRoom room : rooms)
		{
			EnergyCost ec=new EnergyCost();
			
			ec.setRecord(0);
			
			ec.setRecordDate(Util.str2Date(recordDate));
			
			ec.setRefItemId(room.getId());
			
			ec.setRefBuildingId(room.getRefFloorId());
			
			ec.setEnergyCategory(EnergyCost.EnergyCategory.ROOM.value);
			
			ec.setEnergyType(energyType);
			
			ec.setIsBilled(isBilled);
			
			energyCosts.add(ec);
		}
		
		
	}
	
	
	@ResponseBody
	@RequestMapping("/checkBuildingEnergyRecordSummaryByDate.do")
	public ResultType checkBuildingEnergyRecordSummaryByDate(int energyType,String refBuildingId,String recordDate,String lastRecordDate,HttpServletRequest request)
	{
		ResultType result=null;
		
		//List<Integer> list=new ArrayList<Integer>();
		
		Map<String,Object> map =new HashMap<String, Object>();
		
		try {
			
			EnergyCost buildingEC=this.energyCostService.getBuildingEnergyCost(refBuildingId, recordDate, energyType);
			
			if(buildingEC!=null)
			{
				map.put("buildingIsEntry", 1);
				
				//list.add(1);
			}
			else
			{
				//list.add(0);
				
				map.put("buildingIsEntry", 0);
			}
			
			int roomEntryCount=this.energyCostService.getRoomEnergyCostCountByBuilding(refBuildingId, recordDate, energyType);

			//list.add(roomEnergyCount);
			
			map.put("roomEntryCount", roomEntryCount);
			
			int roomCount=this.etopFloorService.getRoomCountByBuilding(refBuildingId,recordDate, 1);
			
			//list.add(roomCount);
			
			map.put("roomCount", roomCount);
			
			//get energycost sum
			double lastBuildingSum=this.energyCostService.getEnergyCostSumByRecordDate(refBuildingId, EnergyCost.EnergyCategory.BUILDING.value, energyType, lastRecordDate);
			
			double currBuildingSum=this.energyCostService.getEnergyCostSumByRecordDate(refBuildingId, EnergyCost.EnergyCategory.BUILDING.value, energyType, recordDate);
			
			map.put("buildingSum",currBuildingSum-lastBuildingSum);
			
			double lastRoomsSum=this.energyCostService.getEnergyCostSumByRecordDate(refBuildingId, EnergyCost.EnergyCategory.ROOM.value, energyType, lastRecordDate);
			
			double currRoomsSum=this.energyCostService.getEnergyCostSumByRecordDate(refBuildingId, EnergyCost.EnergyCategory.ROOM.value, energyType, recordDate);
			
			map.put("roomsSum",currRoomsSum-lastRoomsSum);
			
			result = ResultType.getSuccess("成功",map);
			
			
			//0113同时更新楼能源ActiveRoomCount
			if(buildingEC!=null)
			{
				buildingEC.setActiveRoomCount(roomCount);
				
				List<EnergyCost> list=new ArrayList<EnergyCost>();
				
				list.add(buildingEC);
				
				this.energyCostService.updateEnergyCostList(Util.getOpInfo(), list);
			}
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
			
			result = ResultType.getException("failed！");
			
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("/getEnergyBillList.do")
	public Map<String,Object> getEnergyBillList(PageEnergyBill page,HttpServletRequest request)
	{
		Map<String,Object> result=new HashMap<String, Object>();
		int count=0;
		try {
			if(Util.isNullOrEmpty(page.getRefId()))
			{
				result.put("data", null);
			}
			else
			{
				
				//set refRoomIds
				List<String> refRoomIds=new ArrayList<String>();
								
				//room
				if(page.getIdType()==3)
				{
					refRoomIds.add(page.getRefId());
				}
				else
				{
					refRoomIds=this.etopFloorService.getRoomIdsByParent(page.getRefId(), page.getIdType());
				}
				
				page.setRefRoomIds(refRoomIds);
				
				String orderColumn=request.getParameter("columns[" + request.getParameter("order[0][column]").toString() + "][data]").toString();
				
				String orderDir=request.getParameter("order[0][dir]").toString();
				
				page.setOrderDir(orderDir);
				
				page.setOrderColumn(Util.humpToLine(orderColumn));
							
				List<EnergyBillModel> list=this.etopBillService.getEnergyBillList(page);
				
				count=this.etopBillService.getEnergyBillCount(page);
				
				result.put("data", list);
			
			}
			
			result.put("msg", "");
			
			result.put("draw", page.getDraw());
			
			result.put("recordsTotal", count);
			
			result.put("recordsFiltered", count); 			
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
						
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	@ResponseBody
	@RequestMapping("/getEnergyRecordList.do")
	public Map<String,Object> getEnergyRecordList(PageEnergyBill page,HttpServletRequest request)
	{
		Map<String,Object> result=new HashMap<String, Object>();
		
		int count=0;
		
		try {
			
			if(Util.isNullOrEmpty(page.getRefId()))
			{
				result.put("data", null);
			}
			else
			{
				
				//set refRoomIds
				List<String> refRoomIds=new ArrayList<String>();
						
				//building
				if(page.getIdType()==0)
				{
					refRoomIds.add(page.getRefId());
				}				
				//room
				else if(page.getIdType()==3)
				{
					refRoomIds.add(page.getRefId());
				}
				else
				{
					refRoomIds=this.etopFloorService.getRoomIdsByParent(page.getRefId(), page.getIdType());
				}
				
				
				
				
				page.setRefRoomIds(refRoomIds);
				
				String orderColumn=request.getParameter("columns[" + request.getParameter("order[0][column]").toString() + "][data]").toString();
				
				String orderDir=request.getParameter("order[0][dir]").toString();
				
				page.setOrderDir(orderDir);
				
				page.setOrderColumn(Util.humpToLine(orderColumn));
				
				//出帐日需要加一天
				if(!Util.isNullOrEmpty(page.getBillEndDate()))
				{
					page.setBillEndDate(Util.increaseDate(page.getBillEndDate(), 1));
				}
				
				
							
				List<EnergyRecordModel> list=this.energyCostService.getEnergyListByRoom(page);
				
				count=this.energyCostService.getEnergyCountByRoom(page);
			
				result.put("data", list);
							
			}
			
			
			
			result.put("msg", "");
			
			result.put("draw", page.getDraw());
			
			result.put("recordsTotal", count);
			
			result.put("recordsFiltered", count); 
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
						
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	@ResponseBody
	@RequestMapping("/getEnergySummary.do")
	public ResultType getEnergySummary(PageEnergyBill page,HttpServletRequest request)
	{
		ResultType result=null;
		
		int count=0;
		
		try {
			
			if(Util.isNullOrEmpty(page.getRefId()))
			{
				result= ResultType.getNoData();
			}
			else
			{
				
				//set refRoomIds
				List<String> refRoomIds=new ArrayList<String>();
						
				//building
				if(page.getIdType()==0)
				{
					refRoomIds.add(page.getRefId());
				}				
				//room
				else if(page.getIdType()==3)
				{
					refRoomIds.add(page.getRefId());
				}
				else
				{
					refRoomIds=this.etopFloorService.getRoomIdsByParent(page.getRefId(), page.getIdType());
				}
				
				
				
				
				page.setRefRoomIds(refRoomIds);
				
//				String orderColumn=request.getParameter("columns[" + request.getParameter("order[0][column]").toString() + "][data]").toString();
//				
//				String orderDir=request.getParameter("order[0][dir]").toString();
//				
//				page.setOrderDir(orderDir);
//				
//				page.setOrderColumn(Util.humpToLine(orderColumn));
				
				//出帐日需要加一天
				if(!Util.isNullOrEmpty(page.getBillEndDate()))
				{
					page.setBillEndDate(Util.increaseDate(page.getBillEndDate(), 1));
				}
				
				
							
				List<Map<String,String>> list=this.energyCostService.getEnergySummaryByRoom(page);
				
				//2017-3-15若有null数据，则返回nodata
				Object[] r=list.stream().filter(s->s==null).toArray();
				
				if (r!=null && r.length>0)
			
				{
					result=ResultType.getNoData();
				}
				else
				{
					result=ResultType.getSuccess(list);
				}
							
			}
			
			
			
			
			
			
		} catch (Exception e) {
			
			LoggerUtil.error(e);			
						
			//e.printStackTrace();
		}
		
		return result;
			
	}
	
	
	
}
