package com.etop.management.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.etop.management.bean.PageEnergyBill;
import com.etop.management.bean.Park;
import com.etop.management.bean.Role;
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
import com.etop.management.model.EnergyModel;
import com.etop.management.model.EnergyRecordModel;
import com.etop.management.service.EnergyCostService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.service.RemindService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.Util;
import com.google.gson.Gson;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EnergyCostServiceImpl implements EnergyCostService {

	@Resource
	EnergyCostDao energyCostDao;
	
	@Resource
	EtopFloorDao etopFloorDao;
	
	@Resource
	EtopFloorRoomDao etopFloorRoomDao;
	
	@Resource
	ContractDao contractDao;
	
	@Resource
	ParkDao parkDao;
	
	@Resource
	ContractEnergyDao contractEnergyDao;
	
	@Resource
	EtopSequenceService etopSequenceService;
	
	@Resource
	EtopThresholdService etopThresholdService;
	
	@Resource
	EtopBillDao etopBillDao;
	
	@Resource
	RemindService remindService;
	
	@Override
	public List<EnergyModel> getRoomEditList(String refBlockId,int energyType,String recordDate) {
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("refBlockId", refBlockId);
		
		map.put("energyType", energyType);
		
		map.put("today", recordDate);
		
		return this.energyCostDao.getRoomEditList(map);
	}
	
	
	@Override
	public List<EnergyModel> getRoomEditListByRecordDate(String refBuildingId,String refFloorId,String refBlockId,
			int energyType, String lastRecordDate, String currentRecordDate) {
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		map.put("refBuildingId", refBuildingId);
		
		map.put("refFloorId", refFloorId);
		
		map.put("refBlockId", refBlockId);
		
		map.put("energyType", energyType);
		
		map.put("recordDate", currentRecordDate);
		
		map.put("currentRecordDate", currentRecordDate);
		
		//map.put("isBilled",0);		
		
		List<EnergyModel> result=this.energyCostDao.getRoomEditListByRecordDate(map);
		
		if(result==null || result.size()==0)
		{
			return null;
		}
		
		List<String> refRoomIds=new ArrayList<String>();
		
		for(EnergyModel em:result)
		{
			refRoomIds.add(em.getRefItemId());
		}
		
		
		map=new HashMap<String,Object>();
		
//		map.put("refBlockId", refBlockId);
		
		map.put("energyType", energyType);
		
		map.put("recordDate", lastRecordDate);
		
//		map.put("currentRecordDate", currentRecordDate);
		
		map.put("refRoomIds", refRoomIds);
		
		map.put("isBilled",1);	
				
		List<EnergyModel> lastList=this.energyCostDao.getRoomLastEditListByRecordDate(map);
		
		if(lastList==null || lastList.size()==0)
		{
			return null;
		}
		
		if(result.size()!=lastList.size())
		{
			return null;
		}
		
		
		for(int i=0;i<result.size();i++)
		{
			result.get(i).setLastRecord(lastList.get(i).getRecord());
			
			result.get(i).setLastRecordDate(lastList.get(i).getRecordDate());
		}
		
		
		
		return result;
	}


	@Override
	public List<EnergyModel> getBuildingEditList(String refParkId,int energyType) {
		
		return this.energyCostDao.getBuildingEditList(refParkId, energyType);
	}

//	@Override
//	public int createEnergyCost(EnergyCost entity) {
//		
//		return this.energyCostDao.createEnergyCost(entity);
//	}
	
	/**
	 * 设置自身与后一条记录的使用量，天数，日均
	 * @param op
	 * @param energyCost
	 * @param energyCategory
	 * @param energyType
	 */
	private void setEnergyCostAmounts(OpInfoBean op,EnergyCost energyCost,int energyCategory,int energyType)
	{
		double currAmount=0;
		
		int currDays=0;
		
		double currDailyAmount=0;
		
		double nextAmount=0;
		
		int nextDays=0;
		
		double nextDailyAmount=0;
		
		//先取回前后数据
		List<EnergyModel> models=this.energyCostDao.getEnergyModelsByRecordDate(energyCost.getRefItemId(),energyCategory, energyType, Util.formatDate(energyCost.getRecordDate()));
		
		for(EnergyModel em:models)
		{
			//前一个，根据前一个算出本次。。。
			if(em.getRecordDate().getTime() < energyCost.getRecordDate().getTime())
			{
				currAmount=energyCost.getRecord()-em.getRecord();
				
				currDays=Util.dateDiff(Util.formatDate(energyCost.getRecordDate()), Util.formatDate(em.getRecordDate()));
				
				currDailyAmount=currAmount/currDays;
				
				energyCost.setAmount(currAmount);
				
				energyCost.setDays(currDays);
				
				energyCost.setDailyAmount(currDailyAmount);
				
			}
			else//后一个，直接更新
			{
				nextAmount=em.getRecord()-energyCost.getRecord();
				
				nextDays=Util.dateDiff(Util.formatDate(em.getRecordDate()), Util.formatDate(energyCost.getRecordDate()));
				
				nextDailyAmount=nextAmount/nextDays;
				
				Criteria nc=new Criteria();
				
				nc.put("id", em.getId());
				
				EnergyCost n=this.energyCostDao.getEnergyCost(nc);
				
				if(nc!=null)
				{
					n.setAmount(nextAmount);
					
					n.setDays(nextDays);
					
					n.setDailyAmount(nextDailyAmount);
					
					n.setUpdatedAt(new Date());
					
					n.setUpdatedBy(op.getOper());
					
					this.energyCostDao.updateEnergyCost(n);
				}
				
			}
		}	
	}

	@Override
	public int createEnergyCostList(OpInfoBean op, List<EnergyCost> records,
			int energyCategory, int energyType) {

		int result=0;
		
		LoggerUtil.info(String.format("[createEnergyCostList]starting(%d)....",records.size() ));
		
		for(EnergyCost r:records){
			
			//check entity by refItemId,recordDate,energyType,energyCategory first
			
			Criteria criteria=new Criteria();
			
			criteria.put("energyCategory", energyCategory);
			
			criteria.put("energyType", energyType);
			
			criteria.put("refItemId", r.getRefItemId());
			
			criteria.put("recordDate", r.getRecordDate());	
			
			criteria.put("isBilled", r.getIsBilled());
			
			EnergyCost entity=this.energyCostDao.getEnergyCost(criteria);
			
			this.setEnergyCostAmounts(op, r, energyCategory, energyType);	
			
			
			//存在，且isBilled=0
			if(entity!=null)
			{
				if(entity.getIsBilled()==0)
				{
					entity.setAmount(r.getAmount());
					
					entity.setDays(r.getDays());
					
					entity.setDailyAmount(r.getDailyAmount());
					
					entity.setRecord(r.getRecord());
				
					entity.setUpdatedAt(new Date());
				
					entity.setUpdatedBy(op.getOper());
				
					result+=this.energyCostDao.updateEnergyCost(entity);
					
					LoggerUtil.info(String.format("[createEnergyCostList]update entity:%s",entity.toString() ));
					
				}
			}
			else
			{
				//为空时，需要先判断是否isBilled，是的话(即此房间是新录入房间)，需要先取出此房间的最后已结算读数，跟此次去比较，如果有差值,出帐。
				//此时，并不需要去更新energyCost.isBilled，都为1
				//注：能源记录创建列表中的能源，若是已出帐能源，并不会放进列表中，所以直接下面判断即可。
				if(r.getIsBilled()==1 && r.getEnergyCategory()==EnergyCost.EnergyCategory.ROOM.value)
				{
					EnergyCost lastEnergy=this.energyCostDao.getLastBilledRecordOfRoom(r.getRefItemId(), energyType);
					
					
					if(lastEnergy!=null && r.getRecord()>lastEnergy.getRecord())
					{
						
						List<Contract> contracts=this.contractDao.getActiveEnergyContractsByRoom(r.getRefItemId(), Util.formatDate(r.getRecordDate()));
						
						
						for(Contract contract:contracts)
						{
						
//						 contract=this.contractDao.getLastActiveContractByRoom(r.getRefItemId(), BillSource.ENERGY_CONTRACT.value);
						//录入读数日期>合同开始日期，即最后结算读数为合同开始读数
							//最后结算读数日期＝合同开始日期2017.2.23
						if(contract!=null						
								&& Util.dateDiff(Util.formatDate(lastEnergy.getRecordDate()), Util.formatDate(contract.getContractStartDate()))==0)
						{
							ContractEnergy contractEnergy=this.contractEnergyDao.getContractEnergyByRefContractId(contract.getId());
							
							EtopFloor building=this.etopFloorDao.queryById(lastEnergy.getRefBuildingId());
							
							//TODO:
							Park park=this.parkDao.getParkInfo(contract.getRefParkId());
																					
							if(contractEnergy!=null && park!=null && building!=null)
							{
								//生成帐单
																
								EtopBill etopBill=new EtopBill();
								
								etopBill.setParkId(contract.getRefParkId());
								
								etopBill.setCompanyId(contract.getRefCompanyId());
								
								etopBill.setBillId(etopSequenceService.getFormatId(park.getParkCode(), "zd"));
								
								etopBill.setBillSource(contract.getContractCategory());
								
								etopBill.setBillNoOut(contract.getContractNo());
								
								etopBill.setStartTime(lastEnergy.getRecordDate());
								
								etopBill.setEndTime(Util.str2Date(Util.increaseDate(Util.formatDate(r.getRecordDate()),-1)));//r.getRecordDate()
								
								double amount=(r.getRecord()-lastEnergy.getRecord())*contractEnergy.getPrice(energyType);
								
								etopBill.setAmount(new BigDecimal(amount));
								
								Date deadLine=DateUtil.toTodayNight(Util.str2Date(Util.increaseDate(Util.formatDate(new Date()), building.getEnergyPaymentDay())));
								
								etopBill.setDeadline(deadLine);
								
								etopBill.setBillType(EtopBill.BillType.INCOME.value);
								
								etopBill.setBillStatus(EtopBill.BillStatus.UNPAID.value);
								
								etopBill.setAuditStatus(EtopBill.AuditStatus.UNCHECK.value);
								
								etopBill.setAuditLevel(2);
								
								etopBill.setCompanyName(contract.getCompanyName());
								
								
								if(BillSource.SUBCONTRACT_CONTRACT.value==contract.getContractCategory())
								{
									etopBill.setBillType(EtopBill.BillType.OUTLAY.value);
								}
								
								String desc=String.format("能源费用帐单明细：楼：%s,房间：%s,计费开始日:%s,结束日:%s:\r\n",
										
										contract.getBuilding(),contract.getRoom(),

										Util.formatDate(lastEnergy.getRecordDate()),
										Util.increaseDate(Util.formatDate(r.getRecordDate()),-1));
								
								desc+=String.format("%s:单价:%.2f,使用量:%.2f,总额：%.2f", 
										EnergyCost.EnergyType.valueOf((energyType)).desc,
										contractEnergy.getPrice(energyType),
										r.getRecord()-lastEnergy.getRecord(),
										amount
										
										);
								
								etopBill.setDescription(desc);		
								
								etopBill.setOverdueRate(new BigDecimal(this.etopThresholdService.getValue(contract.getRefParkId(), "overdue_rate")));
							
								
								etopBill.setCreatedTime(new Date());
								etopBill.setAmountPaid(new BigDecimal(0));
								etopBill.setAccountRemission(new BigDecimal(0));
								etopBill.setOverdueFine(new BigDecimal(0));
								etopBill.setOverdueRemission(new BigDecimal(0));

								int cc=this.etopBillDao.add(etopBill);
								
								Gson gson = new Gson();
								
								String billStr=gson.toJson(etopBill);
								
								LoggerUtil.info(String.format("[createEnergyCostList]create bill, and remind:%s",billStr ));
								
								//reminder
								
								String content=String.format("园区(%s)中公司(%s)能源(%s)已出帐，请审核。",park.getParkName(),contract.getCompanyName(),EnergyCost.EnergyType.valueOf(energyType).desc);
								
								this.remindService.remind(park.getId(), Role.QX_YZSP, "能源出帐", content);
								
							}
						}
						}
					}
					
					
				}
				
				
				
				r.setId(UUID.randomUUID().toString());
				
//				r.setAmount(currAmount);
//				
//				r.setDays(currDays);
//				
//				r.setDailyAmount(currDailyAmount);				
				
				r.setCreatedAt(new Date());
				
				r.setCreatedBy(op.getOper());
				
				r.setEnergyCategory(energyCategory);
				
				r.setRecordDate(r.getRecordDate());
				
				r.setEnergyType(energyType);
				
				r.setUpdatedAt(new Date());
				
				r.setUpdatedBy(op.getOper());
				
				result+=this.energyCostDao.createEnergyCost(r);
				
				LoggerUtil.info(String.format("[createEnergyCostList]create entity:%s",r.toString() ));
				
				//楼且isbilled，更新楼lastFeeDate
				if(energyCategory==EnergyCost.EnergyCategory.BUILDING.value && r.getIsBilled()==1)
				{
					
					
					EtopFloor building=this.etopFloorDao.queryById(r.getRefItemId());
					
					//楼最后结算日期为空，或者小于此录入日期，更新之
					if(building!=null)
					{
						if(building.getEnergyLastFeeDate()==null || building.getEnergyLastFeeDate().getTime()<r.getRecordDate().getTime())
						{
							building.setEnergyLastFeeDate(r.getRecordDate());
							
							this.etopFloorDao.update(building);
							
							LoggerUtil.info(String.format("[createEnergyCostList]update building[%s,%s]'s last fee date to %s.",building.getId(),building.getBuildName(),Util.formatDate(r.getRecordDate()) ));
						}
					}
					
					
					//this.energyCostDao.updateFloorEnergyFeeDate(r.getRefItemId(), energyType, Util.formatDate(r.getRecordDate()));
				}
				
				
			}
			
		};
			
		
		LoggerUtil.info(String.format("[createEnergyCostList]finished. result:%d",result ));
		
		return result;
	}

	@Override
	public int generateBillForContractEnergy(ContractEnergyBillModel model) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*@Override
	public int generateEnergyShare(String refParkId, String recordDate) {

		int result=0;
		
		Criteria criteria=new Criteria();
		
		criteria.put("buildType", "floor");
		
		criteria.put("parkId", refParkId);
		
		criteria.put("status", 1);
		
		List<EtopFloor> buildingList=this.etopFloorDao.queryByList(criteria);
		
		for(EtopFloor building : buildingList)
		{
			result+=this.generateShareByBuilding(building,recordDate);
		}
		
		
		
		
		return result;
	}*/
	
	/**
	 * 生成此楼的能源公摊
	 * @param building
	 * @param recordDate
	 * @return
	 */
	/*private int generateShareByBuilding(EtopFloor building,String recordDate)
	{
		int result=0;
		
		//List<ContractEnergyBillModel> list=this.getContractEnergyBillModelsForBill(building.getId());
		
		Criteria criteria=new Criteria();
		
		criteria.put("refFloorId", building.getId());
		
		criteria.put("floorStatus", 1);
		
		List<EtopFloorRoom> rooms=this.etopFloorRoomDao.queryByList(criteria);
		
		//公摊方式
		int shareType=building.getSourceCharge();
				
		//已录入房间总面积 
		float areaSum=0;
		
		// 按面积
		if(shareType==1)
		{
			//已租用户总面积
			//areaSum=this.etopFloorRoomDao.getSumOfBuildArea(building.getId());
			
			for(EtopFloorRoom r:rooms)
			{
				areaSum+=Float.valueOf(String.valueOf(r.getBuildArea()));
			}
			
		}
		
		//已录入房间总用量
		float rPowerSum=this.energyCostDao.getRoomEnergySumByRecordDateAndType(building.getId(), recordDate, 0);
		
		float rWaterSum=this.energyCostDao.getRoomEnergySumByRecordDateAndType(building.getId(), recordDate, 1);
				
		float rGasSum=this.energyCostDao.getRoomEnergySumByRecordDateAndType(building.getId(), recordDate, 2);
		
		float rAcSum=this.energyCostDao.getRoomEnergySumByRecordDateAndType(building.getId(), recordDate, 3);
		
		//楼能源使用量
		float bPowerRecord=this.getAmount(building.getId(), recordDate, 0);
		
		float bWaterRecord=this.getAmount(building.getId(), recordDate, 1);
		
		float bGasRecord=this.getAmount(building.getId(), recordDate, 2);
		
		float bAcRecord=this.getAmount(building.getId(), recordDate, 3);
		
		//公摊量
		float sharePower=bPowerRecord-rPowerSum;
		
		float shareWater=bWaterRecord-rWaterSum;
		
		float shareGas=bGasRecord-rGasSum;
		
		float shareAc=bAcRecord-rAcSum;
		
				
		
		//for(ContractEnergyBillModel model : list)
		for(EtopFloorRoom room :rooms)
		{
			
			ContractEnergyBillModel model=new ContractEnergyBillModel();
			
			//by area
			if(shareType==1 && areaSum!=0)
			{
				//面积公摊率
				float shareRate=Float.valueOf(String.valueOf(room.getBuildArea()))/areaSum;
				
				if(sharePower>0)
				{
					model.setPowerShareAmount(sharePower*shareRate);
				}
				
				if(shareWater>0)
				{
					model.setWaterShareAmount(shareWater*shareRate);
				}
				
				if(shareGas>0)
				{
					model.setGasShareAmount(shareGas*shareRate);
				}
				
				if(shareAc>0)
				{
					model.setAcShareAmount(shareAc*shareRate);
				}
				
			}
			//by records
			if(shareType==2)
			{	
				EnergyCost ec=null;
				
				if(sharePower>0 && rPowerSum!=0)
				{
					ec=this.getEnergyCost(room.getId(), recordDate, 0);
					
					if(ec!=null)
					{
						model.setPowerShareAmount(sharePower*ec.getAmount()/rPowerSum);
					}
				}
				
				if(shareWater>0 && rWaterSum!=0)
				{
					ec=this.getEnergyCost(room.getId(), recordDate, 1);
					
					if(ec!=null)
					{
						model.setWaterShareAmount(shareWater*ec.getAmount()/rWaterSum);
					}
				}
				
				if(shareGas>0 && rGasSum!=0)
				{
					ec=this.getEnergyCost(room.getId(), recordDate, 2);
					
					if(ec!=null)
					{
						model.setGasShareAmount(shareGas*ec.getAmount()/rGasSum);
					}
				}
				
				if(shareAc>0 && rAcSum!=0)
				{
					ec=this.getEnergyCost(room.getId(), recordDate, 3);
					
					if(ec!=null)
					{
						model.setAcShareAmount(shareAc*ec.getAmount()/rAcSum);
					}
				}
			}
			
			//update energyCost
			
			EnergyCost ec=this.getEnergyCost(room.getId(), recordDate, 0);
			
			if(ec!=null)
			{
				ec.setShareAmount(model.getPowerShareAmount());
				
				this.energyCostDao.updateEnergyCost(ec);
			}
			
			ec=this.getEnergyCost(room.getId(), recordDate, 1);
			
			if(ec!=null)
			{
				ec.setShareAmount(model.getWaterShareAmount());
				
				this.energyCostDao.updateEnergyCost(ec);
			}
			
			ec=this.getEnergyCost(room.getId(), recordDate, 2);
			
			if(ec!=null)
			{
				ec.setShareAmount(model.getGasShareAmount());
				
				this.energyCostDao.updateEnergyCost(ec);
			}
			
			ec=this.getEnergyCost(room.getId(), recordDate, 3);
			
			if(ec!=null)
			{
				ec.setShareAmount(model.getAcShareAmount());
				
				this.energyCostDao.updateEnergyCost(ec);
			}
			
			
			
		}
		
		
		
		return result;
	}*/
	
	
	/**
	 * 取使用量
	 * @param itemId
	 * @param recordDate
	 * @param energyType
	 * @return
	 */
	private double getAmount(String itemId,String recordDate,int energyType)
	{
//		Criteria c=new Criteria();
//		
//		c.put("recordDate", recordDate);
//		
//		c.put("refItemId", itemId);
//		
//		c.put("energyType", energyType);
		
		EnergyCost b=this.getEnergyCost(itemId,recordDate,energyType);
		
		if(b!=null)
		{
			return b.getAmount();
		}
		else
		{
			return 0;
		}
		
	}
	
	private EnergyCost getEnergyCost(String itemId,String recordDate,int energyType)
	{
		Criteria c=new Criteria();
		
		c.put("recordDate", recordDate);
		
		c.put("refItemId", itemId);
		
		c.put("energyType", energyType);
		
		EnergyCost b=this.energyCostDao.getEnergyCost(c);
		
		return b;
	}

	@Override
	public List<EnergyRecordModel> getEnergyListByRoom(PageEnergyBill page) {
		
		return this.energyCostDao.getEnergyListByRoom(page);
	}

	@Override
	public int getEnergyCountByRoom(PageEnergyBill page) {
		
		return this.energyCostDao.getEnergyCountByRoom(page);
	}

	@Override
	public EnergyCost getBuildingEnergyCost(String refBuildingId,
			String recordDate, int energyType) {
		// TODO Auto-generated method stub
		
		Criteria criteria=new Criteria();
		
		criteria.put("refItemId", refBuildingId);
		
		criteria.put("recordDate", recordDate);
		
		criteria.put("energyCategory", 1);
		
		criteria.put("energyType", energyType);
		
		return this.energyCostDao.getEnergyCost(criteria);
	}

	@Override
	public int getRoomEnergyCostCountByBuilding(String refBuildingId,
			String recordDate, int energyType) {
		
		return this.energyCostDao.getRoomEnergyCountByBuildingAndDateAndType(refBuildingId, recordDate, energyType);
	}

	@Override
	public EnergyCost getBuildingLastEnergyCost(String refBuildingId,
			String lastFeeDate, int energyType) {
		
		return this.energyCostDao.getBuildingLastEnergyCost(refBuildingId, lastFeeDate, energyType);
	}

	@Override
	public EnergyCost getLastEnergyCost(String id, int energyCategory,
			int energyType) {
		// TODO Auto-generated method stub
		return this.energyCostDao.getLastEnitty(id, energyCategory, energyType);
	}

	@Override
	public EnergyCost getFirstRecordOfRoomAfterStartDate(String refRoomId,
			int energyType, String date) {
		
		return this.energyCostDao.getFirstRecordOfRoomAfterStartDate(refRoomId, energyType, date);
	}


	@Override
	public BuildingEnergyModel getBuildingEnergy(String refBuildingId,
			int energyType) {
		
		return this.energyCostDao.getFloorEnergy(refBuildingId, energyType);
	}


	@Override
	public double getEnergyCostSumByRecordDate(String refItemId,
			int energyCategory, int energyType, String recordDate) {
		
		return this.energyCostDao.getEnergyCostSumByRecordDate(refItemId,energyCategory,energyType,recordDate);
	}


	@Override
	public EnergyCost getValue(String refItemId, String recordDate,
			int energyType, int energyCategory,int isBilled) {
		
		Criteria criteria=new Criteria();
		
		criteria.put("refItemId", refItemId);
		
		criteria.put("recordDate", recordDate);
		
		criteria.put("energyType", energyType);
		
		criteria.put("energyCategory", energyCategory);
		
		if(isBilled>=0)
		{
			criteria.put("isBilled", isBilled);
		}
		return this.energyCostDao.getEnergyCost(criteria);
	}

	@Override
	public EnergyCost getValueById(String id) {

		Criteria criteria=new Criteria();
		
		criteria.put("id", id);
		
		return this.energyCostDao.getEnergyCost(criteria);
	}

	

	@Override
	public List<BuildingEnergyModel> getBuildingEnergyByRoom(String refRoomId) {
		
		return this.energyCostDao.getBuildingEnergyByRoom(refRoomId);
	}


	@Override
	public List<String> checkEnergyCost(List<EnergyCost> records) {
		
		List<String> result=new ArrayList<String>();
		
		for(EnergyCost ec:records)
		{
			List<EnergyModel> ems=this.energyCostDao.getEnergyModelsByRecordDate(ec.getRefItemId(),ec.getEnergyCategory(), ec.getEnergyType(), Util.formatDate(ec.getRecordDate()));
			
			for(EnergyModel em:ems)
			{
				//比前一个大
				if(em.getRecordDate().getTime() < ec.getRecordDate().getTime())
				{
					if(ec.getRecord()< em.getRecord())
					{
						result.add(String.format("%s(%s)%s读数小于前一条(日期:%s,读数:%.2f)",EnergyCost.EnergyCategory.valueOf(ec.getEnergyCategory()).desc,em.getItemName()==null?"":em.getItemName(),EnergyCost.EnergyType.valueOf((em.getEnergyType())).desc,Util.formatDate(em.getRecordDate()) ,em.getRecord()));
					}
				}
				else//比后一个小
				{
					if(ec.getRecord()> em.getRecord())
					{
						result.add(String.format("%s(%s)%s读数大于后一条(日期:%s,读数:%.2f)",EnergyCost.EnergyCategory.valueOf(ec.getEnergyCategory()).desc,em.getItemName()==null?"":em.getItemName(),EnergyCost.EnergyType.valueOf((em.getEnergyType())).desc,Util.formatDate(em.getRecordDate()) ,em.getRecord() ));
					}
				}
			}
			
		}
				
		return result;
	}


	@Override
	public int updateEnergyCostList(OpInfoBean op, List<EnergyCost> records) {
		
		int result=0;
		
		LoggerUtil.info(String.format("[updateEnergyCostList]starting(%d)....",records.size() ));
		for(EnergyCost ec:records)
		{
			this.setEnergyCostAmounts(op, ec, ec.getEnergyCategory(), ec.getEnergyType());
			
			ec.setUpdatedAt(new Date());
			
			ec.setUpdatedBy(op.getOper());			
			
			result+=this.energyCostDao.updateEnergyCost(ec);
			
			LoggerUtil.info(String.format("[updateEnergyCostList]update entity:%s",ec.toString()));
		}
		
		LoggerUtil.info(String.format("[updateEnergyCostList]finished.result:%d",result ));
		
		return result;
	}


	@Override
	public List<Map<String,String>> getEnergySummaryByRoom(PageEnergyBill page) {
		
		return this.energyCostDao.getEnergySummaryByRoom(page);
	}



	@Override
	public List<EtopFloor> getNeedEnterEnergyFloors(String refParkId,
			String today) {
		// TODO Auto-generated method stub
		
		//String[] edArr=today.split("-");
				
		//按月录入日
		//int eDay=Integer.valueOf(edArr[2]);
		
		
		Calendar now = Calendar.getInstance();  
		
		now.setTime(Util.str2Date(today));
		
		int eDay=now.get(Calendar.DATE);
		
		
	    int eWeek = Util.getWeekDay(Util.str2Date(today));
		
		
		return this.energyCostDao.getNeedEnterEnergyFloors(refParkId, eDay, eWeek);
	}


}
