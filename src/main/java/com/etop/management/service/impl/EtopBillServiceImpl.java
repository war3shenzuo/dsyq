package com.etop.management.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.etop.management.bean.BillWithCompanyItem;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.PageEnergyBill;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.dao.EtopBillPayDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBillPay;
import com.etop.management.entity.EtopThreshold;
import com.etop.management.entity.EtopBill.AuditStatus;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopBill.BillType;
import com.etop.management.entity.EtopThreshold.ThresholdKey;
import com.etop.management.model.EnergyBillModel;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


/**
 * 
 * @author lmc
 * @date 2016年8月23日
 */
@Service("etopBillService")
public class  EtopBillServiceImpl  implements EtopBillService {

	@Inject
	private EtopBillDao etopBillDao;
	@Inject
	private EtopBillPayDao etopBillPayDao;
	@Inject
	private EtopThresholdService etopThresholdService;
	
	@Override
	public EtopPage<EtopBill> listAll() {
		PageHelper.startPage(1, 10, "created_time DESC");
		return new EtopPage<EtopBill>(etopBillDao.listAll());
	}

	@Override
	public EtopPage<EtopBill> list(Map<String, Object> condition, int offset, int limit) {
		String order = "created_time DESC";
		if(condition.containsKey("order"))
			order = condition.get("order").toString();
		PageHelper.offsetPage(offset, limit, order);
		return new EtopPage<EtopBill>(etopBillDao.list(condition));
	}
	
	@Override
	public EtopPage<EtopBill> listWithFloor(Map<String, Object> condition, int offset, int limit) {
		String order = "created_time DESC";
		if(condition.containsKey("order"))
			order = condition.get("order").toString();
		PageHelper.offsetPage(offset, limit, order);
//		return new EtopPage<EtopBill>(etopBillDao.listWithFloor(condition));
		Page<EtopBill> page = etopBillDao.listWithFloor(condition);
		for(EtopBill etopBill :page){
			if(etopBill.getBillSource() == 6){
				EtopBill server =etopBillDao.listServiceWithFloor(etopBill.getBillId());
				etopBill.setBuilding(server.getBuildingNo());
				etopBill.setFloor(server.getStorey());
				etopBill.setBlock(server.getZoneNo());
				etopBill.setRoom(server.getRoomNo());
			}
		}
		EtopPage<EtopBill> list = new EtopPage<EtopBill>(page);
		return list;
	}
	
	@Override
	public List<EtopBill> list(Map<String, Object> condition) {
		return etopBillDao.list(condition);
	}
	
