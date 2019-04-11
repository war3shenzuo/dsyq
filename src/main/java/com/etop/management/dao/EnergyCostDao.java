package com.etop.management.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.Criteria;
import com.etop.management.bean.EnergyCost;
import com.etop.management.bean.PageEnergyBill;
import com.etop.management.entity.EtopFloor;
import com.etop.management.model.BuildingEnergyModel;
import com.etop.management.model.EnergyModel;
import com.etop.management.model.EnergyRecordModel;

public interface EnergyCostDao {

	/**
	 * 取回能源录入已出租房间列表<br>
	 * parkId,energyType,floor_status=1,today
	 * @param map
	 * @return
	 */
	List<EnergyModel> getRoomEditList(Map<String,Object> map);
	
	/**
	 * 取回能源录入列表
	 * @param map:
	 * refBlockId,recordDate(录入日期，可配合isBilled取出上次已结算的列表),
	 * isBilled,currentRecordDate(当前录入日期，用于房间创建时间判断，取已出帐的记录时不需要),energyType
	 * @return
	 */
	List<EnergyModel> getRoomEditListByRecordDate(Map<String,Object> map);
	
	
	/**
	 * 根据此次录入的房间，取出上次已结算读数
	 * @param map
	 * @return
	 */
	List<EnergyModel> getRoomLastEditListByRecordDate(Map<String,Object> map);
	
	
	/**
	 * 取楼的能源信息
	 * @param map,energyType,buidingId
	 * @return
	 */
	BuildingEnergyModel getFloorEnergy(@Param("refBuildingId") String refBuildingId,@Param("energyType")int energyType);
	
	/**
	 * 根据房间取楼能源信息列表
	 * @param refRoomId
	 * @return
	 */
	List<BuildingEnergyModel> getBuildingEnergyByRoom(@Param("refRoomId") String refRoomId);
	
	
	/**
	 * 更新楼能源,能源结算日
	 * @param entity
	 */
	void updateFloorEnergyFeeDate(@Param("refBuildingId") String refBuildingId,@Param("energyType")int energyType,@Param("feeDate")String feeDate);
	
	
	/**
	 * 取回园中能源录入楼列表
	 * @param refParkId
	 * @param energyType
	 * @return
	 */
	List<EnergyModel> getBuildingEditList(@Param("refParkId") String refParkId,@Param("energyType")int energyType);
	
	int createEnergyCost(EnergyCost entity);
	
	/**
	 * 根据日期取回该房间前后记录
	 * @param refItemId
	 * @param energyType
	 * @param recordDate
	 * @return
	 */
	List<EnergyModel> getEnergyModelsByRecordDate(@Param("refItemId") String refItemId,@Param("energyCategory")int energyCategory,@Param("energyType")int energyType,@Param("recordDate") String recordDate);
	
	/**
	 * 只更新公摊值 
	 * @param entity
	 * @return
	 */
	int updateEnergyCost(EnergyCost entity);
	
	/**
	 * 更新billid，及isbilled
	 * @param refRoomId
	 * @param feeStartDate
	 * @param feeEndDate
	 * @param refBillId
	 * @return
	 */
	int updateEnergyCostBillId(@Param("refRoomId")String refRoomId,@Param("feeStartDate")String feeStartDate,@Param("feeEndDate")String feeEndDate,@Param("refBillId")String refBillId,@Param("energyType") int energyType);
	/**
	 * 统一设置楼记录出帐状态
	 * @param refBuildingId
	 * @param feeStartDate
	 * @param feeEndDate
	 * @return
	 */
	int updateBuildingEnergyCostIsBilled(@Param("refBuildingId")String refBuildingId,@Param("feeStartDate")String feeStartDate,@Param("feeEndDate")String feeEndDate,@Param("isBilled") int isBilled);
	
	EnergyCost getEnergyCost(Criteria criteria);
	
	/**
	 * 楼中所有房间总用量
	 * refBuildingId,recordDate,energyType
	 * @return
	 */
	float getRoomEnergySumByRecordDateAndType(@Param("refBuildingId")String refBuildingId,@Param("recordDate")String recordDate,@Param("energyType")int energyType);
	
	float getRoomsMaxEnergySumByDateAndType(@Param("refBuildingId")String refBuildingId,@Param("feeDateStart")String feeDateStart,@Param("feeDateEnd")String feeDateEnd,@Param("energyType")int energyType);
	
	float getRoomsMinEnergySumByDateAndType(@Param("refBuildingId")String refBuildingId,@Param("feeDateStart")String feeDateStart,@Param("feeDateEnd")String feeDateEnd,@Param("energyType")int energyType);

	/**
	 * 楼读数
	 * refBuildingId,recordDate,energyType
	 * @param map
	 * @return
	 */
	float getBuildingRecordByRecordDateAndType(@Param("refBuildingId")String refBuildingId,@Param("recordDate")String recordDate,@Param("energyType")int energyType);
	
