package com.etop.management.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.etop.management.bean.BillDelayBean;
import com.etop.management.bean.BillDelayWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.dao.EtopBillDelayDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBillDelay;
import com.etop.management.entity.EtopBillDelay.AuditStatus;
import com.etop.management.service.EtopBillDelayService;
import com.etop.management.service.EtopBillService;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillDelayService<br>
 */
@Service("etopBillDelayService")
public class  EtopBillDelayServiceImpl  implements EtopBillDelayService {

	@Inject
	private EtopBillDelayDao etopBillDelayDao;
	@Inject
	private EtopBillDao etopBillDao;
	@Inject
	private EtopBillService etopBillService;
	
	@Override
	public int apply(EtopBillDelay etopBillDelay) {
		return etopBillDelayDao.add(etopBillDelay);
	}

	@Override
	public int delete(String delayId) {
		return etopBillDelayDao.delete(delayId);
	}

	@Override
	public int deletes(String[] delayIds) {
		return etopBillDelayDao.deletes(delayIds);
	}
	
	@Override
	public int updateSelective(EtopBillDelay etopBillDelay) {
		return etopBillDelayDao.updateSelective(etopBillDelay);
	}

	@Override
	public EtopBillDelay findById(String delayId) {
		return etopBillDelayDao.findById(delayId);
	}

	@Override
	public EtopPage<EtopBillDelay> listByBillId(String billId, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillDelayDao.listByBillId(billId));
	}
	
	@Override
	public EtopPage<EtopBillDelay> listByParkId(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillDelayDao.listByParkId(map));
	}
	
	@Override
	public EtopPage<BillDelayWithUser> listWithUserByParkId(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillDelayDao.listWithUserByParkId(map));
	}
	
	@Override
	public EtopPage<EtopBillDelay> list(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillDelayDao.list(map));
	}
	
	@Override
	public EtopPage<BillDelayWithUser> listWithUser(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillDelayDao.listWithUser(map));
	}
	
	@Override
	public int audit(String[] delayIds, boolean status, String auditor) {
		Map<String, Object> map = new HashMap<>();
		map.put("auditStatus", status?AuditStatus.ACCEPT.value:AuditStatus.REFUSE.value);
		map.put("delayIds", delayIds);
		map.put("auditor", auditor);
		int res = etopBillDelayDao.setAuditByIds(map);
		if(status) {
			List<EtopBillDelay> delays = etopBillDelayDao.listByIds(delayIds);
			for(EtopBillDelay delay : delays) {
				String billId = delay.getBillId();
				EtopBill bill = etopBillDao.findById(billId);
				if(bill.getDeadline().before(delay.getDelayTime())) {
					bill.setDeadline(delay.getDelayTime());
					etopBillDao.update(bill);
					etopBillService.recalculateOverdueFine(billId);
				}
			}
		}
		return res;
	}
	
	@Override
	public int audit(String delayId, boolean status, String auditor) {
		Map<String, Object> map = new HashMap<>();
		map.put("auditStatus", status?AuditStatus.ACCEPT.value:AuditStatus.REFUSE.value);
		map.put("delayIds", new String[]{delayId});
		map.put("auditor", auditor);
		int res = etopBillDelayDao.setAuditByIds(map);
		if(status) {
			EtopBillDelay delay = etopBillDelayDao.findById(delayId);
			String billId = delay.getBillId();
			EtopBill bill = etopBillDao.findById(billId);
			if(bill.getDeadline().before(delay.getDelayTime())) {
				bill.setDeadline(delay.getDelayTime());
				etopBillDao.update(bill);
				etopBillService.recalculateOverdueFine(billId);
			}
		}
		return res;
	}

	@Override
	public EtopPage<BillDelayBean> listWithBill(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "apply_time DESC");
		return new EtopPage<>(etopBillDelayDao.listWithBill(map));
	}
}
