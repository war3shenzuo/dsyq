package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.ContractServiceItem;
import com.etop.management.bean.OpInfoBean;

public interface ContractServiceItemService {

	List<ContractServiceItem> getContractServiceItemList(String refParkId,Boolean isValid);
	
	String saveServiceItem(OpInfoBean op,ContractServiceItem contractServiceItem);
	
	int removeServiceItem(OpInfoBean op,String id);
	
}
