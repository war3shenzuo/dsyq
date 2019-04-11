package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopFacilityDao;
import com.etop.management.entity.EtopFacility;
import com.etop.management.entity.EtopGoods;
import com.etop.management.service.EtopFacilityService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * <br>
 * <b>功能：</b>EtopFacilityService<br>
 */
@Service("etopFacilityService")
public class  EtopFacilityServiceImpl  implements EtopFacilityService {
	
	@Resource
	private EtopFacilityDao etopFacilityDao;

	
	public  EtopPage<EtopFacility> getEtopFacilityList(EtopFacility etopFacility, int offset, int limit) throws Exception {
		PageHelper.offsetPage(offset, limit);
		EtopPage<EtopFacility> list = new EtopPage<EtopFacility>(etopFacilityDao.queryByList(etopFacility.getCriteria()));
		return list;
	}
	
	public  EtopPage<EtopFacility> getEtopFacilityListGroup(EtopFacility etopFacility, int offset, int limit) throws Exception {
		PageHelper.offsetPage(offset, limit);
		EtopPage<EtopFacility> list = new EtopPage<EtopFacility>(etopFacilityDao.queryByListGroup(etopFacility.getCriteria()));
		return list;
	}
	/*@Override
	public EtopPage<EtopFacility> getEtopFacilityList(EtopFacility etopFacility) {
		EtopPage<EtopFacility> page = new EtopPage<EtopFacility>();
		
		int BTablePageNum = (etopFacility.getOffset()/etopFacility.getLimit());
		
		//设置分页
		PageHelper.startPage(BTablePageNum+1, etopFacility.getLimit());
		
		List<EtopFacility> list =  new ArrayList<EtopFacility>();
		
		list = etopFacilityDao.queryByList(etopFacility.getCriteria());
		
		PageInfo<EtopFacility> table =new PageInfo<EtopFacility>(list);
		
		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
	}*/

	@Override
	public EtopFacility getEtopFacilityInfo(String etopFacilityId) {
		return etopFacilityDao.queryById(etopFacilityId);
	}

	@Override
	public void addEtopFacility(EtopFacility param) {
		param.setId(UUID.randomUUID().toString());
		param.setActivated("1");
		etopFacilityDao.add(param);
		
	}

	@Override
	public void updateEtopFacility(EtopFacility etopFacility) {
		etopFacilityDao.updateBySelective(etopFacility);
		
	}

	@Override
	public void activeOrClosePark(EtopFacility etopFacility) {
		etopFacilityDao.updateBySelective(etopFacility);
		
	}

	@Override
	public List<EtopFacility> getfacilityTypeList(String parkId) {
		
		return etopFacilityDao.getfacilityTypeList(parkId);
	}

	@Override
	public List<EtopFacility> getfacilityName(EtopFacility etopFacility) {
		etopFacility.setParkId(UserInfoUtil.getUserInfo().getParkId());
		
		return etopFacilityDao.getfacilityName(etopFacility);
	}

	@Override
	public String provefacilityName(EtopFacility etopFacility) {
		// TODO Auto-generated method stub
		return etopFacilityDao.provefacilityName(etopFacility);
	}
  
	
}
