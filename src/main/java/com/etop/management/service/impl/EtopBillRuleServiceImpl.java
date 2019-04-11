package com.etop.management.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.OpInfoBean;
import com.etop.management.dao.EtopBillRuleDao;
import com.etop.management.entity.EtopBillRule;
import com.etop.management.service.EtopBillRuleService;
import com.etop.management.util.Util;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EtopBillRuleServiceImpl implements EtopBillRuleService {

	
	@Resource
	EtopBillRuleDao etopBillRuleDao;
	
	@Override
	public List<EtopBillRule> getBillRuleList(String refParkId) {
		
		return this.etopBillRuleDao.getBillRuleList(refParkId);
	}

	@Override
	public String saveRule(OpInfoBean op, EtopBillRule rule) {
		
		String result=null;
		
		int r=0;
		
		if(Util.isNullOrEmpty(rule.getId()))
		{
			rule.setId(UUID.randomUUID().toString());
			
			r=this.etopBillRuleDao.createBillRule(rule);
			
		}
		else
		{
			r=this.etopBillRuleDao.updateBillRule(rule);
			
		}
		
		if(r>0)
		{
			result=rule.getId();
		}
		
		return result;
	}

	@Override
	public int removeRule(OpInfoBean op, String id) {
		
		return this.etopBillRuleDao.deleteBillRule(id);
	}



	
	
}
