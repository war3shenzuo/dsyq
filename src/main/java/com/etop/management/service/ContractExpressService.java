package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.ContractExpress;
import com.etop.management.bean.ContractExpressItem;
import com.etop.management.bean.OpInfoBean;

public interface ContractExpressService {

	String save(OpInfoBean op,ContractExpress entity);
	
	ContractExpress getValueByRefContractId(String refContract);
	
	int removeByRefContractId(OpInfoBean op,String refContract);
	
	
	
	ContractExpressItem getValueById(String id);
	
	String saveExpressItem(OpInfoBean op,ContractExpressItem entity);
	
	int removeExpressItem(OpInfoBean op,String id);
	
	List<ContractExpressItem> getExpressItemList(String contractId);
}