	@Override
	public EtopPage<BillWithCompanyItem> listWithComp(Map<String, Object> condition, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "created_time DESC");
		return new EtopPage<BillWithCompanyItem>(etopBillDao.listWithComp(condition));
	}

	@Override
	public EtopPage<BillWithCompanyItem> listActiveWithComp(Map<String, Object> condition, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "created_time DESC");
		return new EtopPage<BillWithCompanyItem>(etopBillDao.listActiveWithComp(condition));
	}
	
	@Override
	public EtopPage<BillWithCompanyItem> listToAudit(Map<String, Object> condition, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "created_time DESC");
		return new EtopPage<BillWithCompanyItem>(etopBillDao.listToAudit(condition));
	}

	@Override
	public int add(EtopBill etopBill) {
		return etopBillDao.add(etopBill);
	}

	@Override
	public EtopBill findById(String billId) {
		return etopBillDao.findById(billId);
	}

	@Override
	public int deletes(String[] billIds) {
		return etopBillDao.deletes(billIds);
	}

	@Override
	public int updateSelective(EtopBill etopBill) {
		return etopBillDao.updateBySelective(etopBill);
	}

	@Override
	public int audit(String[] billIds, int auditStatus, String auditor, String comment) {
		Map<String, Object> map = new HashMap<>();
		map.put("billIds", billIds);
		map.put("auditStatus", auditStatus);
		map.put("auditor", auditor);
		map.put("comment", comment);
		return etopBillDao.audit(map);
	}
	
	@Override
	public int auditTwo(String id, int auditStatus, String auditor, String comment) {
		
		int billStatus =0;
		EtopBill etopBill =etopBillDao.findById(id);
		if((BigDecimal.ZERO).compareTo(etopBill.getAmount()) == 0){
			 billStatus = 2;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("auditStatus", auditStatus);
		map.put("auditor", auditor);
		map.put("comment", comment);
		map.put("billStatus", billStatus);
		return etopBillDao.auditTwo(map);
	}
	
	@Override
	public int financeAudit(String[] billIds, int auditStatus, String finance, String comment) {
		Map<String, Object> map = new HashMap<>();
		map.put("billIds", billIds);
		map.put("auditStatus", auditStatus);
		map.put("finance", finance);
		map.put("comment", comment);
		return etopBillDao.financeAudit(map);
	}

	@Override
	public int updateOverdueFine() {
		return etopBillDao.updateOverdue();
	}

	@Override
	public int recalculateOverdueFine(String billId) {
		EtopBill bill = etopBillDao.findById(billId);
		if(bill == null)
			return 0;
		double rate = bill.getOverdueRate().doubleValue();
		if(rate == 0)
			return 0;
		Date now = new Date();
		if(bill.getDeadline().after(now)) {
			bill.setOverdueFine(BigDecimal.ZERO);
			return etopBillDao.update(bill);
		}
		PageHelper.orderBy("pay_time");
		List<EtopBillPay> payList = etopBillPayDao.listByBillId(billId);
		double needpay = bill.getAmount().subtract(bill.getAccountRemission()).doubleValue();
		needpay = needpay<0 ? 0 : needpay;
		if(payList.isEmpty()) {
			double subtract = DateUtil.between(bill.getDeadline(), now, TimeUnit.DAYS);
			bill.setOverdueFine(new BigDecimal(subtract * needpay * rate));
			return etopBillDao.update(bill);
		}
		double paid = 0;
		double overdue = 0;
		Date dateSign = bill.getDeadline();
		for(EtopBillPay pay : payList) {
			if(pay.getPayTime().before(bill.getDeadline())) {
				paid += pay.getAmount().doubleValue();
			}
			else {
				double substract = DateUtil.between(dateSign, pay.getPayTime(), TimeUnit.DAYS);
				double subpay = needpay>paid ? needpay-paid : 0;
				overdue += rate * substract * subpay;
				paid += pay.getAmount().doubleValue();
				dateSign= DateUtil.toTodayNight(pay.getPayTime());
			}
		}
		double substract = DateUtil.between(dateSign, now, TimeUnit.DAYS);
		double subpay = needpay>paid ? needpay-paid : 0;
		overdue += rate * substract * subpay;
		
		bill.setOverdueFine(new BigDecimal(overdue));
		return etopBillDao.update(bill);
	}

	@Override
	public int recalculateOverdueFine(String[] billIds) {
		int count = 0;
		for(String billId : billIds)
			count += recalculateOverdueFine(billId);
		return count;
	}

	@Override
	public int getAuditLevel(EtopBill etopBill) {
		if(etopBill.getBillSource() == BillSource.UNKNOWN.value)
			return 0;
		double rateThreshold = 0;
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkId", etopBill.getParkId());
		condition.put("key", ThresholdKey.BillAmount.name);
		List<EtopThreshold> list = etopThresholdService.list(condition);
		if(!list.isEmpty())
			rateThreshold = list.get(0).getValue();
		if(etopBill.getBillType() == BillType.OUTLAY.value)
			return 2;
		if(etopBill.getAmount().doubleValue() > rateThreshold)
			return 2;
		return 1;
	}

	@Override
	public List<EnergyBillModel> getEnergyBillList(PageEnergyBill page) {
		return this.etopBillDao.getEnergyBillList(page);
	}

	@Override
	public int getEnergyBillCount(PageEnergyBill page) {
		return this.etopBillDao.getEnergyBillCount(page);
	}

	@Override
	public Map<String, Object> getStatistics(Map<String, Object> map) {
		Map<String, Object> res = new HashMap<>();
		res.put("total", etopBillDao.sum(map));
		if(map.get("auditStatus") == null) {
			map.put("auditStatus", AuditStatus.UNCHECK.value);
			res.put("uncheck", etopBillDao.sum(map));
			map.put("auditStatus", AuditStatus.CHECK_ACCEPT.value);
			res.put("check_accept", etopBillDao.sum(map));
			map.put("auditStatus", AuditStatus.CHECK_REFUSE.value);
			res.put("check_refuse", etopBillDao.sum(map));
			map.put("auditStatus", AuditStatus.FINANCE_ACCEPT.value);
			res.put("finance_accept", etopBillDao.sum(map));
			map.put("auditStatus", AuditStatus.FINANCE_REFUSE.value);
			res.put("finance_refuse", etopBillDao.sum(map));
		}
		else {
			res.put("uncheck", 0);
			res.put("check_accept", 0);
			res.put("check_refuse", 0);
			res.put("finance_accept", 0);
			res.put("finance_refuse", 0);
			if(map.get("auditStatus").equals(AuditStatus.UNCHECK.value)) {
				res.put("uncheck", res.get("total"));
			}
			else if(map.get("auditStatus").equals(AuditStatus.CHECK_ACCEPT.value)) {
				res.put("check_accept", res.get("total"));
			}
			else if(map.get("auditStatus").equals(AuditStatus.FINANCE_ACCEPT.value)) {
				res.put("finance_accept", res.get("total"));
			}
			else if(map.get("auditStatus").equals(AuditStatus.CHECK_REFUSE.value)) {
				res.put("check_refuse", res.get("total"));
			}
			else if(map.get("auditStatus").equals(AuditStatus.FINANCE_REFUSE.value)) {
				res.put("finance_refuse", res.get("total"));
			}
		}
		return res;
	}

	@Override
	public Map<String, Object> getStatisticsWithFloor(Map<String, Object> map) {
		Map<String, Object> res = new HashMap<>();
		res.put("total", etopBillDao.sumWithFloor(map));
		if(map.get("auditStatus") == null) {
			map.put("auditStatus", AuditStatus.UNCHECK.value);
			res.put("uncheck", etopBillDao.sumWithFloor(map));
			map.put("auditStatus", AuditStatus.CHECK_ACCEPT.value);
			res.put("check_accept", etopBillDao.sumWithFloor(map));
			map.put("auditStatus", AuditStatus.CHECK_REFUSE.value);
			res.put("check_refuse", etopBillDao.sumWithFloor(map));
			map.put("auditStatus", AuditStatus.FINANCE_ACCEPT.value);
			res.put("finance_accept", etopBillDao.sumWithFloor(map));
			map.put("auditStatus", AuditStatus.FINANCE_REFUSE.value);
			res.put("finance_refuse", etopBillDao.sumWithFloor(map));
		}
		else {
			res.put("uncheck", 0);
			res.put("check_accept", 0);
			res.put("check_refuse", 0);
			res.put("finance_accept", 0);
			res.put("finance_refuse", 0);
			if(map.get("auditStatus").equals(AuditStatus.UNCHECK.value)) {
				res.put("uncheck", res.get("total"));
			}
			else if(map.get("auditStatus").equals(AuditStatus.CHECK_ACCEPT.value)) {
				res.put("check_accept", res.get("total"));
			}
			else if(map.get("auditStatus").equals(AuditStatus.FINANCE_ACCEPT.value)) {
				res.put("finance_accept", res.get("total"));
			}
			else if(map.get("auditStatus").equals(AuditStatus.CHECK_REFUSE.value)) {
				res.put("check_refuse", res.get("total"));
			}
			else if(map.get("auditStatus").equals(AuditStatus.FINANCE_REFUSE.value)) {
				res.put("finance_refuse", res.get("total"));
			}
		}
		return res;
	}
}
