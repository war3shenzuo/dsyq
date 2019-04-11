package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.ContractEnergy;
import com.etop.management.model.ContractEnergyBillModel;
import com.etop.management.model.EnergySupplyModel;

public interface ContractEnergyDao {
	
	int createContractEnergy(ContractEnergy entity);
	
	int updateContractEnergy(ContractEnergy entity);
	
	int deleteContractEnergy(@Param("id")String id);
	
	int deleteContractEnergyByRefContractId(@Param("refContractId")String refContractId);
	
	ContractEnergy getContractEnergyById(@Param("id")String id);
	
	ContractEnergy getContractEnergyByRefContractId(@Param("refContractId")String refContractId);
	
	/**
	 * 根据楼取能源合同有效出帐列表
	 * @param now
	 * @param refBuildingId
	 * @return
	 */
	List<ContractEnergyBillModel> getContractEnergyBillModelByBuilding(@Param("lastFeeDate") String lastFeeDate,@Param("currFeeDate") String currFeeDate,@Param("refBuildingId") String refBuildingId);
	
	/**
	 * 获取已结束或已终止，且日期在上次结算日与本次结算日之前的需要补登能源的合同
	 * @param refBuildingId
	 * @param currRecordDate
	 * @param lastRecordDate
	 * @return
	 */
	List<EnergySupplyModel> getSupplyContractEnergyList(@Param("refBuildingId") String refBuildingId,@Param("currRecordDate") String currRecordDate,@Param("lastRecordDate") String lastRecordDate);
}
