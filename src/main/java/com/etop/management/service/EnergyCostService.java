package com.etop.management.service;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.EnergyCost;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.bean.PageEnergyBill;
import com.etop.management.entity.EtopFloor;
import com.etop.management.model.BuildingEnergyModel;
import com.etop.management.model.ContractEnergyBillModel;
import com.etop.management.model.EnergyModel;
import com.etop.management.model.EnergyRecordModel;

public interface EnergyCostService {

	
	EnergyCost getValue(String refItemId,String recordDate,int energyType,int energyCategory,int isBilled);
	
	EnergyCost getValueById(String id);
	
	List<EnergyModel> getRoomEditList(String refBlockId,int energyType,String recordDate);
	
	/**
	 * 分别取出此次与上次结算列表，进行拼接
	 * @param refBlockId
	 * @param energyType
	 * @param lastRecordDate
	 * @param currentRecordDate
	 * 
	 * @return
	 */
	List<EnergyModel> getRoomEditListByRecordDate(String refBuildingId,String refFloorId,String refBlockId,int energyType,String lastRecordDate,String currentRecordDate);
	
	List<EnergyModel> getBuildingEditList(String refParkId,int energyType);
	
	//int createEnergyCost(EnergyCost entity);
	
	/**
	 * 创建能源记录
	 * @param op
	 * @param records
	 * @param energyCategory
	 * @param energyType
	 * @return
	 */
	int createEnergyCostList(OpInfoBean op,List<EnergyCost> records,int energyCategory,int energyType);

	
	int updateEnergyCostList(OpInfoBean op,List<EnergyCost> records);
	
	/**
	 * 检测能源录入数据，大于前一次，且小于后一次
	 * @param records
	 * @return 不合法数据信息，包括房间等
	 */
	List<String> checkEnergyCost(List<EnergyCost> records);
	
	int generateBillForContractEnergy(ContractEnergyBillModel model);
	
	/**
	 * 取楼能源信息，公摊，结算日等
	 * @param refBuildingId
	 * @param energyType
	 * @return
	 */
	BuildingEnergyModel getBuildingEnergy(String refBuildingId,int energyType);
	
	List<BuildingEnergyModel> getBuildingEnergyByRoom(String refRoomId);
	
	/**
	 * 录入完成后，手动生成园区公摊
	 * @param refParkId
	 * @param recordDate
	 * @return
	 */
	//int generateEnergyShare(String refParkId,String recordDate);
		
	/**
	 * 取房间所有能源记录
	 * @param page
	 * @return
	 */
	List<EnergyRecordModel> getEnergyListByRoom(PageEnergyBill page);
	
	int  getEnergyCountByRoom(PageEnergyBill page);
	
	
	List<Map<String,String>> getEnergySummaryByRoom(PageEnergyBill page);
	
	/**
	 * 取楼读数
	 * @param refBuildingId
	 * @param recordDate
	 * @return
	 */
	EnergyCost getBuildingEnergyCost(String refBuildingId,String recordDate,int energyType);
	
	/**
	 * 取楼最后一次能源录入
	 * @param refBuildingId
	 * @param lastFeeDate 应该在最后一次结算日后
	 * @param energyType
	 * @return
	 */
	EnergyCost getBuildingLastEnergyCost(String refBuildingId,String lastFeeDate,int energyType);
	
	EnergyCost getLastEnergyCost(String id,int energyCategory,int energyType);
	
	/**
	 * 取得此房间此日期之后的一次抄表，主要用于终止时，必须要先抄表
	 * @param refRoomId
	 * @param energyType
	 * @param date
	 * @return
	 */
	EnergyCost getFirstRecordOfRoomAfterStartDate(String refRoomId,int energyType,String date);
	
	/**
	 * 取该楼已录入数量
	 * @param refBuildingId
	 * @param recordDate
	 * @return
	 */
	int getRoomEnergyCostCountByBuilding(String refBuildingId,String recordDate,int energyType);
	
	/**
	 * 根据录入日取录入数量之和
	 * @param refItemId
	 * @param energyCategory
	 * @param energyType
	 * @param recordDate
	 * @return
	 */
	double getEnergyCostSumByRecordDate(String refItemId,int energyCategory,int energyType,String recordDate);
	
	
	/**
	 * 猎取园区中该日应能源录入楼列表
	 * @param refParkId
	 * @param today
	 * @return
	 */
	List<EtopFloor> getNeedEnterEnergyFloors(String refParkId,String today);
	
	
}
