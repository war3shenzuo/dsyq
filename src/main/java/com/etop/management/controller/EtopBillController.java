package com.etop.management.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.BillDelayWithUser;
import com.etop.management.bean.BillReliefWithUser;
import com.etop.management.bean.BillWithCompanyItem;
import com.etop.management.bean.Companyservice;
import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Park;
import com.etop.management.bean.Remind;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBill.AuditStatus;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopBill.BillStatus;
import com.etop.management.entity.EtopBill.BillType;
import com.etop.management.entity.EtopThreshold;
import com.etop.management.model.ContractModel;
import com.etop.management.service.CompanyServiceService;
import com.etop.management.service.ContractEnergyService;
import com.etop.management.service.ContractService;
import com.etop.management.service.EtopBillDelayService;
import com.etop.management.service.EtopBillReliefService;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopCompanyService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.service.RemindService;
import com.etop.management.service.RoleService;
import com.etop.management.service.UserService;
import com.etop.management.util.DateUtil;
import com.etop.management.util.LoggerUtil;
import com.etop.management.util.UserInfoUtil;
import com.etop.management.util.Util;

/**
 * 
 * @author lmc
 * @date 2016年8月23日
 */
@Controller
@RequestMapping("/bill")
public class EtopBillController extends BaseAppController {
	
	@Inject
	private EtopBillService etopBillService;
	@Inject
	private EtopBillDelayService etopBillDelayService;
	@Inject
	private EtopBillReliefService etopBillReliefService;
	@Inject
	private EtopSequenceService etopSequenceSevice;
	@Inject
	private EtopCompanyService etopCompanyService;
	@Inject
	private ContractService contractService;
	@Inject
	private RemindService remindService;
	@Inject
	private RoleService roleService;
	@Inject
	private UserService userService;
	@Inject
	private EtopThresholdService etopThresholdService;
	@Inject
	private ParkDao parkDao;
	@Inject
	private CompanyServiceService companyServiceService;
	@Inject
	private EtopBillDao etopBillDao;
	@Resource
	ContractEnergyService contractEnergyService;
	
	
	@RequestMapping("/clublistview.do")
	public String clubListView(ModelMap model) {
		EtopUser  user=UserInfoUtil.getUserInfo();
		if(EtopUser.TYPE_GROUP.equals(user.getUserType())){	//园区组管理员
			String parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			Park park =new Park();
			park.setParkGroupId(parkGroupId);
			List<Park> list = parkDao.getParkList(park);
			model.addAttribute("parks", list);
		}
		else {
			model.put("parkId", getParkId(null));
		}
		model.put("readonly", isReadOnly("/bill/clublistview.do"));
		return "bill/clublist";
	}
	
	@RequestMapping("/auditlistview.do")
	public String auditListView(ModelMap model) {
		model.put("readonly", !roleService.isHaveJurisdiction(UserInfoUtil.getUserRoleInfo(), Role.QX_YZSP));
		model.put("auditLevel", 2);
		return "bill/auditlist";
	}
	
	@RequestMapping("/financelistview.do")
	public String financeListView(ModelMap model) {
		model.put("readonly", !roleService.isHaveJurisdiction(UserInfoUtil.getUserRoleInfo(), Role.QX_CWSP));
		model.put("auditLevel", 1);
		return "bill/auditlist";
	}
	
