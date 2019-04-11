package com.etop.management.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.ContractExpress;
import com.etop.management.bean.ContractExpressItem;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.dao.ContractExpressDao;
import com.etop.management.dao.ContractExpressItemDao;
import com.etop.management.service.ContractExpressService;
import com.etop.management.util.Util;
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ContractExpressServiceImpl implements ContractExpressService {

	@Resource
	ContractExpressDao contractExpressDao;
	
	@Resource
	ContractExpressItemDao contractExpressItemDao;
	
	@Override
	public String save(OpInfoBean op, ContractExpress entity) {
		
		if(Util.isNullOrEmpty(entity.getId()))
		{
			entity.setId(UUID.randomUUID().toString());
			
			entity.setCreatedAt(new Date());
			
			entity.setCreatedBy(op.getOper());
			
			entity.setUpdatedAt(new Date());
			
			entity.setUpdatedBy(op.getOper());
			
			this.contractExpressDao.createContractExpress(entity);
			
			
			
		}
		else
		{
			entity.setUpdatedAt(new Date());
			
			entity.setUpdatedBy(op.getOper());
			
			this.contractExpressDao.updateContractExpress(entity);
		}	
		
		return entity.getId();	
		
	}

	@Override
	public ContractExpress getValueByRefContractId(String refContract) {
		// TODO Auto-generated method stub
		return this.contractExpressDao.getContractExpressByRefContractId(refContract);
	}

	@Override
	public int removeByRefContractId(OpInfoBean op, String refContract) {
		// TODO Auto-generated method stub
		return this.contractExpressDao.deleteContractExpressByRefContractId(refContract);
	}

	@Override
	public String saveExpressItem(OpInfoBean op, ContractExpressItem entity) {
		
		if(Util.isNullOrEmpty(entity.getId()))
		{
			entity.setId(UUID.randomUUID().toString());
			
			entity.setCreatedAt(new Date());
			
			entity.setCreatedBy(op.getOper());
			
			entity.setUpdatedAt(new Date());
			
			entity.setUpdatedBy(op.getOper());
			
			this.contractExpressItemDao.createContractExpressItem(entity);
			
			
			
		}
		else
		{
			entity.setUpdatedAt(new Date());
			
			entity.setUpdatedBy(op.getOper());
			
			this.contractExpressItemDao.updateContractExpressItem(entity);
		}	
		
		return entity.getId();	
	}

	@Override
	public int removeExpressItem(OpInfoBean op, String id) {
		// TODO Auto-generated method stub
		return this.contractExpressItemDao.deleteContractExpressItem(id);
	}

	@Override
	public List<ContractExpressItem> getExpressItemList(String contractId) {
		// TODO Auto-generated method stub
		return this.contractExpressItemDao.getContractExpressItemList(contractId);
	}

	@Override
	public ContractExpressItem getValueById(String id) {
		
		return this.contractExpressItemDao.getContractExpressItemById(id);
	}
	

}
