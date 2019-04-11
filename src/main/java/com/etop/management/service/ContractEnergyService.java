package com.etop.management.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.ContractEnergy;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.entity.EtopFloor;
import com.etop.management.model.ContractEnergyBillModel;
import com.etop.management.model.EnergySupplyModel;

public interface ContractEnergyService {

	String save(OpInfoBean op,ContractEnergy entity);
	
	ContractEnergy getValueByRefContractId(String refContract);
		
	//int removeByRefContractId(OpInfoBean op,String refContract);
	
	/**
	 * 检测合同能源读数有效性
	 * @param refContractId
	 * @return
	 */
	List<String> checkEnergyRecord(ContractEnergy contractEnergy);
	
	/**
	 * 需要生成能源帐单的合同数据列表
	 * @param refBuildingId 根据楼来取
	 * @return
	 */
	//List<ContractEnergyBillModel> getContractEnergyBillModelsForBill(String today,String refBuildingId);
	
	
	/**
	 * 生成所有能源帐单
	 * @return
	 */
	int generateAllBill(String today);
	
	
	/**
	 * 根据园区生成能源帐单
	 * @param refParkId
	 * @param today
	 * @return
	 */
	int generateBillByPark(String refParkId,String today);
	
	

	
	/**
	 * 
	 * 根据楼生成能源帐单
	 * @param building
	 * @return
	 */
	int generateBillByBuilding(EtopFloor building,String today);
	
	/**
	 * 获取需要录入补充能源的合同列表，包括已结束与已终止合同的能源读数  。在录入楼能源前录入。
	 * @param refBuildingId
	 * @param currRecordDate
	 * @param lastRecordDate
	 * @return
	 */
	List<EnergySupplyModel> getSupplyContractEnergyList(String refBuildingId,String currRecordDate,String lastRecordDate);
}
