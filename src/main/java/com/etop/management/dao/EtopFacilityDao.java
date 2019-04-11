package com.etop.management.dao;
import java.util.List;

import com.etop.management.entity.EtopFacility;
import com.etop.management.bean.Criteria;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopFacilityDao<br>
 */
public interface EtopFacilityDao {
	/**添加*/
	public int add(EtopFacility etopFacility);
	/**批量添加*/
	public int adds(List<EtopFacility> list);
	/**全修改*/
	public int update(EtopFacility etopFacility);
	/**条件修改*/
	public int updateBySelective(EtopFacility etopFacility); 	
	/**根据Id删除*/
	public int delete(EtopFacility etopFacility);
	/**根据Id查询*/
	public EtopFacility queryById(String id);
	/**根据类条件查询*/
	public EtopFacility queryObject(EtopFacility etopFacility);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
//	public List<EtopFacility> queryByList(Criteria criteria);
	public Page<EtopFacility> queryByList(Criteria criteria);
	
	public Page<EtopFacility> queryByListGroup(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopFacility> myDefinedSelect(Criteria criteria);
	
	public List<EtopFacility> getfacilityTypeList(String parkId);
	
	public List<EtopFacility> getfacilityName(EtopFacility etopFacility);
	
	 String provefacilityName(EtopFacility etopFacility);
}
