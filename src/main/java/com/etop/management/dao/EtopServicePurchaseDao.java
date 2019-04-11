package com.etop.management.dao;
import java.util.List;
import com.etop.management.entity.EtopServicePurchase;
import com.etop.management.bean.Criteria;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServicePurchaseDao<br>
 */
public interface EtopServicePurchaseDao {
	/**添加*/
	public int add(EtopServicePurchase etopServicePurchase);
	/**批量添加*/
	public int adds(List<EtopServicePurchase> list);
	/**全修改*/
	public int update(EtopServicePurchase etopServicePurchase);
	/**条件修改*/
	public int updateBySelective(EtopServicePurchase etopServicePurchase); 	
	/**根据Id删除*/
	public int delete(EtopServicePurchase etopServicePurchase);
	/**根据Id查询*/
	public EtopServicePurchase queryById(String id);
	/** */
	public EtopServicePurchase queryByServiceId(String id);
	/**根据类条件查询*/
	public EtopServicePurchase queryObject(EtopServicePurchase etopServicePurchase);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopServicePurchase> queryByList(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopServicePurchase> myDefinedSelect(Criteria criteria);
	
}
