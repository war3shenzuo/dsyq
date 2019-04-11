package com.etop.management.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.BillDelayBean;
import com.etop.management.bean.BillDelayWithUser;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Remind;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBillDelay;
import com.etop.management.service.EtopBillDelayService;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.RemindService;
import com.etop.management.service.RoleService;
import com.etop.management.util.UserInfoUtil;

/**
 *  
 * @author lmc
 * @date 2016年8月23日
 */
@Controller
@RequestMapping("/bill/delay")
public class EtopBillDelayController extends BaseAppController {
	
	@Inject
	private EtopBillDelayService etopBillDelayService;
	@Inject
	private EtopBillService etopBillService;
	@Inject
	private EtopSequenceService etopSequenceService;
	@Inject
	private RoleService roleService;
	@Inject
	private RemindService remindService;
	
	@RequestMapping("/applyview.do")
	public String delayApplyView(String billId, ModelMap model) {
		model.addAttribute("billId", billId);
		return "bill/delay/apply";
	}
	
	@RequestMapping("/auditlistview.do")
	public String delayAuditListView(ModelMap model) {
		model.put("readonly", !roleService.isHaveJurisdiction(UserInfoUtil.getUserRoleInfo(), Role.QX_YZSP));
		return "bill/delay/auditlist";
	}
	
	@RequestMapping("/detailview.do")
	public String detailView() {
		return "bill/delay/detail";
	}
	
	@RequestMapping("/editview.do")
	public String editView(String delayId, Boolean readonly, ModelMap model) {
		model.addAttribute("delay", etopBillDelayService.findById(delayId));
		model.addAttribute("readonly", readonly==null?isReadOnly("/bill/delay/editview.do"):readonly);
		return "bill/delay/edit";
	}
	
	@RequestMapping("/apply.do")
	@ResponseBody
	public ResultType apply(EtopBillDelay etopBillDelay) {
		EtopBill bill = etopBillService.findById(etopBillDelay.getBillId());
		if(bill == null) {
			return ResultType.getFail("没有这个账单");
		}
		if(bill.getDeadline().after(etopBillDelay.getDelayTime())) {
			return ResultType.getFail("延期要到截止日期之后");
		}
		etopBillDelay.setDelayId(etopSequenceService.getFormatId(getParkCode(), "yq"));
		etopBillDelay.setApplicant(getUserId());
		etopBillDelay.setApplyTime(new Date());
		List<EtopUser> users = roleService.getJurisdiction(getParkId(null), Role.QX_YZSP);
		for(EtopUser user : users) {
			Remind remind = new Remind();
			remind.setTarget(user.getId());
			remind.setTitle("你有一个延期申请待审批");
			remind.setContent("你有一个延期申请待审批");
			remind.setRemindType(Remind.BILL);
			remindService.addRemind(remind);
		}
		if(etopBillDelayService.apply(etopBillDelay) > 0)
			return ResultType.getSuccess();
		else
			return ResultType.getFail();
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public EtopPage<BillDelayWithUser> list(String billId,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("billId", billId);
		return etopBillDelayService.listWithUser(map, offset, limit);
	}
	
	@RequestMapping("/listwithbill.do")
	@ResponseBody
	public EtopPage<BillDelayBean> listWithBill(Boolean audited,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("parkIds", getParkIds());
		map.put("audited", audited);
		return etopBillDelayService.listWithBill(map, offset, limit);
	}
	
	@RequestMapping("/listByParkId.do")
	@ResponseBody
	public EtopPage<BillDelayWithUser> listByParkId(String parkId, String[] parkIds, Integer auditStatus,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> map = new HashMap<>();
//		map.put("parkId", getParkId(parkId));
		map.put("parkIds", parkIds==null?getParkIds():parkIds);
		map.put("auditStatus", auditStatus);
		return etopBillDelayService.listWithUserByParkId(map, offset, limit);
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResultType delete(String delayId) {
		EtopBillDelay delay = etopBillDelayService.findById(delayId);
		if(delay == null) {
			return ResultType.getFail("没有这个申请");
		}
		else if(delay.getAuditStatus() != EtopBillDelay.AuditStatus.UNCHECK.value) {
			return ResultType.getFail("已审核申请不能删除");
		}
		else if(etopBillDelayService.delete(delayId) > 0) {
			return ResultType.getSuccess();
		}
		else {
			return ResultType.getFail();
		}
	}
	
	@RequestMapping("/auditBatch.do")
	@ResponseBody
	public ResultType audit(String[] delayIds, boolean status) {
		int res = etopBillDelayService.audit(delayIds, status, getUserId());
		return ResultType.getSuccess(res);
	}
	
	@RequestMapping("/audit.do")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public ResultType audit(String delayId, boolean status) {
		EtopBillDelay delay = etopBillDelayService.findById(delayId);
		if(delay == null)
			return ResultType.getFail("没有这个申请");
		if(status) {
			EtopBill bill = etopBillService.findById(delay.getBillId());
			if(bill == null)
				return ResultType.getFail("没有这个账单");
			if(bill.getDeadline().after(delay.getDelayTime()))
				return ResultType.getFail("延期要到截止日期之后");
		}
		int res = etopBillDelayService.audit(delayId, status, getUserId());
		return ResultType.getSuccess(res);
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public ResultType update(EtopBillDelay etopBillDelay) {
		EtopBillDelay delay = etopBillDelayService.findById(etopBillDelay.getDelayId());
		if(delay == null) {
			return ResultType.getFail("没有这个申请");
		}
		else if(delay.getAuditStatus() != EtopBillDelay.AuditStatus.UNCHECK.value) {
			return ResultType.getFail("已审核申请不能删除");
		}
		else if(etopBillDelayService.updateSelective(etopBillDelay) > 0) {
			return ResultType.getSuccess();
		}
		else {
			return ResultType.getFail();
		}
	}
}
