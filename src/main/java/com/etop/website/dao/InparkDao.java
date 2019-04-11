package com.etop.website.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.etop.management.bean.ParkGroupPresentation;
import com.etop.website.bean.Inpark;


@Repository
public interface InparkDao {

	
	  
	 Inpark searchInfo(String parkGroupId);
	 
	 List<Inpark> getCity(String city);
	 
	 List<ParkGroupPresentation> getCompanyByparkGroupId(String parkGroupId);

	 
	 List<ParkGroupPresentation> getServiceByparkGroupId(String parkGroupId);
	 

}
