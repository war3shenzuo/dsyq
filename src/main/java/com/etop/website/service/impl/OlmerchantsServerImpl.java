package com.etop.website.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import com.etop.website.bean.Webpark;
import com.etop.website.service.OlmerchantsService;
import com.etop.website.dao.OlmerchantsDao;





@Named("WebparkService")
@Service
public class OlmerchantsServerImpl implements OlmerchantsService {
	
	@Resource
	private OlmerchantsDao OlmerchantsDao;
	


	
	public List<Webpark> searchWebpark(Webpark Webpark) throws Exception {
		  List<Webpark> list=OlmerchantsDao.searchWebpark(Webpark);    		    
          return list;
	}



	@Override
	public Webpark searchInfo(String id) {
		
		return OlmerchantsDao.searchInfo(id);
	}



	@Override
	public List<Webpark> getCity(Webpark webpark) {
		
	    return OlmerchantsDao.getCity(webpark);
	}




	

	
	










	
	
	

}