package com.etop.management.service;

import java.util.List;

import com.etop.management.bean.EtopPage;
import com.etop.management.entity.EtopGoods;
import com.etop.website.bean.ServiceQuotation;


/**
 * 
 * <br>
 * <b>功能：</b>EtopGoodsService<br>
 */
public interface EtopGoodsService{
	
	EtopPage<EtopGoods> getEtopGoodsList(EtopGoods EtopGoods, int offset, int limit) throws Exception;
	
	EtopPage<EtopGoods> getEtopGoodsListGroup(EtopGoods EtopGoods, int offset, int limit) throws Exception;

	EtopGoods getEtopGoodsInfo(String EtopGoodsId) throws Exception;

	void addEtopGoods(EtopGoods EtopGoods) throws Exception;

	void updateEtopGoods(EtopGoods EtopGoods) throws Exception;

	void activeOrClose(EtopGoods etopGoods);

	//void activeOrCloseEtopGoods(EtopGoods EtopGoods);
	public List<EtopGoods> categoryList(String parkId);
	
	public List<EtopGoods> getCategory(EtopGoods etopGoods);
	
	String proveGoodName(EtopGoods etopGoods);
}
