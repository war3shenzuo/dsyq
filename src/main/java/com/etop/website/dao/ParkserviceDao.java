package com.etop.website.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.etop.management.entity.EtopService;
import com.etop.website.bean.Parkservice;



@Repository
public interface ParkserviceDao {

	public void addservice(Parkservice parkservice);
	
	public Map<String,String> searchName(String service_type);
	
	public void addService(Parkservice parkservice);
	
	public void addServer(EtopService service);
	
	public String getCompanyName(String company_id);

}
