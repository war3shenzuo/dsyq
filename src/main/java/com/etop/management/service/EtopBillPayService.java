package com.etop.management.service;

import com.etop.management.bean.BillPayWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopBillPay;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillPayService<br>
 */
public interface EtopBillPayService {

	EtopPage<EtopBillPay> list(String billId, int offset, int limit);
	
	EtopPage<BillPayWithUser> listWithUser(String billId, int offset, int limit);
	
	int add(EtopBillPay etopBillPay);
	
	String pay(EtopBillPay etopBillPay);
	
	String cancelPay(String payId);
}
