package com.etop.management.dao;
import java.util.List;

import com.etop.management.entity.EtopGoods;
import com.etop.management.bean.Criteria;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopGoodsDao<br>
 */
public interface EtopGoodsDao {
	/**添加*/
	public int add(EtopGoods etopGoods);
	/**批量添加*/
	public int adds(List<EtopGoods> list);
	/**全修改*/
	public int update(EtopGoods etopGoods);
	/**条件修改*/
	public int updateBySelective(EtopGoods etopGoods); 	
	/**根据Id删除*/
	public int delete(EtopGoods etopGoods);
	/**根据Id查询*/
	public EtopGoods queryById(String id);
	/**根据类条件查询*/
	public EtopGoods queryObject(EtopGoods etopGoods);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
//	public List<EtopGoods> queryByList(Criteria criteria);
	public Page<EtopGoods> queryByList(Criteria criteria);
	
	public Page<EtopGoods> queryByListGroup(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopGoods> myDefinedSelect(Criteria criteria);
	
	public List<EtopGoods> categoryList(String parkId);
	
	public List<EtopGoods> getCategory(EtopGoods etopGoods);
	
	String proveGoodName(EtopGoods etopGoods);
}
