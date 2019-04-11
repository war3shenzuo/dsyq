package com.etop.website.service;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.ParkGroupPresentation;
import com.etop.website.bean.Inpark;



public interface InparkService {

    


	public Inpark searchInfo(String parkGroupId);

	public List<Inpark> getCity(String city);

	public Map<String, Object> getCity2(Map<String, String> m);

	public List<ParkGroupPresentation> getCompanyByparkGroupId(String parkGroupId);
	
	public List<ParkGroupPresentation> getServiceyByparkGroupId(String parkGroupId);
	

}
