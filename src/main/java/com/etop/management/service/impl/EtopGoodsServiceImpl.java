package com.etop.management.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopGoodsDao;
import com.etop.management.entity.EtopGoods;
import com.etop.management.entity.EtopService;
import com.etop.management.service.EtopGoodsService;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * <br>
 * <b>功能：</b>EtopGoodsService<br>
 */
@Service("etopGoodsService")
@Transactional(propagation = Propagation.REQUIRED)
public class  EtopGoodsServiceImpl  implements EtopGoodsService {
	

	@Resource
	EtopGoodsDao etopGoodDao;
	
	
	public  EtopPage<EtopGoods> getEtopGoodsList(EtopGoods EtopGoods, int offset, int limit) throws Exception {
		PageHelper.offsetPage(offset, limit);
		EtopPage<EtopGoods> list = new EtopPage<EtopGoods>(etopGoodDao.queryByList(EtopGoods.getCriteria()));
		return list;
	}
	public  EtopPage<EtopGoods> getEtopGoodsListGroup(EtopGoods EtopGoods, int offset, int limit) throws Exception {
		PageHelper.offsetPage(offset, limit);
		EtopPage<EtopGoods> list = new EtopPage<EtopGoods>(etopGoodDao.queryByListGroup(EtopGoods.getCriteria()));
		return list;
	}
	
/*	@Override
	public EtopPage<EtopGoods> getEtopGoodsList(EtopGoods EtopGoods) throws Exception{
		
  		EtopPage<EtopGoods> page = new EtopPage<EtopGoods>();
		
		int BTablePageNum = (EtopGoods.getOffset()/EtopGoods.getLimit());
		
		//设置分页
		PageHelper.startPage(BTablePageNum+1, EtopGoods.getLimit());
		
		List<EtopGoods> list =  new ArrayList<EtopGoods>();
		
		list = etopGoodDao.queryByList(EtopGoods.getCriteria());
		
		PageInfo<EtopGoods> table =new PageInfo<EtopGoods>(list);
		
		page.setRows(table.getList());
		page.setTotal(table.getTotal());
		
		return page;
	}*/

	@Override
	public EtopGoods getEtopGoodsInfo(String EtopGoodsId) throws Exception{
		
		return etopGoodDao.queryById(EtopGoodsId);
	}

	@Override
	public void addEtopGoods(EtopGoods EtopGoods) throws Exception{
		EtopGoods.setId(UUID.randomUUID().toString());
		EtopGoods.setCreatedTime(System.currentTimeMillis());
		EtopGoods.setActivated("1");
		etopGoodDao.add(EtopGoods);
	}

	@Override
	public void updateEtopGoods(EtopGoods EtopGoods) throws Exception{
		
		etopGoodDao.updateBySelective(EtopGoods);
		
	}

	@Override
	public void activeOrClose(EtopGoods etopGoods) {
		etopGoodDao.updateBySelective( etopGoods);
		
	}

	@Override
	public List<EtopGoods> categoryList(String parkId) {
		return etopGoodDao.categoryList(parkId);
	}
	
	@Override
	public List<EtopGoods> getCategory(EtopGoods etopGoods) {
		
		return etopGoodDao.getCategory(etopGoods);
	}

	@Override
	public String proveGoodName(EtopGoods etopGoods) {
		// TODO Auto-generated method stub
		return etopGoodDao.proveGoodName(etopGoods);
	}
	
}
