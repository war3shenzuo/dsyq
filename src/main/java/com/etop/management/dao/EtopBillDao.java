package com.etop.management.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.etop.management.bean.BillWithCompanyItem;
import com.etop.management.bean.PageEnergyBill;
import com.etop.management.entity.EtopBill;
import com.etop.management.model.EnergyBillModel;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillDao<br>
 */
public interface EtopBillDao {
	/** 添加 */
	int add(EtopBill etopBill);

	/** 批量添加 */
	int adds(List<EtopBill> list);

	/** 全修改 */
	int update(EtopBill etopBill);

	/** 条件修改 */
	int updateBySelective(EtopBill etopBill);

	/** 根据Id删除 */
	int delete(EtopBill etopBill);
	
	/** 批量删除数据 */
	int deletes(String[] billIds);
	
	/** 批量修改审核状态 */
	int audit(Map<String, Object> map);
	
	/** 修改审核状态 */
	int auditTwo(Map<String, Object> map);
	
	/** 批量修改审核状态 */
	int financeAudit(Map<String, Object> map);

	/** 根据Id查询 */
	EtopBill findById(String id);

	/** 获取所有记录 */
	Page<EtopBill> listAll();
	
	/** 条件查询 */
	Page<EtopBill> list(Map<String, Object> condition);
	
	Page<EtopBill> listWithFloor(Map<String, Object> condition);
	
	EtopBill listServiceWithFloor(String billId);

	Page<BillWithCompanyItem> listWithComp(Map<String, Object> condition);

	Page<BillWithCompanyItem> listActiveWithComp(Map<String, Object> condition);

	Page<BillWithCompanyItem> listToAudit(Map<String, Object> condition);
	
	int updateOverdue();
	
	/**
	 * 根据房间号取相关能源帐单
	 * @return
	 */
	List<EnergyBillModel> getEnergyBillList(PageEnergyBill page);
	
	int getEnergyBillCount(PageEnergyBill page);
	
	BigDecimal sum(Map<String, Object> condition);
	
	BigDecimal sumWithFloor(Map<String, Object> condition);
	
	
	List<EtopBill> searchDeposit();
	
	int updateByBillNoOut(EtopBill etopBill);
}
