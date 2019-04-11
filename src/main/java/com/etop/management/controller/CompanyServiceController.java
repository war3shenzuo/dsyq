package com.etop.management.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.ResultType;
import com.etop.management.bean.ServiceDispatch;
import com.etop.management.dao.EtopResumeDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.entity.EtopResume;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServicePurchase;
import com.etop.management.entity.EtopServiceRecruitment;
import com.etop.management.service.CompanyServiceService;
import com.etop.management.service.EtopServiceFacilityService;
import com.etop.management.service.EtopServicePurchaseService;
import com.etop.management.service.EtopServiceRecruitmentService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.service.ServiceQuotationService;

/**
 * Created by Alan.
 *
 * @author lvxiwei
 * @DATE 2016/8/28
 */
@Controller
@RequestMapping("/companyService")
public class CompanyServiceController extends BaseAppController{

	private final static Logger LOGGER = Logger.getLogger(CompanyServiceController.class);

	@Autowired
	private CompanyServiceService companyServiceService;
	@Resource
	ServiceQuotationService serviceQuotationService;
	@Autowired 
	private EtopServicePurchaseService etopServicePurchaseService; 
	@Autowired 
	private EtopServiceRecruitmentService etopServiceRecruitmentService; 
	@Autowired 
	private EtopServiceFacilityService etopServiceFacilityService; 
	@Autowired 
	private EtopResumeDao etopResumeDao; 
	
	@RequestMapping("/servicelist.do")
	public String list() {
		return "companyService/servicelist";
	}
	
	
	@RequestMapping("/getServiceBycompanyId.do")
	@ResponseBody
	public EtopPage<Companyservice> listbyCompanyId(
			String refCompanyId, String serviceNo,String serviceType,String applyTime,
			 String employeesName, String serviceStatus, String completeTime, String searchValue, String category,
			@RequestParam(defaultValue="0")int offset, @RequestParam(defaultValue="10")int limit) {
		EtopUser etopUser= UserInfoUtil.getUserInfo();
		Map<String, Object> condition = new HashMap<>();
		condition.put("companyId", etopUser.getCompanyId());
		condition.put("serviceNo",serviceNo);
		condition.put("serviceType",serviceType);
		condition.put("serviceStatus",serviceStatus);
		condition.put("completeTime",completeTime);
		condition.put("applyTime",applyTime);
		condition.put("searchValue",searchValue);
		condition.put("category",category);
				return companyServiceService.getServiceBycompanyId(condition, offset, limit);
		
	}

