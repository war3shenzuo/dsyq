package com.etop.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.dao.WebIndexDao;
import com.etop.management.service.WebIndexService;
@Service
public class WebIndexServiceImpl implements WebIndexService {

	@Autowired
	WebIndexDao webIndexDao;

	@Override
	public String getWebIndex(String type) {
		String context = webIndexDao.getWebIndex(type);
		return context;
	}

	

}
