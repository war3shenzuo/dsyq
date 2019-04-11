package com.etop.management.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.etop.management.bean.ContractServiceItem;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.dao.ContractServiceItemDao;
import com.etop.management.service.ContractServiceItemService;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.Util;

@Service
public class ContractServiceItemServiceImpl implements ContractServiceItemService {

	@Resource
	ContractServiceItemDao contractServiceItemDao;
	
	@Override
	public List<ContractServiceItem> getContractServiceItemList(String refParkId,Boolean isValid) {
		
		return this.contractServiceItemDao.getContractServiceItemList(refParkId,isValid);
	}

	@Override
	public String saveServiceItem(OpInfoBean op,
			ContractServiceItem contractServiceItem) {
		
		if(Util.isNullOrEmpty(contractServiceItem.getId()))
		{
			contractServiceItem.setId(UUID.randomUUID().toString());
			
			this.contractServiceItemDao.createContractServiceItem(contractServiceItem);
			
		}
		else
		{
			this.contractServiceItemDao.updateContractServiceItem(contractServiceItem);
		}
		
		LoggerUtil.info(String.format("[saveServiceItem]%s, by %s.",contractServiceItem.toString(),op.getOper()));
		
		return contractServiceItem.getId();
	}

	@Override
	public int removeServiceItem(OpInfoBean op, String id) {
		
		
		LoggerUtil.info(String.format("[removeServiceItem]id:%s, by %s.",id,op.getOper()));
		
		return this.contractServiceItemDao.deleteContractServiceItem(id);
	}

	
	
}
