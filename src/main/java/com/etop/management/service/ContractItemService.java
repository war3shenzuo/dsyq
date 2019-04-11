package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.Contract;
import com.etop.management.bean.ContractItem;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.model.ContractItemBillModel;

public interface ContractItemService {

	String save(OpInfoBean op,ContractItem entity);
	
	int remove(OpInfoBean op,String id);
	
	List<ContractItem> getContractItemList(String contractId);
	
	ContractItem getValueById(String id);
	
	/**
	 * 批量更新项目出帐规则
	 * @param entity
	 * @return
	 */
//	int updateContractItemBillRule(ContractItem entity);
	
	/**
	 * 取其中一个，用作出帐规则
	 * @param refContractId
	 * @return
	 */
	ContractItem getOneOfContractItem(String refContractId);
	
	
//	/**
//	 * 取出该园区需要出帐的合同列表
//	 * @param refParkId
//	 * @return
//	 */
//	List<ContractItemBillModel> getContractItemBillModelByPark(String refParkId,String today);
//	
//	/**
//	 * 按规则出单合同帐
//	 * @param contract
//	 * @return
//	 */
//	List<ContractItemBillModel> getContractItemBillModelByContract(Contract contract);
	
}
