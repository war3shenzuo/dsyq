package com.etop.website.service;

import com.etop.management.bean.EtopPage;
import com.etop.website.bean.EtopSubDomain;

/**
 * <b>功能：</b>EtopSubDomainService<br>
 */
public interface EtopSubDomainService {

	EtopPage<EtopSubDomain> list(int offset, int limit);
	
	EtopSubDomain findById(String hostName);
	
	int add(EtopSubDomain etopSubDomain);
	
	int delete(String hostName);
	
	int deletes(String[] hostNames);
}
