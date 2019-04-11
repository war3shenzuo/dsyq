package com.etop.website.service;

import java.util.List;

import com.etop.website.bean.Webpark;



public interface OlmerchantsService {

    
    public List<Webpark> searchWebpark(Webpark webpark) throws Exception;

	public Webpark searchInfo(String id);

	public List<Webpark> getCity(Webpark webpark);
	


}
