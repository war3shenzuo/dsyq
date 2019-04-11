package com.etop.management.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.BillPayWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBill.BillType;
import com.etop.management.entity.EtopBillIncome;
import com.etop.management.entity.EtopBillPay;
import com.etop.management.service.EtopBillIncomeService;
import com.etop.management.service.EtopBillPayService;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopSequenceService;

/**
 *  
 * @author lmc
 * @date 2016年8月23日
 */
@Controller
@RequestMapping("/bill/pay")
public class EtopBillPayController extends BaseAppController {
	
	@Inject
	private EtopBillPayService etopBillPayService;
	@Inject
	private EtopBillService etopBillService;
	@Inject
	private EtopSequenceService etopSequenceService;
	@Inject
	private EtopBillIncomeService etopBillIncomeService;
	@Inject
	private EtopBillDao etopBillDao;
	
	@RequestMapping("/addview.do")
	public String addView(String billId, ModelMap mm) {
		mm.addAttribute("billId", billId);
		return "/bill/pay/add";
	}
	
	@RequestMapping("/batchview.do")
	public String batchView() {
		return "/bill/pay/batch";
	}
	
	@RequestMapping("/listview.do")
	public String listView() {
		return "/bill/pay/list";
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public EtopPage<BillPayWithUser> list(String billId,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("billId", billId);
		return etopBillPayService.listWithUser(billId, offset, limit);
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	public ResultType add(EtopBillPay etopBillPay) {
		etopBillPay.setPayId(etopSequenceService.getFormatId(getParkCode(), "zf"));
		etopBillPay.setPayTime(new Date());
		etopBillPay.setRecorder(getUserId());
		String msg = etopBillPayService.pay(etopBillPay);
		if(msg.equals("success")) {
			return ResultType.getSuccess();
		}
		else {
			return ResultType.getFail(msg);
		}
	}
	
	@RequestMapping("/addbatch.do")
	@ResponseBody
	@Transactional
	public ResultType addbatch(@RequestBody List<EtopBillPay> billPays,
			@RequestParam BigDecimal total, String companyId, String companyName,String billId) throws Exception {
		if("0".equals(companyId) ){
			companyId = etopBillDao.findById(billId).getCompanyId();
			companyName = etopBillDao.findById(billId).getCompanyName();
		}
		BigDecimal balance = total;
		StringBuffer sb = new StringBuffer();
		for(EtopBillPay pay : billPays) {
			pay.setPayId(etopSequenceService.getFormatId(getParkCode(), "zf"));
			pay.setPayTime(new Date());
			pay.setRecorder(getUserId());
			String msg = etopBillPayService.pay(pay);
			if(!"success".equals(msg)) {
				throw new RuntimeException("支付异常：" + pay.getBillId() + "," + msg);
			}
			EtopBill bill = etopBillService.findById(pay.getBillId());
			if(bill.getBillType() == BillType.INCOME.value)
				balance = balance.subtract(pay.getAmount());
			else if(bill.getBillType() == BillType.OUTLAY.value)
				balance = balance.add(pay.getAmount());
//				balance = balance.subtract(pay.getAmount());
			sb.append(String.format("账单：%s，付款：%.2f\n", pay.getBillId(), pay.getAmount()));
		}
		
		EtopBillIncome income = new EtopBillIncome();
		income.setIncomeId(etopSequenceService.getFormatId(getParkCode(), "rz"));
		income.setParkId(getParkId(null));
		income.setCompanyId(companyId);
		income.setCompanyName(companyName);
		income.setAmount(total);
		income.setBalance(balance);
		income.setPayType(billPays.get(0).getPayType());
		income.setPayNoOut(billPays.get(0).getPayNoOut());
		income.setPayTime(new Date());
		income.setRecorder(getUserId());
		income.setDescription(sb.toString());
		etopBillIncomeService.add(income);
		
		return ResultType.getSuccess();
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResultType delete(String payId) {
		String res = etopBillPayService.cancelPay(payId);
		if(res.equals("success"))
			return ResultType.getSuccess();
		else
			return ResultType.getFail(res);
	}
	
	@RequestMapping("/listincome.do")
	@ResponseBody
	public EtopPage<EtopBillIncome> listIncome(String incomeId, String companyName,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginDate,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("parkIds", getParkIds());
		map.put("incomeId", incomeId);
		map.put("companyName", companyName);
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		return etopBillIncomeService.list(map, offset, limit);
	}
}