	@RequestMapping("/detailview.do")
	public String detailView(String billId, Boolean readonly, ModelMap model) {
		EtopBill etopBill = etopBillService.findById(billId);
//		EtopCompany company = etopCompanyService.getCompInfoById(etopBill.getCompanyId());

		Map<String, Object> condition = new HashMap<>();
		condition.put("billId", billId);
		EtopPage<Companyservice> service = companyServiceService.getServiceByBillId(condition, 0, 100);
		for(Companyservice services : service.getRows()){
			etopBill.setNumber(services.getNumber());
			etopBill.setSubject(services.getSubject());
			etopBill.setCategory(services.getCategory());
			etopBill.setTotalPrice(services.getTotalPrice());
			etopBill.setUnitPrice(services.getUnitPrice());
		}
		condition.put("auditStatus", AuditStatus.CHECK_ACCEPT.value);
		EtopPage<BillDelayWithUser> delays = etopBillDelayService.listWithUser(condition, 0, 100);
		for(BillDelayWithUser delay : delays.getRows()) {
			etopBill.setDescription(etopBill.getDescription() + delay);
		}
		EtopPage<BillReliefWithUser> relieves = etopBillReliefService.listWithUser(condition, 0, 100);
		for(BillReliefWithUser relief : relieves.getRows()) {
			etopBill.setDescription(etopBill.getDescription() + relief);
		}

		model.put("etopBill", etopBill);
		model.put("billType", BillType.valueOf(etopBill.getBillType()).desc);
		model.put("billStatus", BillStatus.valueOf(etopBill.getBillStatus()).desc);
		model.put("billSource", BillSource.valueOf(etopBill.getBillSource()).desc);
		model.put("auditStatus", AuditStatus.valueOf(etopBill.getAuditStatus()).desc);
//		model.put("companyName", company==null?"":company.getCompanyName());
//		model.put("readonly", readonly==null?isReadOnly("/bill/clublistview.do"):readonly);
		if(etopBill.getAuditLevel() == etopBill.getAuditStatus()) {
			model.put("payReadonly", !roleService.isHaveJurisdiction(UserInfoUtil.getUserRoleInfo(), Role.QX_ZF));
			model.put("delayReadonly", !roleService.isHaveJurisdiction(UserInfoUtil.getUserRoleInfo(), Role.QX_YQSQ));
			model.put("reliefReadonly", !roleService.isHaveJurisdiction(UserInfoUtil.getUserRoleInfo(), Role.QX_JMSQ));
		}
		else {
			model.put("payReadonly", true);
			model.put("delayReadonly", true);
			model.put("reliefReadonly", true);
		}
		return "bill/detail";
	}
	
	@RequestMapping("/createview.do")
	public String createView() {
		return "bill/create";
	}
	
	@RequestMapping("/editview.do")
	public String editView(String billId, ModelMap model, Boolean readonly,
			@RequestParam(defaultValue="true") boolean editable) {
		EtopBill etopBill = etopBillService.findById(billId);
//		EtopCompany company = etopCompanyService.getCompInfoById(etopBill.getCompanyId());
		
		model.put("etopBill", etopBill);
//		model.put("company", company);
		model.put("billType", BillType.valueOf(etopBill.getBillType()).desc);
		model.put("billStatus", BillStatus.valueOf(etopBill.getBillStatus()).desc);
		model.put("billSource", BillSource.valueOf(etopBill.getBillSource()).desc);
		model.put("auditStatus", AuditStatus.valueOf(etopBill.getAuditStatus()).desc);
		model.put("readonly", readonly==null?isReadOnly("/bill/createview.do"):readonly);
		return "bill/edit";
	}
	
	@RequestMapping("/updateview.do")
	public String updateView(@RequestParam String billId, ModelMap model) {
		EtopBill bill = etopBillService.findById(billId);
		if(bill == null) {
			return null;
		}
		model.put("bill", bill);
		model.put("billType", BillType.valueOf(bill.getBillType()).desc);
		model.put("billSource", BillSource.valueOf(bill.getBillSource()).desc);
		return "bill/update";
	}
	
