package com.etop.management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.BillReliefBean;
import com.etop.management.bean.BillReliefWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Remind;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBillRelief;
import com.etop.management.service.EtopBillReliefService;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.RemindService;
import com.etop.management.service.RoleService;
import com.etop.management.util.UserInfoUtil;

/**
 * @author lmc
 * @date 2016年8月23日
 */
@Controller
@RequestMapping("/bill/relief")
public class EtopBillReliefController extends BaseAppController {
	
	@Inject
	private EtopBillReliefService etopBillReliefService;
	@Inject
	private EtopBillService etopBillService;
	@Inject
	private EtopSequenceService etopSequenceService;
	@Inject
	private RoleService roleService;
	@Inject
	private RemindService remindService;
	
	@RequestMapping("/applyview.do")
	public String applyView(String billId,String amount, String overdueFine,ModelMap model) {
		model.put("billId", billId);
		model.put("amount", amount);
		model.put("overdueFine", overdueFine);
		return "bill/relief/apply";
	}
	
	@RequestMapping("/editview.do")
	public String editView(String reliefId, Boolean readonly, ModelMap model) {
		model.put("relief", etopBillReliefService.findById(reliefId));
		model.put("readonly", readonly==null?isReadOnly("/bill/delay/editview.do"):readonly);
		return "bill/relief/edit";
	}
	
	@RequestMapping("/auditlistview.do")
	public String reliefAuditView(ModelMap model) {
		model.put("readonly", !roleService.isHaveJurisdiction(UserInfoUtil.getUserRoleInfo(), Role.QX_YZSP));
		return "bill/relief/auditlist";
	}
	
	@RequestMapping("/detailview.do")
	public String detailView(String reliefId) {
		return "bill/relief/detail";
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public EtopPage<BillReliefWithUser> list(String billId,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("billId", billId);
		return etopBillReliefService.listWithUser(map, offset, limit);
	}
	
	@RequestMapping("/listwithbill.do")
	@ResponseBody
	public EtopPage<BillReliefBean> listWithBill(Boolean audited,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("audited", audited);
		map.put("parkIds", getParkIds());
		return etopBillReliefService.listWithBill(map, offset, limit);
	}
	
	@RequestMapping("/listByParkId.do")
	@ResponseBody
	public EtopPage<BillReliefWithUser> listByParkId(String parkId, String[] parkIds, Integer auditStatus,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> map = new HashMap<>();
//		map.put("parkId", getParkId(parkId));
		map.put("parkIds", parkIds==null?getParkIds():parkIds);
		map.put("auditStatus", auditStatus);
		return etopBillReliefService.listWithUserByParkId(map, offset, limit);
	}
	
	@RequestMapping("/apply.do")
	@ResponseBody
	@Transactional(rollbackOn=Exception.class)
	public ResultType apply(EtopBillRelief etopBillRelief) {
		EtopBill bill = etopBillService.findById(etopBillRelief.getBillId());
		if(bill == null)
			return ResultType.getFail("没有这个账单");
		if(bill.getAmount().subtract(bill.getAccountRemission()).compareTo(etopBillRelief.getAmountRemission()) < 0) {
			return ResultType.getFail("本金减免不能大于本金总额");
		}
		if(bill.getOverdueFine().subtract(bill.getOverdueRemission()).compareTo(etopBillRelief.getOverdueRemission()) < 0) {
			return ResultType.getFail("滞纳金减免不能大于滞纳金总额");
		}
		String reliefId = etopSequenceService.getFormatId(getParkCode(), "jm");
		etopBillRelief.setReliefId(reliefId);
		etopBillRelief.setApplicant(getUserId());
		etopBillRelief.setApplyTime(new Date());
		List<EtopUser> users = roleService.getJurisdiction(getParkId(null), Role.QX_YZSP);
		for(EtopUser user : users) {
			Remind remind = new Remind();
			remind.setTarget(user.getId());
			remind.setTitle("你有一个延期申请待审批");
			remind.setContent("你有一个延期申请待审批");
			remind.setRemindType(Remind.BILL);
			remindService.addRemind(remind);
		}
		if(etopBillReliefService.apply(etopBillRelief) > 0)
			return ResultType.getSuccess();
		else
			return ResultType.getFail();
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public ResultType update(EtopBillRelief etopBillRelief) {
		if(etopBillReliefService.updateSelective(etopBillRelief) > 0)
			return ResultType.getSuccess();
		else
			return ResultType.getFail();
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResultType delete(String reliefId) {
		String res = etopBillReliefService.delete(reliefId);
		if("success".equals(res))
			return ResultType.getSuccess();
		else
			return ResultType.getFail(res);
	}
	
	@RequestMapping("/auditBatch.do")
	@ResponseBody
	public ResultType audit(String[] reliefIds, boolean status) {
		int res = etopBillReliefService.audit(reliefIds, status, getUserId());
		return ResultType.getSuccess(res);
	}
	
	@RequestMapping("/audit.do")
	@ResponseBody
	public ResultType audit(String reliefId, boolean status) {
		EtopBillRelief relief = etopBillReliefService.findById(reliefId);
		if(relief == null)
			return ResultType.getFail("没有这个申请");
		if(status) {
			EtopBill bill = etopBillService.findById(relief.getBillId());
			if(bill == null)
				return ResultType.getFail("没有这个账单");
			if(bill.getAmount().subtract(bill.getAccountRemission()).compareTo(relief.getAmountRemission()) < 0)
				return ResultType.getFail("本金减免不能大于本金总额");
			if(bill.getOverdueFine().subtract(bill.getOverdueRemission()).compareTo(relief.getOverdueRemission()) < 0)
				return ResultType.getFail("滞纳金减免不能大于滞纳金总额");
		}
		int res = etopBillReliefService.audit(reliefId, status, getUserId());
		return ResultType.getSuccess(res);
	}
}
