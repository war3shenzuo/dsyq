package com.etop.management.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.Contract;
import com.etop.management.bean.EtopAllGoods;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.Park;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.ContractDao;
import com.etop.management.dao.EtopGoodsDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.dao.ParkGroupDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopBill.AuditStatus;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopBill.BillStatus;
import com.etop.management.entity.EtopBill.BillType;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopGoods;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServicePurchase;
import com.etop.management.entity.EtopThreshold.ThresholdKey;
import com.etop.management.service.EtopBillService;
import com.etop.management.service.EtopGoodsService;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopServicePurchaseService;
import com.etop.management.service.EtopServiceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.service.ServiceQuotationService;
import com.etop.website.service.WebParkService;
/**
 * 
 * <br>
 * <b>功能：</b>EtopServicePurchaseController<br>
 * <br>
 */ 
@Controller
@RequestMapping("/purchase")
public class EtopServicePurchaseController extends BaseAppController{
	
	private final static Logger LOGGER= Logger.getLogger(EtopServicePurchaseController.class);
	@Autowired 
	private EtopServicePurchaseService etopServicePurchaseService; 
	
	@Resource 
	private ParkGroupDao parkGroupDao;
	
	@Resource
	private ParkDao parkDao;
	
	@Resource 
	private EtopSequenceService etopSequenceSevice;
	
	@Resource
	private EtopThresholdService etopThresholdService;
	
	@Resource
	private EtopBillService etopBillService;
	
	@Resource
	private EtopServiceService etopServiceService;
	
	@Resource
	private EtopGoodsDao etopGoodDao;
	@Resource
	ServiceQuotationService serviceQuotationService;
	@Resource
	WebParkService webParkService;
	@Resource 
	private EtopGoodsService etopGoodsService; 
	@Resource 
	private ContractDao contractDao; 
	
	//首页
	@RequestMapping("/index.do")
	public String  index(Model model){
		
		EtopUser  user=UserInfoUtil.getUserInfo();
		String parkGroupId="";
		String page="";
		if("4".equals(user.getUserType())){//园区组管理员
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			Park park =new Park();
			park.setParkGroupId(parkGroupId);
			List<Park> list = parkDao.getParkList(park);
			if(list.size()>0){
				model.addAttribute("defaultParkId", list.get(0).getId());
			}
			model.addAttribute("parks", list);
			page="purchase/index2";
		}else{
			parkGroupId=UserInfoUtil.getUserParkInfo().getParkGroupId();
			page="purchase/index";
			
			
		}
		
		return page;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getServiceList.do")
	public EtopPage<EtopService> getServiceList(EtopService service ,String quotationId, String quotationName,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String serviceStatus, String serviceNo
			, String category, String goodName, String number, String totalPrice, String companyName,String applyTime, String completeTime,String applyTime2, String completeTime2, String parkId) {

		EtopPage<EtopService> page = null;

		try {
			Map<String, Object> condition = new HashMap<>();
			EtopUser  user=UserInfoUtil.getUserInfo();
			if("3".equals(user.getUserType())){//园区组管理员
				service.setParkId(user.getParkId());
				condition.put("parkId",user.getParkId());
			}else{
				condition.put("parkId",parkId);
			}
			condition.put("serviceNo",serviceNo);
			condition.put("serviceStatus",serviceStatus);
			condition.put("category",category);
			condition.put("goodName",goodName);
			condition.put("number",number);
			condition.put("totalPrice",totalPrice);
			condition.put("companyName",companyName);
			condition.put("applyTime",applyTime);
			condition.put("completeTime",completeTime);
			condition.put("applyTime2",applyTime2);
			condition.put("completeTime2",completeTime2);
			page = etopServicePurchaseService.getServiceList(condition, offset, limit);
		} catch (Exception e) {

			LOGGER.error("查询服务列表失败",e);

			e.printStackTrace();
		}

		return page;

	}
	
	@ResponseBody
	@RequestMapping(value = "/goods.do")
	public EtopPage<EtopAllGoods> goods(EtopAllGoods goods) {

		EtopPage<EtopAllGoods> page = null;

		try {
			EtopUser  user=UserInfoUtil.getUserInfo();
			if("3".equals(user.getUserType())){//园区组管理员
				goods.setParkId(user.getParkId());
		
			}
			page = etopServicePurchaseService.calculateGoods(goods);

		} catch (Exception e) {

			LOGGER.error("查询服务列表失败",e);

			e.printStackTrace();
		}

		return page;

	}
	
	@RequestMapping("/calculateGoods.do")
	@ResponseBody
	public EtopPage<EtopAllGoods> calculateGoods(EtopService service ,String quotationId, String quotationName,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit, String serviceStatus, String serviceNo
			, String category, String goodName, String number, String totalPrice, String companyName,String applyTime, String completeTime)  {
		
		Map<String, Object> condition = new HashMap<>();
		condition.put("parkId",UserInfoUtil.getUserInfo().getParkId());
		condition.put("serviceNo",serviceNo);
		condition.put("serviceStatus",serviceStatus);
		condition.put("category",category);
		condition.put("goodName",goodName);
		condition.put("number",number);
		condition.put("totalPrice",totalPrice);
		condition.put("companyName",companyName);
		condition.put("applyTime",applyTime);
		condition.put("completeTime",completeTime);

		
		return  etopServicePurchaseService.calculateGoods(condition, offset, limit);
		
	}
	
	//获取楼信息
	@RequestMapping(value="/getserviceInfo.do")
	public String getserviceInfo(String serviceId,Model model){
		
		
		try {
			
			EtopService service = etopServicePurchaseService.getServiceInfo(serviceId);
			
			EtopServicePurchase purchase=etopServicePurchaseService.getServicePurchaseInfo(serviceId);
			
			model.addAttribute("service", service);
			
			model.addAttribute("purchase", purchase);
			
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType()) && service.getServiceStatus()==202){
				model.addAttribute("bill", 1);
			}else{
				model.addAttribute("bill", 0);
			}
			
			
		} catch (Exception e) {
			
			LOGGER.error("查询服务信息失败！",e);
			
			e.printStackTrace();
		}
		