	/**
	 * 取楼中已录入房间数量
	 * @param refBuildingId
	 * @param recordDate
	 * @param energyType
	 * @return
	 */
	int getRoomEnergyCountByBuildingAndDateAndType(@Param("refBuildingId")String refBuildingId,@Param("recordDate")String recordDate,@Param("energyType")int energyType);
	
	
	//List<EnergyCost> getRoomEnergyListByPeriodAndType();
	
	/**
	 * 房间最后一次结算记录
	 * @param lastBillDate
	 * @return
	 */
	EnergyCost getLastBilledRecordOfRoom(@Param("refRoomId")String refRoomId,@Param("energyType")int energyType);
	
	/**
	 * 取楼最后一次能源录入
	 * @param refBuildingId
	 * @param lastFeeDate 应该在最后一次结算日后
	 * @param energyType
	 * @return
	 */
	EnergyCost getBuildingLastEnergyCost(@Param("refBuildingId")String refBuildingId,@Param("lastFeeDate")String lastFeeDate, @Param("energyType")Integer energyType);
	
	
	
	/**
	 * 合同结束前房间最后一次读数
	 * @param refRoomId 
	 * @param energyType 
	 * @param endDate //小于合同结束日期
	 * @return
	 */
	EnergyCost getLastRecordOfRoomBeforeEndDate(@Param("refRoomId")String refRoomId,@Param("energyType")int energyType,@Param("endDate")String endDate);
	
	/**
	 * 合同开始后最近的记录
	 * @param refRoomId
	 * @param energyType
	 * @param startDate
	 * @return
	 */
	EnergyCost getFirstRecordOfRoomAfterStartDate(@Param("refRoomId")String refRoomId,@Param("energyType")int energyType,@Param("startDate")String startDate);
	
	/**
	 * 取房间读数
	 * @param refRoomId
	 * @param energyType
	 * @param recordDate
	 * @return
	 */
	EnergyCost getRecordOfRoomByRecordDate(@Param("refRoomId")String refRoomId,@Param("energyType")int energyType,@Param("recordDate")String recordDate);
	
	/**
	 * 取recordDate后最近一条读数
	 * @param refRoomId
	 * @param energyType
	 * @param recordDate
	 * @param recordEndDate
	 * @return
	 */
	EnergyCost getNearestRecordOfRoomByRecordDate(@Param("refRoomId")String refRoomId,@Param("energyType")int energyType,@Param("recordDate")String recordDate,@Param("recordEndDate")String recordEndDate);
	
	
	
	EnergyCost getLastEnitty(@Param("id")String id,@Param("energyCategory")int energyCategory,@Param("energyType")int energyType);
	
	/**
	 * 
	 * @param energyCategory
	 * @param energyType
	 * @param dateType 0开始1结束
	 * @param itemId
	 * @param recordDate 计费开始,>开始,或结束日期，<=结束
	 * @return
	 */
	//EnergyCost getEnergyCost(@Param("energyCategory")int energyCategory,@Param("energyType")int energyType,@Param("dateType")int dateType,@Param("itemId") String itemId,@Param("recordDate")String recordDate);
	
	/**
	 * 取能源计费期间记录列表
	 * @param energyType
	 * @param refItemId
	 * @param recordDate
	 * @return
	 */
	List<EnergyCost> getEnergyFeeList(@Param("energyType")int energyType,@Param("refItemId") String refItemId,@Param("dateStart")String dateStart,@Param("dateEnd") String dateEnd);
	
	/**
	 * 取房间所有能源记录
	 * @param page
	 * @return
	 */
	List<EnergyRecordModel> getEnergyListByRoom(PageEnergyBill page);
	
	int getEnergyCountByRoom(PageEnergyBill page);
	
	/**
	 * 取能源统计信息
	 * @param page
	 * @return
	 */
	List<Map<String,String>> getEnergySummaryByRoom(PageEnergyBill page);
	
	
	/**
	 * 取楼首个记录
	 * @param refBuildingId
	 * @param energyType
	 * @return
	 */
	EnergyCost getFirstBuildingEnergyCost(@Param("refBuildingId")String refBuildingId,@Param("energyType")int energyType);
	
	
	
	double getEnergyCostSumByRecordDate(@Param("refBuildingId")String refBuildingId,@Param("energyCategory")int energyCategory, @Param("energyType")int energyType, @Param("recordDate")String recordDate);
	
	
	/**
	 * 取当日该录入能源楼
	 * @param refParkId
	 * @param eDay
	 * @param eWeek
	 * @return
	 */
	List<EtopFloor> getNeedEnterEnergyFloors(@Param("refParkId")String refParkId,@Param("eDay")int eDay,@Param("eWeek")int eWeek);
	
	
}
