package com.etop.management.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.ContractServiceItem;

public interface ContractServiceItemDao {

	List<ContractServiceItem> getContractServiceItemList(@Param("refParkId") String refParkId,@Param("isValid") Boolean isValid);
	
	ContractServiceItem getContractServiceItemById(@Param("id") String id);
	
	
	int deleteContractServiceItem(@Param("id") String id);
	
	int createContractServiceItem(ContractServiceItem entity);
	
	int updateContractServiceItem(ContractServiceItem entity);
}
