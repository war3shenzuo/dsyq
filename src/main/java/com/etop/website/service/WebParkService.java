package com.etop.website.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.etop.management.entity.EtopFloorRoom;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceBusiness;



public interface WebParkService{


	void addservice(Parkservice parkservice,HttpServletRequest request) throws Exception;
	
    Map<String, String> searchName(String service_type);

	void addService(Parkservice parkservice,HttpServletRequest request,ServiceBusiness serviceBusiness, EtopFloorRoom etopFloorRoom) throws Exception;

}
