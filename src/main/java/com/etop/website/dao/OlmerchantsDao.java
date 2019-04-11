package com.etop.website.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etop.website.bean.Webpark;




@Repository
public interface OlmerchantsDao {

	 List<Webpark> searchWebpark(Webpark webpark);
	  
	 Webpark searchInfo(String id);
	 
	 List<Webpark> getCity(Webpark webpark);
	 

}
