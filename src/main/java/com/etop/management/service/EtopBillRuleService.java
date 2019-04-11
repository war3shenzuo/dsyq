package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.OpInfoBean;
import com.etop.management.entity.EtopBillRule;

public interface EtopBillRuleService {
	
	List<EtopBillRule> getBillRuleList(String refParkId);
	
	String saveRule(OpInfoBean op,EtopBillRule rule);
	
	int removeRule(OpInfoBean op,String id);
	
	
}
