package com.etop.management.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.etop.management.bean.Companyservice;
import com.github.pagehelper.Page;



/** 
 * @author lvxiwei 

 * @time   2016年10月11日 下午4:09:17 
 */
@Repository
public interface CompanyServiceDao {

	
	   Page<Companyservice> getServiceBycompanyId(Map<String, Object> condition);
	   
	   Companyservice getServiceInfoById(String id);
	   
	   void cancel(String serviceId);
	   
	   void approve(String serviceId);

	   void statusOfSuer(Companyservice companyservice);
	   
	   String getServiceType(String id);
	   
	   String getGoodName(Object companyId);
	   
	   Page<Companyservice> getServiceByBillId(Map<String, Object> condition);
}
