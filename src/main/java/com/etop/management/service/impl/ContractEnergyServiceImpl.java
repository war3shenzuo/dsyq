package com.etop.management.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.Contract;
import com.etop.management.bean.ContractEnergy;
import com.etop.management.bean.Criteria;
import com.etop.management.bean.EnergyCost;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.bean.Park;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.ContractEnergyDao;
import com.etop.management.dao.EnergyCostDao;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.dao.EtopFloorDao;
import com.etop.management.dao.EtopFloorRoomDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopFloor;
import com.etop.management.model.BuildingEnergyModel;
import com.etop.management.model.ContractEnergyBillModel;
import com.etop.management.model.EnergyItemForBillModel;
import com.etop.management.model.EnergySupplyModel;
import com.etop.management.service.ContractEnergyService;
import com.etop.management.service.EnergyCostService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.Util;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContractEnergyServiceImpl implements ContractEnergyService {

	//private final static LoggerUtil LoggerUtil = LoggerUtil.getLoggerUtil(ContractEnergyService.class);
	
	@Resource
	ContractEnergyDao contractEnergyDao;
	
	@Resource
	EtopFloorDao etopFloorDao;
	
	@Resource
	EtopFloorRoomDao etopFloorRoomDao;
	
	@Resource
	EnergyCostDao energyCostDao;
	
	@Resource
	ContractDao contractDao;
	
	@Resource
	ParkDao parkDao;
	
	@Resource
	EtopBillDao etopBillDao;
	
	@Resource
	EtopSequenceService etopSequenceService;
	
	@Resource
	EtopThresholdService etopThresholdService;
	
	@Resource
	EnergyCostService energyCostService;
	
	@Override
	public String save(OpInfoBean op, ContractEnergy entity) {
		
		
		
		if(Util.isNullOrEmpty(entity.getId()))
		{
			entity.setId(UUID.randomUUID().toString());
			
			entity.setCreatedAt(new Date());
			
			entity.setCreatedBy(op.getOper());
			
			entity.setUpdatedAt(new Date());
			
			entity.setUpdatedBy(op.getOper());
			
			this.contractEnergyDao.createContractEnergy(entity);
			
			
			
		}
		else
		{
			entity.setUpdatedAt(new Date());
			
			entity.setUpdatedBy(op.getOper());
			
			this.contractEnergyDao.updateContractEnergy(entity);
		}
		
		
		
		//12.16这里要同时更新合同的last balance date
		
		Contract contract=this.contractDao.getContractById(entity.getRefContractId());
		
		if(contract!=null)
		{
			contract.setLastBalanceDate(entity.getPowerRecordDate());
			
			this.contractDao.updateContract(contract);
		}
		
		
		LoggerUtil.info(String.format("[contractEnergy.save]%s, and update contract's last balance date.", entity.toString()));
		
		
		return entity.getId();
		
		
	}

	@Override
	public ContractEnergy getValueByRefContractId(String refContract) {

		return this.contractEnergyDao.getContractEnergyByRefContractId(refContract);
	}

//	@Override
//	public int removeByRefContractId(OpInfoBean op, String refContract) {
//
//		return this.contractEnergyDao.deleteContractEnergyByRefContractId(refContract);
//	}

	/*@Override
	public List<ContractEnergyBillModel> getContractEnergyBillModelsForBill(String refBuildingId) {
		
		
		List<ContractEnergyBillModel> result=new ArrayList<ContractEnergyBillModel>();
		
		List<ContractEnergyBillModel> items=this.contractEnergyDao.getContractEnergyBillModelByBuilding(Util.formatDate(new Date()),refBuildingId);
		
		//最后一次出帐日
		String lastBillDateStr="";
		//此次出帐日
		String currentBillDateStr="";
		
		//计费开始日
		String feeDateStart="";
		//计费截止日
		String feeDateEnd="";
		
		//缴费截止日
		String currPaymentDate="";
		
		//缴费金额
		float amount=0;
		
		boolean needbill=false;		
		
		//判断是否需要出帐，并生成model
		for(ContractEnergyBillModel item : items)
		{
			//reset
			needbill=false;	
			//自定义出帐日
			if(item.getBillType()==0)
			{
				String billDates=item.getBillDates();
				
				if(!Util.isNullOrEmpty(billDates))
				{
					List<String> billDatesArr=Arrays.asList(billDates.split(","));
					
					Date now=new Date();
					
					for(String d:billDatesArr)
					{
						d=DateUtil.getYear()+"-"+d;
						
						//今天
						currentBillDateStr=DateUtil.formatDate(now);
						
						//计费截止日，即今天
						feeDateEnd=currentBillDateStr;
								//Util.generateCurrentFeeDate(currentBillDateStr, item.getBillPeriod());
						
						//今天大于等于出帐日，出帐
						//计费截止日>＝分期结束日，出帐
						//|| Util.dateDiff(feeDateEnd,Util.formatDate(item.getEndDate()))>=0
						if(Util.dateDiff(currentBillDateStr,d)>=0)
						{
							if(item.getLastBillDate()==null)
							{
								feeDateStart=Util.formatDate(item.getStartDate());
							}
							else
							{
								feeDateStart=Util.increaseDate(Util.generateCurrentFeeDate(Util.formatDate(item.getLastBillDate()), item.getBillPeriod()),1);
							}
							
							//计费开始日大于项目结束日，略过
							if(Util.dateDiff(feeDateStart, Util.formatDate(item.getEndDate()))>0)
							{
								continue;
							}
							
							
							
//							//比较计费截止日与结束日期
//							int dateDiff=Util.dateDiff(feeDateEnd, Util.formatDate(item.getEndDate()));
//			
//							//若计费截止日已大于等于分期结束日
//							if(dateDiff>=0)
//							{
//								feeDateEnd=Util.formatDate(item.getEndDate());
//							}
						
							currPaymentDate=Util.increaseDate(currentBillDateStr, item.getPaymentDate());
														
							
							needbill=true;
						}
					}
				}
			}
			//统一规则
			if(item.getBillType()==1)
			{
				//出帐日
				String billDateStr=item.getBillDate();
								
				//计费周期
				int billPeriod=item.getBillPeriod();
				
				//出帐间隔月份
				int billIntervalMonth=Integer.valueOf(billDateStr.substring(0, billDateStr.indexOf("-")));
				//出帐日
				int billDate=Integer.valueOf(billDateStr.substring(billDateStr.indexOf("-")+1));
				
				//首次，feeDateStart=startDate
				if(item.getLastBillDate()==null)
				{
					lastBillDateStr=Util.formatDate(item.getStartDate());
					
					feeDateStart=Util.formatDate(item.getStartDate());
				}
				else
				{
					lastBillDateStr=Util.formatDate(item.getLastBillDate());
					
					//上次计费截止日加一天
					feeDateStart=Util.increaseDate(Util.generateCurrentFeeDate(lastBillDateStr, billPeriod),1);							
							
				}
					
				//如果计费开始日已大于分期结束日，即本分期出帐完成，略过
				if(Util.dateDiff(feeDateStart, Util.formatDate(item.getEndDate()))>0)
				{
					continue;
				}				
				
				//生成这次出帐日
				currentBillDateStr=Util.generateCurrentBillDate(lastBillDateStr, billDate, billIntervalMonth);
				
				//计费截止日
				feeDateEnd=Util.generateCurrentFeeDate(currentBillDateStr, billPeriod);	
				
				//若今天大于等于出帐日，出帐，今天 大于出帐日，即自动生成失败，需要再生成
				//或者计费截止日大于等于项目结束日期，即，本分期结束，也出帐
				//currentBillDateStr.equals(Util.formatDate(new Date())) || 
				if(Util.dateDiff(Util.formatDate(new Date()),currentBillDateStr)>=0
						//|| Util.dateDiff(feeDateEnd,Util.formatDate(item.getEndDate()))>=0
						
						
						)
				{
									
//					//比较计费截止日与分期结束日期
//					int dateDiff=Util.dateDiff(feeDateEnd, Util.formatDate(item.getEndDate()));
//	
//					//若计费截止日已大于等于分期结束日
//					if(dateDiff>=0)
//					{
//						feeDateEnd=Util.formatDate(item.getEndDate());
//					}
				
					currPaymentDate=Util.increaseDate(currentBillDateStr, item.getPaymentDate());
										
					//amount=item.getDailyUnitPrice()*Util.dateDiff(feeDateEnd,feeDateStart);
					
					needbill=true;
					
				}				
			
			}		
			
			
			if(needbill)
			{
				//ContractEnergyBillModel model=new ContractEnergyBillModel();
				

								
//				model.setRefContractId(item.getRefContractId());
//				
//				model.setRefContractEnergyId(item.getId());
				
				item.setCurrBillDate(currentBillDateStr);
				
				item.setFeeStartDate(feeDateStart);
				
				item.setFeeEndDate(feeDateEnd);
				
				item.setDeadLine(currPaymentDate);
				
				this.setBillModel(0, 0, item.getRefRoomId(),feeDateStart, feeDateEnd,item);
				
				this.setBillModel(0, 1, item.getRefRoomId(),feeDateStart, feeDateEnd,item);
				
				this.setBillModel(0, 2,item.getRefRoomId(),feeDateStart, feeDateEnd,item);
				
				this.setBillModel(0, 3, item.getRefRoomId(),feeDateStart, feeDateEnd,item);
			
				
//				model.setAcPrice(item.getAcPrice());
//				
//				model.setPowerPrice(item.getPowerPrice());
//				
//				model.setWaterPrice(item.getWaterPrice());
//				
//				model.setGasPrice(item.getGasPrice());
			
				
				result.add(item);					
				
			}
			
			
			
		}
		
		
		return result;
	}
	*/
	/*private void setBillModel(int energyCategory,int energyType,String itemId,String feeStartDate,String feeEndDate,ContractEnergyBillModel model)
	{
		//float result=0;
		
		//get energy cost list
		
		List<EnergyCost> list=this.energyCostDao.getEnergyFeeList(energyType, itemId, feeStartDate,feeEndDate);
		
		//总使用量
		float amount=0;
		//总公摊量
		float amountShare=0;
		//计费抄表开始
		Date ds=null;
		//计费抄表结束
		Date de=null;
		//最后读数
		float lastRecord=0;
				
		
		for(EnergyCost ec :list)
		{
			amount+=ec.getAmount();
			
			amountShare+=ec.getShareAmount();
		}
		
		if(list!=null && list.size()>0)
		{
			ds=list.get(0).getRecordDate();
			
			de=list.get(list.size()-1).getRecordDate();
			
			lastRecord=list.get(list.size()-1).getRecord();
		
		
		
		
		
		
		
//		EnergyCost startRecord=this.energyCostDao.getEnergyCost(energyCategory, energyType, 0,itemId, feeStartDate);
//		
//		EnergyCost endRecord=this.energyCostDao.getEnergyCost(energyCategory, energyType, 1,itemId, feeEndDate);
//		
//		if(startRecord!=null && startRecord!=null)
//		{
//			result=endRecord.getRecord()-startRecord.getRecord();
//			
//			if(model!=null)
//			{
				if(energyType==0)
				{
					model.setPowerStartDate(Util.formatDate(ds));
					
					model.setPowerEndDate(Util.formatDate(de));
					
					model.setPowerRecords(amount);
					
					model.setPowerShareRecords(amountShare);
					
					model.setPowerLastRecord(lastRecord);
				}
				
				if(energyType==1)
				{
					model.setWaterStartDate(Util.formatDate(ds));
					
					model.setWaterEndDate(Util.formatDate(de));
					
					model.setWaterRecords(amount);
					
					model.setWaterShareRecords(amountShare);
					
					model.setWaterLastRecord(lastRecord);
				}
				
				if(energyType==2)
				{
					model.setGasStartDate(Util.formatDate(ds));
					
					model.setGasEndDate(Util.formatDate(de));
					
					model.setGasRecords(amount);
					
					model.setGasShareRecords(amountShare);
					
					model.setGasLastRecord(lastRecord);
				}
				
				if(energyType==3)
				{
					model.setAcStartDate(Util.formatDate(ds));
					
					model.setAcEndDate(Util.formatDate(de));
					
					model.setAcRecords(amount);
					
					model.setAcShareRecords(amountShare);
					
					model.setAcLastRecord(lastRecord);
				}
		}
//			}
//			
//		}
		
		
		//return result;
	}*/
	
	

	@Override
	public int generateAllBill(String today) {
				
		int result=0;
		
		List<String> parkList=this.parkDao.getParkIdList();
		
		for(String park : parkList)
		{
			result+=this.generateBillByPark(park, today);
		}
		
		
		return result;
	}
	@Override
	public int generateBillByPark(String refParkId, String today) {
		
		int result=0;
		
		try
		{
			if(Util.isNullOrEmpty(refParkId))
			{
				return -2;
			}		
		
		
			Criteria criteria=new Criteria();
			
			criteria.put("buildType", "floor");
			
			criteria.put("parkId", refParkId);
			
			//楼状态为有效
			criteria.put("status", "1");
			
			List<EtopFloor> buildingList=this.etopFloorDao.queryByList(criteria);
			
			LoggerUtil.info(String.format("园区（%s）有效楼数量（%d）", refParkId,buildingList==null?0:buildingList.size()));
			//按楼出能源帐
			for(EtopFloor building : buildingList)
			{
				result+=this.generateBillByBuilding(building,today);
			}
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			
			LoggerUtil.error(e);
			
			//出错后，发消息，若为空就不用发
		
				
//				String content=String.format("园区合同自动出帐失败，请管理员查看。");
//				
//				this.remindService.remind(refParkId, Role.QX_YQTX, "合同出帐", content);	
//				
			result=-3;
			
		}
		
		return result;
		
	}
	@Override
	public int generateBillByBuilding(EtopFloor building,String today) {
		
		//判断此楼是否需要出帐
		LoggerUtil.info(String.format("楼（%s）开始能源出帐", building.getBuildName()));
		
		//楼出帐日
		String billDate=building.getEnergyBillDate();
		
		String[] bdArr=billDate.split("-");
		
		int billIntervalMonth=Integer.valueOf(bdArr[0]);
		
		int billDay=Integer.valueOf(bdArr[1]);
		
		//楼上次出帐日,新建楼默认为创建日
		String lastBillDate=Util.formatDate(building.getEnergyLastBillDate());
		
		String currBillDate=this.getLastCurrentBillDate(lastBillDate, billDay, billIntervalMonth,today);
		
		
		LoggerUtil.info(String.format("楼上次出帐日:%s,本次出帐日:%s，相同则不出帐。",lastBillDate,currBillDate));
		
		//当取回的此次出帐日＝＝上次出帐日，不生成
		if(lastBillDate.equals(currBillDate))
		{
			return 0;
		}
		
		
		//判断是否需要出帐
		//今天未到理论出帐日，不用出帐
		if(Util.dateDiff(today,currBillDate)<0){
			return 0;
		}		
				
		//设置楼能源model
		List<BuildingEnergyModel> beList=this.getBuildingEnergyModels(building);
				
		//没有需出帐的能源类型，返回
		if(beList==null || beList.size()==0) return 0;
		
		//取出本楼所有可出帐的能源合同
		List<ContractEnergyBillModel> list=this.contractEnergyDao.getContractEnergyBillModelByBuilding(
					beList.get(0).getFeeStartDate(),beList.get(0).getFeeEndDate(), building.getId());
				//this.getContractEnergyBillModelsForBill(today,building.getId());
		
		if(list==null || list.size()==0) return 0;
		
		LoggerUtil.info("本楼可出帐的能源合同数量:"+list.size()+"，并开始设置能源帐单model,使用量，计费天数等。");
		
		
		//List<ContractEnergyBillModel> result=new ArrayList<ContractEnergyBillModel>();
		
		list.forEach(m->{
						
			//this.setEnergyBillModel(building,m,today,currBillDate);
			this.setContractEnergyBillModel(beList, m, today, currBillDate);
			
		});
		
		LoggerUtil.info("设置能源帐单model结束。");
		
		//设置各合同公摊		
		setShareEnergy(building,list);
		
		
		
		LoggerUtil.info("开始生成帐单。。。");
		
		//出帐
		list.forEach(m->{
						
			//LoggerUtil.info(m.itemDesc());
			
			//TODO:debug
			this.generateBillForEnergyBillModel(m);
			
		});
		
		//楼结算日在这里设置11.22
		
		LoggerUtil.info(String.format("生成帐单结束。更新楼最后结算日:%s，最后出帐日:%s", beList.get(0).getFeeEndDate(),currBillDate));
		
		building.setEnergyLastFeeDate(Util.str2Date(beList.get(0).getFeeEndDate()));
		
		building.setEnergyLastBillDate(Util.str2Date(currBillDate));
		
		this.etopFloorDao.update(building);	
		
		//能源楼记录中都设置为isBilled
		//12.12，所有楼、房间能源 记录都设置为isBilled
		LoggerUtil.info(String.format("更新日期在%s与%s之间的所有能源记录isbilled=1", beList.get(0).getFeeStartDate(), beList.get(0).getFeeEndDate()));
		this.energyCostDao.updateBuildingEnergyCostIsBilled(building.getId(), beList.get(0).getFeeStartDate(), beList.get(0).getFeeEndDate(), 1);
		
		
//		for(BuildingEnergyModel be : beList)
//		{
//			this.energyCostDao.updateRoomEnergyCostIsBilledByBuilding(building.getId(), beList.get(0).getFeeStartDate(), beList.get(0).getFeeEndDate(), 1);
//		}
		
		
		
		return list.size();
		
	}
	
	/**
	 * 楼能源MODEL，设置各能源
	 * @param building
	 * @return
	 */
	private List<BuildingEnergyModel> getBuildingEnergyModels(EtopFloor building)
	{
		LoggerUtil.info(String.format("设置楼(%s)能源model开始。。。", building.getBuildName()));
		
		List<BuildingEnergyModel> list=new ArrayList<BuildingEnergyModel>();
		
		//楼最后结算日，若为空，略过
		Date lastFeeDate=building.getEnergyLastFeeDate();
		
		if(lastFeeDate==null) return null;
		
		//初始化上次结算日，为空时。
		//initBuildingDate(building);
		
		//int roomCount=this.etopFloorRoomDao.getRoomCountByBuilding(building.getId(), 1);
		
		//各能源
		for(int i=0;i<4;i++)
		{
			//取回etop_floor_energy
			BuildingEnergyModel item=this.energyCostDao.getFloorEnergy(building.getId(), i);
			
			//为空，不出帐
			if(item ==null) continue;
			
			//若不公摊且不使用房间使用量，则不需要检测完整性，也不需要出帐
			if(item.getShareType()==EnergyCost.ShareType.NONE.value &&
					item.getRoomAmountUsed()==0	)
			{
				continue;
			}
			
			
			//if(building.getEnergyFeeDate(i)==null) continue;
			
			//此能源类型开始结算日期，即上次结算日
			String feeStartDate=Util.formatDate(lastFeeDate);
			
			//检测是否数据完整，返回本次可结算的楼记录
			EnergyCost lastBuildingCost=checkEnergyCost(i,building.getId(),feeStartDate);
			
			//若有一种能源不完整，全部略过，即此次此楼不出帐				
			if(lastBuildingCost==null) return null;						
			
			String currFeeDate=Util.formatDate(lastBuildingCost.getRecordDate());
			
			if(!Util.isNullOrEmpty(currFeeDate))
			{
				//BuildingEnergyModel item=new BuildingEnergyModel();
				
				item.setRefBuildingId(building.getId());
				
				item.setEnergyType(i);
				
				//item.setShareType(building.getSourceCharge());
				
				item.setFeeStartDate(feeStartDate);
				
				item.setFeeEndDate(currFeeDate);
				
				item.setFeeDates(Util.dateDiff(currFeeDate, feeStartDate));
				
				item.setRoomCount(lastBuildingCost.getActiveRoomCount());
				
				//楼内所有房间使用总量=G
				//两次结算日期之间所有房间读数最大值之和	-  两次结算日期之间所有房间读数最小值之和	
				
				float roomsUsedRecordsStart=this.energyCostDao.getRoomsMinEnergySumByDateAndType(building.getId(), item.getFeeStartDate(), item.getFeeEndDate(), i);
						//this.energyCostDao.getRoomEnergySumByRecordDateAndType(building.getId(), feeStartDate, i);
				
				float roomsUsedRecordsEnd=this.energyCostDao.getRoomsMaxEnergySumByDateAndType(building.getId(), item.getFeeStartDate(), item.getFeeEndDate(), i);
						//this.energyCostDao.getRoomEnergySumByRecordDateAndType(building.getId(), currFeeDate, i);
				
				//楼内房间使用总量
				float roomsUsedRecords=roomsUsedRecordsEnd-roomsUsedRecordsStart;
				
				//12.13
				if(roomsUsedRecords<0)
				{
					LoggerUtil.warn(String.format("楼[%s][%s]内房间使用总量为负(%f)",building.getId(), building.getBuildName(),roomsUsedRecords));
					
					throw new ArithmeticException("楼内房间使用总量为负");
				}
				
				item.setRoomsUsedRecords(roomsUsedRecords);
				
				//楼总使用量					
				Criteria criteria=new Criteria();
				
				criteria.put("energyType", item.getEnergyType());
				
				criteria.put("recordDate", item.getFeeStartDate());
				
				criteria.put("refItemId", building.getId());
				
				criteria.put("energyCategory", EnergyCost.EnergyCategory.BUILDING.value);
				
				EnergyCost buildingRecordsStart=this.energyCostDao.getEnergyCost(criteria);
				
				item.setStartRecord(buildingRecordsStart.getRecord());
				
//				criteria=new Criteria();
//				
//				criteria.put("energyType", item.getEnergyType());
//				
//				criteria.put("recordDate", currFeeDate);
//				
//				criteria.put("refItemId", building.getId());
//				
//				EnergyCost buildingRecordsEnd=this.energyCostDao.getEnergyCost(criteria);
				
				item.setEndRecord(lastBuildingCost.getRecord());
				
				double buildingRecords=lastBuildingCost.getRecord()-buildingRecordsStart.getRecord();
				
				//12.13
				if(buildingRecords<0)
				{
					LoggerUtil.warn(String.format("楼[%s][%s]使用量为负(%f)",building.getId(), building.getBuildName(),buildingRecords));
					
					throw new ArithmeticException("楼使用量为负");
				}
				
				item.setUsedRecords(buildingRecords);
				
				//公摊量
				double shareRecords=buildingRecords-roomsUsedRecords;
				
				//12.13
				if(shareRecords<0)
				{
					LoggerUtil.warn(String.format("楼[%s][%s]公摊量为负(%f)",building.getId(), building.getBuildName(),shareRecords));
					
					throw new ArithmeticException("公摊量为负");
				}
				
				
				item.setShareRecords(shareRecords);
				
				list.add(item);
				
				LoggerUtil.info(item.toString());
				
			}
			
			
		}
		
		LoggerUtil.info(String.format("设置楼(%s)能源model结束。能源类型数量(%d)", building.getBuildName(),list.size()));
		
		return list;
		
		
		
	}
	
	/**
	 * 设置能源合同bill model，包括各项使用量，计费天数等
	 * @param buildingEnergyModels
	 * @param model
	 * @param today
	 * @param currBillDate
	 * @return
	 */
	private int setContractEnergyBillModel(List<BuildingEnergyModel> buildingEnergyModels,ContractEnergyBillModel model,String today,String currBillDate)
	{
		//本期缴费日，从实际生成日算
		String currPaymentDate=Util.increaseDate(today, model.getPaymentDate());
		
		model.setDeadLine(currPaymentDate);
		
		model.setCurrBillDate(today);
		
		LoggerUtil.info("设置能源合同帐单model:"+model.toString());
		
		for(BuildingEnergyModel beModel:buildingEnergyModels)
		{
			EnergyItemForBillModel item=new EnergyItemForBillModel();
			
			item.setShareType(beModel.getShareType());
			
			item.setRoomAmountUsed(beModel.getRoomAmountUsed());
			
			item.setEnergyType(beModel.getEnergyType());
			
			item.setPrice(model.getEnergyPrice(beModel.getEnergyType()));
			
			//计费开始日，即最后一次结算日，
			item.setFeeStartDate(beModel.getFeeStartDate());
			
			item.setBuildArea(Float.valueOf(model.getArea()));
						
			
			//B，D取最大值
			//合同开始日期开始后最近的抄表读数=B
			//11.22改为合同已结算日期+1天读数，即合同中输入的结算日期+1
			//2017-3-1，改为合同开始日期的读数

//			EnergyCost firstCost=this.energyCostDao.getRecordOfRoomByRecordDate(model.getRefRoomId(), 
//					item.getEnergyType(),Util.formatDate(model.getStartDate()));
			//2017-5-19，此处应该为合同开始后最近读数，即recordDate>=contract.startdate,<本次结算结束日的最近一条			
			EnergyCost firstCost=this.energyCostDao.getNearestRecordOfRoomByRecordDate(model.getRefRoomId(), 
					item.getEnergyType(),Util.formatDate(model.getStartDate()),beModel.getFeeEndDate());
			
					//Util.increaseDate(Util.formatDate(model.getEnergyRecordDate(beModel.getEnergyType())),1));				
			
			//最近一次结算的抄表读数=D
			EnergyCost lastFeeCost=this.energyCostDao.getRecordOfRoomByRecordDate(model.getRefRoomId(), 
					item.getEnergyType(), item.getFeeStartDate());
			
			//这次结算日(出帐日之前)的抄表读数，当此房间对应有两个合同时，在使用量分摊方式下，需要两家公司来分摊此房间的公摊
			EnergyCost currFeeCost=this.energyCostDao.getLastRecordOfRoomBeforeEndDate(model.getRefRoomId(), item.getEnergyType(), currBillDate);
			
			
			//TODO:CHECK three of above
			//如果有一个为空，则认为数据不完整，不出帐
			//2017-3-1，老合同有可能没有开始读数，直接用最后结算读数
			//if(firstCost==null || lastFeeCost==null) return 0;
			if(lastFeeCost==null) return 0;
			
			EnergyCost maxStartCost=null;
			
			if(firstCost!=null)
			{
				maxStartCost=firstCost;
				
				//12.15这里还是比较日期吧，因为录入数据肯定是与日期同步
				if(lastFeeCost.getRecordDate().getTime()>=firstCost.getRecordDate().getTime())
				{
					maxStartCost=lastFeeCost;
				}
			}
			else
			{
				maxStartCost=lastFeeCost;
			}
						
						
			
			//合同结束日期结束前最近的抄表读数=C，即计费结束日期
			EnergyCost lastCost=null;
			//预分三种情况，合同已结束(结束后最近一次抄表)，已终止（终止后最近一次抄表），正常（结束前最近一次抄表）
			
//			if(model.getContractStatus()==3)
//			{
//				if(Util.dateDiff(currBillDate, Util.formatDate(model.getEndDate()))>=0)//合同已结束
//				{
//					lastCost=this.energyCostDao.getRecordOfRoomByRecordDate(model.getRefRoomId(), item.getEnergyType(),
//							Util.increaseDate(Util.formatDate(model.getEndDate()),1)
//							);
//				}
//				else//合同正常
//				{
//					lastCost=this.energyCostDao.getRecordOfRoomByRecordDate(model.getRefRoomId(), item.getEnergyType(), Util.formatDate(model.getEndDate()));		
//				}
//			}
//			else//terminate
//			{
//				lastCost=this.energyCostDao.getFirstRecordOfRoomAfterStartDate(model.getRefRoomId(), item.getEnergyType(),
//						
//						Util.increaseDate(Util.formatDate(model.getTerminateDate()),1));
//			}
			
			//11.22改为，结束日期+1与可结算日期中取最小值
			
			EnergyCost contractEndCost=null;
			
			//已结束合同
			if(model.getTerminateDate()==null)
			{
				contractEndCost=this.energyCostDao.getRecordOfRoomByRecordDate(
						model.getRefRoomId(), item.getEnergyType(),
						Util.increaseDate(Util.formatDate(model.getEndDate()),1));
			}
			//终止合同
			else
			{
				contractEndCost=this.energyCostDao.getRecordOfRoomByRecordDate(
						model.getRefRoomId(), item.getEnergyType(),
						Util.increaseDate(Util.formatDate(model.getTerminateDate()),1));
			}
			
			//最后可结算日
			lastCost=this.energyCostDao.getRecordOfRoomByRecordDate(model.getRefRoomId(), item.getEnergyType(), beModel.getFeeEndDate());
			
			//最后不能为空
			if(lastCost==null) return 0;
			
			EnergyCost minEndCost=null;
			
			minEndCost=lastCost;
			
			//两个结束日读数，有可能为空
			//12.15这里还是比较日期吧，因为录入数据大小肯定是与日期同步
			if(contractEndCost!=null)
			{
				if(contractEndCost.getRecordDate().getTime()<lastCost.getRecordDate().getTime())
				{
					minEndCost=contractEndCost;
				}
			}
						
			
			//本期本房间使用量
			double roomUsedRecords=minEndCost.getRecord()-maxStartCost.getRecord();
			
			//12.13
			if(roomUsedRecords<0)
			{
				LoggerUtil.warn(String.format("楼[%s][%s]房间(%s)使用量为负(%f)",model.getRefBuildingId(), model.getBuildingName(),model.getRoomName(),roomUsedRecords));
				
				throw new ArithmeticException(String.format("房间(%s)使用量为负(%f)",model.getRoomName(),roomUsedRecords));
			}
			
			
			item.setUsedRecords(roomUsedRecords);
			
			//item.setUsedAmount(roomUsedRecords*item.getPrice());
			
			item.setFeeStartDate(Util.formatDate(maxStartCost.getRecordDate()));
			
			item.setFeeEndDate(Util.formatDate(minEndCost.getRecordDate()));
			
			item.setFirstRecord(maxStartCost.getRecord());
			
			item.setLastRecord(minEndCost.getRecord());
			
			
			//房间使用天数，即计费天数
			int roomFeeDates=Util.dateDiff(Util.formatDate(minEndCost.getRecordDate()), Util.formatDate(maxStartCost.getRecordDate()));
							
			item.setFeeDates(roomFeeDates);
			
			item.setRoomsUsedRecords(beModel.getRoomsUsedRecords());
			
			item.setRoomsShareRecords(beModel.getShareRecords());
			
			model.getItemList().add(item);
			
			LoggerUtil.info("能源合同项:"+item.desc());
			
			
		}	
		
		
		return 1;
	}
	/**
	 * 设置公摊，根据公摊方式
	 * @param list
	 * @return
	 */
	private int setShareEnergy(EtopFloor building ,List<ContractEnergyBillModel> list)
	{
		
			LoggerUtil.info(String.format("开始设置楼(%s)各合同公摊。。。",building.getBuildName()));
		
			for(ContractEnergyBillModel eb:list)
			{
				LoggerUtil.info(String.format("合同:%s,房间:%s:", eb.getRefContractId(),eb.getRoomName()));
				
				for(EnergyItemForBillModel item:eb.getItemList())
				{
					double shareRecords=0;
					
					if(item.getShareType()==EnergyCost.ShareType.USED.value)
					{					
						//shareRecords=item.getRoomsShareRecords()*(item.getUsedRecords()/item.getRoomsUsedRecords());
						
						float sum=this.getSumOfUsedRecords(list, item.getEnergyType());
						
						shareRecords=item.getRoomsShareRecords()*(item.getUsedRecords()/sum);
						
						LoggerUtil.info(String.format("shareType:%d,roomAmountUsed:%d,used:%.2f/sum of used:%.2f,shareRecords:%.2f",
								item.getShareType(),item.getRoomAmountUsed(),
								item.getUsedRecords(),sum,shareRecords));
						
						
					}
					
					if(item.getShareType()==EnergyCost.ShareType.BUILDAREA.value)
					{
						float sum=getSumOfBuildAreaAndDate(list,item.getEnergyType());
						
						shareRecords=item.getRoomsShareRecords()*(item.getBuildArea()*item.getFeeDates()/sum);
						
						LoggerUtil.info(String.format("shareType:%d,roomAmountUsed:%d,buildArea*FeeDates:%.2f/sum of buildArea*FeeDates:%.2f,shareRecords:%.2f",
								item.getShareType(),item.getRoomAmountUsed(),
								item.getBuildArea()*item.getFeeDates(),sum,shareRecords));
						
						
						
					}
					
					item.setRoomShareRecords(shareRecords);
					
					item.setShareAmount(shareRecords*item.getPrice());
										
					
					//房间使用量
					if(item.getRoomAmountUsed()==1)
					{
						item.setUsedAmount(item.getUsedRecords()*item.getPrice());
					}
					else
					{
						item.setUsedAmount(0);
					}
					
					LoggerUtil.info(item.desc());
				}
			}
		
		LoggerUtil.info("设置公摊结束。");
			
		return 0;
	}
	
	/**
	 * 取楼按面积公摊因子，各房间天数*面积之和
	 * @param list
	 * @param energyType
	 * @return
	 */
	private float getSumOfBuildAreaAndDate(List<ContractEnergyBillModel> list,int energyType)
	{
		float result=0;
		
		for(ContractEnergyBillModel be:list)
		{
			result+=Float.valueOf(be.getArea())*be.getEnergyFeeDates(energyType);
		}
		return result;
	}
	/**
	 * 取楼按使用量公摊因子，各房间使用量之和
	 * @param list
	 * @param energyType
	 * @return
	 */
	private float getSumOfUsedRecords(List<ContractEnergyBillModel> list,int energyType)
	{
		float result=0;
		
		for(ContractEnergyBillModel be:list)
		{
			for(EnergyItemForBillModel m :be.getItemList())
			{
				if(m.getEnergyType()==energyType)
				{
					result+=m.getUsedRecords();
				}
			}
		}
		return result;
	}
	
	
	
	/**
	 * 检测楼能源数据完整性
	 */
	private EnergyCost checkEnergyCost(int energyType,String refBuildingId,String lastFeeDate)
	{
		//先取出楼能源上次结算后，最后一次能源录入记录（无论什么能源，取最后一次录入记录)
		EnergyCost buildingCost=this.energyCostDao.getBuildingLastEnergyCost(refBuildingId, lastFeeDate, null);
		
		if(buildingCost==null) return null;
		
		//然后根据这个日期来取各录入能源
		
		Criteria c=new Criteria();
		
		c.put("recordDate", buildingCost.getRecordDate());
		
		c.put("energyCategory", EnergyCost.EnergyCategory.BUILDING.value);
		
		c.put("refItemId", refBuildingId);
		
		c.put("energyType", energyType);
		
		c.put("isBilled", 0);
		
		
		EnergyCost lastBuildingCost=this.energyCostDao.getEnergyCost(c);
		
		if(lastBuildingCost==null) return null;
		
		
		String recordDate=Util.formatDate(lastBuildingCost.getRecordDate());
		
		//根据楼录入日期，取出本次房间录入总数
		int roomEnterCount=this.energyCostDao.getRoomEnergyCountByBuildingAndDateAndType(refBuildingId, recordDate, energyType);
		
		//相等且isBilled=0，则可以出帐
		//0112，>= 
		if(roomEnterCount>=lastBuildingCost.getActiveRoomCount() && lastBuildingCost.getIsBilled()==0)
		{
			return lastBuildingCost;
		}
		else
		{
			return null;
		}
		
	}
	
	
	
	/**
	 * 为能源合同生成帐单
	 * @param energyType
	 * @param model
	 * @param price
	 */
	private int generateBillForEnergyBillModel(ContractEnergyBillModel model)
	{
		
		//LoggerUtil.info(model.getRoomName());
		
		if(model.getItemList().size()==0) return 0;
		
		Contract contract=this.contractDao.getContractById(model.getRefContractId());
		
		ContractEnergy contractEnergy=this.contractEnergyDao.getContractEnergyById(model.getRefContractEnergyId());
		
		if(contract==null) return -1;
			
		if(contractEnergy==null) return -2;
		
		Park park=this.parkDao.getParkInfo(contract.getRefParkId());
		
		if(park==null) return -3;
		
		float amount=0;
		
		String desc="";
		
		String feeEndDate="";
		
		String feeStartDate="";
		
		//EtopFloor building=this.etopFloorDao.queryById(model.getRefBuildingId());
		
		//building.setEnergyLastBillDate(Util.str2Date(model.getCurrBillDate()));
		
		//building.setEnergyLastFeeDate(model.getEndDate());
		
		for(EnergyItemForBillModel item :model.getItemList())
		{
			amount+=item.getUsedAmount()+item.getShareAmount();
			
			feeStartDate=item.getFeeStartDate();
			
			feeEndDate=item.getFeeEndDate();
		}
		
		//12.13不能有0或负帐单
		if(amount<=0)
		{
			return 0;
		}
		
		EtopBill etopBill=new EtopBill();
		
		etopBill.setParkId(contract.getRefParkId());
		
		etopBill.setCompanyId(contract.getRefCompanyId());
		
		etopBill.setCompanyName(contract.getCompanyName());
		
		etopBill.setBillId(etopSequenceService.getFormatId(park.getParkCode(), "zd"));
		
		etopBill.setBillSource(contract.getContractCategory());
		
		etopBill.setBillNoOut(contract.getContractNo());
		
		etopBill.setStartTime(Util.str2Date(feeStartDate));
		
		etopBill.setEndTime(Util.str2Date(Util.increaseDate(feeEndDate,-1)));
		
		
		
		etopBill.setAmount(new BigDecimal(amount));
		
		etopBill.setDeadline(DateUtil.toTodayNight(Util.str2Date(model.getDeadLine())));
		
		etopBill.setBillType(EtopBill.BillType.INCOME.value);
		
		etopBill.setBillStatus(EtopBill.BillStatus.UNPAID.value);
		
		etopBill.setAuditStatus(EtopBill.AuditStatus.UNCHECK.value);
		
		etopBill.setAuditLevel(2);
		
		
		if(BillSource.SUBCONTRACT_CONTRACT.value==contract.getContractCategory())
		{
			etopBill.setBillType(EtopBill.BillType.OUTLAY.value);
		}
		
		etopBill.setDescription(model.itemDesc());		
		
		etopBill.setOverdueRate(new BigDecimal(this.etopThresholdService.getValue(contract.getRefParkId(), "overdue_rate")));
	
		
		etopBill.setCreatedTime(new Date());
		etopBill.setAmountPaid(new BigDecimal(0));
		etopBill.setAccountRemission(new BigDecimal(0));
		etopBill.setOverdueFine(new BigDecimal(0));
		etopBill.setOverdueRemission(new BigDecimal(0));

		int r=this.etopBillDao.add(etopBill);
	
		
		if(!Util.isNullOrEmpty(feeEndDate))
		{
			contract.setLastBalanceDate(etopBill.getEndTime());//Util.str2Date(feeEndDate)
		
			this.contractDao.updateContract(contract);
		}


		//TODO:remind parker

		LoggerUtil.info(model.itemDesc()
				+"\r\n生成帐单，更新能源记录中与合同关联的记录isbilled=1,billId:"+etopBill.getBillId()
				+ "\r\n并更新合同中的最后结算日与最后结算读数。");
		
		//设置楼信息，更新能源记录
		for(EnergyItemForBillModel item:model.getItemList())
		{
			//amount+=item.getUsedAmount()+item.getShareAmount();
			
			//this.energyCostDao.updateFloorEnergyFeeDate(model.getRefBuildingId(), item.getEnergyType(), item.getFeeEndDate());
			
			//12.12此处只更新关联相关合同的房间能源
			this.energyCostDao.updateEnergyCostBillId(model.getRefRoomId(), item.getFeeStartDate(), item.getFeeEndDate(), etopBill.getBillId(),item.getEnergyType());
			
			LoggerUtil.info(String.format("%s:读数:%f,日期:%s", 
					EnergyCost.EnergyType.valueOf(item.getEnergyType()).desc,
					item.getLastRecord(),
					Util.increaseDate(item.getFeeEndDate(),-1)));
			
			//12.13同时更新合同中的结算日期与结算读数
			if(item.getEnergyType()==EnergyCost.EnergyType.POWER.value)
			{
				contractEnergy.setPowerRecord(item.getLastRecord());
				
				contractEnergy.setPowerRecordDate(etopBill.getEndTime());//Util.str2Date(item.getFeeEndDate())
				
			}
			
			if(item.getEnergyType()==EnergyCost.EnergyType.WATER.value)
			{
				contractEnergy.setWaterRecord(item.getLastRecord());
				
				contractEnergy.setWaterRecordDate(etopBill.getEndTime());//Util.str2Date(item.getFeeEndDate())
				
			}
			
			if(item.getEnergyType()==EnergyCost.EnergyType.GAS.value)
			{
				contractEnergy.setGasRecord(item.getLastRecord());
				
				contractEnergy.setGasRecordDate(etopBill.getEndTime());//Util.str2Date(item.getFeeEndDate())
				
			}
			
			if(item.getEnergyType()==EnergyCost.EnergyType.AC.value)
			{
				contractEnergy.setAcRecord(item.getLastRecord());
				
				contractEnergy.setAcRecordDate(etopBill.getEndTime());//Util.str2Date(item.getFeeEndDate())
				
			}
			
			
//			if(item.getEnergyType()==EnergyCost.EnergyType.POWER.value)
//			{
//				building.setEnergyPowerFeeDate(Util.str2Date(item.getFeeEndDate()));
//				
//				//update energy cost's billid by feestart and feeend
//				
//				this.energyCostDao.updateEnergyCostBillId(model.getRefRoomId(), item.getFeeStartDate(), item.getFeeEndDate(), etopBill.getBillId(),EnergyCost.EnergyType.POWER.value);
//				
//				
//				
//			}
//			if(item.getEnergyType()==EnergyCost.EnergyType.WATER.value)
//			{
//				building.setEnergyWaterFeeDate(Util.str2Date(item.getFeeEndDate()));
//				
//				this.energyCostDao.updateEnergyCostBillId(model.getRefRoomId(), item.getFeeStartDate(), item.getFeeEndDate(), etopBill.getBillId(),EnergyCost.EnergyType.WATER.value);
//			}
//			if(item.getEnergyType()==EnergyCost.EnergyType.GAS.value)
//			{
//				building.setEnergyGasFeeDate(Util.str2Date(item.getFeeEndDate()));
//				
//				
//				this.energyCostDao.updateEnergyCostBillId(model.getRefRoomId(), item.getFeeStartDate(), item.getFeeEndDate(), etopBill.getBillId(),EnergyCost.EnergyType.GAS.value);
//			}
//			if(item.getEnergyType()==EnergyCost.EnergyType.AC.value)
//			{
//				building.setEnergyAcFeeDate(Util.str2Date(item.getFeeEndDate()));
//				
//				this.energyCostDao.updateEnergyCostBillId(model.getRefRoomId(), item.getFeeStartDate(), item.getFeeEndDate(), etopBill.getBillId(),EnergyCost.EnergyType.AC.value);
//			}			
			
			//feeEndDate=item.getFeeEndDate();
		}
		
		contractEnergy.setUpdatedAt(new Date());
		
		this.contractEnergyDao.updateContractEnergy(contractEnergy);
		
		//更新楼信息，11.22移至出账结束后设置	
		
		//this.etopFloorDao.update(building);
		
		
		
		return r;
	}

	
	/**
	 * 根据上次出帐日，算出本次出帐日
	 * @param lastBillDate
	 * @param billDate
	 * @param billIntervalMonth
	 * @return
	 */
	private String generateCurrentBillDate(String lastBillDate,int billDate,int billIntervalMonth)
	{
		String[] arr=lastBillDate.split("-");
		
		int month=Integer.valueOf(arr[1]);
		
		int year=Integer.valueOf(arr[0]);
		
		int day=Integer.valueOf(arr[2]);
		
		lastBillDate=year+"-"+String.format("%02d", month)+"-"+String.format("%02d", billDate);
		
		int im=0;
		
		//出帐日大于上次出帐日，主要针对首次
//		if(billDate>day)
//		{
//			//month=month+billIntervalMonth-1;
//			im=billIntervalMonth-1;
//		}
//		else
//		{
//			//month+=billIntervalMonth;
//			im=billIntervalMonth;
//			
//		}
		im=billIntervalMonth;
//		if(month>12)
//		{
//			int ym=(int)Math.floor(month/12);
//			
//			year+=ym;
//			
//			month=month-12*ym;
//		}
		
		Calendar cal = Calendar.getInstance();  
        cal.setTime(Util.str2Date(lastBillDate));  
        cal.add(Calendar.MONTH, im);  
		
		return Util.formatDate(cal.getTime());
		
		//return year+"-"+String.format("%02d", month)+"-"+String.format("%02d", billDate);
		
	}
	
	/**
	 * 取得最后一个应出帐日
	 * @param lastBillDate
	 * @param billDate
	 * @param billIntervalMonth
	 * @param today
	 * @return
	 */
	private String getLastCurrentBillDate(String lastBillDate,int billDate,int billIntervalMonth,String today)
	{
		String currBillDate="";
		
		do{
			currBillDate=lastBillDate;
			
			lastBillDate=this.generateCurrentBillDate(currBillDate, billDate, billIntervalMonth);			
			
		}
		while(Util.dateDiff(today,lastBillDate)>=0);
		
		return currBillDate;
	}
	
	
	
	
	
	
	
	

	/**
	 * 先设置各能源项，若能源上次结算日为空，则取首次录入日
	 * @param model
	 */
	/*private void setEnergyItemForBillModel(ContractEnergyBillModel model)
	{
		EnergyCost cost=null;
		
		//List<EnergyItemForBillModel> result=new ArrayList<EnergyItemForBillModel>();
		
		EnergyItemForBillModel e=null;
				
		if(model.getLastPowerFeeDate()==null)
		{
			cost=this.energyCostDao.getFirstBuildingEnergyCost(model.getRefBuildingId(), EnergyCost.EnergyType.POWER.value);
			
			if(cost!=null)
			{
				model.setLastPowerFeeDate(cost.getRecordDate());
			}
			
		}

		if(model.getLastPowerFeeDate()!=null)
		{
			e=new EnergyItemForBillModel();
			
			e.setEnergyType(EnergyCost.EnergyType.POWER.value);
			
			e.setBuildArea(Float.valueOf(model.getArea()));
			
			e.setFeeStartDate(Util.formatDate(model.getLastPowerFeeDate()));
			
			e.setPrice(model.getPowerPrice());
			
			e.setShareType(model.getShareType());
			
			model.getItemList().add(e);
		}
		
				
		if(model.getLastWaterFeeDate()==null)
		{
			cost=this.energyCostDao.getFirstBuildingEnergyCost(model.getRefBuildingId(), EnergyCost.EnergyType.WATER.value);
			
			if(cost!=null)
			{
				model.setLastWaterFeeDate(cost.getRecordDate());
			}
			
		}
		
		if(model.getLastWaterFeeDate()!=null)
		{		
			e=new EnergyItemForBillModel();
			
			e.setEnergyType(EnergyCost.EnergyType.WATER.value);
			
			e.setBuildArea(Float.valueOf(model.getArea()));
			
			e.setFeeStartDate(Util.formatDate(model.getLastWaterFeeDate()));
			
			e.setPrice(model.getWaterPrice());
			
			e.setShareType(model.getShareType());
			
			model.getItemList().add(e);
		}
		
		

		
		if(model.getLastGasFeeDate()==null)
		{
			cost=this.energyCostDao.getFirstBuildingEnergyCost(model.getRefBuildingId(), EnergyCost.EnergyType.GAS.value);
			
			if(cost!=null)
			{
				model.setLastGasFeeDate(cost.getRecordDate());
			}
			
		}
		if(model.getLastGasFeeDate()!=null)
		{
			e=new EnergyItemForBillModel();
			
			e.setEnergyType(EnergyCost.EnergyType.GAS.value);
			
			e.setBuildArea(Float.valueOf(model.getArea()));
			
			e.setFeeStartDate(Util.formatDate(model.getLastGasFeeDate()));
			
			e.setPrice(model.getGasPrice());
			
			e.setShareType(model.getShareType());
			
			model.getItemList().add(e);
		
		}
		
		
		if(model.getLastAcFeeDate()==null)
		{
			cost=this.energyCostDao.getFirstBuildingEnergyCost(model.getRefBuildingId(), EnergyCost.EnergyType.AC.value);
			
			if(cost!=null)
			{
				model.setLastAcFeeDate(cost.getRecordDate());
			}
			
		}

		if(model.getLastAcFeeDate()!=null)
		{
			e=new EnergyItemForBillModel();
			
			e.setEnergyType(EnergyCost.EnergyType.AC.value);
			
			e.setBuildArea(Float.valueOf(model.getArea()));
			
			e.setFeeStartDate(Util.formatDate(model.getLastAcFeeDate()));
			
			e.setPrice(model.getAcPrice());
			
			e.setShareType(model.getShareType());
			
			model.getItemList().add(e);
		}
		
		//return result;
	}*/
	
	//初始化楼的出帐结算日期
	/*private void initBuildingDate(EtopFloor building)
	{
		EnergyCost cost=null;
		//
		if(building.getEnergyPowerFeeDate()==null)
		{
			cost=this.energyCostDao.getFirstBuildingEnergyCost(building.getId(), EnergyCost.EnergyType.POWER.value);
			
			if(cost!=null)
			{
				building.setEnergyPowerFeeDate(cost.getRecordDate());
			}
			
		}
		if(building.getEnergyWaterFeeDate()==null)
		{

			cost=this.energyCostDao.getFirstBuildingEnergyCost(building.getId(), EnergyCost.EnergyType.WATER.value);
			
			if(cost!=null)
			{
				building.setEnergyWaterFeeDate(cost.getRecordDate());
			}
		}
		if(building.getEnergyGasFeeDate()==null)
		{

			cost=this.energyCostDao.getFirstBuildingEnergyCost(building.getId(), EnergyCost.EnergyType.GAS.value);
			
			if(cost!=null)
			{
				building.setEnergyGasFeeDate(cost.getRecordDate());
			}
			
		}
		if(building.getEnergyAcFeeDate()==null)
		{
			cost=this.energyCostDao.getFirstBuildingEnergyCost(building.getId(), EnergyCost.EnergyType.AC.value);
		
			if(cost!=null)
			{
				building.setEnergyAcFeeDate(cost.getRecordDate());
			}
			
		}
		
		//设置上个出帐日
		if(building.getEnergyLastBillDate()==null)
		{
			
		}
		
	}
	*/
	
//	@Override
//	public List<ContractEnergyBillModel> getContractEnergyBillModelsForBill(String today,
//			String refBuildingId) {
//		
//		return this.contractEnergyDao.getContractEnergyBillModelByBuilding(today, refBuildingId);
//	}

	@Override
	public List<EnergySupplyModel> getSupplyContractEnergyList(
			String refBuildingId, String currRecordDate, String lastRecordDate) {
		
		return this.contractEnergyDao.getSupplyContractEnergyList(refBuildingId, currRecordDate, lastRecordDate);
	}

	@Override
	public List<String> checkEnergyRecord(ContractEnergy contractEnergy) {
		
		//List<String> result=new ArrayList<String>();
		
		Contract c=this.contractDao.getContractById(contractEnergy.getRefContractId());
		
	
		List<EnergyCost> ecs=new ArrayList<EnergyCost>();
		
		
		EnergyCost ec=new EnergyCost();
		
		ec.setRefItemId(c.getRefRoomId());
		
		ec.setEnergyType(EnergyCost.EnergyType.POWER.value);
		
		ec.setRecord(contractEnergy.getPowerRecord());
		
		ec.setRecordDate(contractEnergy.getPowerRecordDate());
		
		ecs.add(ec);
		
		ec=new EnergyCost();
		
		ec.setRefItemId(c.getRefRoomId());
		
		ec.setEnergyType(EnergyCost.EnergyType.WATER.value);
		
		ec.setRecord(contractEnergy.getWaterRecord());
		
		ec.setRecordDate(contractEnergy.getWaterRecordDate());
		
		ecs.add(ec);
		
		
		ec=new EnergyCost();
		
		ec.setRefItemId(c.getRefRoomId());
		
		ec.setEnergyType(EnergyCost.EnergyType.GAS.value);
		
		ec.setRecord(contractEnergy.getGasRecord());
		
		ec.setRecordDate(contractEnergy.getGasRecordDate());
		
		ecs.add(ec);
		
		
		ec=new EnergyCost();
		
		ec.setRefItemId(c.getRefRoomId());
		
		ec.setEnergyType(EnergyCost.EnergyType.AC.value);
		
		ec.setRecord(contractEnergy.getAcRecord());
		
		ec.setRecordDate(contractEnergy.getAcRecordDate());
		
		ecs.add(ec);
		
		
		List<String> cr=this.energyCostService.checkEnergyCost(ecs);
		
		
		return cr;
	}

	





}
