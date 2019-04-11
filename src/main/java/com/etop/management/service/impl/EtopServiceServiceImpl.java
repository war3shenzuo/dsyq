package com.etop.management.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.Criteria;
import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.Park;
import com.etop.management.bean.Remind;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.Role;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.dao.EtopSequenceDao;
import com.etop.management.dao.EtopServiceDao;
import com.etop.management.dao.EtopServiceFacilityDao;
import com.etop.management.dao.EtopServicePurchaseDao;
import com.etop.management.dao.EtopServiceTypeDao;
import com.etop.management.dao.ParkDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.etop.management.entity.EtopServicePurchase;
import com.etop.management.entity.EtopServiceType;
import com.etop.management.entity.EtopBill.AuditStatus;
import com.etop.management.entity.EtopBill.BillSource;
import com.etop.management.entity.EtopBill.BillStatus;
import com.etop.management.entity.EtopBill.BillType;
import com.etop.management.entity.EtopThreshold.ThresholdKey;
import com.etop.management.service.EtopSequenceService;
import com.etop.management.service.EtopServiceService;
import com.etop.management.service.EtopThresholdService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.ServiceQuotation;
import com.etop.website.dao.ParkserviceDao;
import com.etop.website.dao.ServiceQuotationDao;
import com.etop.website.service.ServiceQuotationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceService<br>
 */
@Service("etopServiceService")
public class  EtopServiceServiceImpl  implements EtopServiceService {

	
	@Resource 
    private EtopServiceDao etopServiceDao;
	
	@Resource
	private EtopServiceTypeDao etopServiceTypeDao;
	@Resource
	ServiceQuotationDao serviceQuotationDao;
	@Resource
	ParkserviceDao parkserviceDao;
	@Resource
	EtopBillDao etopBillDao;
	@Resource
	private EtopServicePurchaseDao etopServicePurchaseDao;
	@Resource
	private EtopSequenceDao etopSequenceDao;
	@Resource
	private EtopThresholdService etopThresholdService;
	@Resource
	private EtopServiceService etopServiceService;
	@Resource 
	private EtopSequenceService etopSequenceSevice;
	@Resource 
	private ParkDao parkDao;
	@Resource
	ServiceQuotationService serviceQuotationService;
	@Override
	public EtopPage<EtopService> getServiceList(EtopService service) {
		
		
		EtopPage<EtopService> page = new EtopPage<EtopService>();
		

		int BTablePageNum = (service.getOffset() / service.getLimit())+ 1;

		// 设置分页
		PageHelper.startPage(BTablePageNum , service.getLimit());

		List<EtopService> list =etopServiceDao.selectListWithTypeName(service);


		PageInfo<EtopService> table = new PageInfo<EtopService>(list);

		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
		
	}

	@Override
	public EtopService getServiceInfo(String serviceId) {
		
		return etopServiceDao.selectObjectWithTypeName(serviceId);
		
	}

	@Override
	public List<EtopServiceType> getServiceTypeList() {
		
		return etopServiceTypeDao.queryByList(new Criteria());
	}

	@Override
	public EtopPage<EtopServiceType> getServiceTypeList(EtopServiceType type) {

		EtopPage<EtopServiceType> page = new EtopPage<EtopServiceType>();
		

		int BTablePageNum = (type.getOffset() / type.getLimit())+ 1;

		// 设置分页
		PageHelper.startPage(BTablePageNum , type.getLimit());

		List<EtopServiceType> list =etopServiceTypeDao.queryByListwithPGId(type);


		PageInfo<EtopServiceType> table = new PageInfo<EtopServiceType>(list);

		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
	}

	@Override
	public int bindParkGroup(String parkGroupId, List<String> typeCodes) {
		EtopServiceType type =new EtopServiceType();
		
		type.setParkgroupId(parkGroupId);
		
		List<String> has=etopServiceTypeDao.queryByListwithPGId(type).stream().map(t ->{return t.getServiceCode();}).collect(Collectors.toList());
		
		typeCodes=new ArrayList<String>(typeCodes);
		
		typeCodes.removeAll(has);
		
		List<Map<String,Object>> res=new ArrayList<Map<String,Object>>();
		for(String t :typeCodes){
			Map<String,Object> map =new HashMap<String, Object>();
			map.put("id",UUID.randomUUID().toString());
			map.put("parkGroupId", parkGroupId);
			map.put("type", t);
			res.add(map);
		}
		return etopServiceTypeDao.bindParkGroup(res) ;
	}