	@RequestMapping(value = "getServiceInfoById.do")
	public Object EmployeesInfoById(Companyservice companyservic, Model model, String id){
		Companyservice companyservice = companyServiceService.getServiceInfoById(id);
			model.addAttribute("Companyservice",companyservice);
		Etopservice parkservice = serviceQuotationService.getQuotationBusinessInfoById(id);
			model.addAttribute("Parkservice", parkservice);
		ServiceDispatch	 serviceDispatch=serviceQuotationService.getDispatchInfoById(id);
		    model.addAttribute("ServiceDispatch", serviceDispatch);	
		return "companyService/companyServiceInfoById";
	}

	
	@ResponseBody
	@RequestMapping(value = "/cancel.do")
	public ResultType cancel(String serviceId, Companyservice companyservice) throws Exception{
		ResultType result= null;
		try{
			companyServiceService.cancel(serviceId,companyservice);
			result =ResultType.getSuccess("撤销成功！");
		}catch(Exception e){
			LOGGER.error("撤销失败",e);
		result = ResultType.getFail("撤销失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/approve.do")
	public ResultType approve(String serviceId, Companyservice companyservice) throws Exception{
		ResultType result= null;
		try{
			companyServiceService.approve(serviceId,companyservice);
			result =ResultType.getSuccess("审批成功！");
		}catch(Exception e){
			LOGGER.error("审批失败",e);
		result = ResultType.getFail("审批失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/statusOfSuer.do")
	public ResultType statusOfSuer(Companyservice companyservice, String serviceId, EtopBill etopBill) throws Exception{
		ResultType result= null;
		try{
			companyServiceService.statusOfSuer(companyservice,serviceId, etopBill);
			result =ResultType.getSuccess("成功修改为已派工！");
		}catch(Exception e){
			LOGGER.error("修改派工失败",e);
		result = ResultType.getFail("修改派工失败！");
		e.printStackTrace();		
	     }
		 return result;
	}
	
	
	@RequestMapping(value = "/getServiceType.do")
	public String getServiceType( Model model, String id){
		
		if(("GGCG").equals(companyServiceService.getServiceType(id)  ) ){

				EtopService service = etopServicePurchaseService.getServiceInfo(id);
				
				EtopServicePurchase purchase=etopServicePurchaseService.getServicePurchaseInfo(id);
				
				model.addAttribute("service", service);
				
				model.addAttribute("purchase", purchase);
				
				EtopUser  user=UserInfoUtil.getUserInfo();
				if(!"4".equals(user.getUserType()) && service.getServiceStatus()==202){
					model.addAttribute("bill", 1);
				}else{
					model.addAttribute("bill", 0);
				}

			
			return "purchase/CompanyServiceInfo";
		}
		else if(("RYDZ").equals(companyServiceService.getServiceType(id)  ) ){

				EtopService service = etopServiceRecruitmentService.getServiceInfo(id);
				
				EtopServiceRecruitment recruitment=etopServiceRecruitmentService.getServicePurchaseInfo(id);
				
				Etopservice etopservice = etopServiceRecruitmentService.querysByServiceId(id);
				
				ServiceDispatch serviceDispatch=serviceQuotationService.getDispatchInfoById(id);
				
//				EtopResume etopResume = etopResumeDao.queryByServiceId(id);
				if(recruitment.getSexual()==1){
					recruitment.setSexualStr("男");
				}else if(recruitment.getSexual()==2){
					recruitment.setSexualStr("女");
				}else if(recruitment.getSexual()==3){
					recruitment.setSexualStr("无要求");
				}
				
				model.addAttribute("service", service);
				
				model.addAttribute("recruitment", recruitment);
				
				model.addAttribute("etopservice", etopservice);
				
				model.addAttribute("ServiceDispatch", serviceDispatch);	
				
//				model.addAttribute("etopResume", etopResume);	
				
				EtopUser  user=UserInfoUtil.getUserInfo();
				if(!"4".equals(user.getUserType()) && service.getServiceStatus()==202){
					model.addAttribute("bill", 1);
				}else{
					model.addAttribute("bill", 0);
				}
	
			return "recruitment/CompanyServiceInfo";
			
		}
		else if(("YYFW").equals(companyServiceService.getServiceType(id)  ) ){
		
				
			EtopService service = etopServiceFacilityService.getServiceInfo(id);
			
			Map<String,Object> facility=etopServiceFacilityService.getServiceFacilityInfo(id);
			
			
			
			model.addAttribute("service", service);
			
			model.addAttribute("facility", facility);
			
			EtopUser  user=UserInfoUtil.getUserInfo();
			if(!"4".equals(user.getUserType()) && service.getServiceStatus()==202){
				model.addAttribute("bill", 1);
			}else{
				model.addAttribute("bill", 0);
			}
			
			return "facility/CompanyServiceInfo";
		}
	 else{
		Companyservice companyservice = companyServiceService.getServiceInfoById(id);
		
	    ServiceDispatch	 serviceDispatch=serviceQuotationService.getDispatchInfoById(id);
		
	    Etopservice parkservice = serviceQuotationService.getQuotationBusinessInfoById(id);
	    
		model.addAttribute("Companyservice",companyservice);
	
		model.addAttribute("Parkservice", parkservice);
	
	    model.addAttribute("ServiceDispatch", serviceDispatch);	
	    
		return "companyService/companyServiceInfoById";
	}

	}	
}
