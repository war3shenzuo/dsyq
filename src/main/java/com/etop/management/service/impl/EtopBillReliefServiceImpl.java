package com.etop.management.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.etop.management.bean.BillReliefBean;
import com.etop.management.bean.BillReliefWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.dao.EtopBillReliefDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBill.BillStatus;
import com.etop.management.entity.EtopBillRelief;
import com.etop.management.entity.EtopBillRelief.AuditStatus;
import com.etop.management.service.EtopBillReliefService;
import com.etop.management.service.EtopBillService;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillReliefService<br>
 */
@Service("etopBillReliefService")
public class  EtopBillReliefServiceImpl  implements EtopBillReliefService {

	@Inject
	private EtopBillReliefDao etopBillReliefDao;
	@Inject
	private EtopBillDao etopBillDao;
	@Inject
	private EtopBillService etopBillService;
	
	@Override
	public EtopBillRelief findById(String reliefId) {
		return etopBillReliefDao.findById(reliefId);
	}

	@Override
	public EtopPage<EtopBillRelief> listByBillId(String billId, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		Map<String, Object> map = new HashMap<>();
		map.put("billId", billId);
		return new EtopPage<>(etopBillReliefDao.list(map));
	}

	@Override
	public EtopPage<EtopBillRelief> list(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillReliefDao.list(map));
	}
	
	@Override
	public EtopPage<BillReliefWithUser> listWithUser(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillReliefDao.listWithUser(map));
	}
	
	@Override
	public EtopPage<EtopBillRelief> listByParkId(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillReliefDao.listByParkId(map));
	}
	
	@Override
	public EtopPage<BillReliefWithUser> listWithUserByParkId(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillReliefDao.listWithUserByParkId(map));
	}

	@Override
	public int apply(EtopBillRelief etopBillRelief) {
		return etopBillReliefDao.add(etopBillRelief);
	}

	@Override
	public String delete(String reliefId) {
		EtopBillRelief relief = etopBillReliefDao.findById(reliefId);
		if(relief == null) {
			return "没有这条记录";
		}
		else if(relief.getAuditStatus() != AuditStatus.UNCHECK.value) {
			return "已审核减免不能删除";
		}
		else if(etopBillReliefDao.delete(reliefId) > 0) {
			return "success";
		}
		else {
			return "记录已被删除";
		}
	}

	@Override
	public int deletes(String[] reliefIds) {
		return etopBillReliefDao.deletes(reliefIds);
	}

	@Override
	public int update(EtopBillRelief etopBillRelief) {
		return etopBillReliefDao.update(etopBillRelief);
	}

	@Override
	public int updateSelective(EtopBillRelief etopBillRelief) {
		return etopBillReliefDao.updateSelective(etopBillRelief);
	}
	
	@Override
	public int audit(String[] reliefIds, boolean status, String auditor) {
		Map<String, Object> map = new HashMap<>();
		map.put("auditStatus", status?AuditStatus.ACCEPT.value:AuditStatus.REFUSE.value);
		map.put("reliefIds", reliefIds);
		map.put("auditor", auditor);
		int res = etopBillReliefDao.setAuditByIds(map);
		if(status) {
			List<EtopBillRelief> relieves = etopBillReliefDao.listByIds(reliefIds);
			for(EtopBillRelief relief : relieves) {
				EtopBill bill = etopBillDao.findById(relief.getBillId());
				bill.setAccountRemission(bill.getAccountRemission().add(relief.getAmountRemission()));
				bill.setOverdueRemission(bill.getOverdueRemission().add(relief.getOverdueRemission()));
				if(bill.isPaid()) {
					bill.setBillStatus(BillStatus.PAID.value);
				}
				etopBillDao.update(bill);
				etopBillService.recalculateOverdueFine(bill.getBillId());
			}
		}
		return res;
	}
	
	@Override
	public int audit(String reliefId, boolean status, String auditor) {
		Map<String, Object> map = new HashMap<>();
		map.put("auditStatus", status?AuditStatus.ACCEPT.value:AuditStatus.REFUSE.value);
		map.put("reliefIds", new String[]{reliefId});
		map.put("auditor", auditor);
		int res = etopBillReliefDao.setAuditByIds(map);
		if(status) {
			EtopBillRelief relief = etopBillReliefDao.findById(reliefId);
			EtopBill bill = etopBillDao.findById(relief.getBillId());
			bill.setAccountRemission(bill.getAccountRemission().add(relief.getAmountRemission()));
			bill.setOverdueRemission(bill.getOverdueRemission().add(relief.getOverdueRemission()));
			if(bill.isPaid()) {
				bill.setBillStatus(BillStatus.PAID.value);
			}
			etopBillDao.update(bill);
			etopBillService.recalculateOverdueFine(bill.getBillId());
		}
		return res;
	}

	@Override
	public EtopPage<BillReliefBean> listWithBill(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillReliefDao.listWithBill(map));
	}
}
