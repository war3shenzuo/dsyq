package com.etop.management.dao;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.ContractExpress;

public interface ContractExpressDao {
	int createContractExpress(ContractExpress entity);
	
	int updateContractExpress(ContractExpress entity);
	
	int deleteContractExpress(@Param("id")String id);
	
	int deleteContractExpressByRefContractId(@Param("refContractId")String refContractId);
	
	ContractExpress getContractExpressById(@Param("id")String id);
	
	ContractExpress getContractExpressByRefContractId(@Param("refContractId")String refContractId);
}
