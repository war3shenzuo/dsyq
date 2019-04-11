package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.Companyservice;
import com.etop.management.bean.Criteria;
import com.etop.management.bean.EtopAllGoods;
import com.etop.management.bean.Etopservice;
import com.etop.management.entity.EtopService;
import com.etop.management.entity.EtopServiceFacility;
import com.etop.website.bean.ServiceQuotation;
import com.github.pagehelper.Page;

/**
 * 
 * <br>
 * <b>功能：</b>EtopServiceDao<br>
 */
public interface EtopServiceDao {
	/**添加*/
	public int add(EtopService etopService);
	/**批量添加*/
	public int adds(List<EtopService> list);
	/**全修改*/
	public int update(EtopService etopService);
	/**条件修改*/
	public int updateBySelective(EtopService etopService); 	
	/**根据Id删除*/
	public int delete(EtopService etopService);
	/**根据Id查询*/
	public EtopService queryById(String id);
	/**根据类条件查询*/
	public EtopService queryObject(EtopService etopService);
	
	public List<EtopService> queryObject();
	/**根据具体条件查询数量*/
	public int queryByCount(Criteria criteria);
	/**根据具体条件批量查询*/
	public List<EtopService> queryByList(Criteria criteria);
	/**自定义更新*/
	public int myDefinedUpdate(Criteria criteria);
	/**自定义删除*/
	public int myDefinedDelete(Criteria criteria);
	/**自定义添加*/
	public int myDefinedAdd(Criteria criteria);
	/**自定义查询*/
	public List<EtopService> myDefinedSelect(Criteria criteria);
	
	/**关联type表查询*/
	public List<EtopService> selectListWithTypeName(EtopService etopService);
	
	
	/**关联type表查询*/
	public EtopService selectObjectWithTypeName(String serviceId);
	
	/**查询采购服务*/
	public List<EtopService> selectPurchaseService(EtopService etopService);
	public Page<EtopService> selectPurchaseService(Map<String, Object> condition);
	
	/**查询待诏服务*/
	public List<EtopService> selectRecruitmentService(EtopService service);
	public Page<EtopService> selectRecruitmentService(Map<String, Object> condition);
	
	/**查询设施服务*/
	public List<EtopService> selectFacilityService(EtopService service);
	
	public List<EtopService> selectFacilityServiceApply(EtopService service);
	
	public List<EtopService> selectFacilityServer(Long currentTime);
	/**计算采购总*/
	public List<EtopAllGoods> calculateGoods(EtopAllGoods goods);
 
	EtopService getServiceNum(@Param("clubId") String clubId);

	EtopService getServiceCompleteNum(@Param("clubId") String clubId);
	

	 Page<Etopservice> calculateQuotation(Map<String, Object> condition);
	 
	 Page<EtopAllGoods> calculateGoods(Map<String, Object> condition);
	 
	 Page<Companyservice> getServiceBycompanyId(Map<String, Object> condition);
}
