package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.ContractExpressItem;

public interface ContractExpressItemDao {
	
	int createContractExpressItem(ContractExpressItem entity);
	
	int updateContractExpressItem(ContractExpressItem entity);
	
	int deleteContractExpressItem(@Param("id")String id);
	
	ContractExpressItem getContractExpressItemById(@Param("id")String id);
	
	List<ContractExpressItem> getContractExpressItemList(@Param("contractId")String contractId);
}