	@Override
	public int unbindParkGroup(String parkGroupId, List<String> typeCodes) {
		Map<String,Object> map =new HashMap<String, Object>();
		map.put("parkGroupId", parkGroupId);
		map.put("types", typeCodes);
		return etopServiceTypeDao.unbindParkGroup(map) ;
	}

	@Override
	public List<EtopServiceType> getServiceTypeListByPGId(EtopServiceType type) {
		
		return etopServiceTypeDao.queryByListwithPGId(type);
	}

	@Override
	public int addServiceType(EtopServiceType serviceType) {
		
		EtopServiceType checkServiceType=etopServiceTypeDao.queryObject(new EtopServiceType(serviceType.getServiceCode()));
		
		if(checkServiceType==null){
		
			return etopServiceTypeDao.add(serviceType);
		
		}else{
			return -1;
		}
	}

	@Override
	public int updateServiceType(EtopServiceType serviceType) {
		EtopServiceType checkServiceType=etopServiceTypeDao.queryObject(new EtopServiceType(serviceType.getServiceCode()));
		
		if(checkServiceType!=null){
		
			return etopServiceTypeDao.update(serviceType);
		
		}else{
			return -1;
		}
	}

	@Override
	public EtopServiceType getServiceTypeInfo(EtopServiceType serviceType) {
		return  etopServiceTypeDao.queryObject(serviceType);
	}

	@Override
	public int activeOrClosePark(EtopServiceType serviceType) {
		return etopServiceTypeDao.activeOrClosePark(serviceType);
	}

	@Override
	public ResultType updateStatus(String serviceId, int status, Companyservice companyservice, String message) {
		EtopService service =new EtopService();
		service.setServiceId(serviceId);
		service.setServiceStatus(status);
		if(status ==102){
	      message = "待回执：";
		}else if(status ==201){
		      message = "否认完工：";
			}
		else if(status ==201){
		      message = "撤销：";
			}
		else if(status ==202){
		      message = "审批：";
			}
		else if(status ==203){
			message = "提供简历：";
		}
		else if(status ==204){
		      message = "完工：";
		      service.setCompleteTime(new Date());
			}
		else if(status ==300){
		      message = "完结：";
			}
		else if(status ==301){
			message = "拒绝该申请：";
		}
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
        changes.append((etopServiceDao.queryById(serviceId).getChanges())+message);
        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
        changes.append( sdf.format(new Date())+"\r");
        service.setChanges(changes.toString());
	    service.setServiceId(serviceId);
		  
		int row= etopServiceDao.updateBySelective(service);
		if(row >0){
			return ResultType.getSuccess();
		}else{
			return ResultType.getError("修改失败");
		}
	}
	
