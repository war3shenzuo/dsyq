package com.etop.website.dao;

import org.springframework.stereotype.Repository;

import com.etop.website.bean.Company;




@Repository
public interface CompanyDao {
  
	 Company searchInfo(String company_id);
	 
	 String searchname(String companyId);

}
