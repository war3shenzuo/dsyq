package com.etop.management.dao;
import java.util.List;
import java.util.Map;

import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.etop.management.bean.Criteria;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceFacilityDao<br>
 */
public interface EtopServiceFacilityDao {
	/**添加*/
	public int add(EtopServiceFacility etopServiceFacility);
	/**批量添加*/
	public int adds(List<EtopServiceFacility> list);
	/**全修改*/
	public int update(EtopServiceFacility etopServiceFacility);
	/**条件修改*/
	public int updateBySelective(EtopServiceFacility etopServiceFacility); 	
	/**根据Id删除*/
	public int delete(EtopServiceFacility etopServiceFacility);
	/**根据Id查询*/
	public EtopServiceFacility queryById(String id);
	/**根据类条件查询*/
	public EtopServiceFacility queryObject(EtopServiceFacility etopServiceFacility);
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopServiceFacility> queryByList(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopServiceFacility> myDefinedSelect(Criteria criteria);
	/**根据服务id查询*/
	public Map<String, Object> queryByServiceId(String serviceId);
	
	public int checkAppointTime(EtopServiceFacility etopServiceFacility);
	
	public EtopServiceFacility getTime(String facilityId);
	
	public Page<EtopServiceFacility> calculateFacility(Map<String, Object> condition);
	
}
