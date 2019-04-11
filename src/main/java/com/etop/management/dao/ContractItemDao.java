package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.ContractItem;

public interface ContractItemDao {

	List<ContractItem> getContractItemList(@Param("contractId")String contractId);
	
	int createContractItem(ContractItem entity);
	
	int updateContractItem(ContractItem entity);
	
	/**
	 * 批量更新项目出帐规则
	 * @param entity
	 * @return
	 */
	int updateContractItemBillRule(ContractItem entity);
	
	ContractItem getOneOfItem(@Param("refContractId") String refContractId);
	
//	int updateContractItemlastFeeDate(ContractItem entity);
	
	int deleteContractItem(@Param("id")String id);
	
	int deleteContractItemByContractId(@Param("refContractId")String refContractId);
	
	ContractItem getContractItemById(@Param("id")String id);
	
	/**
	 * 在有效期的合同项目
	 * @param now
	 * @return
	 */
	List<ContractItem> getContractItemsForBill(@Param("now") String now,@Param("refParkId") String refParkId);
	
	/**
	 * 取合同中的可以生成帐单的有效合同项<br>
	 * 目前主要用于为单个合同出帐单
	 * @param now
	 * @param refContractId
	 * @return
	 */
	List<ContractItem> getContractItemsByContractId(@Param("now") String now,@Param("refContractId") String refContractId);
}