		return "purchase/serviceInfo";
	}
	

	@RequestMapping("/addBill.do")
	@ResponseBody
	public ResultType addBill(String serviceId, ModelMap model, Companyservice companyservice, String message) {
		EtopService service = etopServicePurchaseService.getServiceInfo(serviceId);
		EtopServicePurchase purchase=etopServicePurchaseService.getServicePurchaseInfo(serviceId);
		String amout =purchase.getTotalPrice().toString();
		if("0.00".equals(amout)){
			etopServiceService.updateStatus( serviceId,300, companyservice, message);
			return ResultType.getSuccess();
		}else{
		EtopBill etopBill =new EtopBill();
		EtopUser  user=UserInfoUtil.getUserInfo();
		etopBill.setParkId(user.getParkId());
		etopBill.setBillId(etopSequenceSevice.getFormatId(getParkCode(), "zd"));
		etopBill.setBillType(BillType.INCOME.value);
		etopBill.setBillStatus(BillStatus.UNPAID.value);
		etopBill.setBillSource(BillSource.PARK_SERVICE.value);
		etopBill.setCompanyId(service.getCompanyId());
		etopBill.setCompanyName(service.getCompanyName());
		etopBill.setAmount(purchase.getTotalPrice());
		
	    int day=Double.valueOf(etopThresholdService.getValue(getParkId(user.getParkId()), ThresholdKey.deadline.name)).intValue();//阀值
		etopBill.setDeadline( new LocalDateTime().plusDays(day).toDate(TimeZone.getDefault()));
		
		etopBill.setCreatedTime(new Date());
		etopBill.setAmountPaid(BigDecimal.ZERO);
		etopBill.setAccountRemission(BigDecimal.ZERO);
		etopBill.setOverdueFine(BigDecimal.ZERO);
		etopBill.setOverdueRemission(BigDecimal.ZERO);
		etopBill.setOverdueRate(BigDecimal.ZERO);
		etopBill.setAuditStatus(AuditStatus.UNCHECK.value);
		etopBill.setAuditLevel(2);	
		etopBill.setBillNoOut(serviceId);
		if(etopBillService.add(etopBill) > 0){
			etopServiceService.updateStatus( serviceId,300, companyservice, message);
			return ResultType.getSuccess();
		}else{
			return ResultType.getFail();
		}
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/add.do")
	public ResultType add(EtopServicePurchase param,EtopService service, EtopFloorRoom etopFloorRoom){
		
		ResultType result = null;
		
		try {
			
			etopServicePurchaseService.add(param,service,etopFloorRoom);
			
			result = ResultType.getSuccess("提交服务成功");
		
		} catch (Exception e) {
			
			result = ResultType.getFail("提交服务失败");
			
			LOGGER.error("提交服务失败");
			
			e.printStackTrace();
		}
		
		return result;
		
	}


	@RequestMapping(value = "/purchaseApply.do")
	public String purchaseApply(String companyId, Model model, String service_type,EtopGoods EtopGoods) throws Exception{



//		List<ServiceQuotation> item =  serviceQuotationService.getQuotationServiceList( service_type);
		EtopPage<EtopGoods> page = null;
		EtopUser  user=UserInfoUtil.getUserInfo();
		EtopGoods.setParkId(user.getParkId());
		List<EtopGoods> category =  etopGoodsService.categoryList(user.getParkId());
		model.addAttribute("category",category);
//		List<EtopFloorRoom> room =  serviceQuotationService.selectRoom(user.getCompanyId());
		List<Contract> room =  contractDao.getRoomList(user.getCompanyId());
		model.addAttribute("room",room);
		List<EtopGoods> item = etopGoodDao.queryByList(EtopGoods.getCriteria());
		model.addAttribute("item",item);
		Map<String,String> parkservice=webParkService.searchName(service_type);
		model.addAttribute("parkservice",parkservice);
		return "purchase/purchaseApply";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getPurchaseList.do")
	public List<EtopGoods> getRecruitmentList(String categories,EtopGoods EtopGoods) throws Exception{
		EtopGoods.setParkId(UserInfoUtil.getUserInfo().getParkId());
		EtopGoods.setActivated("1");
		return etopGoodDao.queryByList(EtopGoods.getCriteria());
	}

}
