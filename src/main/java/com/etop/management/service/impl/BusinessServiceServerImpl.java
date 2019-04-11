package com.etop.management.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.bean.Etopservice;
import com.etop.management.bean.ServiceDispatch;
import com.etop.management.dao.BusinessServiceDao;
import com.etop.management.service.BusinessServiceService;
import com.etop.management.util.UserInfoUtil;
import com.etop.website.bean.Parkservice;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;



@Service
public class BusinessServiceServerImpl implements BusinessServiceService{

	@Resource
	BusinessServiceDao businessServiceDao;

	
	@Override
	public EtopPage<ServiceQuotation> makeBusinessService(
			Map<String, Object> condition, Integer offset, Integer limit) {

	PageHelper.offsetPage(offset, limit);
	EtopPage<ServiceQuotation> list = new EtopPage<ServiceQuotation>(businessServiceDao.makeBusinessService(condition));
	return list;
	}
	
	@Override
	public EtopPage<ServiceQuotation> makeBusinessServiceGroup(
			Map<String, Object> condition, Integer offset, Integer limit) {
		
		PageHelper.offsetPage(offset, limit);
		EtopPage<ServiceQuotation> list = new EtopPage<ServiceQuotation>(businessServiceDao.makeBusinessServiceGroup(condition));
		return list;
	}


	@Override
	public int addBusinessService(ServiceQuotation serviceQuotation) {
		
		serviceQuotation.setQuotationId(UUID.randomUUID().toString());
		serviceQuotation.setType("service");
		serviceQuotation.setCreateTime(new Date());
		serviceQuotation.setParkId(UserInfoUtil.getUserInfo().getParkId());
		serviceQuotation.setActivated("1");
		return businessServiceDao.addBusinessService(serviceQuotation);
	}


	@Override
	public EtopPage<Etopservice> getBusinessService(
			Map<String, Object> condition, Integer offset, Integer limit) {
		PageHelper.offsetPage(offset, limit,"apply_time DESC");
//		EtopPage<Etopservice> list = new EtopPage<Etopservice>(businessServiceDao.getBusinessService(condition));
		Page<Etopservice> page = businessServiceDao.getBusinessService(condition);
		for(Etopservice etopservice : page){
			if(etopservice.getFinalPrice() !=null){
				etopservice.setTotalPrice(etopservice.getFinalPrice());
			}else{
				etopservice.setTotalPrice(etopservice.getTotalPrice());
			}
		}
		EtopPage<Etopservice> list = new EtopPage<Etopservice>(page);
		return list;
	}


	@Override
	public EtopPage<ServiceDispatch> dispatchList(
			Map<String, Object> condition, Integer offset, Integer limit) {
		EtopPage<ServiceDispatch> list = new EtopPage<ServiceDispatch>(businessServiceDao.dispatchList(condition));
		return list;
	}


}