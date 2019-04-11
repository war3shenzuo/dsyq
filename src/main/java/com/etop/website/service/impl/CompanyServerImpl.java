package com.etop.website.service.impl;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.etop.website.bean.Company;
import com.etop.website.dao.CompanyDao;
import com.etop.website.service.CompanyService;






@Named("CompanyService")
@Service
public class CompanyServerImpl implements CompanyService {
	
	@Resource
	private CompanyDao companyDao;

	@Override
	public Company searchInfo(String company_id) {
		
		return companyDao.searchInfo(company_id);
	}

	

	
	










	
	
	

}