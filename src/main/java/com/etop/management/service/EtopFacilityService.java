package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopFacility;
import com.etop.management.entity.EtopGoods;


/**
 * 
 * <br>
 * <b>功能：</b>EtopFacilityService<br>
 */
public interface EtopFacilityService{

//	EtopPage<EtopFacility> getEtopFacilityList(EtopFacility etopFacility);
	EtopPage<EtopFacility> getEtopFacilityList(EtopFacility etopFacility, int offset, int limit) throws Exception;
	
	EtopPage<EtopFacility> getEtopFacilityListGroup(EtopFacility etopFacility, int offset, int limit) throws Exception;
	
	EtopFacility getEtopFacilityInfo(String etopFacilityId);

	void addEtopFacility(EtopFacility param);

	void updateEtopFacility(EtopFacility etopFacility);

	void activeOrClosePark(EtopFacility etopFacility);

	public List<EtopFacility> getfacilityTypeList(String parkId);
	
	public List<EtopFacility> getfacilityName(EtopFacility etopFacility);
	
	String provefacilityName(EtopFacility etopFacility);
}
