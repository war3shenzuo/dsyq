package com.etop.management.service;

import java.util.Map;

import com.etop.management.bean.EtopAllGoods;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Etopservice;
import com.etop.management.entity.EtopFloorRoom;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServicePurchase;


/**
 * 
 * <br>
 * <b>功能：</b>EtopServicePurchaseService<br>
 */
public interface EtopServicePurchaseService{
	
	EtopPage<EtopService> getServiceList(EtopService service);
	EtopPage<EtopService> getServiceList(Map<String, Object> condition, int offset, int limit);
	
	EtopService getServiceInfo(String serviceId);

	EtopServicePurchase getServicePurchaseInfo(String serviceId);

	EtopPage<EtopAllGoods> calculateGoods(EtopAllGoods goods);
	
	EtopPage<EtopAllGoods> calculateGoods(Map<String, Object> condition, Integer offset, Integer limit);
	
	void add(EtopServicePurchase param, EtopService service, EtopFloorRoom etopFloorRoom);
}