	@RequestMapping("/listall.do")
	@ResponseBody
	public EtopPage<EtopBill> listAll() {
		return etopBillService.listAll();
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public EtopPage<EtopBill> list(String parkId, String[] parkIds, String billId, Integer billType,
			Integer billStatus, String companyName, Integer billSource, Integer auditStatus, Integer auditLevel,
			String companyId, Boolean paid, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginDate,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkIds", parkIds==null?getParkIds():parkIds);
		condition.put("billId", billId);
		condition.put("billType", billType);
		condition.put("billStatus", billStatus);
		condition.put("companyId", companyId);
		condition.put("companyName", companyName);
		condition.put("billSource", billSource);
		condition.put("auditStatus", auditStatus);
		condition.put("auditLevel", auditLevel);
		condition.put("beginDate", beginDate);
		condition.put("endDate", endDate);
		condition.put("paid", paid);
		return etopBillService.list(condition, offset, limit);
	}
	
	@RequestMapping("/list2.do")
	@ResponseBody
	public EtopPage<EtopBill> list2(String parkId, String[] parkIds, String billId, Integer billType,
			Integer billStatus, String companyName, Integer billSource, Integer auditStatus, Integer auditLevel,
			String companyId, Boolean paid, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginDate,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkIds", parkIds==null?getParkIds():parkIds);
		condition.put("billId", billId);
		condition.put("billType", billType);
		condition.put("billStatus", billStatus);
		condition.put("companyId", companyId);
		condition.put("companyName", companyName);
		condition.put("billSource", billSource);
		condition.put("auditStatus", auditStatus);
		condition.put("auditLevel", auditLevel);
		condition.put("beginDate", beginDate);
		condition.put("endDate", endDate);
		condition.put("paid", paid);
		if(companyId == "" && billId ==""){
			return null;
		}else{
			return etopBillService.list(condition, offset, limit);
		}
	}

	@RequestMapping("/listwithfloor.do")
	@ResponseBody
	public EtopPage<EtopBill> listWithFloor(String parkId, String[] parkIds, String billId, Integer billType,
			Integer billStatus, String companyName, Integer billSource, Integer auditStatus, Integer auditLevel,
			String companyId, Boolean paid, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginDate,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate,
			String buildingSele, String floorSele, String blockSele, String roomNum,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkIds", parkIds==null?getParkIds():parkIds);
		condition.put("billId", billId);
		condition.put("billType", billType);
		condition.put("billStatus", billStatus);
		condition.put("companyId", companyId);
		condition.put("companyName", companyName);
		condition.put("billSource", billSource);
		condition.put("auditStatus", auditStatus);
		condition.put("auditLevel", auditLevel);
		condition.put("beginDate", beginDate);
		condition.put("endDate", endDate);
		condition.put("paid", paid);
		condition.put("buildingSele", buildingSele);
		condition.put("floorSele", floorSele);
		condition.put("blockSele", blockSele);
		condition.put("roomNum", roomNum);
		return etopBillService.listWithFloor(condition, offset, limit);
	}
	
	@Deprecated
	@RequestMapping("/listactivewithcomp.do")
	@ResponseBody
	public EtopPage<BillWithCompanyItem> listActiveWithComp(String parkId, String[] parkIds, Integer billType,
			Integer billStatus, String companyId, Integer billSource,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkIds", parkIds==null?getParkIds():parkIds);
		condition.put("billType", billType);
		condition.put("billStatus", billStatus);
		condition.put("companyId", companyId);
		condition.put("billSource", billSource);
		return etopBillService.listActiveWithComp(condition, offset, limit);
	}
	
	@RequestMapping("/toauditlist.do")
	@ResponseBody
	public EtopPage<EtopBill> toAuditList(String parkId, String[] parkIds, Integer billType,
			int auditLevel, String companyName, Integer billSource, String billId,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkIds", parkIds==null?getParkIds():parkIds);
		condition.put("billId", billId);
		condition.put("billType", billType);
		condition.put("auditStatus", auditLevel - 1);
		condition.put("companyName", companyName);
		condition.put("billSource", billSource);
		return etopBillService.list(condition, offset, limit);
	}
	
	@RequestMapping("/findById.do")
	@ResponseBody
	public ResultType findById(String billId) {
		EtopBill etopBill = etopBillService.findById(billId);
		if(etopBill == null)
			return ResultType.getFail();
		else
			return ResultType.getSuccess(etopBill);
	}
	
	@RequestMapping("/add.do")
	@ResponseBody
	@Transactional(rollbackFor=Exception.class)
	public ResultType add(EtopBill etopBill) {
		
		etopBill.setParkId(getParkId(etopBill.getParkId()));
		etopBill.setBillId(etopSequenceSevice.getFormatId(getParkCode(), "zd"));
		etopBill.setCreatedTime(new Date());
		etopBill.setAmountPaid(BigDecimal.ZERO);
		etopBill.setAccountRemission(BigDecimal.ZERO);
		etopBill.setOverdueFine(BigDecimal.ZERO);
		etopBill.setOverdueRemission(BigDecimal.ZERO);
		etopBill.setOverdueRate(new BigDecimal(etopThresholdService.getValue(etopBill.getParkId(),
				EtopThreshold.ThresholdKey.OverdueRate.name)));
		etopBill.setAuditLevel(2);
		
		if(etopBillService.add(etopBill) > 0) {
			if(etopBill.getBillType() == BillType.INCOME.value && etopBill.getEndTime() != null) {
				ContractModel cm = contractService.getContractByNo(etopBill.getBillNoOut());
				Contract entity = new Contract();
				Util.translate(cm, entity);
				if(entity.getContractEndDate().before(etopBill.getEndTime())
						|| entity.getContractStartDate().after(etopBill.getStartTime()))
					throw new RuntimeException("账单覆盖时间不能超过合同有效期");
				entity.lastBalanceDate = etopBill.getEndTime();
				contractService.updateContract(Util.getOpInfo(), entity);
			}
			String content=String.format("新生成%s账单，请及时审核！ 编号：%s，客户：%s",
					BillSource.valueOf(etopBill.getBillSource()).desc,
					etopBill.getBillId(),etopBill.getCompanyName());
			
			this.remindService.remind(etopBill.getParkId(), Role.QX_CWSP, "合同审批", content);			
			return ResultType.getSuccess();
		}
		else {
			return ResultType.getFail();
		}
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public ResultType update(EtopBill etopBill) {
		if(etopBillService.updateSelective(etopBill) > 0)
			return ResultType.getSuccess();
		else
			return ResultType.getFail();
	}
	
	@RequestMapping("/refuseAndUpdate.do")
	@ResponseBody
	@Transactional
	public ResultType refuseAndUpdate(EtopBill etopBill) {
		String[] billIds = {etopBill.getBillId()};
		etopBillService.financeAudit(billIds, AuditStatus.FINANCE_REFUSE.value, getUserId(),
				AuditStatus.FINANCE_REFUSE.desc);
		
		etopBill.setParkId(getParkId(etopBill.getParkId()));
		etopBill.setBillId(etopSequenceSevice.getFormatId(getParkCode(), "zd"));
		etopBill.setCreatedTime(new Date());
		etopBill.setAmountPaid(BigDecimal.ZERO);
		etopBill.setAccountRemission(BigDecimal.ZERO);
		etopBill.setOverdueFine(BigDecimal.ZERO);
		etopBill.setOverdueRemission(BigDecimal.ZERO);
		etopBill.setOverdueRate(new BigDecimal(etopThresholdService.getValue(etopBill.getParkId(),
				EtopThreshold.ThresholdKey.OverdueRate.name)));
		etopBill.setAuditLevel(2);
		etopBillService.add(etopBill);
		
		billIds[0] = etopBill.getBillId();
		etopBillService.financeAudit(billIds, AuditStatus.FINANCE_ACCEPT.value, getUserId(), "财务修改");
		
		return ResultType.getSuccess();
	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public ResultType delete(String[] billIds) {
		int res = etopBillService.deletes(billIds);
		return ResultType.getSuccess(res);
	}
	
	@RequestMapping("/detail.do")
	@ResponseBody
	public ResultType detail(String billId) {
		EtopBill etopBill = etopBillService.findById(billId);
		if(etopBill != null)
			return ResultType.getSuccess(etopBill);
		else
			return ResultType.getFail();
	}
	
	@RequestMapping("/audit.do")
	@ResponseBody
	@Transactional
	public ResultType audit(String[] billIds, boolean status, int level) throws Exception {
		int auditStatus = status ? level : -level;
		AuditStatus stat = AuditStatus.valueOf(auditStatus);
		int res = 0;
		switch(stat) {
		case CHECK_ACCEPT:
//			res = etopBillService.audit(billIds, auditStatus, getUserId(), "");
//    		for(String id :billIds){
    		for(int i=0;i<billIds.length-1;i++){
    			 etopBillService.auditTwo(billIds[i], auditStatus, getUserId(), "");
    		}
    		res = billIds.length-1;
			Map<String, Object> condition = new HashMap<>();
			condition.put("billIds", billIds);
			condition.put("auditLevel", level);
			List<EtopBill> bills = etopBillService.list(condition);
			for(EtopBill bill : bills) {
				EtopUser user = userService.getUserInfoByCompanyId(bill.getCompanyId());
				if(user != null) {
					Remind remind = new Remind();
					remind.setTarget(user.getId());
					remind.setTitle("你有一个新账单");
					remind.setContent("你有一个新账单");
					remind.setRemindType(Remind.BILL);
					remindService.addRemind(remind);
				}
			}
			break;
		case CHECK_REFUSE:
			res = etopBillService.audit(billIds, 0, getUserId(), stat.desc);
			if(res > 0) {
				List<EtopUser> users = roleService.getJurisdiction(getParkId(null), Role.QX_CWSP);
				for(EtopUser user : users) {
					Remind remind = new Remind();
					remind.setTarget(user.getId());
					remind.setTitle("你有"+res+"个账单被园长拒绝通过");
					remind.setContent("你有"+res+"个账单被园长拒绝通过");
					remind.setRemindType(Remind.BILL);
					remindService.addRemind(remind);
				}
			}
			break;
		case FINANCE_ACCEPT:
			res = etopBillService.financeAudit(billIds, auditStatus, getUserId(), "");
			if(res > 0) {
				List<EtopUser> users = roleService.getJurisdiction(getParkId(null), Role.QX_YZSP);
				for(EtopUser user : users) {
					Remind remind = new Remind();
					remind.setTarget(user.getId());
					remind.setTitle("你有"+res+"个账单待审批");
					remind.setContent("你有"+res+"个账单待审批");
					remind.setRemindType(Remind.BILL);
					remindService.addRemind(remind);
				}
			}
			break;
		case FINANCE_REFUSE:
			res = etopBillService.financeAudit(billIds, auditStatus, getUserId(), stat.desc+";");
			break;
		case UNCHECK:
			break;
		default:
			break;
		}
		
		return ResultType.getSuccess(res);
	}
	
	@RequestMapping("/recalculateOverdue.do")
	@ResponseBody
	public ResultType recalculateOverdue(String[] billIds) {
		int res = 0;
		if(billIds == null || billIds.length == 0) {
			Map<String, Object> map = new HashMap<>();
			map.put("parkIds", getParkIds());
			List<EtopBill> bills = etopBillService.list(map);
			billIds = new String[bills.size()];
			for(int i=0; i<billIds.length; i++) {
				billIds[i] = bills.get(i).getBillId();
			}
		}
		if(billIds.length > 0)
			res = etopBillService.recalculateOverdueFine(billIds);

		return ResultType.getSuccess(res);
	}
	
	@RequestMapping("/userlistview.do")
	public String userListView() {
		return "bill/userlist";
	}
	
	@Deprecated
	@RequestMapping("/listwithUser.do")
	@ResponseBody
	public EtopPage<BillWithCompanyItem> listwithUser(String parkId, String[] parkIds, Integer billType,
			Integer billStatus, String companyId, Integer billSource,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {			
	    EtopUser etopUser= UserInfoUtil.getUserInfo();
		Map<String, Object> condition = new HashMap<>();
//		condition.put("parkIds", parkIds==null?getParkIds():parkIds);
		condition.put("billType", billType);
		condition.put("billStatus", billStatus);
		condition.put("companyId", etopUser.getCompanyId());
		condition.put("billSource", billSource);
		return etopBillService.listActiveWithComp(condition, offset, limit);
	}

	@RequestMapping("/Userdetailview.do")
	public String UserdetailView(String billId, Boolean readonly, ModelMap model) {
		EtopBill etopBill = etopBillService.findById(billId);
		EtopCompany company = etopCompanyService.getCompInfoById(etopBill.getCompanyId());
		model.put("etopBill", etopBill);
		model.put("billType", BillType.valueOf(etopBill.getBillType()).desc);
		model.put("billStatus", BillStatus.valueOf(etopBill.getBillStatus()).desc);
		model.put("billSource", BillSource.valueOf(etopBill.getBillSource()).desc);
		model.put("auditStatus", AuditStatus.valueOf(etopBill.getAuditStatus()).desc);
		model.put("companyName", company==null?"":company.getCompanyName());
//		model.put("readonly", readonly==null?isReadOnly("/bill/clublistview.do"):readonly);
		return "bill/userdetail";
	}
	
	@RequestMapping("/getStatistics.do")
	@ResponseBody
	public ResultType getStatistics(String parkId, String[] parkIds, String billId, Integer billType,
			Integer billStatus, String companyName, Integer billSource, Integer auditStatus, Integer auditLevel,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date beginDate,
			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date endDate,
			String buildingSele, String floorSele, String blockSele, String roomNum) {
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkIds", parkIds==null?getParkIds():parkIds);
		condition.put("billId", billId);
		condition.put("billType", billType);
		condition.put("billStatus", billStatus);
		condition.put("companyName", companyName);
		condition.put("billSource", billSource);
		condition.put("auditStatus", auditStatus);
		condition.put("auditLevel", auditLevel);
		condition.put("beginDate", beginDate);
		condition.put("endDate", endDate);
		condition.put("buildingSele", buildingSele);
		condition.put("floorSele", floorSele);
		condition.put("blockSele", blockSele);
		condition.put("roomNum", roomNum);
		return ResultType.getSuccess(etopBillService.getStatisticsWithFloor(condition));
	}
	
	@RequestMapping("/updateDeposit.do")
	@ResponseBody
	public void updateDeposit() {

		List<EtopBill> bill = etopBillDao.searchDeposit();
		for(EtopBill etopBill : bill){
			String str= etopBill.getDescription();
			String shouzimu = str.substring(0, 2);
			if(shouzimu.equals("租赁") || shouzimu.equals("退还")){
				String jieguo = str.substring(str.indexOf("：")+1,str.indexOf(","));
				etopBill.setBillNoOut(jieguo);
				etopBillDao.updateByBillNoOut(etopBill);
			}
		}
	}
	
	@RequestMapping("/test.do")
	@ResponseBody
	public void billForEnergy()
	{
		
		long start=System.currentTimeMillis();
		
		LoggerUtil.info("bill energy job start...");
		
		
		int count=0;
		
		List<Park> parkList=this.parkDao.getParkNameList();
		
		for(Park park : parkList)
		{
			if("0".equals(park.getActivated()))
			{
				continue;
			}
			
			count=this.contractEnergyService.generateBillByPark(park.getId(), Util.formatDate(new Date()));
			
			if(count>=0)
			{
				String content=String.format("园区(%s)能源已出帐(%d)，请审核。",park.getParkName(),count);
					
				this.remindService.remind(park.getId(), Role.QX_YZSP, "能源出帐", content);
				
				LoggerUtil.info(content);
			}
			if(count==-3)
			{
				String content=String.format("园区(%s)能源自动出帐失败，请管理员查看。",park.getParkName());
				
				this.remindService.remind(park.getId(), Role.QX_YQTX, "能源出帐", content);
				
				LoggerUtil.info(content);
			}
			
		}
		
		
		LoggerUtil.info(String.format("bill energy job finished. spend time %d,count:%d",
				System.currentTimeMillis()-start
				,count)			
				);
		
	}
}
