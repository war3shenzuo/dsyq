package com.etop.management.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.etop.management.bean.BillPayWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.dao.EtopBillPayDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBill.AuditStatus;
import com.etop.management.entity.EtopBill.BillStatus;
import com.etop.management.entity.EtopBillPay;
import com.etop.management.service.EtopBillPayService;
import com.github.pagehelper.PageHelper;

/**
 * 
 * <br>
 * <b>功能：</b>EtopBillPayService<br>
 */
@Service("etopBillPayService")
public class EtopBillPayServiceImpl implements EtopBillPayService {

	@Inject
	private EtopBillPayDao etopBillPayDao;
	@Inject
	private EtopBillDao etopBillDao;
	
	@Override
	public EtopPage<EtopBillPay> list(String billId, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "pay_time DESC");
		return new EtopPage<EtopBillPay>(etopBillPayDao.listByBillId(billId));
	}
	
	@Override
	public EtopPage<BillPayWithUser> listWithUser(String billId, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "pay_time DESC");
		return new EtopPage<BillPayWithUser>(etopBillPayDao.listWithUserByBillId(billId));
	}

	@Override
	public int add(EtopBillPay etopBillPay) {
		return etopBillPayDao.add(etopBillPay);
	}

	@Override
	public String pay(EtopBillPay etopBillPay) {
		EtopBill etopBill = etopBillDao.findById(etopBillPay.getBillId());
		if(etopBill.getBillStatus() == BillStatus.PAID.value)
			return "账单已付清";
		else if(etopBill.getAuditStatus() == AuditStatus.UNCHECK.value)
			return "账单未审核";
		else if(etopBill.getAuditStatus() == AuditStatus.CHECK_REFUSE.value
				|| etopBill.getAuditStatus() == AuditStatus.FINANCE_REFUSE.value)
			return "账单未通过审核";
		etopBillPayDao.add(etopBillPay);
		etopBill.setAmountPaid(etopBill.getAmountPaid().add(etopBillPay.getAmount()));
		etopBill.setPayTime(etopBillPay.getPayTime());
		if(etopBill.isPaid()) {
			etopBill.setBillStatus(BillStatus.PAID.value);
		}
		else {
			etopBill.setBillStatus(BillStatus.NOTENOUGH.value);
		}
		etopBillDao.update(etopBill);
		return "success";
	}
	
	@Override
	public String cancelPay(String payId) {
		EtopBillPay etopBillPay = etopBillPayDao.findById(payId);
		EtopBill etopBill = etopBillDao.findById(etopBillPay.getBillId());
		
		etopBillPayDao.delete(payId);
		etopBill.setAmountPaid(etopBill.getAmountPaid().subtract(etopBillPay.getAmount()));
		PageHelper.orderBy("pay_time desc");
		List<EtopBillPay> paylist = etopBillPayDao.listByBillId(etopBillPay.getBillId()); 
		if(paylist.isEmpty()) {
			etopBill.setBillStatus(BillStatus.UNPAID.value);
			etopBill.setPayTime(null);
		}
		else if(etopBill.isPaid()) {
			etopBill.setBillStatus(BillStatus.PAID.value);
			etopBill.setPayTime(paylist.get(0).getPayTime());
		}
		else {
			etopBill.setBillStatus(BillStatus.NOTENOUGH.value);
			etopBill.setPayTime(paylist.get(0).getPayTime());
		}
		etopBillDao.update(etopBill);
		return "success";
	}
}
