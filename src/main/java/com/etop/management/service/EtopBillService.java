package com.etop.management.service;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.BillWithCompanyItem;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.PageEnergyBill;
import com.etop.management.entity.EtopBill;
import com.etop.management.model.EnergyBillModel;

/**
 * 
 * @author lmc
 * @date 2016年8月23日
 */
public interface EtopBillService{
	
	EtopBill findById(String billId);

	EtopPage<EtopBill> listAll();
	
	EtopPage<EtopBill> list(Map<String, Object> condition, int offset, int limit);
	
	EtopPage<EtopBill> listWithFloor(Map<String, Object> condition, int offset, int limit);

	List<EtopBill> list(Map<String, Object> condition);
	
	int add(EtopBill etopBill);
	
	int deletes(String[] billId);
	
	int updateSelective(EtopBill etopBill);
	
	int audit(String[] billIds, int auditStatus, String auditor, String comment);
	
	int auditTwo(String id, int auditStatus, String auditor, String comment);
	
	int financeAudit(String[] billIds, int auditStatus, String finance, String comment);

	@Deprecated
	EtopPage<BillWithCompanyItem> listWithComp(Map<String, Object> condition, int offset, int limit);
	
	@Deprecated
	EtopPage<BillWithCompanyItem> listActiveWithComp(Map<String, Object> condition, int offset, int limit);
	
	EtopPage<BillWithCompanyItem> listToAudit(Map<String, Object> condition, int offset, int limit);
	
	int updateOverdueFine();
	
	int recalculateOverdueFine(String billId);
	
	int recalculateOverdueFine(String[] billIds);
	
	int getAuditLevel(EtopBill etopBill);
	/**
	 * 根据房间号取相关能源帐单
	 * @return
	 */
	List<EnergyBillModel> getEnergyBillList(PageEnergyBill page);
	
	int getEnergyBillCount(PageEnergyBill page);
	
	Map<String, Object> getStatistics(Map<String, Object> map);
	
	Map<String, Object> getStatisticsWithFloor(Map<String, Object> map);
}
