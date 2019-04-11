package com.etop.management.service;

import java.util.List;
import java.util.Map;

import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.github.pagehelper.Page;


/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceFacilityService<br>
 */
public interface EtopServiceFacilityService{

	EtopPage<EtopService> getServiceList(EtopService service);

	EtopService getServiceInfo(String serviceId);

	Map<String, Object> getServiceFacilityInfo(String serviceId);

	void add(EtopServiceFacility param, EtopService service, EtopFloorRoom etopFloorRoom);
	
	List<EtopService> getServiceList2(EtopService service);

	public EtopPage<EtopServiceFacility> calculateFacility(Map<String, Object> condition, int offset, int limit);
}

