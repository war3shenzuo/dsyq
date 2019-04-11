package com.etop.management.service;

import java.util.Map;

import com.etop.management.bean.BillReliefBean;
import com.etop.management.bean.BillReliefWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopBillRelief;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillReliefService<br>
 */
public interface EtopBillReliefService {

	EtopBillRelief findById(String reliefId);
	
	EtopPage<EtopBillRelief> listByBillId(String billId, int offset, int limit);
	
	EtopPage<EtopBillRelief> list(Map<String, Object> map, int offset, int limit);
	
	EtopPage<BillReliefWithUser> listWithUser(Map<String, Object> map, int offset, int limit);
	
	int apply(EtopBillRelief etopBillRelief);
	
	String delete(String reliefId);
	
	int deletes(String[] reliefIds);
	
	int update(EtopBillRelief etopBillRelief);
	
	int updateSelective(EtopBillRelief etopBillRelief);

	int audit(String[] reliefIds, boolean status, String auditor);
	
	int audit(String reliefId, boolean status, String auditor);

	EtopPage<EtopBillRelief> listByParkId(Map<String, Object> map, int offset, int limit);
	
	EtopPage<BillReliefWithUser> listWithUserByParkId(Map<String, Object> map, int offset, int limit);
	
	EtopPage<BillReliefBean> listWithBill(Map<String, Object> map, int offset, int limit);
}
