package com.etop.management.dao;
import java.util.List;
import java.util.Map;

import com.etop.management.entity.EtopServiceType;
import com.etop.management.bean.Criteria;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceTypeDao<br>
 */
public interface EtopServiceTypeDao {
	/**添加*/
	public int add(EtopServiceType etopServiceType);
	/**批量添加*/
	public int adds(List<EtopServiceType> list);
	/**全修改*/
	public int update(EtopServiceType etopServiceType);
	/**条件修改*/
	public int updateBySelective(EtopServiceType etopServiceType); 	
	/**根据Id删除*/
	public int delete(EtopServiceType etopServiceType);
	/**根据Id查询*/
	public EtopServiceType queryById(String id);
	/**根据类条件查询*/
	public EtopServiceType queryObject(EtopServiceType etopServiceType);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopServiceType> queryByList(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopServiceType> myDefinedSelect(Criteria criteria);
	
	
	
	public List<EtopServiceType> queryByListwithPGId(EtopServiceType type);
	
	public int bindParkGroup(List<Map<String,Object>>  param);
	
	public int unbindParkGroup(Map<String, Object> map);
	
	public int activeOrClosePark(EtopServiceType serviceType);
	
}
