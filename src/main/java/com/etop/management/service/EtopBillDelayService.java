package com.etop.management.service;

import java.util.Map;

import com.etop.management.bean.BillDelayBean;
import com.etop.management.bean.BillDelayWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopBillDelay;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillDelayService<br>
 */
public interface EtopBillDelayService {
	
	int apply(EtopBillDelay etopBillDelay);

	int delete(String delayId);
	
	int deletes(String[] delayIds);
	
	int updateSelective(EtopBillDelay etopBillDelay);
	
	EtopBillDelay findById(String delayId);
	
	EtopPage<EtopBillDelay> listByBillId(String billId, int offset, int limit);
	
	EtopPage<EtopBillDelay> listByParkId(Map<String, Object> map, int offset, int limit);

	EtopPage<BillDelayWithUser> listWithUserByParkId(Map<String, Object> map, int offset, int limit);
	
	EtopPage<EtopBillDelay> list(Map<String, Object> map, int offset, int limit);
	
	EtopPage<BillDelayWithUser> listWithUser(Map<String, Object> map, int offset, int limit);
	
	EtopPage<BillDelayBean> listWithBill(Map<String, Object> map, int offset, int limit);

	int audit(String[] delayIds, boolean status, String auditor);
	
	int audit(String delayId, boolean status, String auditor);
}
