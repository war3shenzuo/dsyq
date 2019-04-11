package com.etop.website.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.website.bean.EtopSubDomain;
import com.etop.website.dao.EtopSubDomainDao;
import com.etop.website.service.EtopSubDomainService;
import com.github.pagehelper.PageHelper;

@Service("etopSubDomainService")
public class EtopSubDomainServiceImpl implements EtopSubDomainService {

	@Inject
	private EtopSubDomainDao etopSubDomainDao;
	
	@Override
	public EtopPage<EtopSubDomain> list(int offset, int limit) {
		String orderBy = "host_name";
		PageHelper.offsetPage(offset, limit, orderBy);
		return new EtopPage<EtopSubDomain>(etopSubDomainDao.list(null));
	}

	@Override
	public EtopSubDomain findById(String hostName) {
		return etopSubDomainDao.findById(hostName);
	}

	@Override
	public int add(EtopSubDomain etopSubDomain) {
		return etopSubDomainDao.add(etopSubDomain);
	}

	@Override
	public int delete(String hostName) {
		return etopSubDomainDao.delete(hostName);
	}

	@Override
	public int deletes(String[] hostNames) {
		return etopSubDomainDao.deletes(hostNames);
	}
}
