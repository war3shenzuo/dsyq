package com.etop.management.service;

import java.util.Map;

import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopBillIncome;
/**
 * 
 * <br>
 * <b>功能：</b>EtopBillIncomeService<br>
 */
public interface EtopBillIncomeService {

	EtopPage<EtopBillIncome> list(Map<String, Object> map, int offset, int limit);
	
	int add(EtopBillIncome etopBillincome);
	
	int delete(String incomeId);
	
	int deletes(String[] incomeIds);
}