	public ResultType updateStatus2(String serviceId, int status, Companyservice companyservice, String message) {
		EtopService service =new EtopService();
		service.setServiceId(serviceId);
		service.setServiceStatus(status);
		if(status ==102){
			message = "待回执：";
		}else if(status ==201){
			message = "否认完工：";
		}
		else if(status ==201){
			message = "撤销：";
		}
		else if(status ==202){
			message = "审批：";
		}
		else if(status ==203){
			message = "提供简历：";
		}
		else if(status ==204){
			message = "完工：";
			service.setCompleteTime(new Date());
		}
		else if(status ==300){
			message = "完结：";
		}
		else if(status ==301){
			message = "拒绝该申请：";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		StringBuffer changes =new  StringBuffer();
		changes.append((etopServiceDao.queryById(serviceId).getChanges())+message);
		changes.append("自动出账"+"&nbsp");
		changes.append( sdf.format(new Date())+"\r");
		service.setChanges(changes.toString());
		service.setServiceId(serviceId);
		
		int row= etopServiceDao.updateBySelective(service);
		if(row >0){
			return ResultType.getSuccess();
		}else{
			return ResultType.getError("修改失败");
		}
	}

	@Override
	public void getFinishList(long difference) {
		
		List<EtopService> list= etopServiceDao.queryByList(new Criteria());
		 for(int i=0;i<list.size();i++){

	       difference =new Date().getTime() - list.get(i).getCompleteTime().getTime();
		   if(difference >=259200){
				
			}
	 }

	}
  
	public void cronaddBill(String serviceId, Companyservice companyservice, String message) {
		EtopService service = etopServiceDao.queryById(serviceId);
		EtopServicePurchase purchase=etopServicePurchaseDao.queryByServiceId(serviceId);
		if(! (purchase.getTotalPrice()).equals(0)){
		EtopBill etopBill =new EtopBill();
		etopBill.setParkId(service.getClubId());
		Park park=parkDao.getParkInfo(service.getClubId());
		etopBill.setBillId(etopSequenceSevice.getFormatId(park.getParkCode(), "zd"));
		etopBill.setBillType(BillType.INCOME.value);
		etopBill.setBillStatus(BillStatus.UNPAID.value);
		etopBill.setBillSource(BillSource.PARK_SERVICE.value);
		etopBill.setCompanyId(service.getCompanyId());
		etopBill.setCompanyName(service.getCompanyName());
		etopBill.setAmount(purchase.getTotalPrice());
	    int day=Double.valueOf(etopThresholdService.getValue(service.getParkId(), ThresholdKey.deadline.name)).intValue();//阀值
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
		etopBillDao.add(etopBill);
		this.updateStatus2( serviceId,300,companyservice, message);
		}
	}
	
	public void addBill(String serviceId, Companyservice companyservice, String message) {
		EtopService service = etopServiceDao.queryById(serviceId);
		Etopservice parkservice = serviceQuotationService.getQuotationBusinessInfoById(serviceId);
		if(! (parkservice.getTotalPrice()).equals(0)){
		EtopBill etopBill =new EtopBill();
		etopBill.setParkId(service.getClubId());
		Park park=parkDao.getParkInfo(service.getClubId());
		etopBill.setBillId(etopSequenceSevice.getFormatId(park.getParkCode(), "zd"));
		etopBill.setBillType(BillType.INCOME.value);
		etopBill.setBillStatus(BillStatus.UNPAID.value);
		etopBill.setBillSource(BillSource.PARK_SERVICE.value);
		etopBill.setCompanyId(service.getCompanyId());
		etopBill.setCompanyName(service.getCompanyName());
		if(parkservice.getFinalPrice() != null){
			etopBill.setAmount(parkservice.getFinalPrice());
		}else{
			etopBill.setAmount(parkservice.getTotalPrice());
		}
		int day=Double.valueOf(etopThresholdService.getValue(service.getParkId(), ThresholdKey.deadline.name)).intValue();//阀值
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
		etopBillDao.add(etopBill);
		this.updateStatus2( serviceId,300,companyservice, message);
		}
	}

	@Override
	public EtopPage<Etopservice> calculateQuotation(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit);

		EtopPage<Etopservice> list = new EtopPage<Etopservice>(etopServiceDao.calculateQuotation(condition));
/*		Page<Etopservice> page =etopServiceDao.calculateQuotation(condition);
		for(Etopservice etopservice :page){
			if(etopservice.getFacilityPrice() != null)
		}*/
		return list;
	}

	public EtopPage<Etopservice> getQuotationBusiness(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit,"apply_time DESC");
//		EtopPage<Parkservice> list = new EtopPage<Parkservice>(serviceQuotationDao.getQuotationBusiness(condition));
		Page<Etopservice> page = serviceQuotationDao.getQuotationBusiness(condition);
		for(Etopservice parkservice : page){
			if(parkservice.getFinalPrice() !=null){
				parkservice.setTotalPrice(parkservice.getFinalPrice());
			}else{
				parkservice.setTotalPrice(parkservice.getTotalPrice());
			}
		}
		EtopPage<Etopservice> list = new EtopPage<Etopservice>(page);
		return list;
	}
	
	
	@Override
	public EtopPage<Companyservice> getServiceBycompanyId(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit,"apply_time DESC");
//      服务名称
		Page<Companyservice> list=etopServiceDao.getServiceBycompanyId(condition);

		for (Companyservice companyservice : list) {
			if("YYFW".equals(companyservice.getServiceType())){
			companyservice.setSubject(companyservice.getFacilityName());
			companyservice.setTotalPrice(companyservice.getFacilityPrice());
			companyservice.setCategory(companyservice.getFacilityType());
			}
//			else if("RYDZ".equals(companyservice.getServiceType())){
//			companyservice.setSubject(companyservice.getServiceName());
//			}
			else if("GGCG".equals(companyservice.getServiceType())){
			companyservice.setSubject(companyservice.getGoodsName());
			companyservice.setNumber(companyservice.getPurnumber());
			companyservice.setTotalPrice(companyservice.getPurprice());

			}
			else {
			companyservice.setNumber(companyservice.getBusnumber());
			companyservice.setTotalPrice(companyservice.getBusprice());
			companyservice.setCategory(companyservice.getCategories());
			}

			
		}
		EtopPage<Companyservice> page = new EtopPage<Companyservice>(list);
		return page;
	}
	
}
