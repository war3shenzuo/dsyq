package com.etop.management.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.enterprise.inject.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.ServiceBusiness;
import com.etop.management.dao.CompanyServiceDao;
import com.etop.management.dao.EtopBillDao;
import com.etop.management.entity.EtopBill;
import com.etop.management.service.CompanyServiceService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.dao.ServiceQuotationDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
@Service
public class CompanyServiceServiceImpl implements CompanyServiceService {

    @Autowired
    private CompanyServiceDao companyServiceDao;
	@Resource
	ServiceQuotationDao serviceQuotationDao;
	@Resource
	EtopBillDao etopBillDao;


	@Override
	public EtopPage<Companyservice> getServiceBycompanyId(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit,"apply_time DESC");
//		EtopPage<Companyservice> list = new EtopPage<Companyservice>(companyServiceDao.getServiceBycompanyId(condition));
//      服务名称
		Page<Companyservice> list=companyServiceDao.getServiceBycompanyId(condition);

		for (Companyservice companyservice : list) {
			if("YYFW".equals(companyservice.getServiceType())){
			companyservice.setSubject(companyservice.getFacilityName());
			companyservice.setTotalPrice(companyservice.getFacilityPrice());
			companyservice.setCategory(companyservice.getFacilityType());
			companyservice.setNumber(companyservice.getDuration());
			}else if("GGCG".equals(companyservice.getServiceType())){
			companyservice.setSubject(companyservice.getGoodName());
			companyservice.setNumber(companyservice.getPurnumber());
			companyservice.setTotalPrice(companyservice.getPurprice());

			}else {
			companyservice.setNumber(companyservice.getBusnumber());
			companyservice.setCategory(companyservice.getCategories());
			if(companyservice.getFinalPrice() !=null){
				companyservice.setTotalPrice(companyservice.getFinalPrice());
			}else{
				companyservice.setTotalPrice(companyservice.getBusprice());
			}
				
			}

			
		}
		EtopPage<Companyservice> page = new EtopPage<Companyservice>(list);
		return page;
	}





	@Override
	public EtopPage<Companyservice> getServiceByBillId(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit,"apply_time DESC");
		Page<Companyservice> list=companyServiceDao.getServiceByBillId(condition);

		for (Companyservice companyservice : list) {
			if("YYFW".equals(companyservice.getServiceType())){
			companyservice.setSubject(companyservice.getFacilityName());
			companyservice.setTotalPrice(companyservice.getFacilityPrice());
			companyservice.setCategory(companyservice.getFacilityType());
			companyservice.setNumber(companyservice.getDuration());
			}else if("GGCG".equals(companyservice.getServiceType())){
			companyservice.setSubject(companyservice.getGoodName());
			companyservice.setNumber(companyservice.getPurnumber());
			companyservice.setTotalPrice(companyservice.getPurprice());
			companyservice.setUnitPrice(companyservice.getPurunitPrice());
			}else {
			companyservice.setNumber(companyservice.getBusnumber());
			companyservice.setCategory(companyservice.getCategories());
			companyservice.setUnitPrice(companyservice.getBusunitPrice());
			if(companyservice.getFinalPrice() !=null){
				companyservice.setTotalPrice(companyservice.getFinalPrice());
			}else{
				companyservice.setTotalPrice(companyservice.getBusprice());
			}
				
			}

			
		}
		EtopPage<Companyservice> page = new EtopPage<Companyservice>(list);
		return page;
	}


	@Override
	public Companyservice getServiceInfoById(String id) {
		Companyservice list = companyServiceDao.getServiceInfoById(id);
	
		return list;
	}



//维修回执（撤销，审批）
	@Override
	public void cancel(String serviceId, Companyservice companyservice) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
	        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(serviceId).getChanges())+"撤销：");
	        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
	        changes.append( sdf.format(new Date())+"\r");
		  companyservice.setChanges(changes.toString());
		  serviceQuotationDao.changStatus(companyservice);
		  companyServiceDao.cancel(serviceId);
		
	}
	@Override
	public void approve(String serviceId, Companyservice companyservice) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
	        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(serviceId).getChanges())+"同意报价：");
	        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
	        changes.append( sdf.format(new Date())+"\r");
		  companyservice.setChanges(changes.toString());
		  serviceQuotationDao.changStatus(companyservice);
		  companyServiceDao.approve(serviceId);
		
	}

	@Override
	public int statusOfSuer(Companyservice companyservice, String serviceId, EtopBill etopBill) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        StringBuffer changes =new  StringBuffer();
        changes.append((serviceQuotationDao.getQuotationBusinessInfoById(serviceId).getChanges())+"完结：");
        changes.append(UserInfoUtil.getUserInfo().getUserName()+"&nbsp");
        changes.append( sdf.format(new Date())+"\r");
	    companyservice.setChanges(changes.toString());
	    companyservice.setServiceId(serviceId);
		serviceQuotationDao.changStatus(companyservice);
		 return etopBillDao.add(etopBill);
	}




	@Override
	public String getServiceType(String id) {
		
		return companyServiceDao.getServiceType(id);
	}

}
